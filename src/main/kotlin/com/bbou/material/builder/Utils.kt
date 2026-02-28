package com.bbou.material.builder

import com.materialkolor.contrast.Contrast
import com.materialkolor.hct.Hct
import com.materialkolor.palettes.CorePalette
import com.materialkolor.palettes.TonalPalette
import com.materialkolor.scheme.SchemeContent

/**
 *  Convert to color int
 *  We remove the '#' if present and parse as a long to handle alpha, then toInt()
 */
fun String.toColorInt(): Int = removePrefix("#").toLong(16).toInt() or (0xFF shl 24)

/**
 *  Convert to '#' + hex form
 */
fun Int.toColorString(): String = "#${"%06X".format(this and 0xFFFFFF)}"

/**
 *  Convert to hue form
 */
private fun Double.toHueString(): String = "%.2f".format(this)

/**
 *  Convert to chroma form
 */
private fun Double.toChromaString(): String = "%.2f".format(this)

/**
 *  Convert to tone form
 */
private fun Double.toToneString(): String = "%.2f".format(this)

/**
 *  Convert to tone form
 */
fun Int.toToneString(): String = "%06X".format(this and 0xFFFFFF)

/**
 *  Convert to hue + chroma form
 */
private fun Hct.toHueChromaString(): String = "H:${this.hue.toHueString()} C:${this.chroma.toChromaString()}"

/**
 *  Convert to tone form
 */
private fun Hct.toToneString(): String = "T:${this.tone.toToneString()}"

/**
 *  Convert to hue,chroma,tone form
 */
private fun Hct.toHueChromaToneString(): String = "${this.toHueChromaString()} ${this.toToneString()}"

/**
 * Print colors in HCT space
 *
 * @param colorInputs colors
 */
fun printHct(vararg colorInputs: Int) {
    println("| Hex    | Hue    | Chroma | Tone   |")
    println("|-----------------------------------| ")
    colorInputs.zip(hct(*colorInputs)).forEach { (c, hct) ->
        val hex = c.toColorString()
        val (hue, chroma, tone) = hct
        println("|${hex.padEnd(8)}|${hue.padEnd(8)}|${chroma.padEnd(8)}|${tone.padEnd(8)}|")
    }
}

/**
 * Express colors in HCT space
 *
 * @param colorInputs colors
 */
fun hct(vararg colorInputs: Int): List<Triple<String, String, String>> {
    return colorInputs.map {
        val hct = Hct.fromInt(it)
        val hue = hct.hue.toHueString()
        val chroma = hct.chroma.toChromaString()
        val tone = hct.tone.toToneString()
        Triple(hue, chroma, tone)
    }.toList()
}

/**
 * Print tone (40) of
 *
 * @param colorInputs colors
 */
fun printColorToneOf(vararg colorInputs: Int, tone: Int = 40) {
    println("| Hex     | Tone${tone.toString().padEnd(2)}  |")
    println("|-------------------| ")
    colorInputs.zip(toneOf(*colorInputs, tone = tone)).forEach { (c, c2) ->
        val hex = c.toColorString()
        val hex2 = c2.toColorString()
        println("| ${hex.padEnd(8)}| ${hex2.padEnd(8)}|")
    }
}

/**
 * Generate tone (40) of
 *
 * @param colorInputs colors
 */
fun toneOf(vararg colorInputs: Int, tone: Int = 40): IntArray {
    return colorInputs.map {
        val hct = Hct.fromInt(it)
        val palette = TonalPalette.fromHct(hct)
        palette.tone(tone)
    }.toIntArray()
}

/**
 * Generate vibrant palette
 *
 * @param seedInput seed input
 */
fun palette(seedInput: Int) {

    val seedHct = Hct.fromInt(seedInput)
    val palette = TonalPalette.fromHct(seedHct)

    println("------------------------------------------------------------------------")
    println("  ${seedInput.toColorString()} ${seedHct.toHueChromaToneString()}")
    println("------------------------------------------------------------------------")
    println("|Tone | Hex     | HCT                       | Use Case Suggestion      |")
    println("|----------------------------------------------------------------------|")

    // Loop through key Material 3 Tones
    for (t in listOf(10, 20, 30, 40, 50, 60, 70, 80, 90, 95, 98, 99, 100)) {
        val label = when (t) {
            99 -> "Bright (Very Pale)"
            95 -> "Container (Vibrant/Pale)"
            90 -> "Variant (Defined)"
            40 -> "Accent"
            else -> ""
        }
        val colorInt = palette.tone(t) and 0xFFFFFF
        val hex = colorInt.toColorString()
        println("|${t.toString().padEnd(4)} | $hex | ${Hct.fromInt(colorInt).toHueChromaToneString().padEnd(25)} | ${label.padEnd(25)}|")
    }
    println("------------------------------------------------------------------------")
}

