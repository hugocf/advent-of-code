package aoc2021

import TestHelpers.readLinesFromResource
import aoc2021.Puzzle01.countIncreasingDepths
import aoc2021.Puzzle01.countIncreasingDepthsBy
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Puzzle01Tests {
    private val exampleDepths = listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)
    private val answerDepths = readLinesFromResource("2021/puzzle01.txt").map { it.toInt() }

    @Test
    fun `count increasing depths example`() {
        assertEquals(7, countIncreasingDepths(exampleDepths))
    }

    @Test
    fun `count increasing depths answer`() {
        assertEquals(1624, countIncreasingDepths(answerDepths))
    }

    @Test
    fun `count window depths example`() {
        assertEquals(5, countIncreasingDepthsBy(3, exampleDepths))
    }

    @Test
    fun `count window depths answer`() {
        assertEquals(1653, countIncreasingDepthsBy(3, answerDepths))
    }
}
