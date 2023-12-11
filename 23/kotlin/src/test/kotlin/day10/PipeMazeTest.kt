package day10

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

internal class PipeMazeTest {
    private val TEXT_INPUTS = listOf(
        ".....",
        ".S-7.",
        ".|.|.",
        ".L-J.",
        "....."
    )

    private lateinit var testSubject: PipeMaze

    @BeforeEach
    fun setup() {
        testSubject = PipeMaze()
    }

    @Test
    fun part1_testExample() {
        val result = testSubject.findTotalSteps(TEXT_INPUTS)

        assertThat(result).isEqualTo(4)
    }

//    @Test
//    fun part2_testExample() {
//        val result = testSubject.sumBackwardsExtrapolatedValues(TEXT_INPUTS)
//
//        assertThat(result).isEqualTo(2)
//    }
}