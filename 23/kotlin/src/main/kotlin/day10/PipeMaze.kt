package day10

import utils.Coordinate
import utils.Grid
import utils.parseGrid
import utils.readFileByLine

class PipeMaze {

    fun findTotalSteps(input: List<String>): Int {
        val grid = parseGrid(input)
        val startPoint = grid.coordinates.find { it.value == 'S' }!!

        return traverseGrid(grid, setOf(), setOf(startPoint)).size / 2
    }

    private tailrec fun traverseGrid(grid: Grid, visited: Set<Coordinate>, toVisit: Set<Coordinate>): Set<Coordinate> {
        if (toVisit.isEmpty()) return visited

        val current = toVisit.elementAt(0)
        val newVisited = visited + current
        val adjacent = getValidDirections(current, grid.getAdjacentCoords(current))
            .filterNot { coord -> newVisited.any { it.y == coord.y && it.x == coord.x } }

        return traverseGrid(grid, newVisited, (toVisit - current + adjacent).toSet())
    }

    private fun getValidDirections(current: Coordinate, adjacent: List<Coordinate>): List<Coordinate> =
        when(current.value) {
            '|' -> adjacent.filter { it.y == current.y - 1 || it.y == current.y + 1 }
            '-' -> adjacent.filter { it.x == current.x - 1 || it.x == current.x + 1 }
            'L' -> adjacent.filter { it.y == current.y - 1 || it.x == current.x + 1 }
            'J' -> adjacent.filter { it.y == current.y - 1 || it.x == current.x - 1 }
            '7' -> adjacent.filter { it.y == current.y + 1 || it.x == current.x - 1 }
            'F' -> adjacent.filter { it.y == current.y + 1 || it.x == current.x + 1 }
            'S' -> adjacent
            else -> emptyList()
        }.filterNot { it.value == '.' }
}

fun main() {
    val input = readFileByLine("src/main/resources/day10/input.txt")
    val pipeMaze = PipeMaze()

    println("Part 1: " + pipeMaze.findTotalSteps(input))
//    println("Part 2: " + pipeMaze.(input))
}