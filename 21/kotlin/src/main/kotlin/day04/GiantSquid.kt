package day04

import utils.readFileByLine

class GiantSquid {
    fun calculateScores(bingoInput: List<String>, part: Int): Int {
        val drawnNumbers: List<String> = bingoInput[0].split(',')
        val boards: List<Board> = parseBoards(bingoInput.subList(2, bingoInput.size))

        if(part == 1) {
            return calculateWinningScore(drawnNumbers, boards)
        }

        return calculateLosingScore(drawnNumbers, boards)
    }

    private fun calculateWinningScore(drawnNumbers: List<String>, boards: List<Board>): Int {
        val winner: Pair<Int, Board> = findWinningBoard(drawnNumbers, boards)

        return winner.first * winner.second.calculateUnmarked()
    }

    private fun calculateLosingScore(drawnNumbers: List<String>, boards: List<Board>): Int {
        val loser: Pair<Int, Board> = findLosingBoard(drawnNumbers, boards)

        return loser.first * loser.second.calculateUnmarked()
    }

    private fun parseBoards(bingoInput: List<String>): List<Board> {
        val blanksCount: Int = bingoInput.count { it.isEmpty() }
        val boardSize: Int = (bingoInput.size - blanksCount) / (blanksCount + 1)

        return bingoInput
            .filter{ !it.isEmpty() }
            .chunked(boardSize)
            .mapIndexed{ index, it ->  Board(index, it, boardSize) }
    }

    private fun findWinningBoard(drawnNumbers: List<String>, boards: List<Board>): Pair<Int, Board> {
        for(d in drawnNumbers) {
            for(b in boards) {
                b.rows = applyDrawnNumber(b.rows, d)

                if(b.checkBoardHasWon()) {
                    return Pair(d.toInt(), b)
                }
            }
        }

        return Pair(0, Board(0,
            listOf(), 0))
    }

    private fun findLosingBoard(drawnNumbers: List<String>, boards: List<Board>): Pair<Int, Board> {
        val boardsCopy: MutableList<Board> = mutableListOf()

        for(d in drawnNumbers) {
            for(b in boards) {
                b.rows = applyDrawnNumber(b.rows, d)

                if(b.checkBoardHasWon() && boardsCopy.count { it.index == b.index } == 0) {
                    boardsCopy.add(b)

                    if(boardsCopy.size == boards.size) {
                        return Pair(d.toInt(), boardsCopy.last())
                    }
                }
            }
        }

        return Pair(0, Board(0, listOf(), 0))
    }

    private fun applyDrawnNumber(rows: List<String>, drawnNumber: String): List<String> {
        return rows.map {
            it.split("[ ]+".toRegex())
                .filter { !it.isEmpty() }
                .map { l -> if (l == drawnNumber) 'X' else l }
                .joinToString(separator = " ")
        }
    }
}

data class Board(var index: Int, var rows: List<String>, val size: Int) {
    fun checkBoardHasWon(): Boolean {
        for(i in rows.indices) {
            val horizontal: Int = rows[i].count{ "X".contains(it) }
            val vertical: Int = rows.map {  it.split("[ ]+".toRegex())[i] }.count { it == "X" }

            if(horizontal == size || vertical == size) {
                return true
            }
        }

        return false
    }

    fun calculateUnmarked(): Int {
        return rows.sumOf {
            it.split("[ ]+".toRegex())
                .filter { it != "X" }
                .sumOf { it.toInt() }
        }
    }
}

fun main() {
    val bingoInput = readFileByLine("src/main/resources/day04/input.txt")
    val giantSquid = GiantSquid()

    println("Part 1: " + giantSquid.calculateScores(bingoInput, 1))
    println("Part 2: " + giantSquid.calculateScores(bingoInput, 2))
}