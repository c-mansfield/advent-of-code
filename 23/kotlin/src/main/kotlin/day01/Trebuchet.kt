package day01

import utils.readFileByLine

class Trebuchet {
    private val numbers = mapOf(
        "zero" to "0",
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9"
    )

    fun findCalibration(document: List<String>): Int = document
            .map { it.filter { line -> line.isDigit() } }
            .sumOf { getLineCalibration(it) }

    fun findCalibrationWithWrittenLetters(document: List<String>): Int = document
           .map { replaceWrittenLetters(it) }
           .sumOf { getLineCalibration(it) }

    private fun getLineCalibration(line: String) = "${line.first()}${line.last()}".toInt()

    private fun replaceWrittenLetters(calibration: String): String = numbers.keys
            .filter { calibration.contains(it) }
            .fold(calibration) { acc, s -> acc.replace(s, "${s.first()}${numbers[s].toString()}${s.last()}") }
            .filter { it.isDigit() }
}

fun main() {
    val document = readFileByLine("src/main/resources/day01/input.txt")
    val trebuchet = Trebuchet()

    println("Part 1: " + trebuchet.findCalibration(document))
    println("Part 2: " + trebuchet.findCalibrationWithWrittenLetters(document))
}