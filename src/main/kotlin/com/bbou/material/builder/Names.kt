package com.bbou.material.builder

import com.materialkolor.hct.Cam16
import com.materialkolor.hct.Hct
import kotlin.math.*

fun findCSSName(colorInt: Int): Pair<String, Int?> = ColorNameFinder(CSS_COLOR_MAP).findName(colorInt)

fun findX11Name(colorInt: Int): Pair<String, Int?> = ColorNameFinder(X11_COLOR_MAP).findName(colorInt)

fun findGpickName(colorInt: Int): Pair<String, Int?> = ColorNameFinder(GPICK_COLOR_MAP).findName(colorInt)

fun findReseneName(colorInt: Int): Pair<String, Int?> = ColorNameFinder(RESENE_COLOR_MAP).findName(colorInt)

class ColorNameFinder(val colorMap: Map<Int, String>) {

    val colorHcts: Map<Int, Hct> by lazy {
        colorMap.map { (intVal, _) -> intVal to Hct.fromInt(intVal) }.toMap()
    }

    fun findName(targetInt: Int): Pair<String, Int?> {
        // 1. Check for an exact hex match first
        colorMap[targetInt]?.let { return it to targetInt }

        // Convert target to Cartesian coordinates for the "Color Plane" for stable distance math
        val targetCam = Cam16.fromInt(targetInt)
        val targetA = targetCam.chroma * cos(Math.toRadians(targetCam.hue))
        val targetB = targetCam.chroma * sin(Math.toRadians(targetCam.hue))

        val closest = colorMap.minByOrNull { (colorInt, _) ->
            val refCam = Cam16.fromInt(colorInt)

            // Convert candidate to Cartesian
            val refA = refCam.chroma * cos(Math.toRadians(refCam.hue))
            val refB = refCam.chroma * sin(Math.toRadians(refCam.hue))

            // Standard Perceptual Weighting:
            val dJ = (targetCam.j - refCam.j) * 1.0  // Lightness often weighted more heavily for "identity"
            val dA = (targetA - refA) * 2.0          // Red-Green (Weighted higher)
            val dB = (targetB - refB) * 2.0          // Yellow-Blue (Weighted higher)

            // Calculate Euclidean distance
            (dJ * dJ) + (dA * dA) + (dB * dB)
        }
        return (closest?.value ?: "Unknown Color") to closest?.key
    }

    fun findName3(targetInt: Int): Pair<String, Int?> {
        // 1. Check for an exact hex match first
        colorMap[targetInt]?.let { return it to targetInt }

        // Convert target to Cartesian coordinates for the "Color Plane" for stable distance math
        val targetCam = Cam16.fromInt(targetInt)
        val targetA = targetCam.chroma * cos(Math.toRadians(targetCam.hue))
        val targetB = targetCam.chroma * sin(Math.toRadians(targetCam.hue))

        val closest = colorMap.minByOrNull { (colorInt, _) ->
            val refCam = Cam16.fromInt(colorInt)

            // Convert candidate to Cartesian
            val refA = refCam.chroma * cos(Math.toRadians(refCam.hue))
            val refB = refCam.chroma * sin(Math.toRadians(refCam.hue))

            // Standard Perceptual Weighting:
            val dJ = (targetCam.j - refCam.j) * 1.0  // Lightness often weighted more heavily for "identity"
            val dA = (targetA - refA) * 1.0          // Red-Green
            val dB = (targetB - refB) * 1.0          // Yellow-Blue

            // Calculate Euclidean distance
            (dJ * dJ) + (dA * dA) + (dB * dB)
        }
        return (closest?.value ?: "Unknown Color") to closest?.key
    }

