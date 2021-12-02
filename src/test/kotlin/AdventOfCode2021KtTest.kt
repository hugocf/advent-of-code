import Puzzle01.countIncreasingDepths
import Puzzle01.countIncreasingDepthsBy
import Puzzle02.moveSubmarine
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File

internal class AdventOfCode2021KtTest {

    @Nested
    inner class Puzzle01 {
        val exampleDepths = listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)

        @Test
        fun `count increasing depths example`() {
            assertEquals(7, countIncreasingDepths(exampleDepths))
        }

        @Test
        fun `count increasing depths answer`() {
            val depths = Context.readLinesFromResource("puzzle01.input.txt").map { it.toInt() }
            assertEquals(1624, countIncreasingDepths(depths))
        }

        @Test
        fun `count window depths example`() {
            assertEquals(5, countIncreasingDepthsBy(3, exampleDepths))
        }

        @Test
        fun `count window depths answer`() {
            val depths = Context.readLinesFromResource("puzzle01.input.txt").map { it.toInt() }
            assertEquals(1653, countIncreasingDepthsBy(3, depths))
        }
    }

    @Nested
    inner class Puzzle02 {
        val exampleCommands = listOf("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2")

        @Test
        fun `move submarine example`() {
            val result = moveSubmarine(start= Position(0, 0), exampleCommands)

            assertEquals(150, result.horizontal * result.depth)
        }

        @Test
        fun `move submarine answer`() {
            val commands = Context.readLinesFromResource("puzzle02.input.txt")

            val result = moveSubmarine(start= Position(0, 0),  commands)

            assertEquals(1746616, result.horizontal * result.depth)
        }

        @Test
        fun `count window depths example`() {
            // assertEquals(5, countIncreasingDepthsBy(3, exampleCommands))
        }

        @Test
        fun `count window depths answer`() {
            //val depths = Context.readLinesFromResource("puzzle01.input.txt").map { it.toInt() }
            //assertEquals(1653, countIncreasingDepthsBy(3, depths))
        }
    }

    private object Context {
        fun readLinesFromResource(fileName: String): List<String> {
            val uri = this.javaClass.getResource(fileName)?.toURI() ?: fail("Cannot find resource: $fileName")
            return File(uri).readLines()
        }
    }
}