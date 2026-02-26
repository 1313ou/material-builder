package com.bbou

import com.materialkolor.hct.Hct
import kotlin.math.pow
import kotlin.math.sqrt

object ColorNameFinder {

    fun findCSSName(colorInt: Int): Pair<String, Int?> = findName(colorInt, CSS_COLOR_MAP)

    fun findX11Name(colorInt: Int): Pair<String, Int?> = findName(colorInt, X11_COLOR_MAP)

    fun findGpickName(colorInt: Int): Pair<String, Int?> {

        return findName(colorInt, GPICK_COLOR_MAP)
    }

    fun findName(targetInt: Int, colorMap: Map<Int, String>): Pair<String, Int?> {
        val colorHcts: Map<Int, Hct> by lazy {
            colorMap.map { (intVal, _) -> intVal to Hct.fromInt(intVal) }.toMap()
        }

        // 1. Direct hit check
        colorMap[targetInt]?.let { return it to targetInt }

        // 2. Perceptual closest match
        val targetHct = Hct.fromInt(targetInt)
        val closest = colorMap.entries.minByOrNull {
            val colorHct = colorHcts[it.key]!!
            //val colorHct2 = Hct.fromInt(it.key)
            //if (colorHct != colorHct2)
            //    throw IllegalStateException("$colorHct != colorHct2")
            // Calculate perceptual distance (the lower the value, the closer the color)
            sqrt(
                (targetHct.hue - colorHct.hue).pow(2) +
                        (targetHct.chroma - colorHct.chroma).pow(2) +
                        (targetHct.tone - colorHct.tone).pow(2)
            )
        }
        // closest?.let { println("${targetInt.toColorString()} ${it.key.toColorString()} ${it.value}") }
        closest?.let { return it.value to it.key }
        return "Unknown Color" to null
    }

    fun name(vararg colorInputs: Int) {
        colorInputs.forEach {
            val (name, value) = findCSSName(it)
            println("${it.toColorString()} ${it.toDescriptiveName()}-> ${value?.toColorString()} $name")
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