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
        fun Position.move(cmd: Pair<String, Int>) = when(cmd.first) {
            "forward" -> this.copy(horizontal = this.horizontal + cmd.second, depth = this.depth + this.aim * cmd.second)
            "up" -> this.copy(aim = this.aim - cmd.second)
            "down" -> this.copy(aim = this.aim + cmd.second)
            else -> this
        }

        return commands
            .map { Pair(it.split(commandDelimiter).first(), it.split(commandDelimiter).last().toInt()) }
            .fold(start) { acc, cmd -> acc.move(cmd) }
    }

    fun moveSubmarine2(start: Position, commands: List<String>): Position {
        fun Position.move(cmd: Pair<String, Int>) = when(cmd.first) {
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
