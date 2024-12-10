package day04

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach

class CeresSearchKtTest {

    val TEST_INPUT: List<String> = listOf("MMMSXXMASM", "MSAMXMSMSA", "AMXSXMAAMM", "MSAMASMSMX", "XMASAMXAMM", "XXAMMXXAMA", "SMSMSASXSS", "SAXAMASAAA", "MAMMMXMMMM", "MXMXAXMASX")

    private lateinit var testSubject: CeresSearch

    @BeforeEach
    fun setup() {
        testSubject = CeresSearch()
    }

    @Test
    fun part1_testExample() {
        val result = testSubject.part1(TEST_INPUT)

        assertThat(result).isEqualTo(18)
    }

    @Test
    fun part2_testExample() {
        val result = testSubject.part2(TEST_INPUT)

        assertThat(result).isEqualTo(9)
    }
}