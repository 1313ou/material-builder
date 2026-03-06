package com.bbou.material.builder

import com.bbou.material.builder.Search.findColorMap
import com.bbou.material.builder.Search.findHashColors
import com.bbou.material.builder.Search.findRgbColors
import com.bbou.material.builder.Search.findXColors
import kotlinx.cli.*
import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val parser = ArgParser("material-builder")

    // Options (start with - or --)
    // @formatter:off
    val operation by parser.option( ArgType.String,     shortName = "o", fullName = "operation",    description = "Operation")                              .required()
    val text by parser.option(      ArgType.String,     shortName = "t", fullName = "text",         description = "Input file lines")
    val file by parser.option(      ArgType.String,     shortName = "f", fullName = "file",         description = "Parsed Input file (field extracted)")
    val max by parser.option(       ArgType.Int,        shortName = "a", fullName = "max",          description = "Take max values (-1=all)")
    val indexes by parser.option(   ArgType.String,     shortName = "i", fullName = "index",        description = "Indexes of values to take")
    val collect by parser.option(   ArgType.Boolean,    shortName = "c", fullName = "collect",      description = "Collect color expressions (#)")          .default(false)
    val collectHex by parser.option(ArgType.Boolean,    shortName = "b", fullName = "collecthex",   description = "Collect color expressions (0x)")         .default(false)
    val collectRgb by parser.option(ArgType.Boolean,    shortName = "3", fullName = "collectrgb",   description = "Collect color expressions (RGB)")        .default(false)
    val collectMap by parser.option(ArgType.Boolean,    shortName = "m", fullName = "collectmap",   description = "Collect key-color expressions (#)")      .default(false)
    val day by parser.option(       ArgType.Boolean,    shortName = "d", fullName = "day",          description = "Theme day colors")                       .default(false)
    val night by parser.option(     ArgType.Boolean,    shortName = "n", fullName = "night",        description = "Theme night colors")                     .default(false)
    val full by parser.option(      ArgType.Boolean,    shortName = "x", fullName = "full",         description = "Full output")                            .default(false)
    val verbose by parser.option(   ArgType.Boolean,    shortName = "v", fullName = "verbose",      description = "Verbose output")                         .default(false)
    // @formatter:on

    // Positional Argument (no prefix)
    // vararg() to collect "everything else" into a List
    var data: List<String> by parser.argument(ArgType.String, description = "Inputs (or parameters)").vararg().optional()

    parser.parse(args)
    if (verbose) {
        System.err.println("operation: $operation")
        System.err.println("text: $text")
        System.err.println("file: $file")
        System.err.println("arguments: $data")
    }

    // from external source

    val textArgs = text?.let {
        val data0 = data
        data = fromFileText(it)
        if (verbose) System.err.println("arguments from file: $data")
        data0
    }

    val fileArgs = file?.let {
        val data0 = data
        data = fromFileFields(it)
        if (verbose) System.err.println("arguments from file: $data")
        data0
    }

    // collector

    if (collect) {
        data = findHashColors(data.joinToString(separator = "\n"))
    }
    if (collectHex) {
        data = findXColors(data.joinToString(separator = "\n"))
    }
    if (collectRgb) {
        data = findRgbColors(data.joinToString(separator = "\n"))
    }
    if (collectMap) {
        val keyIndex = if (!fileArgs.isNullOrEmpty()) fileArgs[0].toInt() else if (!textArgs.isNullOrEmpty()) textArgs[0].toInt() else 0
        val valueIndex = if (!fileArgs.isNullOrEmpty()) fileArgs[1].toInt() else if (!textArgs.isNullOrEmpty()) textArgs[1].toInt() else 0
        data = findColorMap(data.joinToString(separator = "\n"), keyIndex, valueIndex)
    }

    // preprocessor

    if (day) {
        data = mapThemeColors1Day(data)
    }

    if (night) {
        data = mapThemeColors1Night(data)
    }

    // limit and extraction

    max?.let { data = data.take(it) }

    indexes?.let {
        val takeIndexes = indexes!!.split(",").map { it.toInt() }.toHashSet()
        data = data.withIndex().filter { (i, _) -> i in takeIndexes }.map { (_, datai) -> datai }
    }

    when (operation) {
        "print" -> {
            println(data)
        }

        "collect", "collect_hash" -> {
            findHashColors(data.joinToString(separator = "\n")).forEach { println(it) }
        }

        "collect_hex" -> {
            findXColors(data.joinToString(separator = "\n")).forEach { println(it) }
        }

        "collect_rgb" -> {
            findRgbColors(data.joinToString(separator = "\n")).forEach { println(it) }
        }

        "collect_map" -> {
            val keyIndex = if (!fileArgs.isNullOrEmpty()) fileArgs[0].toInt() else if (!textArgs.isNullOrEmpty()) textArgs[0].toInt() else 0
            val valueIndex = if (!fileArgs.isNullOrEmpty()) fileArgs[1].toInt() else if (!textArgs.isNullOrEmpty()) textArgs[1].toInt() else 1
            findColorMap(data.joinToString(separator = "\n"), keyIndex, valueIndex).forEach { println(it) }
        }

        "hct" -> {
            val dataInt = data.map { it.toColorInt() }.toIntArray()
            printHct(*dataInt)
        }

        "tone40", "main" -> {
            val dataInt = data.map { it.toColorInt() }.toIntArray()
            printColorToneOf(*dataInt, tone = 40)
        }

        "name" -> {
            val dataInt = data.map { it.toColorInt() }.toIntArray()
            ColorNameFinder.name(*dataInt)
        }

        "name_gpick" -> {
            val dataInt = data.map { it.toColorInt() }.toIntArray()
            ColorNameFinder.nameGpick(*dataInt)
        }

        "name_css" -> {
            val dataInt = data.map { it.toColorInt() }.toIntArray()
            ColorNameFinder.nameCss(*dataInt)
        }

        "html" -> {
            printHtmlColors(data, if (file != null) file!! else if (text != null) text!! else data.joinToString(separator = ","))
        }

        "palette" -> {
            val index = if (!fileArgs.isNullOrEmpty()) fileArgs[0].toInt() else if (!textArgs.isNullOrEmpty()) textArgs[0].toInt() else 0
            val seedHex = data[index]
            val seedInput = seedHex.toColorInt()
            palette(seedInput)
        }

        "contrasts" -> {
            val index = if (!fileArgs.isNullOrEmpty()) fileArgs[0].toInt() else if (!textArgs.isNullOrEmpty()) textArgs[0].toInt() else 0
            val colorHex = data[index]
            val index2 = if (!fileArgs.isNullOrEmpty() && fileArgs.size >= 2) fileArgs[1].toInt() else if (!textArgs.isNullOrEmpty()) textArgs[1].toInt() else 1
            val onColorHex = data[index2]
            val colorInput = colorHex.toColorInt()
            val onColorInput = onColorHex.toColorInt()
            auditThemeAccessibility(onColorInput, colorInput, "Contrasts $onColorHex on $colorHex")
            exitProcess(1)
        }

        "contrasting" -> {
            val index = if (!fileArgs.isNullOrEmpty()) fileArgs[0].toInt() else if (!textArgs.isNullOrEmpty()) textArgs[0].toInt() else 0
            val colorHex = data[index]
            val colorInput = colorHex.toColorInt()
            val onColor = findOnColor(colorInput)
            auditThemeAccessibility(onColor, colorInput, "${onColor.toColorString()} contrasts  on $colorHex")
        }

        "derive" -> {
            val index = if (!fileArgs.isNullOrEmpty()) fileArgs[0].toInt() else if (!textArgs.isNullOrEmpty()) textArgs[0].toInt() else 0
            val primaryHex = data[index]
            println("From $primaryHex")
            val primaryInput = primaryHex.toColorInt()
            printDeriveOfficialM3Colors(primaryInput)
        }

        "surface_primary" -> {
            val surfaceHex = data[0]
            val primaryHex = data[1]
            val surfaceInput = surfaceHex.toColorInt()
            val primaryInput = primaryHex.toColorInt()
            generateVibrantSurfacePaletteAndPrimary(surfaceInput, primaryInput)
        }

        "surface_primary_theme" -> {
            val surfaceHex = data[0]
            val primaryHex = data[1]
            val surfaceInput = surfaceHex.toColorInt()
            val primaryInput = primaryHex.toColorInt()
            generateVibrantSurfaceTheme(surfaceInput, primaryInput, false)
            println()
            generateVibrantSurfaceTheme(surfaceInput, primaryInput, true)
        }

        "surface" -> {
            val surfaceHex = data[0]
            val primaryHex = data[1]
            val surfaceInput = surfaceHex.toColorInt()
            val primaryInput = primaryHex.toColorInt()
            generateVibrantSurfaceTheme(surfaceInput, primaryInput, isDark = false)
        }

        "attrs" -> {
            printAttrsXml()
        }

        "theme_day" -> {
            printDayM3ThemeXml()
        }

        "theme_night" -> {
            printNightM3ThemeXml()
        }

        "overlays" -> {
            printDayNightM3OverlaysXml()
        }

        "overlays_day" -> {
            printDayM3OverlaysXml()
        }

        "overlays_night" -> {
            printNightM3OverlaysXml()
        }

        "colors" -> {
            printXmlThemeColors(data, full = full)
        }

        "colors_day" -> {
            printXmlThemeColorsDay(data, full = full)
        }

        "colors_night" -> {
            printXmlThemeColorsNight(data, full = full)
        }

        "colors1_day" -> {
            printTextThemeColors1Day(data)
        }

        "colors1_night" -> {
            printTextThemeColors1Night(data)
        }

        "map_day" -> {
            println(mapThemeColors1Day(data).joinToString(separator = " "))
        }

        "map_night" -> {
            println(mapThemeColors1Night(data).joinToString(separator = " "))
        }

        "theme_html" -> {
            printHtmlThemeColors(data, full = full)
        }

        "theme_text" -> {
            printTextThemeColors(data, full = full)
        }

        else -> throw IllegalArgumentException(operation)
    }
}

