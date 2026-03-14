package com.bbou.material.builder

import com.materialkolor.hct.Hct
import com.materialkolor.palettes.TonalPalette
import com.materialkolor.scheme.SchemeContent

const val attrs = """
<resources>
  <attr name="colorCustom" format="color" />
  <attr name="colorOnCustom" format="color" />
  <attr name="colorContainerCustom" format="color" />
  <attr name="colorOnContainerCustom" format="color" />
  <attr name="colorCustomVariant" format="color" />
</resources>
"""

// C O L O R   M A P   B Y   R O L E

private fun fromPalette(tone: Int, palette: TonalPalette): String = palette.tone(tone = tone).toToneString()

private fun fromScheme(role: String, scheme: SchemeContent): String = getSchemeColor(scheme, role).toToneString()

/**
 * Helper to extract colors from the scheme by name
 */
fun getSchemeColor(scheme: SchemeContent, role: String): Int {
    val r = role.removeSuffix("_mediumContrast").removeSuffix("_highContrast")
    return when (r) {
        "surface" -> scheme.surface
        "onSurface" -> scheme.onSurface
        "surfaceContainer" -> scheme.surfaceContainer
        "surfaceVariant" -> scheme.surfaceVariant
        "onSurfaceVariant" -> scheme.onSurfaceVariant
        "surfaceInverse" -> scheme.inverseSurface
        "onSurfaceInverse" -> scheme.inverseOnSurface
        "surfaceDim" -> scheme.surfaceDim
        "surfaceBright" -> scheme.surfaceBright
        "surfaceContainerLowest" -> scheme.surfaceContainerLowest
        "surfaceContainerLow" -> scheme.surfaceContainerLow
        "surfaceContainerHighest" -> scheme.surfaceContainerHighest
        "surfaceContainerHigh" -> scheme.surfaceContainerHigh
        "background" -> scheme.background
        "onBackground" -> scheme.onBackground
        "outline" -> scheme.outline
        "outlineVariant" -> scheme.outlineVariant
        "error" -> scheme.error
        "onError" -> scheme.onError
        "errorContainer" -> scheme.errorContainer
        "onErrorContainer" -> scheme.onErrorContainer
        else -> throw IllegalArgumentException(role)
    }
}

