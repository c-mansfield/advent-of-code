package day03

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GearRatiosTest {
    private val TEXT_INPUTS = listOf(
        "467..114..", "...*......", "..35..633.", "......#...",
        "617*......", ".....+.58.", "..592.....", "......755.",
        "...\$.*....", ".664.598.."
    )

    private lateinit var testSubject: GearRatios

    @BeforeEach
    fun setup() {
        testSubject = GearRatios()
    }

    @Test
    fun part1_testExample() {
        val result = testSubject.sumPartNumbers(TEXT_INPUTS)

        assertThat(result).isEqualTo(4361)
    }

    @Test
    fun part2_testExample() {
        val result = testSubject.sumGearRatios(TEXT_INPUTS)

        assertThat(result).isEqualTo(467835)
    }
}