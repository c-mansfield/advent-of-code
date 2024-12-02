package day02

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RedNosedReportsKtTest {

    val TEST_INPUTS: List<String> = listOf("7 6 4 2 1", "1 2 7 8 9", "9 7 6 2 1", "1 3 2 4 5", "8 6 4 4 1", "1 3 6 7 9")

    private lateinit var testSubject: RedNosedReports

    @BeforeEach
    fun setup() {
        testSubject = RedNosedReports()
    }

    @Test
    fun part1_testExample() {
        val result = testSubject.part1(TEST_INPUTS)

        assertThat(result).isEqualTo(2)
    }

    @Test
    fun part2_testExample() {
        val result = testSubject.part2(TEST_INPUTS)

        assertThat(result).isEqualTo(4)
    }
}