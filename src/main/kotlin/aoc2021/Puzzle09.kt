package aoc2021

object Puzzle09 {
    fun sumLowPointsRiskLevel(input: List<String>): Int {
        val matrix = parseHeatmap(input)

        return matrix.asSequence().mapIndexed { row, line ->
            List(line.size) { col ->
                if (matrix.adjacent(row, col).all { matrix[row][col] < it }) matrix[row][col] else null
            }
        }.flatten().filterNotNull().map { it + 1 }.sum()
    }

    private fun parseHeatmap(input: List<String>): Matrix<Int> =
        input.map { line -> line.split("").filter { it.isNotEmpty() }.map { it.toInt() } }
}