/**
 * Derive the secondary and tertiary palettes from primary color
 * Secondary: Same Hue, but Chroma is divided (muted)
 * Tertiary: Hue is rotated by 60 degrees, Chroma is even lower
 * @return secondaryPalette, tertiaryPalette
 */
fun deriveOfficialM3CPalettes(primaryInput: Int): Triple<TonalPalette, TonalPalette, TonalPalette> {

    // This is the "Engine Room" of the official derivation
    val corePalette = CorePalette.of(primaryInput)
    return Triple(corePalette.a1, corePalette.a2, corePalette.a3)
}

/**
 * Derive the secondary and tertiary Light mode 'main' color (Tone 40)  from primary color
 */
fun deriveOfficialM3Colors(primaryInput: Int, tone: Int = 40): Triple<Int, Int, Int> {
    val (a1, a2, a3) = deriveOfficialM3CPalettes(primaryInput)
    val primary = a1.tone(tone)
    val secondary = a2.tone(tone)
    val tertiary = a3.tone(tone)
    return Triple(primary, secondary, tertiary)
}

/**
 * Generate vibrant surface palette and primary
 *
 * @param surfaceInput surface hint
 * @param primaryInput primary hint
 */
fun generateVibrantSurfacePaletteAndPrimary(surfaceInput: Int, primaryInput: Int) {

    // Surface
    println("--- VIBRANT SURFACE PALETTE (Surface ${surfaceInput.toColorString()}) ---")
    palette(surfaceInput)

    // Primary
    val primary40 = toneOf(primaryInput)[0]
    println("\n--- PRIMARY (Tone 40) (Hint ${primaryInput.toColorString()} ${Hct.fromInt(primaryInput).toHueChromaToneString()}) ---\") ---")
    println("Primary (tone 40): ${primary40.toColorString()} ${Hct.fromInt(primary40).toHueChromaToneString()}")
}

/**
 * Generate vibrant theme
 *
 * @param surfaceInput surface wanted
 * @param primaryInput primary hint
 * @param isDark dark flag
 */
fun generateVibrantSurfaceTheme(surfaceInput: Int, primaryInput: Int, isDark: Boolean) {

    // Initialize the Content Scheme
    // it keeps the surface chroma high
    val surfaceHct = Hct.fromInt(surfaceInput)
    val scheme = SchemeContent(surfaceHct, isDark, 0.0)

    // Access new vibrant tokens
    val surfaceColor = scheme.surface
    val surfaceContainer = scheme.surfaceContainer

    // Define "Hint of Primary" manually
    val primaryHct = Hct.fromInt(primaryInput)
    val primaryPalette = TonalPalette.fromHct(primaryHct)

    // Use Tone 40 for Light mode Primary, 80 for Dark mode
    val primaryColor: Int = if (!isDark) primaryPalette.tone(40) else primaryPalette.tone(80)

    val mode = (if (isDark) "dark" else "light").replaceFirstChar { it.uppercase() }

    println("<!-- Scheme Inputs -->")
    println("<!-- Surface -->")
    println("<color name='surfaceColor0'>${surfaceInput.toColorString()}</color>")
    println("<!-- Primary -->")
    println("<color name='primaryColor0'>${primaryInput.toColorString()}</color>")

    // Output
    println("<!-- Scheme Results $mode -->")
    println("<!-- Vibrant surface -->")
    println("<color name='surfaceColor'>${surfaceColor.toColorString()}</color>")
    println("<!-- Surface Container -->")
    println("<color name='surfaceContainer'>${surfaceContainer.toColorString()}</color>")
    println("<!-- Custom Primary -->")
    println("<color name='primaryColor'>${primaryColor.toColorString()}</color>")
}

/**
 * Audit of contrast
 */
fun auditThemeAccessibility(foregroundInt: Int, backgroundInt: Int, label: String) {
    // 1. Convert colors to HCT to get their Tones
    val fgTone = Hct.fromInt(foregroundInt).tone
    val bgTone = Hct.fromInt(backgroundInt).tone

    // 2. Calculate ratio based on Tones (Standard M3 calculation)
    val ratio = Contrast.ratioOfTones(fgTone, bgTone)

    val status = if (ratio >= 4.5) "✅ PASS" else "⚠️ LOW CONTRAST"
    System.err.println("$label: ${"%.2f".format(ratio)}:1 -> $status")
}
