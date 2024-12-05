package day03

import utils.readFileByLine

data class Memory(val memory: String) {
    fun multiply(): Int = Regex("mul\\((\\d+),(\\d+)\\)")
        .findAll(memory)
        .sumOf { match ->
            val (first, second) = match.destructured
            first.toInt() * second.toInt()
        }
}

class MullItOver {
    fun part1(input: String): Int = Memory(input).multiply()

    fun part2(input: String): Int = Memory(cleanMemory(input)).multiply()

    private fun cleanMemory(input: String): String = Regex("don't\\(\\).*?do\\(\\)")
        .replace(input, "")
        .split("don't()")[0]
}

fun main() {
    val locations = readFileByLine("src/main/resources/day03/input.txt").joinToString { it }
    val mullItOver = MullItOver()

    println("Part 1: " + mullItOver.part1(locations))
    println("Part 2: " + mullItOver.part2(locations))
}