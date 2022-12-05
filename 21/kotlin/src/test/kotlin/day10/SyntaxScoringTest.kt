package day10

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SyntaxScoringTest {
    private lateinit var testSubject: SyntaxScoring

    private var TEST_INPUTS: List<String> = listOf("[({(<(())[]>[[{[]{<()<>>","[(()[<>])]({[<{<<[]>>(","{([(<{}[<>[]}>{[]{[(<()>","(((({<>}<{<{<>}{[]{[]{}","[[<[([]))<([[{}[[()]]]","[{[{({}]{}}([{[{{{}}([]","{<[[]]>}<{[{[{[]{()[[[]","[<(<(<(<{}))><([]([]()","<{([([[(<>()){}]>(<<{{","<{([{{}}[<[[[<>{}]]]>[]]")

    @BeforeEach
    fun setup() {
        testSubject = SyntaxScoring()
    }

    @Test
    fun part1_testExample() {
        val result = testSubject.calculateIllegalScore(TEST_INPUTS)

        assertThat(result).isEqualTo(26397)
    }

    @Test
    fun part2_testExample() {
        val result = testSubject.calculateIncompleteScore(TEST_INPUTS)

        assertThat(result).isEqualTo(288957)
    }
}