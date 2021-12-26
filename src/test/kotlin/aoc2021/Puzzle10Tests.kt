package aoc2021

import TestHelpers.readLinesFromResource
import aoc2021.Puzzle10.middleIncompleteScore
import aoc2021.Puzzle10.totalSyntaxErrorScore
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Puzzle10Tests {
    private val exampleChunks = listOf(
        "[({(<(())[]>[[{[]{<()<>>",
        "[(()[<>])]({[<{<<[]>>(",
        "{([(<{}[<>[]}>{[]{[(<()>",
        "(((({<>}<{<{<>}{[]{[]{}",
        "[[<[([]))<([[{}[[()]]]",
        "[{[{({}]{}}([{[{{{}}([]",
        "{<[[]]>}<{[{[{[]{()[[[]",
        "[<(<(<(<{}))><([]([]()",
        "<{([([[(<>()){}]>(<<{{",
        "<{([{{}}[<[[[<>{}]]]>[]]",
    )
    private val answerChunks = readLinesFromResource("2021/puzzle10.txt")

    @Test
    fun `total error example`() {
        assertEquals(26397, totalSyntaxErrorScore(exampleChunks))
    }

    @Test
    fun `total error answer`() {
        assertEquals(392421, totalSyntaxErrorScore(answerChunks))
    }

    @Test
    fun `middle incomplete example`() {
        assertEquals(288957, middleIncompleteScore(exampleChunks))
    }

    @Test
    fun `middle incomplete answer`() {
        assertEquals(2769449099, middleIncompleteScore(answerChunks))
    }
}
