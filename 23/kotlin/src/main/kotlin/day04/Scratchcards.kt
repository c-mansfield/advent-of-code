package day04

import utils.readFileByLine
import kotlin.math.pow

data class Card(val id: Int, val winningNumbers: List<Int>, val cardNumbers: List<Int>) {
    fun matchingNumbers(): Int = cardNumbers.intersect(winningNumbers.toSet()).size

    fun score(): Int = matchingNumbers().let { if (it != 0) 2.0.pow(it - 1).toInt() else 0 }
}

class Scratchcards {
    fun sumWinningCarPoints(input: List<String>): Int = parseCards(input).sumOf { it.score() }

    fun sumWinningScratchcards(input: List<String>): Int {
        val cards = parseCards(input)
        var items: MutableList<Int> = cards.map { 1 }.toMutableList()

        cards.forEachIndexed { index, card ->
            for (i in index + 1..index + card.matchingNumbers()) {
                items[i] += items[index]
            }
        }

        return items.sum()
    }

    private fun parseCards(input: List<String>): List<Card> = input.map {
        val splitCard = it.split(":", "|")
        Card(
            splitCard[0].split(" +".toRegex())[1].toInt(),
            splitCard[1].trim().split(" +".toRegex()).map { it.toInt() },
            splitCard[2].trim().split(" +".toRegex()).map { it.toInt() }
        )
    }
}

fun main() {
    val input = readFileByLine("src/main/resources/day04/input.txt")
    val scratchcards =  Scratchcards();

    println("Part 1: " + scratchcards.sumWinningCarPoints(input))
    println("Part 2: " + scratchcards.sumWinningScratchcards(input))
}