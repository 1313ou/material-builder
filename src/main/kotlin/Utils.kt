package com.bbou

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
        println("\t<item name=\"$attr\">@color/md_theme_${mode}_$it</item>")
    }
    println("</style>\n")
}

// Light tone, dark tone, palette index (1= primary, 2=secondary, 3=tertiary)
// Role definitions: Key -> (LightTone, DarkTone, Palette)
val accentRoleDefs = mapOf(
    "primary" to Triple(40, 80, 1),
    "onPrimary" to Triple(100, 20, 1),
    "primaryContainer" to Triple(90, 30, 1),
    "onPrimaryContainer" to Triple(10, 90, 1),
    "inversePrimary" to Triple(80, 40, 1),
    "primaryFixed" to Triple(90, 90, 1),
    "onPrimaryFixed" to Triple(10, 10, 1),
    "primaryFixedDim" to Triple(80, 80, 1),
    "onPrimaryFixedVariant" to Triple(30, 30, 1),

    "inversePrimary_mediumContrast" to Triple(80, 40, 1),
    "primary_mediumContrast" to Triple(26, 90, 1),
    "onPrimary_mediumContrast" to Triple(100, 0, 1),
    "primaryContainer_mediumContrast" to Triple(54, 54, 1),
    "onPrimaryContainer_mediumContrast" to Triple(0, 100, 1),
    "primaryFixed_mediumContrast" to Triple(74, 74, 1),
    "onPrimaryFixed_mediumContrast" to Triple(0, 0, 1),
    "primaryFixedDim_mediumContrast" to Triple(58, 58, 1),
    "onPrimaryFixedVariant_mediumContrast" to Triple(10, 10, 1),

    "inversePrimary_highContrast" to Triple(90, 20, 1),
    "primary_highContrast" to Triple(0, 100, 1),
    "onPrimary_highContrast" to Triple(100, 0, 1),
    "primaryContainer_highContrast" to Triple(36, 74, 1),
    "onPrimaryContainer_highContrast" to Triple(100, 0, 1),
    "primaryFixed_highContrast" to Triple(54, 54, 1),
    "onPrimaryFixed_highContrast" to Triple(100, 100, 1),
    "primaryFixedDim_highContrast" to Triple(36, 36, 1),
    "onPrimaryFixedVariant_highContrast" to Triple(0, 0, 1),

    "secondary" to Triple(40, 80, 2),
    "onSecondary" to Triple(100, 20, 2),
    "secondaryContainer" to Triple(90, 30, 2),
    "onSecondaryContainer" to Triple(10, 90, 2),
    "secondaryFixed" to Triple(90, 90, 2),
    "onSecondaryFixed" to Triple(10, 10, 2),
    "secondaryFixedDim" to Triple(80, 80, 2),
    "onSecondaryFixedVariant" to Triple(30, 30, 2),

    "secondary_mediumContrast" to Triple(26, 90, 2),
    "onSecondary_mediumContrast" to Triple(100, 0, 2),
    "secondaryContainer_mediumContrast" to Triple(54, 54, 2),
    "onSecondaryContainer_mediumContrast" to Triple(0, 100, 2),
    "secondaryFixed_mediumContrast" to Triple(74, 74, 2),
    "onSecondaryFixed_mediumContrast" to Triple(0, 0, 2),
    "secondaryFixedDim_mediumContrast" to Triple(58, 58, 2),
    "onSecondaryFixedVariant_mediumContrast" to Triple(10, 10, 2),

    "secondary_highContrast" to Triple(0, 100, 2),
    "onSecondary_highContrast" to Triple(100, 0, 2),
    "secondaryContainer_highContrast" to Triple(36, 74, 2),
    "onSecondaryContainer_highContrast" to Triple(100, 0, 2),
    "secondaryFixed_highContrast" to Triple(54, 54, 2),
    "onSecondaryFixed_highContrast" to Triple(100, 100, 2),
    "secondaryFixedDim_highContrast" to Triple(36, 36, 2),
    "onSecondaryFixedVariant_highContrast" to Triple(0, 0, 2),

    "tertiary" to Triple(40, 80, 3),
    "onTertiary" to Triple(100, 20, 3),
    "tertiaryContainer" to Triple(90, 30, 3),
    "onTertiaryContainer" to Triple(10, 90, 3),
    "tertiaryFixed" to Triple(90, 90, 3),
    "onTertiaryFixed" to Triple(10, 10, 3),
    "tertiaryFixedDim" to Triple(80, 80, 3),
    "onTertiaryFixedVariant" to Triple(30, 30, 3),

    "tertiary_mediumContrast" to Triple(2, 90, 3),
    "onTertiary_mediumContrast" to Triple(100, 0, 3),
    "tertiaryContainer_mediumContrast" to Triple(54, 54, 3),
    "onTertiaryContainer_mediumContrast" to Triple(0, 100, 3),
    "tertiaryFixed_mediumContrast" to Triple(74, 74, 3),
    "onTertiaryFixed_mediumContrast" to Triple(0, 0, 3),
    "tertiaryFixedDim_mediumContrast" to Triple(58, 58, 3),
    "onTertiaryFixedVariant_mediumContrast" to Triple(10, 10, 3),

    "tertiary_highContrast" to Triple(0, 100, 3),
    "onTertiary_highContrast" to Triple(100, 0, 3),
    "tertiaryContainer_highContrast" to Triple(36, 74, 3),
    "onTertiaryContainer_highContrast" to Triple(100, 0, 3),
    "tertiaryFixed_highContrast" to Triple(54, 54, 3),
    "onTertiaryFixed_highContrast" to Triple(100, 100, 3),
    "tertiaryFixedDim_highContrast" to Triple(36, 36, 3),
    "onTertiaryFixedVariant_highContrast" to Triple(0, 0, 3),
)

