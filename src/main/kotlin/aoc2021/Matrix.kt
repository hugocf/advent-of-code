package aoc2021

typealias Matrix<T> = List<List<T>>

fun <T> Matrix<T>.adjacent(row: Int, col: Int): Set<T> = setOfNotNull(
    this.getOrNull(row - 1)?.getOrNull(col),
    this.getOrNull(row + 1)?.getOrNull(col),
    this.getOrNull(row)?.getOrNull(col - 1),
    this.getOrNull(row)?.getOrNull(col + 1),
)

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
