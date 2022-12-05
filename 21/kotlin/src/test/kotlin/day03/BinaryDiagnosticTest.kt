package day03

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class BinaryDiagnosticTest {
    private lateinit var testSubject: BinaryDiagnostic

    private var TEST_INPUTS: List<String> = listOf("00100","11110","10110","10111","10101","01111","00111","11100","10000","11001","00010","01010")

    @BeforeEach
    fun setup() {
        testSubject = BinaryDiagnostic()
    }

    @Test
    fun part1_testExample() {
        val result = testSubject.powerConsumption(TEST_INPUTS, 1)

        assertThat(result).isEqualTo(198)
    }

    @Test
    fun part2_testExample() {
        val result = testSubject.powerConsumption(TEST_INPUTS, 2)

        assertThat(result).isEqualTo(230)
    }
}