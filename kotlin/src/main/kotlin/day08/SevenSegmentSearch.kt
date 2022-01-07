package day08

import utils.readFileByLine

class SevenSegmentSearch {
    fun countUniqueDigitInstances(input: List<String>): Int {
        return parseInput(input).sumOf { element -> element.outputValues.count { it.length in listOf(2, 4, 3, 7) } }
    }

    fun calculateOutput(input: List<String>): Int =  parseInput(input).sumOf { element -> findOutputValue(element) }

    private fun findOutputValue(segmentDisplay: SegmentDisplay): Int {
        val digits: MutableMap<String, Int> = matchSegmentToDigit(segmentDisplay)

        return segmentDisplay.outputValues
            .map { output -> digits[digits.keys.first { output.toCharArray().sorted() == it.toCharArray().sorted() }]}
            .joinToString("")
            .toInt()
    }

    fun matchSegmentToDigit(segmentDisplay: SegmentDisplay): MutableMap<String, Int> {
        val digits: MutableMap<String, Int> = mutableMapOf()
        val digitSegments6: MutableList<String> = segmentDisplay.uniqueSignals.filter { it.count() == 6 }.toMutableList()
        val digitSegments5: MutableList<String> = segmentDisplay.uniqueSignals.filter { it.count() == 5 }.toMutableList()

        for(i in listOf(1,4,7,8)) digits[findDigitSegments(segmentDisplay.uniqueSignals, i)[0]] = i

        // Segments of 6 lines
        val nineSegment: String = digitSegments6.first { it.filterNot { seg -> seg in findDigitSegments(segmentDisplay.uniqueSignals, 4)[0] }.length == 2 }
        digits[nineSegment] = 9
        digitSegments6.remove(nineSegment)

        val zeroSegment: String = digitSegments6.first { it.filterNot { seg -> seg in findDigitSegments(segmentDisplay.uniqueSignals, 7)[0] }.length == 3 }
        digits[zeroSegment] = 0
        digitSegments6.remove(zeroSegment)

        digits[digitSegments6.first()] = 6

        // Segments of 5 lines
        val threeSegment: String = digitSegments5.first { it.filterNot { seg -> seg in findDigitSegments(segmentDisplay.uniqueSignals, 1)[0] }.length == 3 }
        digits[threeSegment] = 3
        digitSegments5.remove(threeSegment)

        val twoSegment: String = digitSegments5.first { it.filterNot { seg -> seg in findDigitSegments(segmentDisplay.uniqueSignals, 4)[0] }.length == 3 }
        digits[twoSegment] = 2
        digitSegments5.remove(twoSegment)

        digits[digitSegments5.first()] = 5

        return digits
    }

    fun findDigitSegments(segments: List<String>, digit: Int): List<String> {
        return segments.filter { it.count() == DigitSegment.getDigitSegment(digit).segmentCount }
    }

    fun parseInput(input: List<String>): List<SegmentDisplay> {
        val segmentDisplays: MutableList<SegmentDisplay> = mutableListOf()

        return segmentDisplays.apply {
            input.forEach {
                val lineSplit: List<String> = it.split(" | ")
                this.add(SegmentDisplay(lineSplit[0].split(' '), lineSplit[1].split(' ')))
            }
        }
    }
}

data class SegmentDisplay(var uniqueSignals: List<String>, var outputValues: List<String>)

enum class DigitSegment(var digit: Int, var segmentCount: Int) {
    ZERO(0, 6),
    ONE(1, 2),
    TWO(2, 5),
    THREE(3, 5),
    FOUR(4, 4),
    FIVE(5, 5),
    SIX(6, 6),
    SEVEN(7, 3),
    EIGHT(8, 7),
    NINE(9, 6);

    companion object {
        fun getDigitSegment(digit: Int): DigitSegment = values().first() { it.digit == digit }
    }
}

fun main() {
    val sevenSegmentSearch = SevenSegmentSearch()
    val input = readFileByLine("src/main/resources/day08/input.txt")

    println("Part 1: " + sevenSegmentSearch.countUniqueDigitInstances(input))
    println("Part 2: " + sevenSegmentSearch.calculateOutput(input))
}