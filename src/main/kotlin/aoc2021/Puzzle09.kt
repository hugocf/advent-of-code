package aoc2021

object Puzzle09 {
    fun sumLowPointsRiskLevel(input: List<String>): Int {
        val matrix = parseHeatmap(input)

        return matrix.findLowPoints().sumOf { matrix[it] + 1 }
    }

    fun multiplyThreeLargestBasins(input: List<String>): Int {
        val matrix = parseHeatmap(input)

        return matrix
            .findLowPoints()
            .map { matrix.findBasin(it) }
            .sortedBy { it.size }
            .takeLast(3)
            .fold(1) { acc, el -> acc * el.size }
    }

    private fun parseHeatmap(input: List<String>): Matrix<Int> =
        input.map { line -> line.split("").filter { it.isNotEmpty() }.map { it.toInt() } }

    private fun Matrix<Int>.findLowPoints(): Set<Point> =
        this.mapIndexed { row, line ->
            List(line.size) { col ->
                if (this.valuesAdjacentTo(row, col).all { this[row][col] < it }) Point(row, col) else null
            }
        }.flatten().filterNotNull().toSet()

    private fun Matrix<Int>.findBasin(start: Point): Set<Point> {
        val isOutsideBasin = { p: Point -> this[p] == 9 }
        if (isOutsideBasin(start)) return emptySet()

        val basinPoints = mutableSetOf(start)
        val pointsToProcess = mutableSetOf(start)
        do {
            pointsToProcess += pointsToProcess.flatMap { this.pointsAdjacentTo(it) }
            pointsToProcess -= basinPoints
            pointsToProcess.removeAll(isOutsideBasin)
            basinPoints += pointsToProcess
        } while (pointsToProcess.isNotEmpty())

        return basinPoints
    }
}
