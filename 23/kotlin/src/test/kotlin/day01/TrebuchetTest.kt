package day01

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import assertk.assertThat
import assertk.assertions.isEqualTo

internal class TrebuchetTest {

    private lateinit var testSubject: Trebuchet

    @BeforeEach
    fun setup() {
        testSubject = Trebuchet()
    }

    @Test
    fun part1_testExample() {
        val testInputs: List<String> = listOf("1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet")
        val result = testSubject.findCalibration(testInputs)

        assertThat(result).isEqualTo(142)
    }

    @Test
    fun part2_testExample() {
        val testInputs: List<String> = listOf(
            "two1nine", "eightwothree", "abcone2threexyz", "xtwone3four", "4nineeightseven2", "zoneight234", "7pqrstsixteen"
        )
        val result = testSubject.findCalibrationWithWrittenLetters(testInputs)

        assertThat(result).isEqualTo(281)
    }

}