package day02

import java.io.File
import kotlin.math.abs

data class Report(val level: List<Int>) {

    fun isSafe(): Boolean = (level.sorted() == level || level.sortedDescending() == level) && isBounded()

    private fun isBounded(): Boolean = level.zipWithNext().all { (a, b) -> abs(a - b) in 1..3 }
}

class RedNosedReports {
    fun part1(input: List<String>): Int = parseReports(input).count { it.isSafe() }

    fun part2(input: List<String>): Int = parseReports(input)
        .map { report ->
            report.level.indices.any { index ->
                val subList = report.level.filterIndexed { i, _ -> i != index }
                Report(subList).isSafe()
            }
        }.count { it }

    private fun parseReports(input: List<String>): List<Report> = input.map { Report(it.split(" ").map { it.toInt() }) }
}

fun main() {
    val locations = readFileByLine("src/main/resources/day02/input.txt")
    val redNosedReports = RedNosedReports()

    println("Part 1: " + redNosedReports.part1(locations))
    println("Part 2: " + redNosedReports.part2(locations))
}

fun readFileByLine(filePath: String): List<String> = File(filePath).readLines()