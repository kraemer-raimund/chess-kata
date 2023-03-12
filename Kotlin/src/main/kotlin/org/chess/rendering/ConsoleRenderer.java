package org.chess.rendering;

import org.chess.domain.ChessPiece;
import org.chess.domain.GameState;
import org.chess.domain.PlayerColor;
import org.chess.domain.Position;

public class ConsoleRenderer {

    private static final String EMPTY_BOARD = """
                        
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
            """;

    private final char[] RENDER_BUFFER = new char[EMPTY_BOARD.length()];

    public String render(GameState gameState) {
        renderEmptyBoard();
        renderChessPieces(gameState);

        return new String(RENDER_BUFFER);
    }

    private void renderEmptyBoard() {
        System.arraycopy(EMPTY_BOARD.toCharArray(), 0, RENDER_BUFFER, 0, RENDER_BUFFER.length);
    }

    private void renderChessPieces(GameState gameState) {
        gameState.chessPiecePositions().forEach(this::renderChessPiece);
    }

    private void renderChessPiece(Position position, ChessPiece chessPiece) {
        final int horizontalOffsetForLabels = 5;
        final int squareWidth = 6;
        final int squareHeight = 2;
        final int horizontalOffsetWithinSquare = 4;

        // Rows on board are counted from bottom, but in the buffer from top.
        final int rowFromTop = 8 - position.row();

        final int initialEmptyLines = 1;
        final int line = initialEmptyLines + rowFromTop * squareHeight;
        final int offsetWithinLine = horizontalOffsetForLabels + (position.column() - 1) * squareWidth + horizontalOffsetWithinSquare;
        final int charsPerLine = 55;
        final int index = line * charsPerLine + offsetWithinLine;

        RENDER_BUFFER[index] = toChar(chessPiece);
    }

    private char toChar(ChessPiece chessPiece) {
        return switch (chessPiece.name()) {
            case PAWN -> chessPiece.color() == PlayerColor.WHITE ? '♙' : '♟';
            case ROOK -> chessPiece.color() == PlayerColor.WHITE ? '♖' : '♜';
            case KNIGHT -> chessPiece.color() == PlayerColor.WHITE ? '♘' : '♞';
            case BISHOP -> chessPiece.color() == PlayerColor.WHITE ? '♗' : '♝';
            case QUEEN -> chessPiece.color() == PlayerColor.WHITE ? '♕' : '♛';
            case KING -> chessPiece.color() == PlayerColor.WHITE ? '♔' : '♚';
        };
    }
}
