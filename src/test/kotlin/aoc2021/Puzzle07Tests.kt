package aoc2021

import TestHelpers.readLinesFromResource
import aoc2021.Puzzle07.minimumFuelCost
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Puzzle07Tests {
    private val examplePositions = "16,1,2,0,4,2,7,1,2,14"
    private val answerPositions = readLinesFromResource("2021/puzzle07.txt")

    @Test
    fun `the example`() {
        assertEquals(37, minimumFuelCost(examplePositions))
    }

    @Test
    fun `the answer`() {
        assertEquals(351901, minimumFuelCost(answerPositions.first()))
    }
}
