package day01

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HistorianHysteriaKtTest {

    val TEST_INPUTS: List<String> = listOf("3   4", "4   3", "2   5", "1   3", "3   9", "3   3")

    private lateinit var testSubject: HistorianHysteria

    @BeforeEach
    fun setup() {
        testSubject = HistorianHysteria()
    }

    @Test
    fun part1_testExample() {
        val result = testSubject.part1(TEST_INPUTS)

        assertThat(result).isEqualTo(11)
    }

    @Test
    fun part2_testExample() {
        val result = testSubject.part2(TEST_INPUTS)

        assertThat(result).isEqualTo(31)
    }
}