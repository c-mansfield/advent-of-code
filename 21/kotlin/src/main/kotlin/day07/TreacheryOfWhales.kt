package day07

import utils.readFileByLine
import kotlin.math.abs

class TreacheryOfWhales {
    fun calculateFuel(input: List<String>): Int {
        val initialFuel: List<Int> = input[0].split(",").map { it.toInt() }
        val fuelMedian: Int = findMedian(initialFuel)

        return initialFuel.fold(0) {
                sum, element -> sum + abs(element - fuelMedian)
        }
    }

    fun calculateFuelWithRate(input: List<String>): Int? {
        val initialFuel: List<Int> = input[0].split(",").map { it.toInt() }
        val bestRate: MutableList<Int> = mutableListOf()

        for(i in initialFuel.minOrNull()!! until initialFuel.maxOrNull()!!) {
            bestRate.add(initialFuel.fold(0) { sum, element ->
                sum + findBurnRate(abs(element - i))
            })
        }

        return bestRate.minOrNull()
    }

    fun findBurnRate(number: Int): Int = ((number * number) + number) / 2

    fun findMedian(numbers: List<Int>): Int = (numbers.sorted()[numbers.size / 2] + numbers.sorted()[(numbers.size - 1) / 2]) / 2
}

fun main() {
    val treacheryOfWhales = TreacheryOfWhales()
    val input = readFileByLine("src/main/resources/day07/input.txt")

    println("Part 1: " + treacheryOfWhales.calculateFuel(input))
    println("Part 2: " + treacheryOfWhales.calculateFuelWithRate(input))
}

