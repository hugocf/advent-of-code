package aoc2021

object Puzzle08 {
    fun countEasyDigits(input: List<String>): Int {
        val segments = input.map { it.split(" | ").last().split(" ") }

        return segments.flatten().count { listOf(2, 3, 4, 7).contains(it.length) }
    }
}
