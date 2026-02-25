package com.bbou

import java.io.File
import kotlin.sequences.toList
import kotlinx.cli.*

fun main(args: Array<String>) {
    val parser = ArgParser("material-builder")

    // Options (start with - or --)
    val operation by parser.option(ArgType.String, shortName = "o", description = "Operation").required()
    val input by parser.option(ArgType.String, shortName = "i", description = "Input file")
    val verbose by parser.option(ArgType.Boolean, shortName = "v", description = "Verbose output").default(false)

    // Positional Argument (no prefix)
    // vararg() to collect "everything else" into a List
    var data: List<String> by parser.argument(ArgType.String, description = "Inputs (or parameters)").vararg().optional()

    parser.parse(args)
    println("operation: $operation")
    println("input: $input")
    println("arguments: $data")
    val args2 = input?.let {
        val data0 = data
        data = fromFile(it)
        println("arguments from file: $data")
        data0
    }
    println()

    when (operation) {
        "info" -> {
            println(data)
        }

        "palette" -> {
            val surfaceHex = data[0]
            val primaryHex = data[1]
            val surfaceInput = surfaceHex.toColorInt()
            val primaryInput = primaryHex.toColorInt()
            generateFullVibrantPalette(surfaceInput, primaryInput)
        }

        "derive" -> {
            val index = if (args.isNotEmpty()) args2!![0].toInt() else 0
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
            generateTheme()
        }

        "colors" -> {
            printXmlThemeColors(data)
        }

        "html" -> {
            printHtmlThemeColors(data)
        }
    }
}

fun fromFile(filePath: String): List<String> {
    return File(filePath).useLines { lines ->
        lines.map { it.takeWhile({ c: Char -> !c.isWhitespace() }) }.toList()
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

fun printXmlThemeColors(args: List<String>) {
    val (lightColors, darkColors) = generateThemeColors(args)
    printXmlColors(lightColors, darkColors)
}

fun printHtmlThemeColors(args: List<String>) {
    val (lightColors, darkColors) = generateThemeColors(args)
    printHtmlColors(lightColors, darkColors)
}

fun generateThemeColors(args: List<String>): Pair<Map<String, String>, Map<String, String>> {
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
        generateDayNightM3XmlColors(surfaceInput, listOf(primaryInput, secondaryInput, tertiaryInput), surfaceRolesRange = surfaceRolesMin, accentRolesRange = accentRolesMin)
    } else {
        generateDayNightM3XmlColors(surfaceInput, listOf(primaryInput), surfaceRolesRange = surfaceRolesMin, accentRolesRange = accentRolesMin)
    }
}

fun generateTheme() {
    generateDayNightM3XmlTheme()
}

//// Vibrant Surface: #FBF9F8 Surface Container: #EFEDED Custom Primary Hint: #1B6D24
// Example: A very pale mint surface and a deeper forest green primary hint
// val args = listOf("#E0F2F1", "#2E7D32", "#FF0000", "#00FF00")
// val args2 = listOf("#E0F2F1", "#2E7D32", "#FF0000", "#00FF00")
