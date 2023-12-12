package day11

import utils.Coordinate
import utils.parseGrid
import utils.readFileByLine
import kotlin.math.abs

class CosmicExpansion {
    fun sumShortestPaths(input: List<String>, expansion: Int): Long {
        val galaxies = parseGalaxies(input, if(expansion > 1) expansion - 1 else expansion)
        return generatePairs(galaxies).sumOf { distanceBetweenPoints(it) }
    }

    private fun distanceBetweenPoints(points: Pair<Coordinate, Coordinate>): Long =
        (abs(points.first.x - points.second.x) + abs(points.first.y - points.second.y)).toLong()

    private fun generatePairs(galaxies: List<Coordinate>): List<Pair<Coordinate, Coordinate>> =
        galaxies.flatMapIndexed { index, first -> galaxies.drop(index + 1).map { second -> Pair(first, second) } }

    private fun parseGalaxies(input: List<String>, expansion: Int): List<Coordinate> {
        val grid = parseGrid(input)

        val emptySpaces = grid.coordinates.filter { it.value == '.' }
        val maxX = grid.coordinates.maxBy { it.x }.x
        val maxY = grid.coordinates.maxBy { it.y }.y

        val emptyColumns = (0 .. maxX).filter { x -> emptySpaces.filter { it.x == x }.size == maxY + 1 }
        val emptyRows = (0 .. maxY).filter { y -> emptySpaces.filter { it.y == y }.size == maxX + 1 }

        return grid.coordinates.filter { it.value == '#' }
            .map { coordinate ->
                val newX = coordinate.x + (expansion * emptyColumns.count { it < coordinate.x })
                val newY = coordinate.y + (expansion * emptyRows.count { it < coordinate.y })
                Coordinate(newX, newY, coordinate.value)
            }
    }
}

fun main() {
    val input = readFileByLine("src/main/resources/day11/input.txt")
    val cosmicExpansion = CosmicExpansion()

    println("Part 1: " + cosmicExpansion.sumShortestPaths(input, 1))
    println("Part 2: " + cosmicExpansion.sumShortestPaths(input, 1000000))
}