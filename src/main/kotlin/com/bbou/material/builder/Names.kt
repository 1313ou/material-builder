package com.bbou.material.builder

import com.materialkolor.hct.Cam16
import com.materialkolor.hct.Hct
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

fun findCSSName(colorInt: Int): Pair<String, Int?> = ColorNameFinder(CSS_COLOR_MAP).findName(colorInt)

fun findX11Name(colorInt: Int): Pair<String, Int?> = ColorNameFinder(X11_COLOR_MAP).findName(colorInt)

fun findGpickName(colorInt: Int): Pair<String, Int?> = ColorNameFinder(GPICK_COLOR_MAP).findName(colorInt)

fun findReseneName(colorInt: Int): Pair<String, Int?> = ColorNameFinder(RESENE_COLOR_MAP).findName(colorInt)

class ColorNameFinder(val colorMap: Map<Int, String>) {

    val colorHcts: Map<Int, Hct> by lazy {
        colorMap.map { (intVal, _) -> intVal to Hct.fromInt(intVal) }.toMap()
    }

    fun findName(targetInt: Int): Pair<String, Int?> {
        return findNameByUCSDistanceWithCartesianCoordinates(targetInt, lightnessWeight = 1.0, redGreenWeight = 2.0, yellowBlueWeight = 2.0)
    }

    // The Cam16 values used (J, C, h) are polar coordinates.
    // Using a standard Euclidean formula (a2+b2+c2) on polar coordinates is mathematically "incorrect"
    // for finding distance because the "width" of a hue degree changes depending on how much chroma
    // there is.
    // (Think of it like longitudes on a globe: 1∘ at the equator is a huge distance, but 1∘ at the North Pole is nearly zero).
    // Solution use Cartesian Coordinates (a,b)
    fun findNameByUCSDistanceWithCartesianCoordinates(
        targetInt: Int,
        redGreenWeight: Double = 2.0,
        yellowBlueWeight: Double = 2.0,
        lightnessWeight: Double = 1.0
    ): Pair<String, Int?> {
        // Check for an exact hex match first
        colorMap[targetInt]?.let { return it to targetInt }

        // Convert target to Cartesian coordinates for the "Color Plane" for stable distance math
        val targetCam = Cam16.fromInt(targetInt)
        val targetA = targetCam.chroma * cos(Math.toRadians(targetCam.hue))
        val targetB = targetCam.chroma * sin(Math.toRadians(targetCam.hue))

        val closest = colorMap.minByOrNull { (candidateInt, _) ->
            val candidateCam = Cam16.fromInt(candidateInt)

            // Convert candidate to Cartesian
            val candidateA = candidateCam.chroma * cos(Math.toRadians(candidateCam.hue))
            val candidateB = candidateCam.chroma * sin(Math.toRadians(candidateCam.hue))

            // Weighed Perceptual Weighting
            val dA = (targetA - candidateA) * redGreenWeight          // Red-Green (Weighted higher)
            val dB = (targetB - candidateB) * yellowBlueWeight        // Yellow-Blue (Weighted higher)
            val dJ = (targetCam.j - candidateCam.j) * lightnessWeight // Lightness often weighted more heavily for "identity"

            // Calculate Euclidean distance
            (dJ * dJ) + (dA * dA) + (dB * dB)
        }
        return (closest?.value ?: "Unknown Color") to closest?.key
    }

    // The Cam16 values used (J, C, h) are polar coordinates.
    fun findNameByUCSDistanceWithPolarCoordinates(
        targetInt: Int,
        hueWeight: Double = 2.0,
        chromaWeight: Double = 1.0,
        lightnessWeight: Double = 0.5
    ): Pair<String, Int?> {

        // Direct hit check
        colorMap[targetInt]?.let { return it to targetInt }

        val targetCam = Cam16.fromInt(targetInt)

        val closest = colorMap.entries.minByOrNull { (candidateInt, _) ->
            val candidateCam = Cam16.fromInt(candidateInt)

            // Circular Hue Difference with wrap-around fix (0-180 range)
            val rawHueDiff = abs(targetCam.hue - candidateCam.hue)
            val hueDiff = if (rawHueDiff > 180.0) 360.0 - rawHueDiff else rawHueDiff

            // Distance
            // We weight Hue heavily (2.0) to stop the "Yellow -> Green" jump.
            // We weight J (Lightness) and C (Chroma) lower.
            val dH = hueDiff * hueWeight
            val dC = (targetCam.chroma - candidateCam.chroma) * chromaWeight
            val dJ = (targetCam.j - candidateCam.j) * lightnessWeight

            // Using squared distance (skipping sqrt for speed)
            (dH * dH) + (dC * dC) + (dJ * dJ)
        }
        return (closest?.value ?: "Unknown Color") to closest?.key
    }

    fun findNameByHCTDistance(
        targetInt: Int,
        hueWeight: Double = 1.0,
        chromaWeight: Double = 1.0,
        toneWeight: Double = 2.0
    ): Pair<String, Int?> {

        // Direct hit check
        colorMap[targetInt]?.let { return it to targetInt }

        // Perceptual closest match
        val targetHct = Hct.fromInt(targetInt)
        val closest = colorMap.entries.minByOrNull {
            val colorHct = colorHcts[it.key]!!
            //val colorHct2 = Hct.fromInt(it.key)
            //if (colorHct != colorHct2)
            //    throw IllegalStateException("$colorHct != colorHct2")

            // Circular Hue Difference with wrap-around fix (0-180 range)
            val rawHueDiff = abs(targetHct.hue - colorHct.hue)
            val hueDiff = if (rawHueDiff > 180.0) 360.0 - rawHueDiff else rawHueDiff

            // Distance
            val dH = hueDiff * hueWeight
            val dC = (targetHct.chroma - colorHct.chroma) * chromaWeight
            val dT = (targetHct.tone - colorHct.tone) * toneWeight

            // Calculate perceptual distance (the lower the value, the closer the color)
            // Euclidean distance in 3D HCT space
            // Using squared distance (skipping sqrt for speed)
            (dH * dH) + (dC * dC) + (dT * dT)
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
                println(
                    "${it.toColorString()} ${descriptive.padEnd(25)} -> CSS = ${css.padEnd(25)}, X11 = ${x11.padEnd(25)}, GPICK = ${gpick.padEnd(25)}, RESENE = ${
                        resene.padEnd(
                            25
                        )
                    }"
                )
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