fun fromFileText(filePath: String): List<String> {
    return File(filePath).useLines { lines ->
        lines
            .toList()
    }
}

fun fromFileFields(filePath: String): List<String> {
    return File(filePath).useLines { lines ->
        lines
            .map { it.takeWhile { c: Char -> !c.isWhitespace() } }
            .toList()
    }
}

fun printDeriveOfficialM3Colors(primaryInput: Int, tone: Int = 40) {
    val accents = deriveOfficialM3Colors(primaryInput, tone = tone)
    printAccentColors(accents, tone = tone)
}

fun printAccentColors(accents: Triple<Int, Int, Int>, tone: Int = 40) {
    val (primaryColor, secondaryColor, tertiaryColor) = accents
    println("Primary (Tone $tone): ${primaryColor.toColorString()}")
    println("Secondary (Tone $tone): ${secondaryColor.toColorString()}")
    println("Tertiary (Tone $tone): ${tertiaryColor.toColorString()}")
}

fun printXmlThemeColors(args: List<String>, full: Boolean = false) {
    printXmlThemeColorsDay(args, full = full)
    printXmlThemeColorsNight(args, full = full)
}

fun printXmlThemeColorsDay(args: List<String>, full: Boolean = false) {
    val colors = generateThemeColors(args, isDark = false, full = full)
    printXmlColors(colors, mode = "light")
}

