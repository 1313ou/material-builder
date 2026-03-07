package com.bbou.material.builder

import com.bbou.material.builder.Search.findColorNV
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
    val operation by parser.option(     ArgType.String,     shortName = "o",    fullName = "operation",     description = "Operation")                                  .required()
    val text by parser.option(          ArgType.String,     shortName = "t",    fullName = "text",          description = "Input file lines")
    val file by parser.option(          ArgType.String,     shortName = "f",    fullName = "file",          description = "Parsed Input file (field extracted)")
    val max by parser.option(           ArgType.Int,        shortName = "a",    fullName = "max",           description = "Take max values (-1=all)")
    val indexes by parser.option(       ArgType.String,     shortName = "i",    fullName = "index",         description = "Indexes of values to take")

    val precollect by parser.option(    ArgType.Boolean,    shortName = "c0",   fullName = "precollect",    description = "PreCollect color expressions")               .default(false)
    val precollectBy by parser.option(  ArgType.String,     shortName = "by0",  fullName = "precollect_by", description = "PreCollect method")                          // "#", "0x", "rgb", "nv"

    val collectBy by parser.option(     ArgType.String,     shortName = "by",   fullName = "collect_by",    description = "Collect method")                             // "#", "0x", "rgb", "nv"

    val day by parser.option(           ArgType.Boolean,    shortName = "d0",   fullName = "day",           description = "Preprocess as theme day colors")             .default(false)
    val night by parser.option(         ArgType.Boolean,    shortName = "n0",   fullName = "night",         description = "Preprocess as Theme night colors")           .default(false)
    val dark by parser.option(          ArgType.Boolean,    shortName = "d",    fullName = "dark",          description = "Theme dark colors")                          .default(false)

    val basic by parser.option(         ArgType.Boolean,    shortName = "b",    fullName = "basic",         description = "Basic output")                               .default(false)
    val verbose by parser.option(       ArgType.Boolean,    shortName = "v",    fullName = "verbose",       description = "Verbose output")                             .default(false)
    // @formatter:on

    // Positional Argument (no prefix)
    // vararg() to collect "everything else" into a List
    var data: List<String> by parser.argument(ArgType.String, description = "Inputs (or parameters)").vararg().optional()

    parser.parse(args)
    if (verbose) {
        System.err.println("operation: $operation")
        System.err.println("text: $text")
        System.err.println("file: $file")
        System.err.println("precollect: $precollect")
        System.err.println("precollect by: $precollectBy")
        System.err.println("collect by: $collectBy")
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

    if (precollect) {
        when (precollectBy) {
            "0x" -> {
                data = findXColors(data.joinToString(separator = "\n"))
            }

            "rgb" -> {
                data = findRgbColors(data.joinToString(separator = "\n"))
            }

            "nv" -> {
                val nameIndex = if (!fileArgs.isNullOrEmpty()) fileArgs[0].toInt() else if (!textArgs.isNullOrEmpty()) textArgs[0].toInt() else 0
                val valueIndex = if (!fileArgs.isNullOrEmpty()) fileArgs[1].toInt() else if (!textArgs.isNullOrEmpty()) textArgs[1].toInt() else 1
                data = findColorNV(data.joinToString(separator = "\n"), nameIndex, valueIndex, limit = 2)
            }

            null, "#" -> {
                data = findHashColors(data.joinToString(separator = "\n"))
            }

            else -> {
                throw IllegalArgumentException(precollectBy)
            }
        }
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

        "collect" -> {
            when (collectBy) {
                "0x" -> {
                    findXColors(data.joinToString(separator = "\n")).forEach { println(it) }
                }

                "rgb" -> {
                    findRgbColors(data.joinToString(separator = "\n")).forEach { println(it) }
                }

                "nv" -> {
                    val keyIndex = if (!fileArgs.isNullOrEmpty()) fileArgs[0].toInt() else if (!textArgs.isNullOrEmpty()) textArgs[0].toInt() else 0
                    val valueIndex = if (!fileArgs.isNullOrEmpty()) fileArgs[1].toInt() else if (!textArgs.isNullOrEmpty()) textArgs[1].toInt() else 1
                    findColorNV(data.joinToString(separator = "\n"), keyIndex, valueIndex).forEach { println(it) }
                }

                null, "#" -> {
                    findHashColors(data.joinToString(separator = "\n")).forEach { println(it) }
                }

                else -> {
                    throw IllegalArgumentException(collectBy)
                }
            }
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

        "html_nv" -> {
            val data2 = data.map {
                val fields = it.split("=")
                fields[0] to fields[1]
            }
            printHtmlColorNV(data2, if (file != null) file!! else if (text != null) text!! else data.joinToString(separator = ","))
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

        "colors2" -> {
            printXmlThemeColors(data, full = !basic)
        }

        "colors" -> {
            if (dark)
                printXmlThemeColorsNight(data, full = !basic)
            else
                printXmlThemeColorsDay(data, full = !basic)
        }

        "colors1" -> {
            if (dark)
                printTextThemeColors1Night(data)
            else
                printTextThemeColors1Day(data)
        }

        "map" -> {
            if (dark)
                println(mapThemeColors1Night(data).joinToString(separator = " "))
            else
                println(mapThemeColors1Day(data).joinToString(separator = " "))
        }

        "themes_html" -> {
            printHtmlThemesColors(data, full = !basic)
        }

        "theme_html" -> {
            printHtmlThemeColors(data, isDark = dark, full = !basic)
        }

        "themes_text" -> {
            printTextThemesColors(data, full = !basic)
        }

        "theme_text" -> {
            printTextThemeColors(data, isDark = dark, full = !basic)
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

fun printHtmlThemesColors(args: List<String>, full: Boolean = false) {
    val lightColors = generateThemeColors(args, isDark = false, full = full)
    val darkColors = generateThemeColors(args, isDark = true, full = full)
    printHtmlColorMaps(lightColors, darkColors, args.joinToString(separator = ","))
}

fun printHtmlThemeColors(args: List<String>, isDark: Boolean = false, full: Boolean = false) {
    val colors = generateThemeColors(args, isDark = isDark, full = full)
    printHtmlColorMap(colors, args.joinToString(separator = ","))
}

fun printTextThemesColors(args: List<String>, full: Boolean = false) {
    val lightColors = generateThemeColors(args, isDark = false, full = full)
    val darkColors = generateThemeColors(args, isDark = true, full = full)
    printTextColors(lightColors, darkColors)
}

fun printTextThemeColors(args: List<String>, isDark: Boolean = false, full: Boolean = false) {
    val colors = generateThemeColors(args, isDark = isDark, full = full)
    printTextColors(colors)
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
