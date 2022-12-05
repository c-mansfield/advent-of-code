package day09

import utils.*

class SmokeBasin {
    fun calculateLowPointRisk(input: List<String>): Int {
        val heatmap: Grid = parseGrid(input)
        return getLowPoints(heatmap).sumOf { it.value + 1 }
    }

    fun getLowPoints(heatmap: Grid): MutableList<Coordinate> {
        return mutableListOf<Coordinate>().apply {
            heatmap.coordinates.forEach { current ->
                if(heatmap.getAdjacentCoords(current, false).all { it.value > current.value })
                    this.add(current)
            }
        }
    }

    fun calculateLargestBasins(input: List<String>): Int {
        val heatmap: Grid = parseGrid(input)
        val basins: MutableList<Int> = mutableListOf()
        val basinStarts: MutableList<Coordinate> = getLowPoints(heatmap)

        basinStarts.forEach {
            basins.add(getBasinSize(heatmap, it))
        }

        return basins.sorted().takeLast(3).reduce { acc, i -> acc * i }
    }

    fun getBasinSize(heatmap: Grid, startPoint: Coordinate): Int {
        var basinSize = 1
        var evaluating: MutableList<Coordinate> = mutableListOf(startPoint)
        val visited: MutableList<Coordinate> = mutableListOf()

        while(evaluating.size > 0) {
            val tempBasins: MutableList<Coordinate> = mutableListOf()

            evaluating.forEach {
                heatmap.getAdjacentCoords(it, false).forEach { coordinate ->
                    if(coordinate.value != 9 && coordinate !in visited && coordinate !in tempBasins) {
                        basinSize++
                        tempBasins.add(coordinate)
                    }
                }
            }

            visited.addAll(evaluating)
            evaluating.clear()
            evaluating = tempBasins
        }

        return basinSize
    }
}

fun main() {
    val smokeBasin = SmokeBasin()
    val input = readFileByLine("src/main/resources/day09/input.txt")

    println("Part 1: " + smokeBasin.calculateLowPointRisk(input))
    println("Part 2: " + smokeBasin.calculateLargestBasins(input))
}


