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

    const val HASH_PATTERN = "#[0-9a-fA-F]{6,}"

    const val X_PATTERN = "0x[0-9a-fA-F]{6,}"

    const val RGB_PATTERN = "^([0-9]+)\\s+([0-9]+)\\s+([0-9]+)"

    const val MAP_PATTERN = "^(.*)$"

    fun findHashColors(text: String): List<String> = findAllOccurrences(text, pattern = HASH_PATTERN)

    fun findXColors(text: String): List<String> = findAllOccurrences(text, pattern = X_PATTERN)

    fun findRgbColors(text: String): List<String> {
        val results = findAllMatches(text, pattern = RGB_PATTERN).toList()
        return results.map { match ->
            val r = match.groupValues[1].toInt()
            val g = match.groupValues[2].toInt()
            val b = match.groupValues[3].toInt()
            rgb(r, g, b).toColorString()
        }.toList()
    }

    fun findColorNV(text: String, nameIndex: Int, valueIndex: Int, limit: Int = 0): List<String> {
        val results = findAllMatches(text, pattern = MAP_PATTERN).toList()
        return results.map { match ->
            val line = match.groupValues[1].trim()
            val fields = line.split("\\s+".toRegex(), limit = limit)
            val name = fields[nameIndex]
            val value = fields[valueIndex]
            "$name=$value"
        }.toList()
    }

    private fun rgb(red: Int, green: Int, blue: Int): Int {
        return (red shl 16) or (green shl 8) or blue
    }
}
