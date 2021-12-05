package aoc2021

import TestHelpers.readLinesFromResource
import aoc2021.Puzzle04.playGameToLoose
import aoc2021.Puzzle04.playGameToWin
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Puzzle04Tests {
    private val exampleBingo = listOf(
        "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1",
        "",
        "22 13 17 11  0",
        " 8  2 23  4 24",
        "21  9 14 16  7",
        " 6 10  3 18  5",
        " 1 12 20 15 19",
        "",
        " 3 15  0  2 22",
        " 9 18 13 17  5",
        "19  8  7 25 23",
        "20 11 10 24  4",
        "14 21 16 12  6",
        "",
        "14 21 17 24  4",
        "10 16 15  9 19",
        "18  8 23 26 20",
        "22 11 13  6  5",
        " 2  0 12  3  7",
    )
    private val answerBingo = readLinesFromResource("2021/puzzle04.txt")

    @Test
    fun `win bingo example`() {
        assertEquals(4512, playGameToWin(exampleBingo))
    }

    @Test
    fun `win bingo answer`() {
        assertEquals(44088, playGameToWin(answerBingo))
    }

    @Test
    fun `loose bingo example`() {
        assertEquals(1924, playGameToLoose(exampleBingo))
    }

    @Test
    fun `loose bingo answer`() {
        assertEquals(23670, playGameToLoose(answerBingo))
    }
}