    fun findPerceptualName(targetInt: Int): Pair<String, Int?> {
        // 1. Direct hit check
        colorMap[targetInt]?.let { return it to targetInt }

        val targetCam = Cam16.fromInt(targetInt)

        val closest = colorMap.entries.minByOrNull { entry ->
            val colorCam = Cam16.fromInt(entry.key)

            // Circular Hue Difference (0-180 range)
            val rawHueDiff = abs(targetCam.hue - colorCam.hue)
            val hueDiff = if (rawHueDiff > 180.0) 360.0 - rawHueDiff else rawHueDiff

            // WEIGHTED DISTANCE
            // We multiply Hue by 2.0 because a wrong hue is a much bigger
            // "error" than a slightly wrong brightness or saturation.
            val dH = hueDiff * 2.0
            val dC = targetCam.chroma - colorCam.chroma
            val dJ = targetCam.j - colorCam.j

            // Using squared distance (skipping sqrt for speed)
            (dH * dH) + (dC * dC) + (dJ * dJ)
        }
        return (closest?.value ?: "Unknown Color") to closest?.key
    }

    fun findName2(targetInt: Int): Pair<String, Int?> {

        // 1. Direct hit check
        colorMap[targetInt]?.let { return it to targetInt }

        // 2. Perceptual closest match
        val targetHct = Hct.fromInt(targetInt)
        val closest = colorMap.entries.minByOrNull {
            val colorHct = colorHcts[it.key]!!
            //val colorHct2 = Hct.fromInt(it.key)
            //if (colorHct != colorHct2)
            //    throw IllegalStateException("$colorHct != colorHct2")

            // Circular Hue Difference
            val rawHueDiff = abs(targetHct.hue - colorHct.hue)
            val hueDiff = if (rawHueDiff > 180.0) 360.0 - rawHueDiff else rawHueDiff
            val chromaDiff = targetHct.chroma - colorHct.chroma
            val toneDiff = targetHct.tone - colorHct.tone
            val toneWeight = 2.0

            // Calculate perceptual distance (the lower the value, the closer the color)
            sqrt(hueDiff.pow(2) + chromaDiff.pow(2) + toneDiff.pow(2) * toneWeight)
        }
        // closest?.let { println("${targetInt.toColorString()} ${it.key.toColorString()} ${it.value}") }
        closest?.let { return it.value to it.key }
        return "Unknown Color" to null
    }

    companion object {

        fun name(vararg colorInputs: Int) {
            colorInputs.forEach {
                val (nameCss, valueCss) = findCSSName(it)
                val (nameX11, valueX11) = findX11Name(it)
                val (nameGPick, valueGPick) = findGpickName(it)
                val (nameResene, valueResene) = findReseneName(it)
                val descriptive = it.toDescriptiveName()
                val css = "${valueCss?.toColorString()},$nameCss"
                val x11 = "${valueX11?.toColorString()},$nameX11"
                val gpick = "${valueGPick?.toColorString()},$nameGPick"
                val resene = "${valueResene?.toColorString()},$nameResene"
                println("${it.toColorString()} ${descriptive.padEnd(25)} -> CSS = ${css.padEnd(25)}, X11 = ${x11.padEnd(25)}, GPICK = ${gpick.padEnd(25)}, RESENE = ${resene.padEnd(25)}")
             }
        }

        fun Int.toDescriptiveName(): String {
            val hct = Hct.fromInt(this)

            // Determine Prefix
            val prefix = when {
                hct.tone < 35.0 -> "Dark "
                hct.tone > 75.0 -> "Light "
                else -> ""
            }

            // Determine Base Name
            val baseName = when {
                hct.chroma < 5.0 -> return if (hct.tone < 12.0) "Black" else if (hct.tone > 88.0) "White" else "Gray"
                hct.hue in 0.0..15.0 || hct.hue in 345.0..360.0 -> "Red"
                hct.hue in 15.0..45.0 -> if (hct.tone < 40.0) "Brown" else "Orange"
                hct.hue in 45.0..75.0 -> "Yellow"
                hct.hue in 75.0..155.0 -> "Green"
                hct.hue in 155.0..190.0 -> "Cyan"
                hct.hue in 190.0..270.0 -> "Blue"
                hct.hue in 270.0..320.0 -> "Purple"
                hct.hue in 320.0..345.0 -> "Pink"
                else -> "Color"
            }
            return "$prefix$baseName".trim()
        }
    }
}