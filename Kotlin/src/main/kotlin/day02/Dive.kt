package day02

import utils.readFileByLine

class Dive {
    fun calculatePlannedCourse(commands: List<String>, part: Int): Int {
        var positions = Positions()

        commands
            .map{ it.split(" ") }
            .forEach() {
                when(part) {
                    1 -> positions.move(it[0], it[1].toInt())
                    2 -> positions.aim(it[0], it[1].toInt())
                }
            }

        return positions.calculateFinal()
    }
}

data class Positions(var depth: Int = 0, var horizontal: Int = 0, var aim: Int = 0) {
    fun calculateFinal(): Int {
        return horizontal * depth
    }

    fun move(direction: String, length: Int) {
        when (direction) {
            "forward" -> horizontal += length
            "down" -> depth += length
            "up" -> depth -= length
        }
    }

    fun aim(direction: String, length: Int) {
        when (direction) {
            "forward" -> {
                horizontal += length
                depth += aim * length
            }
            "down" -> aim += length
            "up" -> aim -= length
        }
    }
}

fun main() {
    var commands = readFileByLine("src/main/resources/day02/input.txt")
    val dive = Dive()

    println("Part 1: " + dive.calculatePlannedCourse(commands, 1))
    println("Part 2: " + dive.calculatePlannedCourse(commands, 2))
}
