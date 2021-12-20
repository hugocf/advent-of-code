package aoc2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

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
}
