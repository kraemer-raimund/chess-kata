package org.chess.input

import org.assertj.core.api.Assertions
import org.chess.domain.Move
import org.chess.domain.Position
import org.junit.jupiter.api.Test

internal class MoveCommandParserTest {
    @Test
    fun shouldThrowExceptionForInvalidInput() {
        val parser = MoveCommandParser()
        val invalidInputs = listOf(
            "asdf",
            "e0 -> e8",
            "e1 -> e9",
            "c1 -> i5",
            "c1 <- i5"
        )

        invalidInputs.forEach { input ->
            Assertions.assertThatExceptionOfType(IllegalArgumentException::class.java)
                .isThrownBy { parser.parse(input) }
        }
    }

    @Test
    fun shouldParseMoveCommand() {
        val parser = MoveCommandParser()
        val expectedMovesByValidMoveCommand =
            mapOf(
                Pair("a1 -> a2", Move(Position(1, 1), Position(1, 2))),
                Pair("f3   ->a7", Move(Position(6, 3), Position(1, 7))),
                Pair("h7->     h7", Move(Position(8, 7), Position(8, 7))),
                Pair("f3->a7", Move(Position(6, 3), Position(1, 7)))
            )

        expectedMovesByValidMoveCommand.forEach { (input, expectedMove) ->
            val move = parser.parse(input)
            Assertions.assertThat(move).isEqualTo(expectedMove)
        }
    }
}
