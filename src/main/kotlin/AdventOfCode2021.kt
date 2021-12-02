object Puzzle01 {
    fun countIncreasingDepths(depths: List<Int>): Int =
        depths.zipWithNext { a, b -> if (a < b) 1 else 0 }.sum()

    fun countIncreasingDepthsBy(size: Int, depths: List<Int>): Int =
        countIncreasingDepths(depths.windowed(size) { it.sum() })
}

data class Position(val horizontal: Int, val depth: Int)

object Puzzle02 {
    fun moveSubmarine(start: Position, commands: List<String>): Position {
        val delimiter = " "
        val grouped = commands
            .map { Pair(it.split(delimiter).first(), it.split(delimiter).last()) }
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
}
