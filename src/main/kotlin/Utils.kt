package com.bbou

import com.materialkolor.contrast.Contrast
import com.materialkolor.hct.Hct
import com.materialkolor.scheme.SchemeContent
import com.materialkolor.palettes.TonalPalette

/**
 *  Convert to color int
 *  We remove the '#' if present and parse as a long to handle alpha, then toInt()
 */
private fun String.toColorInt(): Int = removePrefix("#").toLong(16).toInt() or (0xFF shl 24)

/**
 *  Convert to '#' + hex form
 */
private fun Int.toColorString(): String = "#${"%06X".format(this and 0xFFFFFF)}"

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
private fun Int.toToneString(): String = "%06X".format(this and 0xFFFFFF)

/**
 *  Convert to hue + chroma form
 */
private fun Hct.toHueChromaString(): String = "Hue:${this.hue.toHueString()} Chroma:${this.chroma.toChromaString()}"

/**
 *  Convert to tone form
 */
private fun Hct.toToneString(): String = "Tone:${this.hue.toToneString()}"

/**
 * Generate vibrant theme
 *
 * @param surfaceInput surface wanted
 * @param primaryInput primary hint
 * @param isDark dark flag
 */
fun generateVibrantSurfaceTheme(surfaceInput: Int, primaryInput: Int, isDark: Boolean) {

    // Convert to HCT
    val surfaceHct = Hct.fromInt(surfaceInput)
    val primaryHct = Hct.fromInt(primaryInput)

    // Initialize the Content Scheme
    // it keeps the surface chroma high
    val scheme = SchemeContent(surfaceHct, isDark, 0.0)

    // Access new vibrant tokens
    val surfaceColor = scheme.surface
    val surfaceContainer = scheme.surfaceContainer

    // Define "Hint of Primary" manually
    val primaryPalette = TonalPalette.fromHct(primaryHct)

    // Use Tone 40 for Light mode Primary, 80 for Dark mode
    val primaryColor: Int = if (!isDark) primaryPalette.tone(40) else primaryPalette.tone(80)

    println("<!-- Scheme Inputs -->")
    println("<!-- Surface -->")
    println("<color name='surfaceColor0'>${surfaceInput.toColorString()}</color>")
    println("<!-- Primary -->")
    println("<color name='primaryColor0'>${primaryInput.toColorString()}</color>")

    // Output
    println("<!-- Scheme Results -->")
    println("<!-- Vibrant surface -->")
    println("<color name='surfaceColor'>${surfaceColor.toColorString()}</color>")
    println("<!-- Surface Container -->")
    println("<color name='surfaceContainer'>${surfaceContainer.toColorString()}</color>")
    println("<!-- Custom Primary -->")
    println("<color name='primaryColor'>${primaryColor.toColorString()}</color>")
}

/**
 * Generate vibrant palette
 *
 * @param surfaceInput surface wanted
 * @param primaryInput primary hint
 */
fun generateFullVibrantPalette(surfaceInput: Int, primaryInput: Int) {

    // Convert to HCT
    val surfaceHct = Hct.fromInt(surfaceInput)
    val primaryHct = Hct.fromInt(primaryInput)

    // Palettes
    val surfacePalette = TonalPalette.fromHct(surfaceHct)
    val primaryPalette = TonalPalette.fromHct(primaryHct)

    println("--- VIBRANT SURFACE PALETTE (${surfaceHct.toHueChromaString()}) ---")
    println("Tone | Hex Code   | Use Case Suggestion")
    println("---------------------------------------")

    // Loop through key Material 3 Tones
    val tones = listOf(10, 20, 30, 40, 50, 80, 90, 95, 98, 99, 100)
    for (t in tones) {
        val label = when (t) {
            99 -> "Surface Bright (Very Pale)"
            95 -> "Surface Container (Vibrant/Pale)"
            90 -> "Surface Variant (Defined)"
            40 -> "Primary-style Accent"
            else -> ""
        }
        val hex = surfacePalette.tone(t).toHexString()
        println("${t.toString().padEnd(4)} | #$hex | $label")
    }

    println("\n--- PRIMARY HINT (Tone 40) ---")
    println("Primary: #${primaryPalette.tone(40).toHexString()}")
}

