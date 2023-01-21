package org.chess.presentation.rendering.console;

import org.chess.domain.GameState;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ConsoleBoardRendererTest {

    @Test
    void shouldRenderEmptyBoard() {
        final var renderer = new ConsoleBoardRenderer();
        final var gameState = new GameState(Map.of());

        var actual = renderer.render(gameState);
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

    @Test
    void shouldRenderInitialPositions() {
        final var renderer = new ConsoleBoardRenderer();
        final var gameState = GameState.initialState();

        var actual = renderer.render(gameState);
        var expected = """
                                   
                     ╔═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╗
                  8  ║  ♜  ║  ♞  ║  ♝  ║  ♛  ║  ♚  ║  ♝  ║  ♞  ║  ♜  ║
                     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
                  7  ║  ♟  ║  ♟  ║  ♟  ║  ♟  ║  ♟  ║  ♟  ║  ♟  ║  ♟  ║
                     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
                  6  ║     ║     ║     ║     ║     ║     ║     ║     ║
                     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
                  5  ║     ║     ║     ║     ║     ║     ║     ║     ║
                     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
                  4  ║     ║     ║     ║     ║     ║     ║     ║     ║
                     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
                  3  ║     ║     ║     ║     ║     ║     ║     ║     ║
                     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
                  2  ║  ♙  ║  ♙  ║  ♙  ║  ♙  ║  ♙  ║  ♙  ║  ♙  ║  ♙  ║
                     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
                  1  ║  ♖  ║  ♘  ║  ♗  ║  ♕  ║  ♔  ║  ♗  ║  ♘  ║  ♖  ║
                     ╚═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╝
                        a     b     c     d     e     f     g     h
                """;

        assertThat(actual).isEqualTo(expected);
    }
}
