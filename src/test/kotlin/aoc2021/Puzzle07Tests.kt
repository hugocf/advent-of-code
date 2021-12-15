package aoc2021

import TestHelpers.readLinesFromResource
import aoc2021.Puzzle07.minimumConstantFuelCost
import aoc2021.Puzzle07.minimumLinearFuelCost
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Puzzle07Tests {
    private val examplePositions = "16,1,2,0,4,2,7,1,2,14"
    private val answerPositions = readLinesFromResource("2021/puzzle07.txt")

    @Test
    fun `constant fuel spend example`() {
        assertEquals(37, minimumConstantFuelCost(examplePositions))
    }

    @Test
    fun `constant fuel spend answer`() {
        assertEquals(351901, minimumConstantFuelCost(answerPositions.first()))
    }

    @Test
    fun `linear fuel spend example`() {
        assertEquals(168, minimumLinearFuelCost(examplePositions))
    }

    @Test
    fun `linear fuel spend answer`() {
        assertEquals(101079875, minimumLinearFuelCost(answerPositions.first()))
    }
}
