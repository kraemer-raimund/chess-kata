package org.chess.rendering

import org.assertj.core.api.Assertions
import org.chess.domain.GameState
import org.junit.jupiter.api.Test

internal class ConsoleRendererTest {

    @Test
    fun shouldRenderEmptyBoard() {
        val renderer = ConsoleRenderer()
        val gameState = GameState(emptyMap())
        val actual = renderer.render(gameState)
        val expected = """
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

        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun shouldRenderInitialPositions() {
        val renderer = ConsoleRenderer()
        val gameState = GameState.initialState()
        val actual = renderer.render(gameState)
        val expected = """
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
"""

        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
