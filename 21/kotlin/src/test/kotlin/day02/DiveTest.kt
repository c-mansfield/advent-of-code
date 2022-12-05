package day02

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import assertk.assertThat
import assertk.assertions.isEqualTo

internal class DiveTest {

    private lateinit var testSubject: Dive
    private var TEST_INPUTS: List<String> = listOf("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2")

    @BeforeEach
    fun setUp() {
        testSubject = Dive()
    }

    @Test
    fun part1_testExample() {
        val result = testSubject.calculatePlannedCourse(TEST_INPUTS, 1)

        assertThat(result).isEqualTo(150)
    }

    @Test
    fun part2_testExample() {
        val result = testSubject.calculatePlannedCourse(TEST_INPUTS, 2)

        assertThat(result).isEqualTo(900)
    }
}