/**
 * Helper to extract colors from the scheme by name
 */
fun getSchemeColor(scheme: SchemeContent, role: String): Int = when (role) {
    "surface" -> scheme.surface
    "onSurface" -> scheme.onSurface
    "surfaceContainer" -> scheme.surfaceContainer
    "background" -> scheme.background
    "outline" -> scheme.outline
    else -> 0
}

fun printThemeXml(name: String, mode: String, keys: Collection<String>) {
    println("\n<style name=\"$name\" parent=\"Theme.Material3.${mode.replaceFirstChar { it.uppercase() }}.NoActionBar\">")
    keys.forEach { println("    <item name=\"color${it.replaceFirstChar { it.uppercase() }}\">@color/md_theme_${mode}_$it</item>") }
    println("</style>\n")
}

fun generateThemeXml(themeName: String, mode: String, roles: List<String>) {
    println("")
    val parent = if (mode == "light") "Theme.Material3.Light" else "Theme.Material3.Dark"
    println("<style name=\"$themeName\" parent=\"$parent.NoActionBar\">")
    roles.forEach { name ->
        val attr = "color${name.replaceFirstChar { it.uppercase() }}"
        println("    <item name=\"$attr\">@color/md_theme_${mode}_$name</item>")
    }
    println("</style>\n")
}

/**
 *
 * @param surfaceInput surface wanted
 * @param primaryInput primary hint
 * @param secondaryInput secondary hint
 * @param tertiaryInput tertiary hint
 */
fun generateCompleteM3Theme(
    surfaceInput: Int,
    primaryInput: Int,
    secondaryInput: Int,
    tertiaryInput: Int
) {
    val surfaceHct = Hct.fromInt(surfaceInput)

    // Generate Schemes (using surface for the overall "Content" vibe)
    val lightScheme = SchemeContent(surfaceHct, false, 0.0)
    val darkScheme = SchemeContent(surfaceHct, true, 0.0)

    // Setup Palettes for specific "Hints"
    val primaryPalette = TonalPalette.fromHct(Hct.fromInt(primaryInput))
    val secondaryPalette = TonalPalette.fromHct(Hct.fromInt(secondaryInput))
    val tertiaryPalette = TonalPalette.fromHct(Hct.fromInt(tertiaryInput))

    // Role definitions: Key -> (LightTone, DarkTone, Palette)
    val accentRoles = mapOf(
        "primary" to Triple(40, 80, primaryPalette),
        "onPrimary" to Triple(100, 20, primaryPalette),
        "primaryContainer" to Triple(90, 30, primaryPalette),
        "onPrimaryContainer" to Triple(10, 90, primaryPalette),

        "secondary" to Triple(40, 80, secondaryPalette),
        "onSecondary" to Triple(100, 20, secondaryPalette),
        "secondaryContainer" to Triple(90, 30, secondaryPalette),
        "onSecondaryContainer" to Triple(10, 90, secondaryPalette),

        "tertiary" to Triple(40, 80, tertiaryPalette),
        "onTertiary" to Triple(100, 20, tertiaryPalette),
        "tertiaryContainer" to Triple(90, 30, tertiaryPalette),
        "onTertiaryContainer" to Triple(10, 90, tertiaryPalette)
    )

    // 1. Output colors.xml
    println("\n<resources>")
    accentRoles.forEach { (name, data) ->
        println("    <color name=\"md_theme_light_$name\">#${"%06X".format(data.third.tone(data.first) and 0xFFFFFF)}</color>")
        println("    <color name=\"md_theme_dark_$name\">#${"%06X".format(data.third.tone(data.second) and 0xFFFFFF)}</color>")
    }
    // Add vibrant surface colors from the scheme
    listOf("surface", "onSurface", "surfaceContainer").forEach { name ->
        println(
            "    <color name=\"md_theme_light_$name\">#${
                "%06X".format(
                    getSchemeColor(
                        lightScheme,
                        name
                    ) and 0xFFFFFF
                )
            }</color>"
        )
        println(
            "    <color name=\"md_theme_dark_$name\">#${
                "%06X".format(
                    getSchemeColor(
                        darkScheme,
                        name
                    ) and 0xFFFFFF
                )
            }</color>"
        )
    }
    println("</resources>\n")

    // 2. Output Themes (Shortened for brevity)
    printThemeXml("Theme.MyApp.Light", "light", accentRoles.keys + listOf("surface", "onSurface", "surfaceContainer"))
    printThemeXml("Theme.MyApp.Dark", "dark", accentRoles.keys + listOf("surface", "onSurface", "surfaceContainer"))
}

