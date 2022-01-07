package day10

import utils.readFileByLine

class SyntaxScoring {
    fun calculateIllegalScore(input: List<String>): Int {
        var out = 0

        input.forEach {
            val firstIllegalChar = removeCompleteChunks(it).firstOrNull { character -> listOf(')', '}', ']', '>').contains(character) }

            if (firstIllegalChar != null) out += IllegalCharacterScores.getIllegalScore(firstIllegalChar)
        }

        return out
    }

    fun calculateIncompleteScore(input: List<String>): Long {
        val incompleteScores: MutableList<Long> = mutableListOf()

        input.forEach {
            val closingCharacters = findClosingCharacters(removeCompleteChunks(it))

            if(closingCharacters.isNotBlank()) incompleteScores.add(IllegalCharacterScores.getFullIncompleteScore(closingCharacters))
        }

        return incompleteScores.sorted()[(incompleteScores.size / 2)]
    }

    private fun findClosingCharacters(incompleteChunks: String): String = if(incompleteChunks.contains(Regex("[})>\\]]"))) "" else incompleteChunks.reversed()

    private fun removeCompleteChunks(line: String): String {
        val parenRegex = listOf("\\(\\)", "\\{\\}", "\\[\\]", "\\<\\>")
        var newLine = line; var lineCopy: String

        while (true) {
            lineCopy = newLine
            parenRegex.forEach {  newLine = newLine.replace(it.toRegex(), "") }

            if(newLine == lineCopy) return newLine
        }
    }
}

enum class IllegalCharacterScores(var opening: Char, var closing: Char, var illegalScore: Int, var incompleteScore: Long) {
    BRACKET('(', ')', 3, 1),
    SQUARE('[', ']', 57, 2),
    CURLY('{', '}', 1197, 3),
    CROCODILE('<', '>', 25137, 4);

    companion object {
        fun getIllegalCharacterScores(character: Char): IllegalCharacterScores = values().first() { it.opening == character || it.closing == character }

        fun getIllegalScore(character: Char): Int = getIllegalCharacterScores(character).illegalScore

        fun getFullIncompleteScore(chunk: String): Long = chunk
            .toCharArray()
            .map { getIllegalCharacterScores(it).incompleteScore }
            .reduce() { acc, out -> acc * 5L + out}
    }

}

fun main() {
    val syntaxScoring = SyntaxScoring()
    val input = readFileByLine("src/main/resources/day10/input.txt")

    println("Part 1: " + syntaxScoring.calculateIllegalScore(input))
    println("Part 2: " + syntaxScoring.calculateIncompleteScore(input))
}