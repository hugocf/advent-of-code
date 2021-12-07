package aoc2021

import aoc2021.Matrix.transpose
import java.time.Instant

object Puzzle04 {
    data class Game(val boards: List<Board>) {
        val hasAllWinners: Boolean get() = boards.all(Board::isWinner)
        val hasOneWinner: Boolean get() = boards.any(Board::isWinner)
        val looserBoard: Board? get() = boards.filterNot { it.won == null }.maxByOrNull { it.won!! }
        val winnerBoard: Board? get() = boards.filterNot { it.won == null }.minByOrNull { it.won!! }

        fun mark(num: Int) = this.copy(boards = boards.map { it.mark(num) })

        companion object {
            fun load(input: List<String>): Pair<List<Int>, Game> {
                val numbers = input.take(1).flatMap { it.split(",").map(String::toInt) }
                val boards = input.drop(1).filter(String::isNotEmpty).chunked(5, Board::load)
                return numbers to Game(boards)
            }
        }
    }

    data class Board(val grid: List<List<Int?>>, val won: Instant? = null) {
        val allUnmarked: List<Int> get() = grid.flatMap { row -> row.filterNotNull() }

        fun mark(num: Int): Board {
            fun List<Int?>.allAreMarked() = this.filterNotNull().isEmpty()
            fun Board.hasFullRow() = this.grid.any { row -> row.allAreMarked() }
            fun Board.hasFullCol() = this.grid.transpose(0).any { col -> col.allAreMarked() }

            val marked = this.copy(grid = grid.map { row -> row.map { cell -> if (cell == num) null else cell } })
            val hasJustWon = !marked.isWinner() && (marked.hasFullRow() || marked.hasFullCol())
            return marked.copy(won = if (hasJustWon) Instant.now() else marked.won)
        }

        fun isWinner(): Boolean = this.won != null

        companion object {
            fun load(input: List<String>): Board = input.map { it.trim().split(" +".toRegex()).map(String::toInt) }.let(::Board)
        }
    }

    fun playGameToWin(input: List<String>) =
        playGame(input, shouldStop = Game::hasOneWinner, resultBoard = Game::winnerBoard)

    fun playGameToLoose(input: List<String>) =
        playGame(input, shouldStop = Game::hasAllWinners, resultBoard = Game::looserBoard)

    private fun playGame(input: List<String>, shouldStop: (Game) -> Boolean, resultBoard: (Game) -> Board?): Int {
        val (numbers, startGame) = Game.load(input)
        val stoppedGame = numbers.fold(0 to startGame) { acc, num ->
            if (shouldStop(acc.second)) acc else num to acc.second.mark(num)
        }
        return (resultBoard(stoppedGame.second)?.allUnmarked?.sum() ?: 1) * stoppedGame.first
    }
}
