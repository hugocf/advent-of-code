package aoc2021

import aoc2021.Math.median
import kotlin.math.abs

object Puzzle07 {
    fun minimumConstantFuelCost(input: String): Int {
        val positions = parseInitialState(input)
        val targetPosition = positions.median().toInt()

        return positions.sumOf { abs(it - targetPosition) }
    }

    fun minimumLinearFuelCost(input: String): Int {
        val positions = parseInitialState(input)
        val lowest = positions.minOrNull() ?: 0
        val highest = positions.maxOrNull() ?: 0
        val frequency = positions.groupingBy { it }.eachCount()
        val costBetween = { start: Int, stop: Int -> (0..abs(start - stop)).sum() }
        val fuelCostTo = { target: Int -> frequency.entries.sumOf { costBetween(it.key, target) * it.value } }

        return (lowest..highest).map { fuelCostTo(it) }.minOf { it }
    }

    private fun parseInitialState(input: String) = input.split(",").map(String::toInt)
}
