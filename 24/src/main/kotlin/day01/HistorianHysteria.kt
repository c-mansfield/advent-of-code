package day01

import java.io.File
import kotlin.math.abs

class HistorianHysteria {
    fun part1(input: List<String>): Int = parseLocations(input)
        .fold(0) { acc, next -> acc + abs(next.first - next.second) }

    fun part2(input: List<String>): Int {
        val locations = parseLocations(input)
        val secondLocations = locations.map { second -> second.second }

        return locations.sumOf {
            it.first * (secondLocations.count { second -> second == it.first })
        }
    }

    private fun parseLocations(input: List<String>): List<Pair<Int, Int>> {
        val leftLocations = input.map { it.split("   ")[0] }.sorted()
        val rightLocations = input.map { it.split("   ")[1] }.sorted()

        return leftLocations.zip(rightLocations).map { (left, right) ->
            Pair(left.toInt(), right.toInt())
        }
    }
}

fun main() {
    val locations = readFileByLine("src/main/resources/day01/input.txt")
    val historianHysteria = HistorianHysteria()

    println("Part 1: " + historianHysteria.part1(locations))
    println("Part 2: " + historianHysteria.part2(locations))
}

fun readFileByLine(filePath: String): List<String> = File(filePath).readLines()