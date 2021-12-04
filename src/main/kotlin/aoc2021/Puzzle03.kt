package aoc2021

import aoc2021.Puzzle03.Matrix.transpose

object Puzzle03 {
    data class PowerConsumption(val gammaRate: Int, val epsionRate: Int)

    data class LifeSupport(val oxygenGeneratorRating: Int, val co2ScrubberRating: Int)

    object Matrix {
        fun List<List<String>>.transpose(): List<List<String>> {
            val rows = this.size
            val cols = this.first().size
            val transposed = MutableList(cols) { MutableList(rows) { "" } }

            transposed.forEachIndexed { row, line ->
                line.forEachIndexed { col, _ ->
                    transposed[row][col] = this[col][row]
                }
            }

            return transposed
        }
    }

    fun calculatePowerConsumption(report: List<String>): PowerConsumption {
        operator fun Pair<String, String>.plus(other: Pair<String, String>) =
            Pair(this.first + other.first, this.second + other.second)

        val common = report.groupByDigits().reduce { acc, pair -> acc + pair }

        return PowerConsumption(
            gammaRate = common.first.toInt(2),
            epsionRate = common.second.toInt(2)
        )
    }

    fun calculateLifeSupport(report: List<String>): LifeSupport {
        fun search(values: List<String>, criteria: (String, Pair<String, String>) -> Boolean): List<String> {
            var shortlist = values
            var idx = 0

            do {
                val common = shortlist.groupByDigits().drop(idx).take(1).first()
                shortlist = shortlist.filter { criteria(it[idx].toString(), common) }
                idx += 1
            } while (shortlist.size > 1)

            return shortlist
        }

        val mostCommon = search(report) { value, common -> value == common.first }
        val leastCommon = search(report) { value, common -> value == common.second }

        return LifeSupport(
            oxygenGeneratorRating = mostCommon.first().toInt(2),
            co2ScrubberRating = leastCommon.first().toInt(2)
        )
    }

    private fun List<String>.groupByDigits() = this
        .map { it.splitPerCharacter() }
        .transpose()
        .map { digits -> digits.groupBy { it } }
        .map { Pair(it.mostCommonKey(), it.leastCommonKey()) }

    private fun String.splitPerCharacter() = this.toList().map { it.toString() }

    private fun <V> Map<String, List<V>>.mostCommonKey() =
        if (this.freq("0") == this.freq("1")) "1" else this.maxByOrNull { it.value.size }?.key!!

    private fun <V> Map<String, List<V>>.leastCommonKey() =
        if (this.freq("0") == this.freq("1")) "0" else this.minByOrNull { it.value.size }?.key!!

    private fun <K, V> Map<K, List<V>>.freq(key: K) = this[key]?.size ?: 0
}
