package com.bbou.material.builder

import com.bbou.material.builder.Search.findHashColors
import com.bbou.material.builder.Search.findRgbColors
import com.bbou.material.builder.Search.findXColors
import kotlinx.cli.*
import java.io.File

fun main(args: Array<String>) {
    val parser = ArgParser("material-builder")

    // Options (start with - or --)
    val operation by parser.option(ArgType.String, shortName = "o", fullName = "operation", description = "Operation").required()
    val text by parser.option(ArgType.String, shortName = "t", fullName = "text", description = "Unparsed Input file")
    val file by parser.option(ArgType.String, shortName = "f", fullName = "file", description = "Parsed Input file (field extracted)")
    val scrape by parser.option(ArgType.Boolean, shortName = "s", fullName = "scrape", description = "Scrape color expressions").default(false)
    val full by parser.option(ArgType.Boolean, shortName = "x", fullName = "full", description = "Full output").default(false)
    val verbose by parser.option(ArgType.Boolean, shortName = "v", fullName = "verbose", description = "Verbose output").default(false)

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

    if (scrape) {
        data = findHashColors(data.joinToString(separator = "\n"))
    }

    when (operation) {
        "args" -> {
            println(data)
        }

        "scrape" -> {
            findHashColors(data.joinToString(separator = "\n")).forEach { println(it) }
        }

        "scrapex" -> {
            findXColors(data.joinToString(separator = "\n")).forEach { println(it) }
        }

        "scrapergb" -> {
            findRgbColors(data.joinToString(separator = "\n")).forEach { println(it) }
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

        "html" -> {
            printHtmlColors(data)
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
        }

        "contrasting" -> {
            val index = if (!fileArgs.isNullOrEmpty()) fileArgs[0].toInt() else if (!textArgs.isNullOrEmpty()) textArgs[0].toInt() else 0
            val colorHex = data[index]
            val colorInput = colorHex.toColorInt()
            val onColor = getOnColor(colorInput)
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

        "theme" -> {
            printDayNightM3ThemeXml()
        }

        "colors" -> {
            printXmlThemeColors(data, full = full)
        }

        "themehtml" -> {
            printHtmlThemeColors(data, full = full)
        }

        "themetext" -> {
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
            .map { it.takeWhile({ c: Char -> !c.isWhitespace() }) }
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
    val (lightColors, darkColors) = generateThemeColors(args, full)
    printXmlColors(lightColors, mode = "light")
    printXmlColors(darkColors, mode = "dark")
}

fun printHtmlThemeColors(args: List<String>, full: Boolean = false) {
    val (lightColors, darkColors) = generateThemeColors(args, full)
    printHtmlColors(lightColors, darkColors)
}

fun printTextThemeColors(args: List<String>, full: Boolean = false) {
    val (lightColors, darkColors) = generateThemeColors(args, full)
    printTextColors(lightColors, darkColors)
}

fun generateThemeColors(args: List<String>, full: Boolean = false): Pair<Map<String, String>, Map<String, String>> {
    val surfaceHex = args[0]
    val primaryHex = args[1]
    val surfaceInput = surfaceHex.toColorInt()
    val primaryInput = primaryHex.toColorInt()
    auditThemeAccessibility(surfaceInput, primaryInput, "Primary $primaryHex on Surface $surfaceHex")

    return if (args.size > 2) {
        val secondaryHex = args[2]
        val tertiaryHex = args[3]
        val secondaryInput = secondaryHex.toColorInt()
        val tertiaryInput = tertiaryHex.toColorInt()
        generateDayNightM3XmlColors(
            surfaceInput, listOf(primaryInput, secondaryInput, tertiaryInput),
            surfaceRolesRange = if (full) surfaceRoles else surfaceRolesMin,
            accentRolesRange = if (full) accentRoles else accentRolesMin
        )
    } else {
        generateDayNightM3XmlColors(
            surfaceInput, listOf(primaryInput),
            surfaceRolesRange = if (full) surfaceRoles else surfaceRolesMin,
            accentRolesRange = if (full) accentRoles else accentRolesMin
        )
    }
}