fun generateFullDayNightXml(
    surfaceInput: Int,
    primaryInput: Int
) {

    val surfaceHct = Hct.fromInt(surfaceInput)

    // Generate Schemes (using surface for the overall "Content" vibe)
    val lightScheme = SchemeContent(surfaceHct, false, 0.0)
    val darkScheme = SchemeContent(surfaceHct, true, 0.0)

    // Setup Palette for your specific "Hint"
    val primaryPalette = TonalPalette.fromHct(Hct.fromInt(primaryInput))

    // 2. Define standard Material 3 color roles
    val roles = listOf(
        "surface" to null, // Handled by SchemeContent
        "onSurface" to null, // Handled by SchemeContent
        "surfaceContainer" to null, // Handled by SchemeContent
        "background" to null, // Handled by SchemeContent
        "outline" to null, // Handled by SchemeContent

        "primary" to (40 to 80), // (Light Tone, Dark Tone)
        "onPrimary" to (100 to 20),
        "primaryContainer" to (90 to 30),
        "onPrimaryContainer" to (10 to 90),
    )

    // colors
    println()
    println("<resources>")
    roles.forEach { (role, tones) ->
        val lightHex = if (tones != null) fromPalette(tones.first, primaryPalette) else fromScheme(role, lightScheme)
        val darkHex = if (tones != null) fromPalette(tones.second, primaryPalette) else fromScheme(role, darkScheme)
        println("    <color name=\"md_theme_light_$role\">#$lightHex</color>")
        println("    <color name=\"md_theme_dark_$role\">#$darkHex</color>")
    }
    println("</resources>\n")

    // thmes
    generateThemeXml("Theme.MyApp.Light", "light", roles.map { it.first })
    generateThemeXml("Theme.MyApp.Dark", "dark", roles.map { it.first })
}

private fun fromPalette(tone: Int, palette: TonalPalette): String = palette.tone(tone).toToneString()

private fun fromScheme(role: String, scheme: SchemeContent): String = getSchemeColor(scheme, role).toToneString()

fun auditThemeAccessibility(foregroundInt: Int, backgroundInt: Int, label: String) {
    // 1. Convert colors to HCT to get their Tones
    val fgTone = Hct.fromInt(foregroundInt).tone
    val bgTone = Hct.fromInt(backgroundInt).tone

    // 2. Calculate ratio based on Tones (Standard M3 calculation)
    val ratio = Contrast.ratioOfTones(fgTone, bgTone)

    val status = if (ratio >= 4.5) "✅ PASS" else "⚠️ LOW CONTRAST"
    println("$label: ${"%.2f".format(ratio)}:1 -> $status")
}

// Vibrant Surface: #FFFBF9F8
// Surface Container: #FFEFEDED
// Custom Primary Hint: #FF1B6D24
fun main() {
    // Example: A very pale mint surface and a deeper forest green primary hint
    val args = "#E0F2F1" to "#2E7D32"
    val args2 = "#E0F2F1" to "#2E7D32"

    val surfaceHex = args.first
    val primaryHex = args.first
    val surfaceInput = surfaceHex.toColorInt()
    val primaryInput = primaryHex.toColorInt()

    auditThemeAccessibility(surfaceInput, primaryInput, "Primary $primaryHex on Surface $surfaceHex")

    generateFullVibrantPalette(surfaceInput, primaryInput)
    println()
    generateVibrantSurfaceTheme(surfaceInput, primaryInput, isDark = false)
    println()
    generateFullDayNightXml(surfaceInput, primaryInput)
}
