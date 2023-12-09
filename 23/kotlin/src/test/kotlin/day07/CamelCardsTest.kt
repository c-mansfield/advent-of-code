package day07

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

internal class CamelCardsTest {
    private val TEXT_INPUTS = listOf("32T3K 765", "T55J5 684", "KK677 28", "KTJJT 220", "QQQJA 483")

    private lateinit var testSubject: CamelCards

    @BeforeEach
    fun setup() {
        testSubject = CamelCards()
    }

    @Test
    fun part1_testExample() {
        val result = testSubject.sumTotalWinnings(TEXT_INPUTS, false)

        assertThat(result).isEqualTo(6440)
    }

    @Test
    fun part2_testExample() {
        val result = testSubject.sumTotalWinnings(TEXT_INPUTS, true)

        assertThat(result).isEqualTo(5905)
    }
}