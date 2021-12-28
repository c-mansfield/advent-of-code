package day03

import utils.readFileByLine

class BinaryDiagnostic {
    fun powerConsumption(report: List<String>, part: Int): Int {
        val gamma = findMostCommon(report, '1')
        val epsilon = invertBinary(gamma)

        if(part == 1)
            return gamma.toInt(2) * epsilon.toInt(2)

        return getLifeSupportRating(report)
    }

    fun getLifeSupportRating(report: List<String>): Int {
        val oxygen = determineRatings(report, '1')
        val co2 = determineRatings(report, '0')

        return oxygen.toInt(2) * co2.toInt(2)
    }

    fun determineRatings(report: List<String>, ratingType: Char, index: Int = 0): String {
        var outputRating: List<String> = report

        if(report.size == 1) return report[0]

        outputRating = outputRating.filter { it[index] == findRowMostCommonBit(report, index, ratingType) }

        return determineRatings(outputRating, ratingType, index + 1)
    }

    private fun findMostCommon(report: List<String>, bit: Char): String {
        var mostCommon = ""

        for(i in 0 until report[0].length) {
            mostCommon += findRowMostCommonBit(report, i, bit)
        }

        return mostCommon
    }

    private fun findRowMostCommonBit(row: List<String>, index: Int, bit: Char): Char {
        val counts: Pair<Int, Int> = Pair(row.map { it[index] }.count { it == '0' }, row.map { it[index] }.count { it == '1' })

        return when(bit) {
            '1' -> if (counts.first <= counts.second) '1' else '0'
            '0'-> if (counts.first <= counts.second) '0' else '1'
            else -> '/'
        }
    }

    private fun invertBinary(binary: String): String = binary.map { if (it == '0') "1" else "0" }.joinToString("")
}

fun main() {
    val report = readFileByLine("src/main/resources/day03/input.txt")
    val binaryDiagnostic = BinaryDiagnostic()

    println("Part 1: " + binaryDiagnostic.powerConsumption(report, 1))
    println("Part 2: " + binaryDiagnostic.powerConsumption(report, 2))
}