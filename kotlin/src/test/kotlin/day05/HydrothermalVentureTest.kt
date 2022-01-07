package day05

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HydrothermalVentureTest {
    private lateinit var testSubject: HydrothermalVenture

    private var TEST_INPUTS: List<String> = listOf("0,9 -> 5,9", "8,0 -> 0,8", "9,4 -> 3,4", "2,2 -> 2,1", "7,0 -> 7,4", "6,4 -> 2,0", "0,9 -> 2,9", "3,4 -> 1,4", "0,0 -> 8,8", "5,5 -> 8,2")

    @BeforeEach
    fun setup() {
        testSubject = HydrothermalVenture()
    }

    @Test
    fun part1_testExample() {
        val result = testSubject.findOverlaps(TEST_INPUTS, 1)

        assertThat(result).isEqualTo(5)
    }

    @Test
    fun part2_testExample() {
        val result = testSubject.findOverlaps(TEST_INPUTS, 2)

        assertThat(result).isEqualTo(12)
    }
}