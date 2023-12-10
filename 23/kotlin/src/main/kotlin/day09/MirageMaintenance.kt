package day09

import utils.readFileByLine

class MirageMaintenance {
    fun sumExtrapolatedValues(inputs: List<String>): Int = parseInput(inputs).sumOf {
            val differences = traverse(listOf(it)).reversed()
            differences.subList(1, differences.size).fold(differences[0][0]) { acc, element -> element[element.size - 1] + acc }
        }

    fun sumBackwardsExtrapolatedValues(inputs: List<String>): Int = parseInput(inputs).sumOf {
        val differences = traverse(listOf(it)).reversed()
        differences.subList(1, differences.size).fold(differences[0][0]) { acc, element -> element[0] - acc }
    }

    private tailrec fun traverse(current: List<List<Int>>): List<List<Int>> {
        if (current[current.size - 1].all { it == current[current.size - 1][0] }) return current

        val next: List<Int?> = current[current.size - 1].mapIndexed { index, element ->
            current[current.size - 1].getOrNull(index + 1).let { it?.minus(element) }
        }

        return traverse((current + listOf(next.filterNotNull())))
    }

    private fun parseInput(inputs: List<String>): List<List<Int>> = inputs.map { it.split(" ").map { it.toInt() } }
}

fun main() {
    val input = readFileByLine("src/main/resources/day09/input.txt")
    val mirageMaintenance = MirageMaintenance()

    println("Part 1: " + mirageMaintenance.sumExtrapolatedValues(input))
    println("Part 2: " + mirageMaintenance.sumBackwardsExtrapolatedValues(input))
}