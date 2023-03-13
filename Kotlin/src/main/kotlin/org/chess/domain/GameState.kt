package org.chess.domain

data class GameState(val chessPiecePositions: Map<Position, ChessPiece>, val currentPlayer: PlayerColor) {

    constructor(chessPiecePositions: Map<Position, ChessPiece>) : this(chessPiecePositions, PlayerColor.WHITE)

    companion object {
        fun initialState(): GameState {
            return GameState(initialPositions(), PlayerColor.WHITE)
        }

        private fun initialPositions(): Map<Position, ChessPiece> {
            return mapOf(
                Pair(Position(1, 1), ChessPiece(ChessPieceName.ROOK, PlayerColor.WHITE)),
                Pair(Position(2, 1), ChessPiece(ChessPieceName.KNIGHT, PlayerColor.WHITE)),
                Pair(Position(3, 1), ChessPiece(ChessPieceName.BISHOP, PlayerColor.WHITE)),
                Pair(Position(4, 1), ChessPiece(ChessPieceName.QUEEN, PlayerColor.WHITE)),
                Pair(Position(5, 1), ChessPiece(ChessPieceName.KING, PlayerColor.WHITE)),
                Pair(Position(6, 1), ChessPiece(ChessPieceName.BISHOP, PlayerColor.WHITE)),
                Pair(Position(7, 1), ChessPiece(ChessPieceName.KNIGHT, PlayerColor.WHITE)),
                Pair(Position(8, 1), ChessPiece(ChessPieceName.ROOK, PlayerColor.WHITE)),
                Pair(Position(1, 2), ChessPiece(ChessPieceName.PAWN, PlayerColor.WHITE)),
                Pair(Position(2, 2), ChessPiece(ChessPieceName.PAWN, PlayerColor.WHITE)),
                Pair(Position(3, 2), ChessPiece(ChessPieceName.PAWN, PlayerColor.WHITE)),
                Pair(Position(4, 2), ChessPiece(ChessPieceName.PAWN, PlayerColor.WHITE)),
                Pair(Position(5, 2), ChessPiece(ChessPieceName.PAWN, PlayerColor.WHITE)),
                Pair(Position(6, 2), ChessPiece(ChessPieceName.PAWN, PlayerColor.WHITE)),
                Pair(Position(7, 2), ChessPiece(ChessPieceName.PAWN, PlayerColor.WHITE)),
                Pair(Position(8, 2), ChessPiece(ChessPieceName.PAWN, PlayerColor.WHITE)),
                Pair(Position(1, 7), ChessPiece(ChessPieceName.PAWN, PlayerColor.BLACK)),
                Pair(Position(2, 7), ChessPiece(ChessPieceName.PAWN, PlayerColor.BLACK)),
                Pair(Position(3, 7), ChessPiece(ChessPieceName.PAWN, PlayerColor.BLACK)),
                Pair(Position(4, 7), ChessPiece(ChessPieceName.PAWN, PlayerColor.BLACK)),
                Pair(Position(5, 7), ChessPiece(ChessPieceName.PAWN, PlayerColor.BLACK)),
                Pair(Position(6, 7), ChessPiece(ChessPieceName.PAWN, PlayerColor.BLACK)),
                Pair(Position(7, 7), ChessPiece(ChessPieceName.PAWN, PlayerColor.BLACK)),
                Pair(Position(8, 7), ChessPiece(ChessPieceName.PAWN, PlayerColor.BLACK)),
                Pair(Position(1, 8), ChessPiece(ChessPieceName.ROOK, PlayerColor.BLACK)),
                Pair(Position(2, 8), ChessPiece(ChessPieceName.KNIGHT, PlayerColor.BLACK)),
                Pair(Position(3, 8), ChessPiece(ChessPieceName.BISHOP, PlayerColor.BLACK)),
                Pair(Position(4, 8), ChessPiece(ChessPieceName.QUEEN, PlayerColor.BLACK)),
                Pair(Position(5, 8), ChessPiece(ChessPieceName.KING, PlayerColor.BLACK)),
                Pair(Position(6, 8), ChessPiece(ChessPieceName.BISHOP, PlayerColor.BLACK)),
                Pair(Position(7, 8), ChessPiece(ChessPieceName.KNIGHT, PlayerColor.BLACK)),
                Pair(Position(8, 8), ChessPiece(ChessPieceName.ROOK, PlayerColor.BLACK))
            )
        }
    }
}
