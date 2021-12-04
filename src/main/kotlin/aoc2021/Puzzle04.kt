package aoc2021

import aoc2021.Matrix.transpose

object Puzzle04 {
    data class Board(val grid: List<List<Int?>>) {
        val allUnmarked: List<Int> get() = grid.flatMap { row -> row.filterNotNull() }

        fun mark(num: Int) = this.copy(grid = grid.map { row -> row.map { cell -> if (cell == num) null else cell } })

        fun isWinner(): Boolean =
            grid.any { row -> row.allMarked() } || grid.transpose(-1).any { col -> col.allMarked() }

        private fun List<Int?>.allMarked() = this.filterNotNull().isEmpty()

        companion object {
            fun load(input: List<String>) = Board(input.map { it.trim().split(" +".toRegex()).map(String::toInt) })
        }
    }

    data class Game(val boards: List<Board>) {
        val hasWinner: Boolean get() = boards.any(Board::isWinner)
        val winnerBoard: Board? get() = boards.find(Board::isWinner)

        fun mark(num: Int) = this.copy(boards = boards.map { it.mark(num) })

        companion object {
            fun load(input: List<String>): Game {
                var boards = emptyList<Board>()
                var rest = input

                do {
                    rest = rest.drop(1)
                    boards = boards + Board.load(rest.take(5))
                    rest = rest.drop(5)
                } while (rest.isNotEmpty())

                return Game(boards)
            }
        }
    }

    fun playGame(input: List<String>): Int {
        val numbers = input.take(1).flatMap { it.split(",").map(String::toInt) }
        val startGame = Game.load(input.drop(1))
        var lastNumber: Int? = null

        val stopGame = numbers.fold(startGame) { acc, num ->
            if (acc.hasWinner)
                acc
            else {
                lastNumber = num
                acc.mark(num)
            }
        }

        return (stopGame.winnerBoard?.allUnmarked?.sum() ?: 1) * (lastNumber ?: 1)
    }
}
