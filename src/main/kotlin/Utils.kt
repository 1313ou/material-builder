package com.bbou

import com.materialkolor.contrast.Contrast
import com.materialkolor.hct.Hct
import com.materialkolor.scheme.SchemeContent
import com.materialkolor.palettes.TonalPalette

/**
 *  Convert to color int
 *  We remove the '#' if present and parse as a long to handle alpha, then toInt()
 */
fun String.toColorInt(): Int = removePrefix("#").toLong(16).toInt() or (0xFF shl 24)

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

private fun fromPalette(tone: Int, palette: TonalPalette): String = palette.tone(tone).toToneString()

private fun fromScheme(role: String, scheme: SchemeContent): String = getSchemeColor(scheme, role).toToneString()

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

private fun printThemeXml(themeName: String, mode: String, roles: Collection<String>) {
    val parent = "Theme.Material3.${mode.replaceFirstChar { it.uppercase() }}.NoActionBar"
    println("\n<style name=\"$themeName\" parent=\"$parent\">")
    roles.forEach {
        val attr = "color${it.replaceFirstChar { it.uppercase() }}"
        println("    <item name=\"$attr\">@color/md_theme_${mode}_$it</item>")
    }
    println("</style>\n")
}

// Light tone, dark tone, palette index (1= primary, 2=secondary, 3=tertiary)
val accentRoles = mapOf(
    "primary" to Triple(40, 80, 1),
    "onPrimary" to Triple(100, 20, 1),
    "primaryContainer" to Triple(90, 30, 1),
    "onPrimaryContainer" to Triple(10, 90, 1),
    "inversePrimary" to null,
    "primaryFixed" to null,
    "onPrimaryFixed" to null,
    "primaryFixedDim" to null,
    "onPrimaryFixedVariant" to null,

    "primary_mediumContrast" to null,
    "onPrimary_mediumContrast" to null,
    "primaryContainer_mediumContrast" to null,
    "onPrimaryContainer_mediumContrast" to null,
    "inversePrimary_mediumContrast" to null,
    "primaryFixed_mediumContrast" to null,
    "onPrimaryFixed_mediumContrast" to null,
    "primaryFixedDim_mediumContrast" to null,
    "onPrimaryFixedVariant_mediumContrast" to null,

    "primary_highContrast" to null,
    "onPrimary_highContrast" to null,
    "primaryContainer_highContrast" to null,
    "onPrimaryContainer_highContrast" to null,
    "inversePrimary_highContrast" to null,
    "primaryFixed_highContrast" to null,
    "onPrimaryFixed_highContrast" to null,
    "primaryFixedDim_highContrast" to null,
    "onPrimaryFixedVariant_highContrast" to null,

    "secondary" to Triple(40, 80, 2),
    "onSecondary" to Triple(100, 20, 2),
    "secondaryContainer" to Triple(90, 30, 2),
    "onSecondaryContainer" to Triple(10, 90, 2),
    "secondaryFixed" to null,
    "onSecondaryFixed" to null,
    "secondaryFixedDim" to null,
    "onSecondaryFixedVariant" to null,

    "secondary_mediumContrast" to null,
    "onSecondary_mediumContrast" to null,
    "secondaryContainer_mediumContrast" to null,
    "onSecondaryContainer_mediumContrast" to null,
    "secondaryFixed_mediumContrast" to null,
    "onSecondaryFixed_mediumContrast" to null,
    "secondaryFixedDim_mediumContrast" to null,
    "onSecondaryFixedVariant_mediumContrast" to null,

    "secondary_highContrast" to null,
    "onSecondary_highContrast" to null,
    "secondaryContainer_highContrast" to null,
    "onSecondaryContainer_highContrast" to null,
    "secondaryFixed_highContrast" to null,
    "onSecondaryFixed_highContrast" to null,
    "secondaryFixedDim_highContrast" to null,
    "onSecondaryFixedVariant_highContrast" to null,

    "tertiary" to Triple(40, 80, 3),
    "onTertiary" to Triple(100, 20, 3),
    "tertiaryContainer" to Triple(90, 30, 3),
    "onTertiaryContainer" to Triple(10, 90, 3),
    "tertiaryFixed" to null,
    "onTertiaryFixed" to null,
    "tertiaryFixedDim" to null,
    "onTertiaryFixedVariant" to null,

    "tertiary_mediumContrast" to null,
    "onTertiary_mediumContrast" to null,
    "tertiaryContainer_mediumContrast" to null,
    "onTertiaryContainer_mediumContrast" to null,
    "tertiaryFixed_mediumContrast" to null,
    "onTertiaryFixed_mediumContrast" to null,
    "tertiaryFixedDim_mediumContrast" to null,
    "onTertiaryFixedVariant_mediumContrast" to null,

    "tertiary_highContrast" to null,
    "onTertiary_highContrast" to null,
    "tertiaryContainer_highContrast" to null,
    "onTertiaryContainer_highContrast" to null,
    "tertiaryFixed_highContrast" to null,
    "onTertiaryFixed_highContrast" to null,
    "tertiaryFixedDim_highContrast" to null,
    "onTertiaryFixedVariant_highContrast" to null,
)

