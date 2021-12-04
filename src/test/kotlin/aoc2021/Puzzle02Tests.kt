package aoc2021

import TestHelpers.readLinesFromResource
import aoc2021.Puzzle02.Position
import aoc2021.Puzzle02.moveSubmarine
import aoc2021.Puzzle02.moveSubmarine2
import aoc2021.Puzzle02.moveSubmarineByAim
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Puzzle02Tests {
    private val exampleCommands = listOf("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2")
    private val answerCommands = readLinesFromResource("2021/puzzle02.txt")

    @Test
    fun `move submarine example`() {
        val result = moveSubmarine(Position.start, exampleCommands)
        assertEquals(150, result.horizontal * result.depth)

        val result2 = moveSubmarine2(Position.start, exampleCommands)
        assertEquals(150, result2.horizontal * result2.depth)
    }

    @Test
    fun `move submarine answer`() {
        val result = moveSubmarine(Position.start, answerCommands)
        assertEquals(1746616, result.horizontal * result.depth)

        val result2 = moveSubmarine2(Position.start, answerCommands)
        assertEquals(1746616, result2.horizontal * result2.depth)
    }

    @Nested
    inner class MoveSubmarineByAim {
        @Test
        fun `down increases aim by X`() {
            assertEquals(Position(0, 0, 5), moveSubmarineByAim(Position.start, listOf("down 5")))
        }

        @Test
        fun `up decreases aim by X`() {
            assertEquals(Position(0, 0, -5), moveSubmarineByAim(Position.start, listOf("up 5")))
        }

        @Test
        fun `forward increases horizontal by X and depth by aim times X`() {
            assertEquals(Position(5, 15, 3), moveSubmarineByAim(Position(0, 0, 3), listOf("forward 5")))
        }

        @Test
        fun `move example`() {
            val result = moveSubmarineByAim(Position.start, exampleCommands)

            assertEquals(900, result.horizontal * result.depth)
        }

        @Test
        fun `move answer`() {
            val result = moveSubmarineByAim(Position.start, answerCommands)

            assertEquals(1741971043, result.horizontal * result.depth)
        }
    }
}
