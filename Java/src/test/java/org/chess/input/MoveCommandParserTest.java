package org.chess.input;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.List;

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
                assertThatExceptionOfType(ParseException.class).isThrownBy(() -> parser.parse(input))
        );
    }
}
