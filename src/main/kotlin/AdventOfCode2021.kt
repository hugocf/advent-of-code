object Puzzle01 {
    fun countIncreasingDepths(depths: List<Int>) = depths.zipWithNext() { a, b -> if (a < b) 1 else 0 }.sum()
}
