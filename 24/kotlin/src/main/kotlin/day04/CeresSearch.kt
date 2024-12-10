package day04

import utils.parseGrid
import utils.readFileByLine

class CeresSearch {
    fun part1(input: List<String>): Int {
        val grid = parseGrid(input)

        return grid.coordinates.filter { it.value == 'X' }
            .sumOf { xCoord ->
                grid.getAdjacentCoords(xCoord, true)
                    .filter { it.value == 'M' }
                    .map {
                        val xA = it.x - (xCoord.x - it.x)
                        val yA = it.y - (xCoord.y - it.y)
                        val xS = xA - (it.x - xA)
                        val yS = yA - (it.y - yA)

                        grid.getGridCoordinate(xA, yA)?.value == 'A' && grid.getGridCoordinate(xS, yS)?.value == 'S'
                    }.count { it }
        }
    }

    fun part2(input: List<String>): Int {
        val grid = parseGrid(input)

        return grid.coordinates.filter { it.value == 'A' }
            .map { xCoord ->
                val topLeft = grid.coordinates.find { xCoord.x + 1 == it.x && xCoord.y - 1 == it.y }
                val topRight = grid.coordinates.find { xCoord.x + 1 == it.x && xCoord.y + 1 == it.y }
                val bottomLeft = grid.coordinates.find { xCoord.x - 1 == it.x && xCoord.y - 1 == it.y }
                val bottomRight = grid.coordinates.find { xCoord.x - 1 == it.x && xCoord.y + 1 == it.y }

                ((topLeft?.value == 'S' && bottomRight?.value == 'M') || (topLeft?.value == 'M' && bottomRight?.value == 'S')) &&
                ((topRight?.value == 'S' && bottomLeft?.value == 'M') || (topRight?.value == 'M' && bottomLeft?.value == 'S'))
            }.count { it }
    }
}

fun main() {
    val locations = readFileByLine("src/main/resources/day04/input.txt")
    val ceresSearch = CeresSearch()

    println("Part 1: " + ceresSearch.part1(locations))
    println("Part 2: " + ceresSearch.part2(locations))
}