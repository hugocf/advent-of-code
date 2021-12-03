import Puzzle01.countIncreasingDepths
import Puzzle01.countIncreasingDepthsBy
import Puzzle02.moveSubmarine
import Puzzle02.moveSubmarine2
import Puzzle02.moveSubmarineByAim
import Puzzle3.calculateDiagnostics
import Puzzle3.transpose
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File

internal class AdventOfCode2021KtTest {

    @Nested
    inner class Puzzle01 {
        private val exampleDepths = listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)
        private val answerDepths = Context.readLinesFromResource("puzzle01.input.txt").map { it.toInt() }

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

    @Nested
    inner class Puzzle02 {
        private val exampleCommands = listOf("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2")
        private val answerCommands = Context.readLinesFromResource("puzzle02.input.txt")

        @Test
        fun `move submarine example`() {
            val result = moveSubmarine(Position.start, exampleCommands)
            assertEquals(150, result.horizontal * result.depth)

            val result2 = moveSubmarine2(Position.start, exampleCommands)
            assertEquals(150, result2.horizontal * result2.depth)
        }

        @Test
        fun `move submarine answer`() {
            val result = moveSubmarine(Position.start,  answerCommands)
            assertEquals(1746616, result.horizontal * result.depth)

            val result2 = moveSubmarine2(Position.start,  answerCommands)
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
            fun `the example`() {
                val result = moveSubmarineByAim(Position.start, exampleCommands)

                assertEquals(900, result.horizontal * result.depth)
            }

            @Test
            fun `the answer`() {
                val result = moveSubmarineByAim(Position.start, answerCommands)

                assertEquals(1741971043, result.horizontal * result.depth)
            }
        }
    }

    @Nested
    inner class Puzzle03 {
        private val exampleReport = listOf("00100", "11110", "10110", "10111", "10101", "01111", "00111", "11100", "10000", "11001", "00010", "01010")
        private val answerReport = Context.readLinesFromResource("puzzle03.input.txt")

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

            assertEquals(expected, transpose(matrix))
        }

        @Test
        fun `calculate diagnostics rates`() {
            val report = listOf("00111", "11100", "00000")
            assertEquals(Diagnostics("00100".toInt(2), "11011".toInt(2)), calculateDiagnostics(report))
       }

        @Test
        fun `calculate diagnostics example`() {
            val result = calculateDiagnostics(exampleReport)
            assertEquals(198, result.gammaRate * result.epsionRate)
        }

        @Test
        fun `calculate diagnostics answer`() {
            val result = calculateDiagnostics(answerReport)
            assertEquals(2583164, result.gammaRate * result.epsionRate)
        }
    }

    private object Context {
        fun readLinesFromResource(fileName: String): List<String> {
            val uri = this.javaClass.getResource(fileName)?.toURI() ?: fail("Cannot find resource: $fileName")
            return File(uri).readLines()
        }
    }
}