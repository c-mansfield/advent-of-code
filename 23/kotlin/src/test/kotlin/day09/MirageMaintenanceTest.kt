package day09

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

internal class MirageMaintenanceTest {
    private val TEXT_INPUTS = listOf(
        "0 3 6 9 12 15",
        "1 3 6 10 15 21",
        "10 13 16 21 30 45"
    )

    private lateinit var testSubject: MirageMaintenance

    @BeforeEach
    fun setup() {
        testSubject = MirageMaintenance()
    }

    @Test
    fun part1_testExample() {
        val result = testSubject.sumExtrapolatedValues(TEXT_INPUTS)

        assertThat(result).isEqualTo(114)
    }

    @Test
    fun part2_testExample() {
        val result = testSubject.sumBackwardsExtrapolatedValues(TEXT_INPUTS)

        assertThat(result).isEqualTo(2)
    }
}