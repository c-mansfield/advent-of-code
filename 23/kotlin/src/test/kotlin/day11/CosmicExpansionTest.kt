package day11

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

internal class CosmicExpansionKtTest {
    private val TEXT_INPUTS = listOf(
        "...#......",
        ".......#..",
        "#.........",
        "..........",
        "......#...",
        ".#........",
        ".........#",
        "..........",
        ".......#..",
        "#...#....."
    )

    private lateinit var testSubject: CosmicExpansion

    @BeforeEach
    fun setup() {
        testSubject = CosmicExpansion()
    }

    @Test
    fun part1_testExample() {
        val result = testSubject.sumShortestPaths(TEXT_INPUTS, 1)

        assertThat(result).isEqualTo(374);
    }

    @Test
    fun part2_testExample() {
        val result = testSubject.sumShortestPaths(TEXT_INPUTS, 100)

        assertThat(result).isEqualTo(8410)
    }
}