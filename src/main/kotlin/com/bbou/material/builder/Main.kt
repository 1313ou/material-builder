package com.bbou.material.builder

import com.bbou.material.builder.Search.findHashColors
import kotlinx.cli.*
import java.io.File

fun main(args: Array<String>) {
    val parser = ArgParser("material-builder")

    // Options (start with - or --)
    val operation by parser.option(ArgType.String, shortName = "o", fullName = "operation", description = "Operation").required()
    val file by parser.option(ArgType.String, shortName = "f", fullName = "file", description = "Input file")
    val scrape by parser.option(ArgType.Boolean, shortName = "s", fullName = "scrape", description = "Scrape color expressions").default(false)
    val full by parser.option(ArgType.Boolean, shortName = "x", fullName = "full", description = "Full output").default(false)

    // Positional Argument (no prefix)
    // vararg() to collect "everything else" into a List
    var data: List<String> by parser.argument(ArgType.String, description = "Inputs (or parameters)").vararg().optional()

    parser.parse(args)
    System.err.println("operation: $operation")
    System.err.println("input: $file")
    System.err.println("arguments: $data")
    val args2 = file?.let {
        val data0 = data
        data = if (scrape) fromFileText(it) else fromFileFields(it)
        System.err.println("arguments from file: $data")
        data0
    }
    if (scrape) {
        data= findHashColors(data.joinToString( separator = "\n" ))
    }

    when (operation) {
        "info" -> {
            println(data)
        }

        "hct" -> {
            val dataInt = data.map { it.toColorInt() }.toIntArray()
            hct(*dataInt)
        }

        "name" -> {
            val dataInt = data.map { it.toColorInt() }.toIntArray()
            ColorNameFinder.Companion.name(*dataInt)
        }

        "palette" -> {
            val index = if (!file.isNullOrEmpty() && args.isNotEmpty()) args2!![0].toInt() else 0
            val seedHex = data[index]
            val seedInput = seedHex.toColorInt()
            palette(seedInput)
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

        "derive" -> {
            val index = if (!file.isNullOrEmpty() && args.isNotEmpty()) args2!![0].toInt() else 0
            val primaryHex = data[index]
            println("From $primaryHex")
            val primaryInput = primaryHex.toColorInt()
            printDeriveOfficialM3Colors(primaryInput)
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

        "html" -> {
            printHtmlThemeColors(data, full = full)
        }

        "text" -> {
            printTextThemeColors(data, full = full)
        }
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

fun printDeriveOfficialM3Colors(primaryInput: Int, tone: Int = 40): Triple<Int, Int, Int> {
    val accents = deriveOfficialM3Colors(primaryInput, tone)
    printAccentColors(accents, tone)
    return accents
}

fun printAccentColors(accents: Triple<Int, Int, Int>, tone: Int = 40) {
    val (primaryColor, secondaryColor, tertiaryColor) = accents
    println("Primary (Tone $tone): ${primaryColor.toColorString()}")
    println("Secondary (Tone $tone): ${secondaryColor.toColorString()}")
    println("Tertiary (Tone $tone): ${tertiaryColor.toColorString()}")
}

fun printXmlThemeColors(args: List<String>, full: Boolean = false) {
    val (lightColors, darkColors) = generateThemeColors(args, full)
    printXmlColors(lightColors, darkColors)
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
