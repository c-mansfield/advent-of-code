package day02

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class CubeConundrumTest {

    private val TEXT_INPUTS = listOf(
        "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green", "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
        "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red", "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
        "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
    )

    private lateinit var testSubject: CubeConundrum

    @BeforeEach
    fun setup() {
        testSubject = CubeConundrum()
    }

    @Test
    fun part1_testExample() {
        val result = testSubject.sumPossibleGames(TEXT_INPUTS)

        assertThat(result).isEqualTo(8)
    }

    @Test
    fun part2_testExample() {
        val result = testSubject.sumPowerGames(TEXT_INPUTS)

        assertThat(result).isEqualTo(2286)
    }
}