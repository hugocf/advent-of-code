package aoc2021

import TestHelpers.readLinesFromResource
import aoc2021.Puzzle09.multiplyThreeLargestBasins
import aoc2021.Puzzle09.sumLowPointsRiskLevel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Puzzle09Tests {
    private val exampleHeatmap = listOf(
        "2199943210",
        "3987894921",
        "9856789892",
        "8767896789",
        "9899965678",
    )
    private val answerHeatmap = readLinesFromResource("2021/puzzle09.txt")

    @Test
    fun `sum low points example`() {
        assertEquals(15, sumLowPointsRiskLevel(exampleHeatmap))
    }

    @Test
    fun `sum low points answer`() {
        assertEquals(444, sumLowPointsRiskLevel(answerHeatmap))
    }

    @Test
    fun `three largest basins example`() {
        assertEquals(1134, multiplyThreeLargestBasins(exampleHeatmap))
    }

    @Test
    fun `three largest basins answer`() {
        assertEquals(1168440, multiplyThreeLargestBasins(answerHeatmap))
    }
}
