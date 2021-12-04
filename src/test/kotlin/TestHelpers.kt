import org.junit.jupiter.api.Assertions
import java.io.File

object TestHelpers {
    fun readLinesFromResource(fileName: String): List<String> {
        val uri =
            this.javaClass.getResource(fileName)?.toURI() ?: Assertions.fail("Cannot find resource: $fileName")
        return File(uri).readLines()
    }
}
