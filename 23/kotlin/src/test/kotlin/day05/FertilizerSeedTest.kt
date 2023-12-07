package day05

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

internal class FertilizerSeedTest {
    private val TEXT_INPUT = File("src/test/resources/day05/input.txt").readText().split("\n\n")

    private lateinit var testSubject: FertilizerSeed

    @BeforeEach
    fun setup() {
        testSubject = FertilizerSeed()
    }

    @Test
    fun part1_testExample() {
        val result = testSubject.findLowestLocationNumber(TEXT_INPUT)

        assertThat(result).isEqualTo(35)
    }

    @Test
    fun part2_testExample() {
        val result = testSubject.findLowestWithSeedRange(TEXT_INPUT)

        assertThat(result).isEqualTo(46)
    }
}