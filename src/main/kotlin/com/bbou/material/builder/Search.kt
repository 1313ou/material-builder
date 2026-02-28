package com.bbou.material.builder

object Search {


    /**
     * Finds all matches of a given [pattern] in the [text],
     * ignoring line breaks.
     */
    fun findAllOccurrences(text: String, pattern: String): List<String> {
        // RegexOption.DOT_MATCHES_ALL ensures the '.' character matches newlines
        val regex = Regex(pattern, RegexOption.MULTILINE) // setOf(RegexOption.MULTILINE, RegexOption.DOT_MATCHES_ALL)
        return regex.findAll(text).map { it.value }.toList()
    }

    fun findAllMatches(text: String, pattern: String): Sequence<MatchResult> {
        // RegexOption.DOT_MATCHES_ALL ensures the '.' character matches newlines
        val regex = Regex(pattern, RegexOption.MULTILINE) // setOf(RegexOption.MULTILINE, RegexOption.DOT_MATCHES_ALL)
        return regex.findAll(text)
    }

    val hashPattern = "#[0-9a-fA-F]{6,}"

    val xPattern = "0x[0-9a-fA-F]{6,}"

    val rgbPattern = "^([0-9]+)\\s+([0-9]+)\\s+([0-9]+)"

    fun findHashColors(text: String): List<String> = findAllOccurrences(text, pattern = hashPattern)

    fun findXColors(text: String): List<String> = findAllOccurrences(text, pattern = xPattern)

    fun findRgbColors(text: String): List<String> {
        val results = findAllMatches(text, pattern = rgbPattern).toList()
        return results.map { match ->
            val r = match.groupValues[1].toInt()
            val g = match.groupValues[2].toInt()
            val b = match.groupValues[3].toInt()
            rgb(r, g, b).toColorString()
        }.toList()
    }

    private fun rgb(red: Int, green: Int, blue: Int): Int {
        return (red shl 16) or (green shl 8) or blue
    }
}
