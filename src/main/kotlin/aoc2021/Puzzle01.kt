package aoc2021

object Puzzle01 {
    fun countIncreasingDepths(depths: List<Int>): Int =
        depths.zipWithNext().count { it.first < it.second }

    fun countIncreasingDepthsBy(size: Int, depths: List<Int>): Int =
        countIncreasingDepths(depths.windowed(size) { it.sum() })
}
