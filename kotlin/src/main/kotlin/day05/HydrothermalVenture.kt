package day05

import utils.readFileByLine

class HydrothermalVenture {
    fun findOverlaps(input: List<String>, part: Int): Int {
        var lines: MutableList<Line> = parseLines(input).toMutableList()
        var allLinePoints: MutableList<Pair<Int,Int>> = mutableListOf()

        lines.forEach {
            if(part == 2 || (it.p1.first == it.p2.first || it.p1.second == it.p2.second))
                allLinePoints.addAll(it.points)
        }

        return countOverlaps(allLinePoints)
    }

    fun countOverlaps(allLinePoints: List<Pair<Int,Int>>): Int {
        return allLinePoints
            .groupingBy { it }
            .eachCount()
            .filter { it.value > 1 }.values
            .size
    }

    fun parseLines(input: List<String>): List<Line> {
        var lines: MutableList<Line> = mutableListOf()

        input.forEach {
            var coords: List<String> = it.split(" -> ")
            var p1: List<String> = coords[0].split(",")
            var p2: List<String> = coords[1].split(",")

            lines.add(Line(Pair(p1[0].toInt(), p1[1].toInt()), Pair(p2[0].toInt(), p2[1].toInt())))
        }

        return lines
    }
}

data class Line(val p1: Pair<Int, Int>, val p2: Pair<Int, Int>) {
    val points: List<Pair<Int, Int>> = getLinePoints()

    fun getLinePoints(): List<Pair<Int, Int>> {
        val linePoints: MutableList<Pair<Int, Int>> = mutableListOf()
        val xDistance: Int = Math.max(p1.first, p2.first) - Math.min(p1.first, p2.first)
        val yDistance: Int = Math.max(p1.second, p2.second) - Math.min(p1.second, p2.second)
        val length: Int = (if(xDistance > yDistance) xDistance else yDistance) + 1

        val xValues: List<Int> = getAxisPoints(p1.first, p2.first, length)
        val yValues: List<Int> = getAxisPoints(p1.second, p2.second, length)

        for(i in xValues.indices)
            linePoints.add(Pair(xValues[i], yValues[i]))

        return linePoints
    }

    fun getAxisPoints(p1AxisValue: Int, p2AxisValue: Int, length: Int): List<Int> {
        val axisPoints: MutableList<Int> = determineAxisDirection(p1AxisValue, p2AxisValue)

        return if(axisPoints.size > 1) axisPoints else List(length) { axisPoints[0] }
    }

    private fun determineAxisDirection(p1AxisValue: Int, p2AxisValue: Int): MutableList<Int> {
        if(p1AxisValue < p2AxisValue)
            return (p1AxisValue..p2AxisValue).toMutableList()

        return (p1AxisValue downTo p2AxisValue).toMutableList()
    }
}

fun main() {
    val hydrothermalVenture = HydrothermalVenture()
    val hvInput = readFileByLine("src/main/resources/day05/input.txt")

    println("Part 1: " + hydrothermalVenture.findOverlaps(hvInput, 1))
    println("Part 2: " + hydrothermalVenture.findOverlaps(hvInput, 2))
}

