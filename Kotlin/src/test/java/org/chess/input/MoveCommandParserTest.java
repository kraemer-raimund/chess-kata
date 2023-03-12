package org.chess.input;

import org.chess.domain.Move;
import org.chess.domain.Position;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class MoveCommandParserTest {

    @Test
    void shouldThrowExceptionForInvalidInput() {
        final var parser = new MoveCommandParser();
        final var invalidInputs = List.of(
                "asdf",
                "e0 -> e8",
                "e1 -> e9",
                "c1 -> i5",
                "c1 <- i5"
        );

        invalidInputs.forEach(input ->
                assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> parser.parse(input))
        );
    }

    @Test
    void shouldParseMoveCommand() {
        final var parser = new MoveCommandParser();
        final var expectedMovesByValidMoveCommand = Map.of(
                "a1 -> a2", new Move(new Position(1, 1), new Position(1, 2)),
                "f3   ->a7", new Move(new Position(6, 3), new Position(1, 7)),
                "h7->     h7", new Move(new Position(8, 7), new Position(8, 7)),
                "f3->a7", new Move(new Position(6, 3), new Position(1, 7))
        );

        expectedMovesByValidMoveCommand.forEach((input, expectedMove) ->
                assertThat(parser.parse(input)).isEqualTo(expectedMove)
        );
    }
}
