package day09

import utils.readFileByLine

class SmokeBasin {
    fun calculateLowPointRisk(input: List<String>): Int {
        val heatmap: List<Coordinate> = parseInput(input)
        return getLowPoints(heatmap).sumOf { it.value + 1 }
    }

    fun getLowPoints(heatmap: List<Coordinate>): MutableList<Coordinate> {
        return mutableListOf<Coordinate>().apply {
            heatmap.forEach { current ->
                if(current.getAdjacentCoordinates(heatmap).all { it.value > current.value })
                    this.add(current)
            }
        }
    }

    fun calculateLargestBasins(input: List<String>): Int {
        val heatmap: List<Coordinate> = parseInput(input)
        val basins: MutableList<Int> = mutableListOf()
        val basinStarts: MutableList<Coordinate> = getLowPoints(heatmap)

        basinStarts.forEach {
            basins.add(getBasinSize(heatmap, it))
        }

        return basins.sorted().takeLast(3).reduce { acc, i -> acc * i }
    }

    fun getBasinSize(heatmap: List<Coordinate>, startPoint: Coordinate): Int {
        var basinSize = 1
        var evaluating: MutableList<Coordinate> = mutableListOf(startPoint)
        val visited: MutableList<Coordinate> = mutableListOf()

        while(evaluating.size > 0) {
            val tempBasins: MutableList<Coordinate> = mutableListOf()

            evaluating.forEach {
                it.getAdjacentCoordinates(heatmap).forEach { coord ->
                    if(coord.value != 9 && coord !in visited && coord !in tempBasins) {
                        basinSize++
                        tempBasins.add(coord)
                    }
                }
            }

            visited.addAll(evaluating)
            evaluating.clear()
            evaluating = tempBasins
        }

        return basinSize
    }

    fun parseInput(input: List<String>): List<Coordinate> {
        return input.mapIndexed() { y, it ->
            it.toCharArray().mapIndexed { x, value ->
                Coordinate(x,input.size-1-y,value.toString().toInt())
            }
        }.flatten()
    }
}

data class Coordinate(val x: Int, val y: Int, val value: Int) {
    fun getAdjacentCoordinates(heatmap: List<Coordinate>): MutableList<Coordinate> {
        return mutableListOf<Coordinate>().apply {
            heatmap.find { x - 1 == it.x && y == it.y }?.let { this.add(it) }
            heatmap.find { x + 1 == it.x && y == it.y }?.let { this.add(it) }
            heatmap.find { x == it.x && y - 1 == it.y }?.let { this.add(it) }
            heatmap.find { x == it.x && y + 1 == it.y }?.let { this.add(it) }
        }
    }

}

fun main() {
    val smokeBasin = SmokeBasin()
    val input = readFileByLine("src/main/resources/day09/input.txt")

    println("Part 1: " + smokeBasin.calculateLowPointRisk(input))
    println("Part 2: " + smokeBasin.calculateLargestBasins(input))
}


