package day11

import utils.*

class DumboOctopus {
    private var flashes: Int = 0

    fun findTotalFlashesAfterDays(input: List<String>, days: Int): Int {
        var grid: Grid = parseGrid(input)

        repeat(days) {
            grid = increaseEnergyLevels(grid)
            grid.coordinates.forEach { grid = checkOctopusFlashing(grid, it) }
        }

        return flashes
    }

    fun findWhenAllFlash(input: List<String>): Int {
        var grid: Grid = parseGrid(input)
        var step = 0

        while(true) {
            step++
            grid = increaseEnergyLevels(grid)
            grid.coordinates.forEach { grid = checkOctopusFlashing(grid, it) }

            if(checkAllFlashing(grid)) break
        }

        return step
    }

    private fun increaseEnergyLevels(grid: Grid): Grid = Grid(grid.coordinates.map { it.copy(value = it.value + 1) }.toMutableList())

    private fun checkOctopusFlashing(grid: Grid, coordinate: Coordinate): Grid {
        var newGrid = grid

        if(coordinate.value > 9) {
            flashes++
            newGrid.getGridCoordinate(coordinate.x, coordinate.y)?.value = 0

            newGrid.getAdjacentCoords(coordinate, true).forEach {
                newGrid.getGridCoordinate(it.x, it.y)?.let { adjacent ->
                    if (adjacent.value != 0) adjacent.value++
                    newGrid = checkOctopusFlashing(newGrid, adjacent)
                }
            }
        }

        return newGrid
    }

    private fun checkAllFlashing(grid: Grid) = grid.coordinates.count { it.value == 0 } == grid.coordinates.size
}

fun main() {
    val dumboOctopus = DumboOctopus()
    val input = readFileByLine("src/main/resources/day11/input.txt")

    println("Part 1: " + dumboOctopus.findTotalFlashesAfterDays(input, 100))
    println("Part 2: " + dumboOctopus.findWhenAllFlash(input))
}