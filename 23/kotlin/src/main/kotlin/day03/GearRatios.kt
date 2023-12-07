package day03

import utils.*

class GearRatios {
    fun sumPartNumbers(input: List<String>): Int {
        val grid = parseGrid(input)

        return grid.coordinates
             .filter { it.value.isDigit() && grid.getAdjacentCoords(it).any { it.value != '.' && !it.value.isDigit() } }
             .map { findNumberCoordinates(grid, it) }
             .distinct()
             .sumOf { it.value }
    }

    fun sumGearRatios(input: List<String>): Int {
        val grid = parseGrid(input)

        return grid.coordinates
            .filter { it.value == '*' }
            .map {
                grid.getAdjacentCoords(it).filter { it.value.isDigit() }.map { findNumberCoordinates(grid, it) }.distinct()
            }
            .filter { it.size == 2 }
            .sumOf { it.fold(1) { sum, element -> sum * element.value }.toInt() }
    }

    private fun findNumberCoordinates(grid: Grid, coordinate: Coordinate): Line {
        val numberXCoordinates = grid.coordinates
            .filter { it.y == coordinate.y }
            .fold(Pair(coordinate.x, coordinate.x)) { acc, _ ->
                when {
                    grid.getGridCoordinate(acc.first - 1, coordinate.y)?.value?.isDigit() == true -> Pair(acc.first - 1, acc.second)
                    grid.getGridCoordinate(acc.second  + 1, coordinate.y)?.value?.isDigit() == true -> Pair(acc.first, acc.second + 1)
                    else -> acc
                }
            }

        return Line(
            numberXCoordinates.first, numberXCoordinates.second, coordinate.y, coordinate.y,
            (numberXCoordinates.first..numberXCoordinates.second).toList().fold("") { acc, i -> acc + grid.getGridCoordinate(i, coordinate.y)?.value }.toInt()
        )
    }
}

fun main() {
    val input = readFileByLine("src/main/resources/day03/input.txt")
    val gearRatios = GearRatios();

    println("Part 1: " + gearRatios.sumPartNumbers(input))
    println("Part 2: " + gearRatios.sumGearRatios(input))
}