// Handled by SchemeContent
val surfaceRoles = listOf(
    "surface",
    "onSurface",
    "surfaceVariant",
    "onSurfaceVariant",
    "inverseSurface",
    "inverseOnSurface",
    "surfaceDim",
    "surfaceBright",
    "surfaceContainerLowest",
    "surfaceContainerLow",
    "surfaceContainer",
    "surfaceContainerHigh",
    "surfaceContainerHighest",
    "surface_mediumContrast",
    "onSurface_mediumContrast",
    "surfaceVariant_mediumContrast",
    "onSurfaceVariant_mediumContrast",
    "inverseSurface_mediumContrast",
    "inverseOnSurface_mediumContrast",
    "surfaceDim_mediumContrast",
    "surfaceBright_mediumContrast",
    "surfaceContainerLowest_mediumContrast",
    "surfaceContainerLow_mediumContrast",
    "surfaceContainer_mediumContrast",
    "surfaceContainerHigh_mediumContrast",
    "surfaceContainerHighest_mediumContrast",
    "surface_highContrast",
    "onSurface_highContrast",
    "surfaceVariant_highContrast",
    "onSurfaceVariant_highContrast",
    "inverseSurface_highContrast",
    "inverseOnSurface_highContrast",
    "surfaceDim_highContrast",
    "surfaceBright_highContrast",
    "surfaceContainerLowest_highContrast",
    "surfaceContainerLow_highContrast",
    "surfaceContainer_highContrast",
    "surfaceContainerHigh_highContrast",
    "surfaceContainerHighest_highContrast",

    "background",
    "onBackground",
    "background_mediumContrast",
    "onBackground_mediumContrast",
    "background_highContrast",
    "onBackground_highContrast",

    "outline",
    "outlineVariant",
    "outline_mediumContrast",
    "outlineVariant_mediumContrast",
    "outline_highContrast",
    "outlineVariant_highContrast",

    "scrim",
    "scrim_mediumContrast",
    "scrim_highContrast",

    "error",
    "onError",
    "errorContainer",
    "onErrorContainer",
    "error_mediumContrast",
    "onError_mediumContrast",
    "errorContainer_mediumContrast",
    "onErrorContainer_mediumContrast",
    "error_highContrast",
    "onError_highContrast",
    "errorContainer_highContrast",
    "onErrorContainer_highContrast",
)

/**
 * Generate full day/night M3 Theme
 * @param surfaceInput surface wanted
 * @param primaryInput primary hint
 */
fun generateDayNightM3XmlTheme(
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
    val accentRoles = listOf(
        "primary" to (40 to 80), // (Light Tone, Dark Tone)
        "onPrimary" to (100 to 20),
        "primaryContainer" to (90 to 30),
        "onPrimaryContainer" to (10 to 90),
    )

    val roles = surfaceRoles + accentRoles.map { it.first }

    // colors
    println()
    println("<resources>")
    surfaceRoles.forEach { role ->
        val lightHex = fromScheme(role, lightScheme)
        println("    <color name=\"md_theme_light_$role\">#$lightHex</color>")
    }
    accentRoles.forEach { (role, tones) ->
        val lightHex = fromPalette(tones.second, primaryPalette)
        println("    <color name=\"md_theme_light_$role\">#$lightHex</color>")
    }
    println()
    surfaceRoles.forEach { role ->
        val darkHex = fromScheme(role, darkScheme)
        println("    <color name=\"md_theme_dark_$role\">#$darkHex</color>")
    }
    accentRoles.forEach { (role, tones) ->
        val darkHex = fromPalette(tones.second, primaryPalette)
        println("    <color name=\"md_theme_dark_$role\">#$darkHex</color>")
    }
    println("</resources>\n")

    // themes
    printThemeXml("Theme.MyApp.Light", "light", roles)
    printThemeXml("Theme.MyApp.Dark", "dark", roles)
}

/**
 * Generate complete M3 Theme
 * @param surfaceInput surface wanted
 * @param primaryInput primary hint
 * @param secondaryInput secondary hint
 * @param tertiaryInput tertiary hint
 */
fun generateCompleteDayNightM3XmlTheme(
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

    val roles = surfaceRoles + accentRoles.keys

    // 1. Output colors.xml
    println("\n<resources>")
    // Add vibrant surface colors from the scheme
    val surfaceRoles = listOf("surface", "onSurface", "surfaceContainer", "background", "outline")
    surfaceRoles.forEach { role ->
        println("    <color name=\"md_theme_light_$role\">#${fromScheme(role, lightScheme)}</color>")
    }
    accentRoles.forEach { (role, data) ->
        println("    <color name=\"md_theme_light_$role\">#${fromPalette(data.first, data.third)}</color>")
    }
    println()
    surfaceRoles.forEach { role ->
        println("    <color name=\"md_theme_dark_$role\">#${fromScheme(role, darkScheme)}</color>")
    }
    accentRoles.forEach { (role, data) ->
        println("    <color name=\"md_theme_dark_$role\">#${fromPalette(data.second, data.third)}</color>")
    }
    println("</resources>\n")

    // 2. Output Themes (Shortened for brevity)
    printThemeXml("Theme.MyApp.Light", "light", roles)
    printThemeXml("Theme.MyApp.Dark", "dark", roles)
}

fun auditThemeAccessibility(foregroundInt: Int, backgroundInt: Int, label: String) {
    // 1. Convert colors to HCT to get their Tones
    val fgTone = Hct.fromInt(foregroundInt).tone
    val bgTone = Hct.fromInt(backgroundInt).tone

    // 2. Calculate ratio based on Tones (Standard M3 calculation)
    val ratio = Contrast.ratioOfTones(fgTone, bgTone)

    val status = if (ratio >= 4.5) "✅ PASS" else "⚠️ LOW CONTRAST"
    println("$label: ${"%.2f".format(ratio)}:1 -> $status")
}
