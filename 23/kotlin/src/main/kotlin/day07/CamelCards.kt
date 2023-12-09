package day07

import utils.readFileByLine

enum class Card(val symbol: Char, val score: Char) {
    ACE('A', 'A'),
    KING('K', 'B'),
    QUEEN('Q', 'C'),
    JACK('J', 'D'),
    TEN('T', 'E'),
    NINE('9', 'F'),
    EIGHT('8', 'G'),
    SEVEN('7', 'H'),
    SIX('6', 'I'),
    FIVE('5', 'J'),
    FOUR('4', 'K'),
    THREE('3', 'L'),
    TWO('2', 'M'),
    JOKER('J', 'N');

    companion object {
        fun findCardScore(symbol: Char, allowJokers: Boolean): Char = if (allowJokers && symbol == 'J') JOKER.score else entries.first { it.symbol == symbol }.score
    }
}

data class Hand(val cards: String, val bid: Int, val allowJokers: Boolean) {
    fun findHandScore(): Int {
        val cardsToEvaluate = if (allowJokers) getJokerCards() else cards
        val sum = cardsToEvaluate.toList()
            .distinct()
            .map { char -> cardsToEvaluate.count { it == char } }

        return when {
            sum.any { it == 5 } -> 1
            sum.any { it == 4 } -> 2
            sum.any { it == 3 } && sum.any { it == 2 } -> 3
            sum.any { it == 3 } -> 4
            sum.filter { it == 2 }.size == 2 -> 5
            sum.any { it == 2 } -> 6
            else -> 7
        }
    }

    fun findSymbolScore(): String = cards.toList().map { Card.findCardScore(it, allowJokers) }.toString()

    private fun getJokerCards(): String {
        val cardCounts = cards.replace("J", "").toList().groupingBy { it }.eachCount()
        return if (cardCounts.isNotEmpty()) cards.replace('J', cardCounts.maxBy { it.value }.key) else cards
    }
}

class CamelCards {
    fun sumTotalWinnings(inputs: List<String>, allowJokers: Boolean): Int = parseHands(inputs, allowJokers)
        .sortedWith(compareBy<Hand> { it.findHandScore() }.thenBy { it.findSymbolScore() })
        .reversed()
        .foldIndexed(0) { index, acc, hand -> acc + (hand.bid * (index + 1)) }

    private fun parseHands(inputs: List<String>, allowJokers: Boolean): List<Hand> = inputs.map {
        val splitLine = it.split(" ")
        Hand(splitLine[0], splitLine[1].toInt(), allowJokers)
    }
}

fun main() {
    val input = readFileByLine("src/main/resources/day07/input.txt")
    val camelCards = CamelCards()

    println("Part 1: " + camelCards.sumTotalWinnings(input, false))
    println("Part 2: " + camelCards.sumTotalWinnings(input, true))
}