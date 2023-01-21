package org.chess.presentation.rendering.console;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConsoleBoardRendererTest {

    @Test
    void shouldRenderEmptyBoard() {
        final var renderer = new ConsoleBoardRenderer();
        final var board = new Board();

        var actual = renderer.renderBoard(board);
        var expected = """
                                   
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

        assertThat(actual).isEqualTo(expected);
    }
}