fun printXmlThemeColorsNight(args: List<String>, full: Boolean = false) {
    val colors = generateThemeColors(args, isDark = true, full = full)
    printXmlColors(colors, mode = "dark")
}

fun printHtmlThemeColors(args: List<String>, full: Boolean = false) {
    val lightColors = generateThemeColors(args, isDark = false, full = full)
    val darkColors = generateThemeColors(args, isDark = true, full = full)
    printHtmlColors(lightColors, darkColors, args.joinToString(separator = ","))
}

fun printTextThemeColors(args: List<String>, full: Boolean = false) {
    val lightColors = generateThemeColors(args, isDark = false, full = full)
    val darkColors = generateThemeColors(args, isDark = true, full = full)
    printTextColors(lightColors, darkColors)
}

fun printTextThemeColors1Day(args: List<String>) {
    val lightColors = generateThemeColors(args, isDark = false, one = true)
    printTextColors(lightColors)
}

fun printTextThemeColors1Night(args: List<String>) {
    val darkColors = generateThemeColors(args, isDark = true, one = true)
    printTextColors(darkColors)
}

fun mapThemeColors1Day(args: List<String>): List<String> {
    val lightColors = generateThemeColors(args, isDark = false, one = true)
    return mapColors(lightColors)
}

fun mapThemeColors1Night(args: List<String>): List<String> {
    val darkColors = generateThemeColors(args, isDark = true, one = true)
    return mapColors(darkColors)
}

fun generateThemeColors(args: List<String>, isDark: Boolean = false, contrasts: List<String> = listOf("medium", "high"), full: Boolean = false, one: Boolean = false): Map<String, String> {
    val surfaceHintHex = args[0]
    val primaryHintHex = args[1]
    val surfaceHint = surfaceHintHex.toColorInt()
    val primaryHint = primaryHintHex.toColorInt()

    return if (args.size > 2) {
        val secondaryHintHex = args[2]
        val tertiaryHintHex = args[3]
        val secondaryHint = secondaryHintHex.toColorInt()
        val tertiaryHint = tertiaryHintHex.toColorInt()
        generateM3XmlColors(
            surfaceHint, listOf(primaryHint, secondaryHint, tertiaryHint),
            customRolesRange = if (one) customRoles1 else customRoles,
            surfaceRolesRange = if (one) surfaceRoles1 else if (full) surfaceRoles else surfaceRolesMin,
            accentRolesRange = if (one) accentRoles1 else if (full) accentRoles else accentRolesMin,
            contrasts = if (one) emptyList() else contrasts,
            isDark = isDark,
        )
    } else {
        generateM3XmlColors(
            surfaceHint, listOf(primaryHint),
            customRolesRange = if (one) customRoles1 else customRoles,
            surfaceRolesRange = if (one) surfaceRoles1 else if (full) surfaceRoles else surfaceRolesMin,
            accentRolesRange = if (one) accentRoles1 else if (full) accentRoles else accentRolesMin,
            contrasts = if (one) emptyList() else contrasts,
            isDark = isDark,
        )
    }
}
