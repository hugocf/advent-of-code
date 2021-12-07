package aoc2021

import TestHelpers.readLinesFromResource
import aoc2021.Puzzle05.countOverlappingPoints
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Puzzle05Tests {
    private val exampleLines = listOf(
        "0,9 -> 5,9",
        "8,0 -> 0,8",
        "9,4 -> 3,4",
        "2,2 -> 2,1",
        "7,0 -> 7,4",
        "6,4 -> 2,0",
        "0,9 -> 2,9",
        "3,4 -> 1,4",
        "0,0 -> 8,8",
        "5,5 -> 8,2",
    )
    private val answerLines = readLinesFromResource("2021/puzzle05.txt")

    @Test
    fun `lines example`() {
        assertEquals(5, countOverlappingPoints(exampleLines))
    }

    @Test
    fun `lines answer`() {
        assertEquals(6005, countOverlappingPoints(answerLines))
    }
}
