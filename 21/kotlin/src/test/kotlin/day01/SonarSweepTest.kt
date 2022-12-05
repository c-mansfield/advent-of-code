package day01

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import assertk.assertThat
import assertk.assertions.isEqualTo

internal class SonarSweepTest {

    private lateinit var testSubject: SonarSweep

    private var TEST_INPUTS: List<Int> = listOf(199,200,208,210,200,207,240,269,260,263)

    @BeforeEach
    fun setup() {
        testSubject = SonarSweep()
    }

    @Test
    fun part1_testExample() {
        val result = testSubject.depthIncreases(TEST_INPUTS, 1)

        assertThat(result).isEqualTo(7)
    }

    @Test
    fun part2_testExample() {
        val result = testSubject.depthIncreases(TEST_INPUTS, 3)

        assertThat(result).isEqualTo(5)
    }

}