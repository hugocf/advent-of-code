object Puzzle01 {
    fun countIncreasingDepths(depths: List<Int>): Int =
        depths.zipWithNext { a, b -> if (a < b) 1 else 0 }.sum()

    fun countIncreasingDepthsBy(size: Int, depths: List<Int>): Int =
        countIncreasingDepths(depths.windowed(size) { it.sum() })
}

data class Position(val horizontal: Int, val depth: Int, val aim: Int = 0) {
    companion object {
        val start = Position(0, 0, 0)
    }
}

object Puzzle02 {
    private const val commandDelimiter = " "

    fun moveSubmarine(start: Position, commands: List<String>): Position {
        val grouped = commands
            .map { Pair(it.split(commandDelimiter).first(), it.split(commandDelimiter).last()) }
            .groupBy { it.first }
            .mapValues { entry -> entry.value.map { it.second.toInt() } }

        val moveForward = grouped["forward"]?.sum() ?: 0
        val moveUp = grouped["up"]?.sum() ?: 0
        val moveDown = grouped["down"]?.sum() ?: 0

        return Position(
            start.horizontal + moveForward,
            start.depth + moveDown - moveUp,
        )
    }

    fun moveSubmarineByAim(start: Position, commands: List<String>): Position {
        fun Position.move(cmd: Pair<String, Int>) = when (cmd.first) {
            "forward" -> this.copy(
                horizontal = this.horizontal + cmd.second,
                depth = this.depth + this.aim * cmd.second
            )
            "up" -> this.copy(aim = this.aim - cmd.second)
            "down" -> this.copy(aim = this.aim + cmd.second)
            else -> this
        }

        return commands
            .map { Pair(it.split(commandDelimiter).first(), it.split(commandDelimiter).last().toInt()) }
            .fold(start) { acc, cmd -> acc.move(cmd) }
    }

    fun moveSubmarine2(start: Position, commands: List<String>): Position {
        fun Position.move(cmd: Pair<String, Int>) = when (cmd.first) {
            "forward" -> this.copy(horizontal = this.horizontal + cmd.second)
            "up" -> this.copy(depth = this.depth - cmd.second)
            "down" -> this.copy(depth = this.depth + cmd.second)
            else -> this
        }

        return commands
            .map { Pair(it.split(commandDelimiter).first(), it.split(commandDelimiter).last().toInt()) }
            .fold(start) { acc, cmd -> acc.move(cmd) }
    }
}

data class Diagnostics(val gammaRate: Int, val epsionRate: Int)

object Puzzle3 {
    fun calculateDiagnostics(report: List<String>): Diagnostics {
        fun String.splitPerCharacter() = this.toList().map { it.toString() }
        fun <K, V> Map<K, List<V>>.mostCommonKey() = this.maxByOrNull { it.value.size }?.key!!
        fun <K, V> Map<K, List<V>>.leastCommonKey() = this.minByOrNull { it.value.size }?.key!!
        operator fun Pair<String, String>.plus(other: Pair<String, String>) = Pair(this.first + other.first, this.second + other.second)

        val common = report
            .map { it.splitPerCharacter() }
            .transpose()
            .map { digits -> digits.groupBy { it } }
            .map { Pair(it.mostCommonKey(), it.leastCommonKey()) }
            .reduce { acc, pair -> acc + pair }

        return Diagnostics(common.first.toInt(2), common.second.toInt(2))
    }

    fun List<List<String>>.transpose(): List<List<String>> {
        val rows = this.size
        val cols = this.first().size
        val transposed = MutableList(cols) { MutableList(rows) { "" } }

        transposed.forEachIndexed { row, line ->
            line.forEachIndexed { col, _ ->
                transposed[row][col] = this[col][row]
            }
        }

        return transposed
    }
}
