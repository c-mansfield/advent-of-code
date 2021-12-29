package day07

import assertk.assertThat
import assertk.assertions.isEqualTo
import day02.GiantSquid
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TreacheryOfWhalesTest {
    private lateinit var testSubject: TreacheryOfWhales

    private var TEST_INPUTS: List<String> = listOf("16,1,2,0,4,2,7,1,2,14")

    @BeforeEach
    fun setup() {
        testSubject = TreacheryOfWhales()
    }

    @Test
    fun part1_testExample() {
        val result = testSubject.calculateFuel(TEST_INPUTS)

        assertThat(result).isEqualTo(37)
    }

    @Test
    fun part2_testExample() {
        val result = testSubject.calculateFuelWithRate(TEST_INPUTS)

        assertThat(result).isEqualTo(168)
    }
}