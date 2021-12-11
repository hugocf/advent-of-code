package aoc2021

import aoc2021.Math.median
import kotlin.math.abs

object Puzzle07 {
    fun minimumFuelCost(input: String): Int {
        val positions = parseInitialState(input)
        val targetPosition = positions.median().toInt()

        return positions.sumOf { abs(it - targetPosition) }
    }

    private fun parseInitialState(input: String) = input.split(",").map(String::toInt)
}
