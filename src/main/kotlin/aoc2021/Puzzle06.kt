package aoc2021

object Puzzle06 {
    fun naiveCountFishAfterDays(days: Int, input: String): Int {
        val start = parseInitialState(input)

        val fishAfterDays = (1..days).fold(start) { acc, _ ->
            val nextDay = acc.map { if (it - 1 < 0) 6 else it - 1 }
            val newBorn = acc.filter { it == 0 }.map { 8 }
            nextDay + newBorn
        }

        return fishAfterDays.size
    }

    fun optimisedCountFishAfterDays(days: Int, input: String): Long {
        val initialState = parseInitialState(input)
        val fishDaysLeft = MutableList<Long>(9) { idx -> initialState.count { it == idx }.toLong() }

        repeat(days) {
            val toRespawn = fishDaysLeft.removeAt(0)
            fishDaysLeft[6] = fishDaysLeft[6] + toRespawn
            fishDaysLeft.add(toRespawn)
        }

        return fishDaysLeft.sum()
    }

    private fun parseInitialState(input: String) = input.split(",").map(String::toInt)
}
