package com.bbou

//// Vibrant Surface: #FBF9F8 Surface Container: #EFEDED Custom Primary Hint: #1B6D24
// Example: A very pale mint surface and a deeper forest green primary hint
// val args = listOf("#E0F2F1", "#2E7D32", "#FF0000", "#00FF00")
// val args2 = listOf("#E0F2F1", "#2E7D32", "#FF0000", "#00FF00")


fun main(args: Array<String>) {

    when (args[0]) {
        "palette" -> {
            val surfaceHex = args[1]
            val primaryHex = args[2]
            val surfaceInput = surfaceHex.toColorInt()
            val primaryInput = primaryHex.toColorInt()
            generateFullVibrantPalette(surfaceInput, primaryInput)
        }

        "theme" -> {
            val surfaceHex = args[1]
            val primaryHex = args[2]
            val surfaceInput = surfaceHex.toColorInt()
            val primaryInput = primaryHex.toColorInt()
            generateVibrantSurfaceTheme(surfaceInput, primaryInput, isDark = false)
        }

        else -> {
            val surfaceHex = args[0]
            val primaryHex = args[1]
            val surfaceInput = surfaceHex.toColorInt()
            val primaryInput = primaryHex.toColorInt()
            auditThemeAccessibility(surfaceInput, primaryInput, "Primary $primaryHex on Surface $surfaceHex")

            if (args.size > 2) {
                val secondaryHex = args[2]
                val tertiaryHex = args[3]
                val secondaryInput = secondaryHex.toColorInt()
                val tertiaryInput = tertiaryHex.toColorInt()
                generateCompleteDayNightM3XmlTheme(surfaceInput, primaryInput, secondaryInput, tertiaryInput)
            } else {
                generateDayNightM3XmlTheme(surfaceInput, primaryInput)
            }
        }
    }
}