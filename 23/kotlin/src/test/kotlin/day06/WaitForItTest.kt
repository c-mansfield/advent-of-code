package day06

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

internal class WaitForItTest {
    private val TEXT_INPUTS = listOf(
        "Time:      7  15   30", "Distance:  9  40  200"
    )

    private lateinit var testSubject: WaitForIt

    @BeforeEach
    fun setup() {
        testSubject = WaitForIt()
    }

    @Test
    fun part1_testExample() {
        val result = testSubject.sumWaysToBeatRecord(TEXT_INPUTS)

        assertThat(result).isEqualTo(288)
    }

    @Test
    fun part2_testExample() {
        val result = testSubject.findSingleRaceRecord(TEXT_INPUTS)

        assertThat(result).isEqualTo(71503)
    }
}