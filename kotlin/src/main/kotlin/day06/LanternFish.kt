package day06

import utils.readFileByLine

class LanternFish {
    fun calculateFishAfterNDays(input: List<String>, days: Int): Long  {
        var fish: LongArray = initiateFishArray(input)

        repeat(days) {
            fish = simulateDayStep(fish)
        }

        return fish.sum()
    }

    private fun initiateFishArray(input: List<String>): LongArray {
        val initialInputs: List<Int> = input[0].split(',').map { it.toInt() }

        return LongArray(9).apply {
            initialInputs.forEach {
                this[it] += 1L
            }
        }
    }

    private fun simulateDayStep(fish: LongArray): LongArray {
        val fishZero: Long = fish[0]

        fish[0] = 0L

        for(i in 1 until fish.size) {
            fish[i - 1] += fish[i]
            fish[i] -= fish[i]
        }

        fish[6] += fishZero
        fish[8] += fishZero

        return fish
    }
}

fun main() {
    val lanternFish = LanternFish()
    val input = readFileByLine("src/main/resources/day06/input.txt")

    println("Part 1: " + lanternFish.calculateFishAfterNDays(input, 80))
    println("Part 2: " + lanternFish.calculateFishAfterNDays(input, 256))
}

