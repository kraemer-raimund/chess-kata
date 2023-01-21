package org.chess.presentation.rendering.console;

import org.chess.domain.GameState;

public class ConsoleBoardRenderer {

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

    private final char[] renderBuffer = new char[EMPTY_BOARD.length()];

    public String render(GameState gameState) {
        renderEmptyBoard();

        return new String(renderBuffer);
    }

    private void renderEmptyBoard() {
        System.arraycopy(EMPTY_BOARD.toCharArray(), 0, renderBuffer, 0, renderBuffer.length);
    }
}
