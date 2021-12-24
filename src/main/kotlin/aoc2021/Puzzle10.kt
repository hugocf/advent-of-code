package aoc2021

import aoc2021.Puzzle10.Helpers.calculatePoints
import aoc2021.Puzzle10.Helpers.checkSyntaxError
import aoc2021.Puzzle10.Helpers.parseChunks

typealias ChunksLine = List<Char>

object Puzzle10 {
    fun totalSyntaxErrorScore(input: List<String>): Int =
        parseChunks(input).mapNotNull(::checkSyntaxError).map(::calculatePoints).sum()

    private object Helpers {
        fun parseChunks(input: List<String>): List<ChunksLine> = input.map { it.toCharArray().toList() }

        fun checkSyntaxError(line: ChunksLine): SyntaxError? {
            val opening = Stack<Char>()

            line.forEach { char ->
                if (char.isOpening()) opening.push(char)
                else {
                    val latest = opening.pop() ?: ' '
                    if (char doesNotClose latest) return SyntaxError(delimiters[latest]?.close ?: ' ', char)
                }
            }

            return null
        }

        fun calculatePoints(error: SyntaxError): Int = delimiters[error.found]?.errorPoints ?: 0

        data class SyntaxError(val expected: Char, val found: Char)

        data class Stack<T>(private val data: ArrayDeque<T> = ArrayDeque()) {
            fun push(element: T) = data.addFirst(element)

            fun pop() = data.removeFirstOrNull()
        }

        data class Chunk(val open: Char, val close: Char, val errorPoints: Int)

        val delimiters = setOf(
            Chunk('(', ')', 3),
            Chunk('[', ']', 57),
            Chunk('{', '}', 1197),
            Chunk('<', '>', 25137),
        )

        fun Char.isOpening() = delimiters.any { it.open == this }

        infix fun Char.doesNotClose(open: Char) = !delimiters.any { it.open == open && it.close == this }

        operator fun Set<Chunk>.get(search: Char) = delimiters.find { it.open == search || it.close == search }
    }
}