val accentRoles = accentRoleDefs.keys.toList()

val primaryAccentRoles = listOf(
    "primary",
    "onPrimary",
    "primaryContainer",
    "onPrimaryContainer",
)

val secondaryAccentRoles = listOf(
    "secondary",
    "onSecondary",
    "secondaryContainer",
    "onSecondaryContainer",
)

val tertiaryAccentRoles = listOf(
    "tertiary",
    "onTertiary",
    "tertiaryContainer",
    "onTertiaryContainer",
)

var accentRolesMin = primaryAccentRoles + secondaryAccentRoles + tertiaryAccentRoles

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

val surfaceRolesMin = listOf("surface", "onSurface", "surfaceContainer", "background", "outline")

val roles = surfaceRoles + accentRoles

val rolesMin = surfaceRolesMin + primaryAccentRoles

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

const val namePrefix = "md_theme_"

/**
 * Generate complete M3 Theme
 * @param surfaceInput surface wanted
 * @param accentHints  hints (primary, secondary, tertiary)
 * @param surfaceRolesRange surface role range
 * @param accentRolesRange  accent role range
 */
fun generateDayNightM3XmlColors(
    surfaceInput: Int,
    accentHints: List<Int>,
    surfaceRolesRange: List<String> = surfaceRoles,
    accentRolesRange: List<String> = accentRoles,
): Pair<Map<String, String>, Map<String, String>> {
    val surfaceHct = Hct.fromInt(surfaceInput)

    // Generate Schemes (using surface for the overall "Content" vibe)
    val lightScheme = SchemeContent(surfaceHct, false, 0.0)
    val darkScheme = SchemeContent(surfaceHct, true, 0.0)

    // Setup accent hints
    val accentInputs = if (accentHints.size == 3) accentHints else {
        val primaryInput = accentHints[0]
        deriveOfficialM3Colors(primaryInput).toList()
    }

    // Setup Palettes for hints
    val palettes = Array(accentInputs.size) { TonalPalette.fromHct(Hct.fromInt(accentInputs[it])) }

    // Light color map
    val lightMap = LinkedHashMap<String, String>()
    // Add vibrant surface colors from the scheme
    surfaceRolesRange.forEach { role ->
        lightMap[role] = "#${fromScheme(role, lightScheme)}"
    }
    accentRolesRange.forEach { role ->
        val data = accentRoleDefs[role]!!
        lightMap[role] = "#${fromPalette(data.first, palettes[data.third - 1])}"
    }

    val darkMap = LinkedHashMap<String, String>()
    surfaceRolesRange.forEach { role ->
        darkMap[role] = "#${fromScheme(role, darkScheme)}"
    }
    accentRolesRange.forEach { role ->
        val data = accentRoleDefs[role]!!
        darkMap[role] = "#${fromPalette(data.second, palettes[data.third - 1])}"
    }
    return lightMap to darkMap
}

/**
 * Generate complete M3 Theme
 * @param surfaceInput surface wanted
 * @param accentHints  hints (primary, secondary, tertiary)
 * @param surfaceRolesRange surface role range
 * @param accentRolesRange  accent role range
 */
fun printXmlColors(
    surfaceInput: Int,
    accentHints: List<Int>,
    surfaceRolesRange: List<String> = surfaceRoles,
    accentRolesRange: List<String> = accentRoles,
) {
    val (lightMap, darkMap) = generateDayNightM3XmlColors(surfaceInput, accentHints, surfaceRolesRange, accentRolesRange)
    printXmlColors(lightMap, darkMap)
}

fun printXmlColors(vararg maps: Map<String, String>) {
    maps.forEach {
        println("\n<resources>")
        it.forEach { (key, value) ->
            println("\t<color name=\"$key\">#$value</color>")
        }
        println("</resources>\n")
    }
}

/**
 * Generate complete M3 Theme
 * @param rolesRange  role range
 */
fun generateDayNightM3XmlTheme(
    rolesRange: List<String> = roles,
) {
    printThemeXml("Theme.MyApp.Light", "light", rolesRange)
    printThemeXml("Theme.MyApp.Dark", "dark", rolesRange)
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
