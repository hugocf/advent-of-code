package aoc2021

import aoc2021.Puzzle10.Helpers.ValidationResult.*
import aoc2021.Puzzle10.Helpers.middle
import aoc2021.Puzzle10.Helpers.score
import aoc2021.Puzzle10.Helpers.validateChunks

object Puzzle10 {
    fun totalSyntaxErrorScore(input: List<String>): Long =
        input.map(::validateChunks).filterIsInstance<SyntaxError>().sumOf { it.score() }

    fun middleIncompleteScore(input: List<String>): Long =
        input.map(::validateChunks).filterIsInstance<Incomplete>().map { it.score() }.sorted().middle()

    private object Helpers {
        fun validateChunks(line: String): ValidationResult {
            val opening = Stack<OpenChunk>()

            line.mapNotNull(::enumOfChar).forEach {
                when (it) {
                    is OpenChunk -> opening.push(it)
                    is CloseChunk -> {
                        val latest = opening.pop()
                        if (it doesNotClose latest) return SyntaxError(expected = delimiters[latest]!!, found = it)
                    }
                }
            }

            return if (opening.isEmpty()) Complete else Incomplete(unclosed = opening.toList())
        }

        sealed interface ValidationResult {
            object Complete : ValidationResult
            data class SyntaxError(val expected: CloseChunk, val found: CloseChunk) : ValidationResult
            data class Incomplete(val unclosed: List<OpenChunk>) : ValidationResult
        }

        fun SyntaxError.score(): Long = when (this.found) {
            CloseChunk.Curve -> 3
            CloseChunk.Square -> 57
            CloseChunk.Curly -> 1197
            CloseChunk.Angle -> 25137
        }

        fun Incomplete.score(): Long {
            val points = { c: CloseChunk ->
                when (c) {
                    CloseChunk.Curve -> 1
                    CloseChunk.Square -> 2
                    CloseChunk.Curly -> 3
                    CloseChunk.Angle -> 4
                }
            }
            return unclosed.reversed().mapNotNull { delimiters[it] }.fold(0) { acc, el -> acc * 5 + points(el) }
        }

        data class Stack<T>(private val data: ArrayDeque<T> = ArrayDeque()) {
            fun push(element: T) = data.addLast(element)

            fun pop() = data.removeLastOrNull()

            fun isEmpty() = data.isEmpty()

            fun toList() = data.toList()
        }

        val delimiters = mapOf(
            OpenChunk.Curve to CloseChunk.Curve,
            OpenChunk.Square to CloseChunk.Square,
            OpenChunk.Curly to CloseChunk.Curly,
            OpenChunk.Angle to CloseChunk.Angle,
        )

        sealed interface CharWrapper {
            val raw: Char
        }

        enum class OpenChunk(override val raw: Char) : CharWrapper {
            Curve('('), Square('['), Curly('{'), Angle('<')
        }

        enum class CloseChunk(override val raw: Char) : CharWrapper {
            Curve(')'), Square(']'), Curly('}'), Angle('>')
        }

        fun enumOfChar(char: Char) =
            listOf(OpenChunk.values().toList(), CloseChunk.values().toList()).flatten().find { it.raw == char }

        infix fun CloseChunk.doesNotClose(open: OpenChunk?) = this != delimiters[open]

        fun <T> List<T>.middle() = this[lastIndex / 2]
    }
}
