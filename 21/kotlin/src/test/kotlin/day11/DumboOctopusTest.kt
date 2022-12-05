package day11

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DumboOctopusTest {
    private lateinit var testSubject: DumboOctopus

    private var TEST_INPUTS: List<String> = listOf("5483143223", "2745854711", "5264556173", "6141336146", "6357385478", "4167524645", "2176841721", "6882881134", "4846848554", "5283751526")

    @BeforeEach
    fun setup() {
        testSubject = DumboOctopus()
    }

    @Test
    fun part1_testExample() {
        val result = testSubject.findTotalFlashesAfterDays(TEST_INPUTS, 100)

        assertThat(result).isEqualTo(1656)
    }

    @Test
    fun part2_testExample() {
        val result = testSubject.findWhenAllFlash(TEST_INPUTS)

        assertThat(result).isEqualTo(195)
    }
}