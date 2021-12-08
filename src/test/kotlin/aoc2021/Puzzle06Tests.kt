package aoc2021

import TestHelpers.readLinesFromResource
import aoc2021.Puzzle06.countFishAfterDays
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Puzzle06Tests {
    private val exampleState = "3,4,3,1,2"
    private val answerState = readLinesFromResource("2021/puzzle06.txt")

    @Test
    fun `80 days example`() {
        assertEquals(5934, countFishAfterDays(80, exampleState))
    }

    @Test
    fun `80 days answer`() {
        assertEquals(358214, countFishAfterDays(80, answerState.first()))
    }
}
