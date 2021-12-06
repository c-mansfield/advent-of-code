package day01

import utils.readFileByLine

class SonarSweep {
    fun depthIncreases(depths: List<Int>, windowSize: Int): Int {
        return depths
            .windowed(windowSize, 1, transform = List<Int>::sum)
            .zipWithNext()
            .count{ (previous, current) -> previous < current }
    }
}

fun main() {
    val depths = readFileByLine("src/main/resources/day01/input.txt").map { it.toInt() }
    val sonarSweep = SonarSweep()

    println("Part 1: " + sonarSweep.depthIncreases(depths, 1))
    println("Part 2: " + sonarSweep.depthIncreases(depths, 3))
}