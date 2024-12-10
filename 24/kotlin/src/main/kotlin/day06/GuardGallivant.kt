package day06

import utils.parseGrid
import utils.readFileByLine

class GuardGallivant {
    fun part1(input: List<String>): Int {
        val grid = parseGrid(input)

        return 1
    }

    fun part2(input: List<String>): Int = 1
}

fun main() {
    val locations = readFileByLine("src/main/resources/day05/input.txt")
    val guardGallivant = GuardGallivant()

    println("Part 1: " + guardGallivant.part1(locations))
    println("Part 2: " + guardGallivant.part2(locations))
}