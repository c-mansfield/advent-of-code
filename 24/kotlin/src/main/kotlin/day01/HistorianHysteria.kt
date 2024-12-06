package day01

import kotlin.math.abs
import utils.readFileByLine

data class Location(val left: Int, val right: Int)

class HistorianHysteria {
    fun part1(input: List<String>): Int = parseLocations(input)
        .fold(0) { acc, next -> acc + abs(next.left - next.right) }

    fun part2(input: List<String>): Int {
        val locations = parseLocations(input)
        val secondLocations = locations.map { second -> second.right }

        return locations.sumOf {
            it.left * (secondLocations.count { second -> second == it.left })
        }
    }

    private fun parseLocations(input: List<String>): List<Location> {
        val leftLocations = input.map { it.split("   ")[0] }.sorted()
        val rightLocations = input.map { it.split("   ")[1] }.sorted()

        return leftLocations.zip(rightLocations).map { (left, right) ->
            Location(left.toInt(), right.toInt())
        }
    }
}

fun main() {
    val locations = readFileByLine("src/main/resources/day01/input.txt")
    val historianHysteria = HistorianHysteria()

    println("Part 1: " + historianHysteria.part1(locations))
    println("Part 2: " + historianHysteria.part2(locations))
}