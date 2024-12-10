package day05

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach

class PrintQueueKtKtTest {

    val TEST_INPUT: List<String> = listOf(
        "47|53", "97|13", "97|61", "97|47", "75|29", "61|13", "75|53", "29|13", "97|29", "53|29", "61|53",
        "97|53", "61|29", "47|13", "75|47", "97|75", "47|61", "75|61", "47|29", "75|13", "53|13", "",
        "75,47,61,53,29", "97,61,53,29,13", "75,29,13", "75,97,47,61,53", "61,13,29", "97,13,75,29,47"
    )

    private lateinit var testSubject: PrintQueue

    @BeforeEach
    fun setup() {
        testSubject = PrintQueue()
    }

    @Test
    fun part1_testExample() {
        val result = testSubject.part1(TEST_INPUT)

        assertThat(result).isEqualTo(143)
    }

    @Test
    fun part2_testExample() {
        val result = testSubject.part2(TEST_INPUT)

        assertThat(result).isEqualTo(123)
    }
}