package aoc2021

import kotlin.math.abs

typealias Line = List<Coordinate>
typealias Coordinate = Pair<Int, Int>

object Puzzle05 {
    data class Segment(val from: Coordinate, val to: Coordinate)

    fun overlappingPerpendicularLines(input: List<String>): Int =
        input.map(Helpers::loadLine).map(Helpers::drawSimpleLine).flatten().groupBy { it }.count { it.value.size > 1 }

    fun overlappingAllLines(input: List<String>): Int =
        input.map(Helpers::loadLine).map(Helpers::drawAnyLine).flatten().groupBy { it }.count { it.value.size > 1 }

    private object Helpers {
        fun loadLine(input: String): Segment =
            input.split(" -> ").map(::loadCoordinate).let { Segment(it.first(), it.last()) }

        private fun loadCoordinate(input: String): Coordinate =
            input.split(",").map(String::toInt).let { point -> point.first() to point.last() }

        fun drawSimpleLine(s: Segment): Line = drawHorizontalLine(s) + drawVerticalLine(s)

        fun drawAnyLine(s: Segment): Line = drawHorizontalLine(s) + drawVerticalLine(s) + drawDiagonalLine(s)

        private fun drawHorizontalLine(s: Segment): Line =
            if (s.from.first == s.to.first) range(s.from.second, s.to.second).map { s.from.first to it }
            else emptyList()

        private fun drawVerticalLine(s: Segment): Line =
            if (s.from.second == s.to.second) range(s.from.first, s.to.first).map { it to s.from.second }
            else emptyList()

        private fun drawDiagonalLine(s: Segment): Line {
            val hdist = abs(s.from.first - s.to.first)
            val vdist = abs(s.from.second - s.to.second)
            val xs = range(s.from.first, s.to.first)
            val ys = range(s.from.second, s.to.second)
            return if (hdist > 0 && vdist > 0 && hdist == vdist) xs.zip(ys) else emptyList()
        }

        private fun range(a: Int, b: Int) = if (a < b) a..b else a downTo b
    }
}
