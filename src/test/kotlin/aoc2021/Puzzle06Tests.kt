package aoc2021

import TestHelpers.readLinesFromResource
import aoc2021.Puzzle06.naiveCountFishAfterDays
import aoc2021.Puzzle06.optimisedCountFishAfterDays
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Puzzle06Tests {
    private val exampleState = "3,4,3,1,2"
    private val answerState = readLinesFromResource("2021/puzzle06.txt")

    @Test
    fun `80 days example`() {
        assertEquals(5934, naiveCountFishAfterDays(80, exampleState))
        assertEquals(5934, optimisedCountFishAfterDays(80, exampleState))
    }

    @Test
    fun `80 days answer`() {
        assertEquals(358214, naiveCountFishAfterDays(80, answerState.first()))
        assertEquals(358214, optimisedCountFishAfterDays(80, answerState.first()))
    }

    @Test
    fun `256 days example`() {
        assertEquals(26984457539, optimisedCountFishAfterDays(256, exampleState))
    }

    @Test
    fun `256 days answer`() {
        assertEquals(1622533344325, optimisedCountFishAfterDays(256, answerState.first()))
    }
}
