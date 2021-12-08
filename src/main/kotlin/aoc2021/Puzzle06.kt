package aoc2021

object Puzzle06 {
    fun countFishAfterDays(days: Int, input: String): Int {
        val initialState = input.split(",").map(String::toInt)
        val fishAfterDays = (1..days).fold(initialState) { acc, _ ->
            val nextDay = acc.map { if (it - 1 < 0) 6 else it - 1 }
            val newBorn = acc.filter { it == 0 }.map { 8 }
            nextDay + newBorn
        }
        return fishAfterDays.size
    }
}
