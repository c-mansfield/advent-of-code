package day05

import java.io.File


data class Map(val destinationRange: Long, val sourceRange: Long, val rangeLength: Long) {
    fun calculateSeedInRange(seed: Long): Long =
        if (seed in (sourceRange..sourceRange+rangeLength)) seed + (destinationRange - sourceRange) else seed
}

class FertilizerSeed {

    fun findLowestLocationNumber(input: List<String>): Long {
        val seeds = input[0].split(":")[1].trim().split(" ").map { it.toLong() }
        val maps = parseMaps(input.subList(1, input.size))

        return seeds
            .map { maps.fold(it) { acc, element -> element.map { map -> map.calculateSeedInRange(acc) }.find { value -> value != acc } ?: acc } }
            .min()
    }

    fun findLowestWithSeedRange(input: List<String>): Long {
        val maps = parseMaps(input.subList(1, input.size))
        val seeds = input[0].split(":")[1].trim().split(" ")
            .map { it.toLong() }
        var minValue = Long.MAX_VALUE

        for(i in 0..seeds.size-2 step 2) {
            for(z in seeds[i]..seeds[i] + seeds[i+1]) {
                val value = maps.fold(z) { acc, element -> element.map { map -> map.calculateSeedInRange(acc) }.find { value -> value != acc } ?: acc }

                if (value < minValue) {
                    minValue = value
                }
            }
        }
        return minValue
    }

    private fun parseMaps(maps: List<String>): List<List<Map>> {
        return maps.map {
            val splitLine = it.split("\n")
            splitLine.subList(1, splitLine.size).map {
                val splitValues = it.split(" ").map { it.toLong() }
                Map(splitValues[0], splitValues[1], splitValues[2])
            }
        }
    }
}

fun main() {
    val input = File("src/main/resources/day05/input.txt").readText().split("\n\n")
    val fertilizerSeed =  FertilizerSeed();

    println("Part 1: " + fertilizerSeed.findLowestLocationNumber(input))
    println("Part 2: " + fertilizerSeed.findLowestWithSeedRange(input))
}