object Puzzle01 {
    fun countIncreasingDepths(depths: List<Int>) = depths.fold(Pair(0, depths.first())) { acc, el ->
        Pair(if (el > acc.second) acc.first + 1 else acc.first, el)
    }.first
}
