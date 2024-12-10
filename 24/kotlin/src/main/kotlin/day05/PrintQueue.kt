package day05

import utils.readFileByLine
import java.util.Collections.swap

typealias Rule = Pair<Int, Int>

data class Update(val pages: List<Int>) {
    fun grouped(): List<Pair<Int, Int>> = pages.indices.flatMap { i ->
        pages.drop(i + 1).map { pages[i] to it }
    }

    fun middle(): Int = pages[pages.size / 2]
}

data class SafetyManual(val rules: List<Rule>, val updates: List<Update>) {
    fun isValid(update: Update): Boolean = update.grouped().all { it in rules }
}

class PrintQueue {
    fun part1(input: List<String>): Int {
        val safetyManual = parseInput(input)
        return safetyManual.updates.sumOf { update -> if (safetyManual.isValid(update)) update.middle() else 0 }
    }

    fun part2(input: List<String>): Int {
        val safetyManual = parseInput(input)
        return safetyManual.updates.sumOf { update -> if (!safetyManual.isValid(update)) order(update, safetyManual.rules).middle() else 0 }
    }

    private fun order(update: Update, rules: List<Rule>): Update {
        tailrec fun orderRecursively(currentUpdate: Update): Update {
            val newUpdate = Update(currentUpdate.grouped().fold(currentUpdate.pages.toMutableList()) { acc, pair ->
                if (pair !in rules) {
                    swap(acc, acc.indexOf(pair.first), acc.indexOf(pair.second))
                    acc
                } else {
                    acc
                }
            })

            return if (currentUpdate == newUpdate) currentUpdate else orderRecursively(newUpdate)
        }

        return orderRecursively(update)
    }

    private fun parseInput(input: List<String>): SafetyManual {
        val splitIndex = input.indexOf("")
        val rules = input.subList(0, splitIndex).map {
            val (before, after) = it.split("|")
            Pair(before.toInt(), after.toInt())
        }
        val updates = input.subList(splitIndex + 1, input.size)
            .map { Update(it.split(",").map(String::toInt)) }

        return SafetyManual(rules, updates)
    }
}

fun main() {
    val locations = readFileByLine("src/main/resources/day05/input.txt")
    val printQueue = PrintQueue()

    println("Part 1: " + printQueue.part1(locations))
    println("Part 2: " + printQueue.part2(locations))
}