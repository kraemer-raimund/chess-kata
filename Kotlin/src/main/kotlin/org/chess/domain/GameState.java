package org.chess.domain;

import java.util.Map;

import static java.util.Map.entry;

public record GameState(Map<Position, ChessPiece> chessPiecePositions, PlayerColor currentPlayer) {

    public GameState(Map<Position, ChessPiece> chessPiecePositions) {
        this(chessPiecePositions, PlayerColor.WHITE);
    }

    public static GameState initialState() {
        return new GameState(initialPositions(), PlayerColor.WHITE);
    }

    private static Map<Position, ChessPiece> initialPositions() {
        return Map.ofEntries(
                entry(new Position(1, 1), new ChessPiece(ChessPieceName.ROOK, PlayerColor.WHITE)),
                entry(new Position(2, 1), new ChessPiece(ChessPieceName.KNIGHT, PlayerColor.WHITE)),
                entry(new Position(3, 1), new ChessPiece(ChessPieceName.BISHOP, PlayerColor.WHITE)),
                entry(new Position(4, 1), new ChessPiece(ChessPieceName.QUEEN, PlayerColor.WHITE)),
                entry(new Position(5, 1), new ChessPiece(ChessPieceName.KING, PlayerColor.WHITE)),
                entry(new Position(6, 1), new ChessPiece(ChessPieceName.BISHOP, PlayerColor.WHITE)),
                entry(new Position(7, 1), new ChessPiece(ChessPieceName.KNIGHT, PlayerColor.WHITE)),
                entry(new Position(8, 1), new ChessPiece(ChessPieceName.ROOK, PlayerColor.WHITE)),

                entry(new Position(1, 2), new ChessPiece(ChessPieceName.PAWN, PlayerColor.WHITE)),
                entry(new Position(2, 2), new ChessPiece(ChessPieceName.PAWN, PlayerColor.WHITE)),
                entry(new Position(3, 2), new ChessPiece(ChessPieceName.PAWN, PlayerColor.WHITE)),
                entry(new Position(4, 2), new ChessPiece(ChessPieceName.PAWN, PlayerColor.WHITE)),
                entry(new Position(5, 2), new ChessPiece(ChessPieceName.PAWN, PlayerColor.WHITE)),
                entry(new Position(6, 2), new ChessPiece(ChessPieceName.PAWN, PlayerColor.WHITE)),
                entry(new Position(7, 2), new ChessPiece(ChessPieceName.PAWN, PlayerColor.WHITE)),
                entry(new Position(8, 2), new ChessPiece(ChessPieceName.PAWN, PlayerColor.WHITE)),

                entry(new Position(1, 7), new ChessPiece(ChessPieceName.PAWN, PlayerColor.BLACK)),
                entry(new Position(2, 7), new ChessPiece(ChessPieceName.PAWN, PlayerColor.BLACK)),
                entry(new Position(3, 7), new ChessPiece(ChessPieceName.PAWN, PlayerColor.BLACK)),
                entry(new Position(4, 7), new ChessPiece(ChessPieceName.PAWN, PlayerColor.BLACK)),
                entry(new Position(5, 7), new ChessPiece(ChessPieceName.PAWN, PlayerColor.BLACK)),
                entry(new Position(6, 7), new ChessPiece(ChessPieceName.PAWN, PlayerColor.BLACK)),
                entry(new Position(7, 7), new ChessPiece(ChessPieceName.PAWN, PlayerColor.BLACK)),
                entry(new Position(8, 7), new ChessPiece(ChessPieceName.PAWN, PlayerColor.BLACK)),

                entry(new Position(1, 8), new ChessPiece(ChessPieceName.ROOK, PlayerColor.BLACK)),
                entry(new Position(2, 8), new ChessPiece(ChessPieceName.KNIGHT, PlayerColor.BLACK)),
                entry(new Position(3, 8), new ChessPiece(ChessPieceName.BISHOP, PlayerColor.BLACK)),
                entry(new Position(4, 8), new ChessPiece(ChessPieceName.QUEEN, PlayerColor.BLACK)),
                entry(new Position(5, 8), new ChessPiece(ChessPieceName.KING, PlayerColor.BLACK)),
                entry(new Position(6, 8), new ChessPiece(ChessPieceName.BISHOP, PlayerColor.BLACK)),
                entry(new Position(7, 8), new ChessPiece(ChessPieceName.KNIGHT, PlayerColor.BLACK)),
                entry(new Position(8, 8), new ChessPiece(ChessPieceName.ROOK, PlayerColor.BLACK))
        );
    }
}
