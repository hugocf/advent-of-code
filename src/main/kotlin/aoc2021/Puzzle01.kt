package aoc2021

object Puzzle01 {
    fun countIncreasingDepths(depths: List<Int>): Int =
        depths.zipWithNext { a, b -> if (a < b) 1 else 0 }.sum()

    fun countIncreasingDepthsBy(size: Int, depths: List<Int>): Int =
        countIncreasingDepths(depths.windowed(size) { it.sum() })
}
