package day06

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach

class GuardGallivantKtTest {

    val TEST_INPUT: List<String> = listOf("....#.....", ".........#", "..........", "..#.......", ".......#..", "..........", ".#..^.....", "........#.", "#.........", "......#...")

    private lateinit var testSubject: GuardGallivant

    @BeforeEach
    fun setup() {
        testSubject = GuardGallivant()
    }

    @Test
    fun part1_testExample() {
        val result = testSubject.part1(TEST_INPUT)

        assertThat(result).isEqualTo(41)
    }

    @Test
    fun part2_testExample() {
        val result = testSubject.part2(TEST_INPUT)

        assertThat(result).isEqualTo(9)
    }
}