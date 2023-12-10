package day08

import utils.readFileByLine

data class Wasteland(val instructions: List<Char>, val nodes: Map<String, Pair<String, String>>) {
    tailrec fun traverse(current: Set<String>, direction: Int, count: Int): Int {
        if (current.all { it.endsWith('Z') }) {
            return count
        }

        val next = current.map {
            val currentNode = nodes.getValue(it)
            if (instructions[direction] == 'L') currentNode.first else currentNode.second
        }.toSet()
        return traverse(next, (direction + 1) % instructions.size, count + 1)
    }
}

class HauntedWasteland {
    fun findStepsTillEnd(input: List<String>, start: String): Int {
        val wasteland = parseWasteland(input)
        val startNodes = wasteland.nodes.filter { it.key.endsWith(start) }.keys

        return wasteland.traverse(startNodes, 0, 0)
    }

    private fun parseWasteland(input: List<String>): Wasteland {
        val nodes = input.subList(2, input.size).associate {
            val splitLine = it.split(" = ")
            val direction = splitLine[1].removeSurrounding("(", ")").split(", ")
            splitLine[0] to Pair(direction[0], direction[1])
        }
        return Wasteland(input[0].toList(), nodes)
    }
}

fun main() {
    val input = readFileByLine("src/main/resources/day08/input.txt")
    val hauntedWasteland = HauntedWasteland()

    println("Part 1: " + hauntedWasteland.findStepsTillEnd(input, "AAA"))
    println("Part 2: " + hauntedWasteland.findStepsTillEnd(input, "A"))
}