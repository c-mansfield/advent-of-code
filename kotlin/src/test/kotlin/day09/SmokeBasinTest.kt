package day09

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SmokeBasinTest {
    private lateinit var testSubject: SmokeBasin

    private var TEST_INPUTS: List<String> = listOf("2199943210","3987894921","9856789892","8767896789","9899965678")

    @BeforeEach
    fun setup() {
        testSubject = SmokeBasin()
    }

    @Test
    fun part1_testExample() {
        val result = testSubject.calculateLowPointRisk(TEST_INPUTS)

        assertThat(result).isEqualTo(15)
    }

    @Test
    fun part2_testExample() {
        val result = testSubject.calculateLargestBasins(TEST_INPUTS)

        assertThat(result).isEqualTo(1134)
    }
}