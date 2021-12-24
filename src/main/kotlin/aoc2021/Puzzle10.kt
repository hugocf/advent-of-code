package aoc2021

import aoc2021.Puzzle10.Helpers.ValidationResult
import aoc2021.Puzzle10.Helpers.ValidationResult.SyntaxError
import aoc2021.Puzzle10.Helpers.validateChunks

object Puzzle10 {
    fun totalSyntaxErrorScore(input: List<String>): Int =
        input.mapNotNull(::validateChunks).map(ValidationResult::score).sum()

    private object Helpers {
        fun validateChunks(line: String): ValidationResult? {
            val opening = Stack<Char>()

            line.forEach { char ->
                if (char.isOpening()) opening.push(char)
                else {
                    val latest = opening.pop() ?: ' '
                    if (char doesNotClose latest) return SyntaxError(delimiters[latest] ?: ' ', char)
                }
            }

            return null
        }

        sealed interface ValidationResult {
            fun score() = 0

            data class SyntaxError(val expected: Char, val found: Char): ValidationResult {
                private val points = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
                override fun score() = points[found] ?: 0
            }
        }

        data class Stack<T>(private val data: ArrayDeque<T> = ArrayDeque()) {
            fun push(element: T) = data.addFirst(element)

            fun pop() = data.removeFirstOrNull()
        }

        val delimiters = mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')

        fun Char.isOpening() = delimiters.keys.contains(this)

        infix fun Char.doesNotClose(open: Char) = this != delimiters[open]
    }
}
