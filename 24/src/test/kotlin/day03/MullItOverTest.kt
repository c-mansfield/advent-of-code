package day03

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MullItOverTest {

    private lateinit var testSubject: MullItOver

    @BeforeEach
    fun setup() {
        testSubject = MullItOver()
    }

    @Test
    fun part1_testExample() {
        val testInput = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"

        val result = testSubject.part1(testInput)

        assertThat(result).isEqualTo(161)
    }

    @Test
    fun part2_testExample() {
        val testInput = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"

        val result = testSubject.part2(testInput)

        assertThat(result).isEqualTo(48)
    }
}