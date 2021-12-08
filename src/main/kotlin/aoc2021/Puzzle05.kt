package aoc2021

import aoc2021.Puzzle05.Helpers.drawLine
import aoc2021.Puzzle05.Helpers.isStraightLine
import aoc2021.Puzzle05.Helpers.loadLine
import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.sign

typealias Line = List<Coordinate>
typealias Coordinate = Pair<Int, Int>

object Puzzle05 {
    data class Segment(val from: Coordinate, val to: Coordinate)

    fun overlappingPerpendicularLines(input: List<String>): Int =
        input.map(::loadLine).filter(::isStraightLine).map(::drawLine).flatten().groupBy { it }.count { it.value.size > 1 }

    fun overlappingAllLines(input: List<String>): Int =
        input.map(::loadLine).map(::drawLine).flatten().groupBy { it }.count { it.value.size > 1 }

    private object Helpers {
        fun loadLine(input: String): Segment =
            input.split(" -> ").map(::loadCoordinate).let { Segment(it.first(), it.last()) }

        private fun loadCoordinate(input: String): Coordinate =
            input.split(",").map(String::toInt).let { point -> point.first() to point.last() }

        fun isStraightLine(s: Segment): Boolean = s.from.first == s.to.first || s.from.second == s.to.second

        fun drawLine(s: Segment): Line {
            val horizontalPoints = s.to.first - s.from.first
            val verticalPoints = s.to.second - s.from.second
            val totalPoints = max(horizontalPoints.absoluteValue, verticalPoints.absoluteValue) + 1

            val xs = generateSequence(s.from.first) { it + horizontalPoints.sign }.take(totalPoints).toList()
            val ys = generateSequence(s.from.second) { it + verticalPoints.sign }.take(totalPoints).toList()

            return xs zip ys
        }
    }
}
