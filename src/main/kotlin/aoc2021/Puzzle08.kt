package aoc2021

object Puzzle08 {
    fun countEasyDigits(input: List<String>): Int =
        parseEntries(input).map { it.digits }.flatten().count { listOf(2, 3, 4, 7).contains(it.length) }

    fun sumAllDigits(input: List<String>): Int =
        parseEntries(input).map { entry ->
            val translation = SegmentsMap.analise(entry.patterns)
            entry.digits.map(translation::convert)
        }.sumOf { it.joinToString("").toInt() }

    private fun parseEntries(input: List<String>) = input.map {
        val parts = it.split(" | ")
        Entry(parts.first().split(" "), parts.last().split(" "))
    }

    private data class Entry(val patterns: List<String>, val digits: List<String>)

    private data class SegmentsMap(private val segmentToDigit: Map<String, Int>) {
        fun convert(segment: String): Int = segmentToDigit[segment.sorted()]!!

        companion object {
            fun analise(patterns: List<String>): SegmentsMap {
                val d1 = patterns.find { it.length == 2 } ?: "1 not found"
                val d4 = patterns.find { it.length == 4 } ?: "4 not found"
                val d7 = patterns.find { it.length == 3 } ?: "7 not found"
                val d8 = patterns.find { it.length == 7 } ?: "8 not found"
                val fiveSegments = patterns.filter { it.length == 5 }
                val d2 = fiveSegments.find { (d4 minusSegmentsOf it).size == 2 } ?: "2 not found"
                val d3 = fiveSegments.find { d1 overlapsWith it } ?: "3 not found"
                val d5 = fiveSegments.find { it != d2 && it != d3 } ?: "5 not found"
                val sixSegments = patterns.filter { it.length == 6 }
                val d6 = sixSegments.find { !(d1 overlapsWith it) } ?: "6 not found"
                val d9 = sixSegments.find { d4 overlapsWith it } ?: "9 not found"
                val d0 = sixSegments.find { it != d6 && it != d9 } ?: "0 not found"

                return SegmentsMap(mapOf(
                    d1.sorted() to 1,
                    d2.sorted() to 2,
                    d3.sorted() to 3,
                    d4.sorted() to 4,
                    d5.sorted() to 5,
                    d6.sorted() to 6,
                    d7.sorted() to 7,
                    d8.sorted() to 8,
                    d9.sorted() to 9,
                    d0.sorted() to 0,
                ))
            }

            private infix fun String.minusSegmentsOf(other: String): Set<Char> = this.toSet() - other.toSet()

            private infix fun String.overlapsWith(other: String): Boolean = (this.toSet() - other.toSet()).isEmpty()

            private fun String.sorted(): String = this.toSortedSet().joinToString("")
        }
    }
}
