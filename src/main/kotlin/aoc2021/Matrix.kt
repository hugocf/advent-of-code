package aoc2021

object Matrix {
    fun <T> List<List<T>>.transpose(init: T): List<List<T>> {
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
}
