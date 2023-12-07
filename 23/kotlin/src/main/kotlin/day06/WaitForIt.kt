package day06

import utils.readFileByLine

data class Race(val time: Long, val distance: Long) {
    fun secondsAllowedInRace(): List<Long> = (1..time).toList().filter { (time - it) * it > distance }
}

class WaitForIt {
    fun sumWaysToBeatRecord(inputs: List<String>): Long = parseRaces(inputs).fold(1) { acc, race -> acc * race.secondsAllowedInRace().size }

    fun findSingleRaceRecord(input: List<String>): Long {
        val allowedRaceSeconds = Race(
            input[0].split(":")[1].replace(" ", "").toLong(),
            input[1].split(":")[1].replace(" ", "").toLong(),
        ).secondsAllowedInRace()

        return allowedRaceSeconds.max() - allowedRaceSeconds.min() + 1
    }

    private fun parseRaces(input: List<String>): List<Race> {
        val times = input[0].split(":")[1].trim().split(" ").filter { it.isNotBlank() }.map { it.toLong() }
        val distance = input[1].split(":")[1].trim().split(" ").filter { it.isNotBlank() }.map { it.toLong() }

        return times.mapIndexed { index, s -> Race(s, distance[index]) }
    }
}

fun main() {
    val input = readFileByLine("src/main/resources/day06/input.txt")
    val waitForIt = WaitForIt();

    println("Part 1: " + waitForIt.sumWaysToBeatRecord(input))
    println("Part 2: " + waitForIt.findSingleRaceRecord(input))
}