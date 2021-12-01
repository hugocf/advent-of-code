import Puzzle01.countIncreasingDepths
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.io.File

internal class AdventOfCode2021KtTest {

    @Nested
    inner class Puzzle01 {
        @Test
        fun `count increasing depths example`() {
            assertEquals(7, countIncreasingDepths(listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)))
        }

        @Test
        fun `count increasing depths answer`() {
            val sourceName = "puzzle01.input.txt"
            val sourceUri = this.javaClass.getResource(sourceName)?.toURI() ?: fail("Cannot file resource: $sourceName")
            val depths = File(sourceUri).readLines().map { it.toInt() }

            assertEquals(1624, countIncreasingDepths(depths))
        }
    }
}