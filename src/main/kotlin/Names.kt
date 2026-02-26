package com.bbou

import com.materialkolor.hct.Hct
import kotlin.math.pow
import kotlin.math.sqrt

object ColorNameFinder {

    fun findCSSName(colorInt: Int): String = findName(colorInt, CSS_COLOR_MAP)

    fun findGpickName(colorInt: Int): String = findName(colorInt, GPICK_COLOR_MAP)

    fun findName(pickedColorInt: Int, colorMap: Map<Int, String>): String {
        // 1. Direct hit check
        colorMap[pickedColorInt]?.let { return it }

        // 2. Perceptual closest match
        val pickedHct = Hct.fromInt(pickedColorInt)

        return colorMap.entries.minByOrNull { entry ->
            val targetHct = Hct.fromInt(entry.key)

            // Calculate perceptual distance (the lower the value, the closer the color)
            sqrt(
                (pickedHct.hue - targetHct.hue).pow(2) +
                        (pickedHct.chroma - targetHct.chroma).pow(2) +
                        (pickedHct.tone - targetHct.tone).pow(2)
            )
        }?.value ?: "Unknown Color"
    }
}