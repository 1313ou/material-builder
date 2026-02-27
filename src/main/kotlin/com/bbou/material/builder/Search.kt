package com.bbou.material.builder

object Search {


    /**
     * Finds all matches of a given [pattern] in the [text],
     * ignoring line breaks.
     */
    fun findAllOccurrences(text: String, pattern: String): List<String> {
        // RegexOption.DOT_MATCHES_ALL ensures the '.' character matches newlines
        val regex = Regex(pattern, RegexOption.DOT_MATCHES_ALL)

        return regex.findAll(text).map { it.value }.toList()
    }

    val hashPattern = "#[0-9a-fA-F]{6,}"

    val xPattern = "0x[0-9a-fA-F]{6,}"

    fun findHashColors(text: String): List<String> = findAllOccurrences(text, pattern = hashPattern)

    fun findXColors(text: String): List<String> = findAllOccurrences(text, pattern = xPattern)
}
