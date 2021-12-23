package aoc2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class MatrixTests {
    @Test
    fun `transpose a matrix`() {
        val matrix = listOf(
            listOf("1", "2", "3"),
            listOf("4", "5", "6"),
        )
        val expected = listOf(
            listOf("1", "4"),
            listOf("2", "5"),
            listOf("3", "6"),
        )

        assertEquals(expected, matrix.transpose(""))
    }

    @Test
    fun `get adjacent cells`() {
        val matrix = listOf(
            listOf(1, 2, 3),
            listOf(4, 5, 6),
            listOf(7, 8, 9),
        )

        // @formatter:off
        assertAll(
            { assertEquals(setOf(2, 4),         matrix.valuesAdjacentTo(0, 0)) },
            { assertEquals(setOf(1, 3, 5),      matrix.valuesAdjacentTo(0, 1)) },
            { assertEquals(setOf(2, 6),         matrix.valuesAdjacentTo(0, 2)) },
            { assertEquals(setOf(1, 5, 7),      matrix.valuesAdjacentTo(1, 0)) },
            { assertEquals(setOf(2, 4, 6, 8),   matrix.valuesAdjacentTo(1, 1)) },
            { assertEquals(setOf(3, 5, 9),      matrix.valuesAdjacentTo(1, 2)) },
            { assertEquals(setOf(4, 8),         matrix.valuesAdjacentTo(2, 0)) },
            { assertEquals(setOf(7, 5, 9),      matrix.valuesAdjacentTo(2, 1)) },
            { assertEquals(setOf(8, 6),         matrix.valuesAdjacentTo(2, 2)) },
        )
        // @formatter:on
    }
}