fun getContrastAdjustedColor(
    surfaceHct: Hct,
    role: String,
    isDark: Boolean
): Int {
    // 1. Determine the contrast level based on the suffix
    val contrastLevel = when {
        role.endsWith("_highContrast") -> 1.0
        role.endsWith("_mediumContrast") -> 0.5
        else -> 0.0
    }

    // 2. Create the scheme with that specific contrast level
    // This is where the magic happens—it shifts the Tones automatically
    val scheme = SchemeContent(surfaceHct, isDark, contrastLevel)

    // 3. Clean the role name to use in your existing when() block
    val cleanRole = role.removeSuffix("_mediumContrast").removeSuffix("_highContrast")

    // 4. Call your existing function with the contrast-aware scheme
    return getSchemeColor(scheme, cleanRole)
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
    customRolesRange: List<String> = customRoles,
    surfaceRolesRange: List<String> = surfaceRoles,
    accentRolesRange: List<String> = accentRoles,
    contrasts: List<String> = listOf(),
    isDark: Boolean = false
): Map<String, String> {

    // Result color map by role
    val colorMap = LinkedHashMap<String, String>()

    // C U S T O M
    customRolesRange.forEach { role ->
        val value = when (role) {
            "custom" -> surfaceInput.toColorString()
            "onCustom" -> findOnColor(surfaceInput, minRatio = 7.0).toColorString()
            "customVariant" -> findTonalVariant(surfaceInput, isDark = isDark, deltaDark = 10, deltaLight = -10).toColorString() // 10 darker, 10 lighter
            "onCustomVariant" -> findOnColor(findTonalVariant(surfaceInput, isDark = isDark, deltaDark = 90, deltaLight = 30), minRatio = 7.0).toColorString()
            else -> throw IllegalArgumentException("Unknown custom $role")
        }
        colorMap[role] = value
    }

    // S U R F A C E
    val surfaceHct = Hct.fromInt(surfaceInput)
    // Generate Scheme (using surface for the overall "Content" vibe)
    val scheme = SchemeContent(surfaceHct, isDark, 0.0)
    // Base contrast
    surfaceRolesRange.forEach { role ->
        colorMap[role] = "#${fromScheme(role, scheme)}"
    }
    // Medium and high contrast
    if (contrasts.isNotEmpty()) {
        contrasts.forEach { contrast ->
            val contrastLevel = when (contrast) {
                "medium" -> 0.5
                "high" -> 1.0
                else -> throw IllegalArgumentException(contrast)
            }
            val contrastScheme = SchemeContent(surfaceHct, isDark, contrastLevel)
            surfaceRolesRange.forEach { role ->
                val contrastRole = "${role}_${contrast}Contrast"
                colorMap[contrastRole] = "#${fromScheme(contrastRole, contrastScheme)}"
            }
        }
    }

    // A C C E N T S
    // Setup accent hints
    val accentInputs = if (accentHints.size == 3) accentHints else {
        val primaryInput = accentHints[0]
        deriveOfficialM3Colors(primaryInput).toList()
    }
    // Setup palettes for accents
    val palettes = Array(accentInputs.size) { TonalPalette.fromHct(Hct.fromInt(accentInputs[it])) }
    // Base contrast
    accentRolesRange.forEach { role ->
        val data = accentRoleDefs[role]!!
        val tone = if (isDark) data.second else data.first
        val paletteIndex = data.third - 1
        colorMap[role] = "#${fromPalette(tone, palettes[paletteIndex])}"
    }
    // Medium and high contrast
    contrasts.forEach { contrast ->
        accentRolesRange.forEach { role ->
            val contrastRole = "${role}_${contrast}Contrast"
            val data = accentRoleDefs[contrastRole]!!
            val tone = if (isDark) data.second else data.first
            val paletteIndex = data.third - 1
            colorMap[contrastRole] = "#${fromPalette(tone, palettes[paletteIndex])}"
        }
    }
    return colorMap
}

// P R I N T

fun mapColors(colorMap: Map<String, String>, rolesRange: List<String> = customRoles1 + surfaceRoles1 + accentRoles1): List<String> {
    return rolesRange.map { role ->
        colorMap[role]!!
    }.toList()
}

fun printTextColors(vararg maps: Map<String, String>) {
    maps.forEach {
        it.forEach { (key, value) ->
            val colorInt = value.toColorInt()
            val name = findGpickName(colorInt)
            val name2 = findCSSName(colorInt)
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
    println("\t<item name=\"colorCustom\">@color/${colorPrefix}custom</item>")
    println("\t<item name=\"colorOnCustom\">@color/${colorPrefix}onCustom</item>")
    println("\t<item name=\"colorCustomVariant\">@color/${colorPrefix}customVariant</item>")
    rolesRange.forEach {
        var attr = "color${it.replaceFirstChar { c -> c.uppercase() }}"
        attr = attr.removeSuffix("_highContrast")
        attr = attr.removeSuffix("_mediumContrast")
        if (attr == "colorBackground")
            attr = "android:$attr"
        println("\t<item name=\"$attr\">@color/${colorPrefix}$it</item>")
    }
    println("</style>\n")
}

/**
 * Generate complete M3 Theme
 * @param rolesRange  role range
 */
fun printDayNightM3ThemeXml(themeName: String = "AppTheme", rolesRange: List<String> = roles) {
    println("<resources>")
    printM3ThemeXml(themeName, "light", rolesRange)
    printM3ThemeXml(themeName, "dark", rolesRange)
    println("</resources>")
}

fun printDayM3ThemeXml(themeName: String = "AppTheme", rolesRange: List<String> = roles) {
    println("<resources>")
    printM3ThemeXml(themeName, "light", rolesRange)
    println("</resources>")
}

fun printNightM3ThemeXml(themeName: String = "AppTheme", rolesRange: List<String> = roles) {
    println("<resources>")
    printM3ThemeXml(themeName, "dark", rolesRange)
    println("</resources>")
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
