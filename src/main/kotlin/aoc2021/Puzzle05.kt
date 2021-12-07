package aoc2021

import kotlin.math.max
import kotlin.math.min

typealias Line = List<Coordinate>
typealias Coordinate = Pair<Int, Int>

object Puzzle05 {
    fun countOverlappingPoints(input: List<String>): Int =
        input.map(Helpers::loadLine).flatten().groupBy { it }.count { it.value.size > 1 }

    private object Helpers {
        fun loadLine(input: String): Line = input
            .split(" -> ")
            .map(::loadCoordinate)
            .let { drawStraightLine(it.first(), it.last()) }

        fun loadCoordinate(input: String): Coordinate =
            input.split(",").map(String::toInt).let { point -> point.first() to point.last() }

        fun drawStraightLine(from: Coordinate, to: Coordinate): Line {
            fun range(a: Int, b: Int) = min(a, b)..max(a, b)

            return when {
                from.first == to.first -> range(from.second, to.second).map { from.first to it }
                from.second == to.second -> range(from.first, to.first).map { it to from.second }
                else -> emptyList()
            }
        }
    }
}
