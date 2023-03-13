package org.chess.rendering

import org.chess.domain.*

class ConsoleRenderer {

    private val renderBuffer = CharArray(EMPTY_BOARD.length)

    fun render(gameState: GameState): String {
        renderEmptyBoard()
        renderChessPieces(gameState)
        return String(renderBuffer)
    }

    private fun renderEmptyBoard() {
        System.arraycopy(EMPTY_BOARD.toCharArray(), 0, renderBuffer, 0, renderBuffer.size)
    }

    private fun renderChessPieces(gameState: GameState) {
        gameState
            .chessPiecePositions
            .forEach(::renderChessPiece)
    }

    private fun renderChessPiece(position: Position, chessPiece: ChessPiece) {
        val horizontalOffsetForLabels = 5
        val squareWidth = 6
        val squareHeight = 2
        val horizontalOffsetWithinSquare = 4

        // Rows on board are counted from bottom, but in the buffer from top.
        val rowFromTop = 8 - position.row
        val initialEmptyLines = 1
        val line = initialEmptyLines + rowFromTop * squareHeight
        val offsetWithinLine =
            horizontalOffsetForLabels + (position.column - 1) * squareWidth + horizontalOffsetWithinSquare
        val charsPerLine = 55
        val index = line * charsPerLine + offsetWithinLine
        renderBuffer[index] = toChar(chessPiece)
    }

    private fun toChar(chessPiece: ChessPiece): Char {
        return when (chessPiece.name) {
            ChessPieceName.PAWN -> if (chessPiece.color === PlayerColor.WHITE) '♙' else '♟'
            ChessPieceName.ROOK -> if (chessPiece.color === PlayerColor.WHITE) '♖' else '♜'
            ChessPieceName.KNIGHT -> if (chessPiece.color === PlayerColor.WHITE) '♘' else '♞'
            ChessPieceName.BISHOP -> if (chessPiece.color === PlayerColor.WHITE) '♗' else '♝'
            ChessPieceName.QUEEN -> if (chessPiece.color === PlayerColor.WHITE) '♕' else '♛'
            ChessPieceName.KING -> if (chessPiece.color === PlayerColor.WHITE) '♔' else '♚'
        }
    }

    companion object {
        private const val EMPTY_BOARD = """
     ╔═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╗
  8  ║     ║     ║     ║     ║     ║     ║     ║     ║
     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
  7  ║     ║     ║     ║     ║     ║     ║     ║     ║
     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
  6  ║     ║     ║     ║     ║     ║     ║     ║     ║
     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
  5  ║     ║     ║     ║     ║     ║     ║     ║     ║
     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
  4  ║     ║     ║     ║     ║     ║     ║     ║     ║
     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
  3  ║     ║     ║     ║     ║     ║     ║     ║     ║
     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
  2  ║     ║     ║     ║     ║     ║     ║     ║     ║
     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
  1  ║     ║     ║     ║     ║     ║     ║     ║     ║
     ╚═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╝
        a     b     c     d     e     f     g     h
"""
    }
}
