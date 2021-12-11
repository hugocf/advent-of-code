package aoc2021

import aoc2021.Math.median
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class MathTests {
    @Test
    fun `odd sized list`() {
        assertEquals(1.0, listOf(0, 1, 2).median())
    }

    @Test
    fun `even sized list`() {
        assertEquals(2.5, listOf(0, 1, 2, 3, 4, 5).median())
    }
}
