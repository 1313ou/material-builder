package com.bbou

import com.materialkolor.hct.Hct
import com.materialkolor.palettes.TonalPalette
import com.materialkolor.scheme.SchemeContent

// C O L O R   M A P   B Y   R O L E

private fun fromPalette(tone: Int, palette: TonalPalette): String = palette.tone(tone).toToneString()

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

// P R I N T

fun printTextColors(vararg maps: Map<String, String>) {
    maps.forEach {
        it.forEach { (key, value) ->
            val colorInt = value.toColorInt()
            val name = ColorNameFinder.findCSSName(colorInt)
            val name2 = ColorNameFinder.findGpickName(colorInt)
            println("$key $value $name $name2")
        }
    }
}

fun printXmlColors(vararg maps: Map<String, String>) {
    maps.forEach {
        println("<resources>")
        it.forEach { (key, value) ->
            println("\t<color name=\"$key\">$value</color>")
        }
        println("</resources>")
    }
}

private fun printM3ThemeXml(themeName: String, mode: String, rolesRange: Collection<String>) {
    val parent = "Theme.Material3.${mode.replaceFirstChar { it.uppercase() }}.NoActionBar"
    println("\n<style name=\"$themeName\" parent=\"$parent\">")
    rolesRange.forEach {
        val attr = "color${it.replaceFirstChar { it.uppercase() }}"
        println("\t<item name=\"$attr\">@color/md_theme_${mode}_$it</item>")
    }
    println("</style>\n")
}

/**
 * Generate complete M3 Theme
 * @param rolesRange  role range
 */
fun printDayNightM3ThemeXml(
    rolesRange: List<String> = roles,
) {
    printM3ThemeXml("Theme.MyApp.Light", "light", rolesRange)
    printM3ThemeXml("Theme.MyApp.Dark", "dark", rolesRange)
}


