package com.bbou.material.builder

import com.materialkolor.hct.Hct
import com.materialkolor.palettes.TonalPalette
import com.materialkolor.scheme.SchemeContent

val attrs = """
<resources>
  <attr name="colorCustomColor" format="color" />
  <attr name="colorOnCustomColor" format="color" />
  <attr name="colorCustomColorContainer" format="color" />
  <attr name="colorOnCustomColorContainer" format="color" />
</resources>
"""

// C O L O R   M A P   B Y   R O L E

private fun fromPalette(tone: Int, palette: TonalPalette): String = palette.tone(tone = tone).toToneString()

private fun fromScheme(role: String, scheme: SchemeContent): String = getSchemeColor(scheme, role).toToneString()

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

/**
 * Generate M3 Theme Colors
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
    val lightMap = generateM3XmlColors(surfaceInput, accentHints, surfaceRolesRange, accentRolesRange, isDark = false)
    val darkMap = generateM3XmlColors(surfaceInput, accentHints, surfaceRolesRange, accentRolesRange, isDark = true)
    return lightMap to darkMap
}

/**
 * Generate M3 Theme Colors
 * @param surfaceInput surface wanted
 * @param accentHints  hints (primary, secondary, tertiary)
 * @param surfaceRolesRange surface role range
 * @param accentRolesRange  accent role range
 * @param isDark theme is dark
 */
fun generateM3XmlColors(
    surfaceInput: Int,
    accentHints: List<Int>,
    surfaceRolesRange: List<String> = surfaceRoles,
    accentRolesRange: List<String> = accentRoles,
    isDark: Boolean = false
): Map<String, String> {
    val surfaceHct = Hct.fromInt(surfaceInput)

    // Generate Schemes (using surface for the overall "Content" vibe)
    val scheme = SchemeContent(surfaceHct, isDark, 0.0)

    // Setup accent hints
    val accentInputs = if (accentHints.size == 3) accentHints else {
        val primaryInput = accentHints[0]
        deriveOfficialM3Colors(primaryInput).toList()
    }

    // Setup palettes for accents
    val palettes = Array(accentInputs.size) { TonalPalette.fromHct(Hct.fromInt(accentInputs[it])) }

    // Color map
    val colorMap = LinkedHashMap<String, String>()
    colorMap["custom"] = surfaceInput.toColorString()
    // Add vibrant surface colors from the scheme
    surfaceRolesRange.forEach { role ->
        colorMap[role] = "#${fromScheme(role, scheme)}"
    }
    accentRolesRange.forEach { role ->
        val data = accentRoleDefs[role]!!
        colorMap[role] = "#${fromPalette(data.first, palettes[data.third - 1])}"
    }

    return colorMap
}

// P R I N T

fun printTextColors(vararg maps: Map<String, String>) {
    maps.forEach {
        it.forEach { (key, value) ->
            val colorInt = value.toColorInt()
            val name = findCSSName(colorInt)
            val name2 = findGpickName(colorInt)
            println("$key $value $name $name2")
        }
    }
}

fun printXmlColors(vararg maps: Map<String, String>, mode: String, colorPrefix: String = "md_theme_") {
    maps.forEach {
        println("\n<!-- $mode -->")
        println("<resources>")
        it.forEach { (key, value) ->
            println("\t<color name=\"${colorPrefix}$key\">$value</color>")
        }
        println("</resources>")
    }
}

private fun printM3ThemeXml(themeName: String, mode: String, rolesRange: Collection<String>, colorPrefix: String = "md_theme_") {
    val parent = "Theme.Material3.${mode.replaceFirstChar { it.uppercase() }}.NoActionBar"
    println("\n<!-- $mode -->")
    println("<style name=\"$themeName\" parent=\"$parent\">")
    println("\t<item name=\"customColor\">@color/custom</item>")
    println("\t<item name=\"onCustomColor\">@color/onCustom</item>")
    rolesRange.forEach {
        val attr = "color${it.replaceFirstChar { it.uppercase() }}"
        println("\t<item name=\"$attr\">@color/${colorPrefix}$it</item>")
    }
    println("</style>\n")
}

/**
 * Generate complete M3 Theme
 * @param rolesRange  role range
 */
fun printDayNightM3ThemeXml(themeName: String = "AppTheme", rolesRange: List<String> = roles) {
    printDayM3ThemeXml(themeName, rolesRange)
    printNightM3ThemeXml(themeName, rolesRange)
}

fun printDayM3ThemeXml(themeName: String = "AppTheme", rolesRange: List<String> = roles) {
    printM3ThemeXml(themeName, "light", rolesRange)
}

fun printNightM3ThemeXml(themeName: String = "AppTheme", rolesRange: List<String> = roles) {
    printM3ThemeXml(themeName, "dark", rolesRange)
}

fun printDayNightM3OverlaysXml(overlayName: String = "ThemeOverlays.AppTheme", rolesRange: List<String> = roles) {
    println("<resources>")
    printDayM3OverlaysXml(overlayName, rolesRange)
    printNightM3OverlaysXml(overlayName, rolesRange)
    println("</resources>")
}

fun printDayM3OverlaysXml(overlayName: String = "ThemeOverlays.AppTheme", rolesRange: List<String> = roles) {
    println("<resources>")
    val rolesMediumContrast = rolesRange.map { it + "_mediumContrast" }.toList()
    printM3ThemeXml("$overlayName.MediumContrast", "light", rolesMediumContrast)
    val rolesHighContrast = rolesRange.map { it + "_highContrast" }.toList()
    printM3ThemeXml("$overlayName.HighContrast", "light", rolesHighContrast)
    println("</resources>")
}

fun printNightM3OverlaysXml(overlayName: String = "ThemeOverlays.AppTheme", rolesRange: List<String> = roles) {
    println("<resources>")
    val rolesMediumContrast = rolesRange.map { it + "_mediumContrast" }.toList()
    printM3ThemeXml("$overlayName.MediumContrast", "dark", rolesMediumContrast)
    val rolesHighContrast = rolesRange.map { it + "_highContrast" }.toList()
    printM3ThemeXml("$overlayName.HighContrast", "dark", rolesHighContrast)
    println("</resources>")
}

fun printAttrsXml() {
    println(attrs)
}
