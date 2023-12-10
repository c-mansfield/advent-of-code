package day08

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

internal class HauntedWastelandTest {
    private val TEXT_INPUTS = listOf(
        "LLR",
        "",
        "AAA = (BBB, BBB)",
        "BBB = (AAA, ZZZ)",
        "ZZZ = (ZZZ, ZZZ)"
    )

    private val TEXT_INPUTS_2 = listOf(
        "LR",
        "",
        "11A = (11B, XXX)",
        "11B = (XXX, 11Z)",
        "11Z = (11B, XXX)",
        "22A = (22B, XXX)",
        "22B = (22C, 22C)",
        "22C = (22Z, 22Z)",
        "22Z = (22B, 22B)",
        "XXX = (XXX, XXX)"
    )

    private lateinit var testSubject: HauntedWasteland

    @BeforeEach
    fun setup() {
        testSubject = HauntedWasteland()
    }

    @Test
    fun part1_testExample() {
        val result = testSubject.findStepsTillEnd(TEXT_INPUTS, "AAA")

        assertThat(result).isEqualTo(6)
    }

    @Test
    fun part2_testExample() {
        val result = testSubject.findStepsTillEnd(TEXT_INPUTS_2, "A")

        assertThat(result).isEqualTo(6)
    }
}