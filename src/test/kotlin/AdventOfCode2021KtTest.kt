import Puzzle01.countIncreasingDepths
import Puzzle01.countIncreasingDepthsBy
import Puzzle02.moveSubmarine
import Puzzle02.moveSubmarine2
import Puzzle02.moveSubmarineByAim
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

    private object Context {
        fun readLinesFromResource(fileName: String): List<String> {
            val uri = this.javaClass.getResource(fileName)?.toURI() ?: fail("Cannot find resource: $fileName")
            return File(uri).readLines()
        }
    }
}