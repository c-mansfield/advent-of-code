package day06

import assertk.assertThat
import assertk.assertions.isEqualTo
import day02.GiantSquid
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LanternFishTest {
    private lateinit var testSubject: LanternFish

    private var TEST_INPUTS: List<String> = listOf("3,4,3,1,2")

    @BeforeEach
    fun setup() {
        testSubject = LanternFish()
    }

    @Test
    fun part1_testExample() {
        val result = testSubject.calculateFishAfterNDays(TEST_INPUTS,  80)

        assertThat(result).isEqualTo(5934)
    }

    @Test
    fun part2_testExample() {
        val result = testSubject.calculateFishAfterNDays(TEST_INPUTS,  256)

        assertThat(result).isEqualTo(26984457539)
    }
}