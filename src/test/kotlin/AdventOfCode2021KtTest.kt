import Puzzle01.countIncreasingDepths
import Puzzle01.countIncreasingDepthsBy
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File

internal class AdventOfCode2021KtTest {

    @Nested
    inner class Puzzle01 {
        val exampleList = listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)

        @Test
        fun `count increasing depths example`() {
            assertEquals(7, countIncreasingDepths(exampleList))
        }

        @Test
        fun `count increasing depths answer`() {
            val depths = Context.readLinesFromResource("puzzle01.input.txt").map { it.toInt() }
            assertEquals(1624, countIncreasingDepths(depths))
        }

        @Test
        fun `count window depths example`() {
            assertEquals(5, countIncreasingDepthsBy(3, exampleList))
        }

        @Test
        fun `count window depths answer`() {
            val depths = Context.readLinesFromResource("puzzle01.input.txt").map { it.toInt() }
            assertEquals(1653, countIncreasingDepthsBy(3, depths))
        }
    }

    private object Context {
        fun readLinesFromResource(fileName: String): List<String> {
            val uri = this.javaClass.getResource(fileName)?.toURI() ?: fail("Cannot find resource: $fileName")
            return File(uri).readLines()
        }
    }
}