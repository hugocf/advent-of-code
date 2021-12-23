package aoc2021

typealias Matrix<T> = List<List<T>>

data class Point(val row: Int, val col: Int)

fun <T> Matrix<T>.valuesAdjacentTo(row: Int, col: Int): Set<T> =
    pointsAdjacentTo(Point(row, col)).map { this[it] }.toSet()

fun <T> Matrix<T>.pointsAdjacentTo(p: Point): Set<Point> = setOfNotNull(
    p.copy(row = p.row - 1).let { if (this.containsPoint(it)) it else null },
    p.copy(row = p.row + 1).let { if (this.containsPoint(it)) it else null },
    p.copy(col = p.col - 1).let { if (this.containsPoint(it)) it else null },
    p.copy(col = p.col + 1).let { if (this.containsPoint(it)) it else null },
)

fun <T> Matrix<T>.containsPoint(p: Point): Boolean =
    (p.row in 0..this.lastIndex) && (p.col in 0..this[0].lastIndex)

operator fun <T> Matrix<T>.get(p: Point): T = this[p.row][p.col]

fun <T> Matrix<T>.transpose(init: T): Matrix<T> {
    val rows = this.size
    val cols = this.first().size
    val transposed = MutableList(cols) { MutableList(rows) { init } }

    transposed.forEachIndexed { row, line ->
        line.forEachIndexed { col, _ ->
            transposed[row][col] = this[col][row]
        }
    }

    return transposed
}
