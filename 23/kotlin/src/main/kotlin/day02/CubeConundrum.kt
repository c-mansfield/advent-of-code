package day02

import utils.readFileByLine

data class Game(val id: Int, val rounds: List<Round>)
data class Round(val blue: Int, val red: Int, val green: Int)

class CubeConundrum {

    fun sumPossibleGames(input: List<String>): Int {
        return parseGames(input)
            .filter { c -> c.rounds.all { it.blue <= 14 && it.red <= 12 && it.green <= 13 } }
            .sumOf { it.id }
    }

    fun sumPowerGames(input: List<String>): Int {
        return parseGames(input)
            .map { c -> c.rounds.maxOf { it.blue }  * c.rounds.maxOf { it.green } * c.rounds.maxOf { it.red }}
            .sumOf { it }
    }

    private fun parseGames(input: List<String>): List<Game> {
        return input
            .map { it.split(": ")}
            .map { (game, sets) -> Game(game.split(" ")[1].toInt(), parseRounds(sets)) }
    }

    private fun parseRounds(game: String): List<Round> {
        return game.split("; ")
            .map { it.split(", ") }
            .map { Round(it.getColourTotal("blue"), it.getColourTotal("red"), it.getColourTotal("green")) }
    }

    private fun List<String>.getColourTotal(colour: String): Int = find { it.contains(colour) }?.split(" ")?.get(0)?.toInt() ?: 0
}

fun main() {
    val input = readFileByLine("src/main/resources/day02/input.txt")
    val codeConundrum = CubeConundrum();

    println("Part 1: " + codeConundrum.sumPossibleGames(input))
    println("Part 2: " + codeConundrum.sumPowerGames(input))

}