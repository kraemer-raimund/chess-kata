package org.chess.input;

import org.chess.domain.Move;
import org.chess.domain.Position;

import java.util.Locale;

public class MoveCommandParser {

    public Move parse(String moveCommand) {
        final var canonicalFormat = toCanonicalFormat(moveCommand);
        requireCorrectFormatOrThrow(canonicalFormat, moveCommand);
        return parseFromCanonicalFormat(canonicalFormat);
    }

    private static String toCanonicalFormat(String moveCommand) {
        return moveCommand
                .replace(" ", "")
                .toLowerCase(Locale.ENGLISH);
    }

    private static void requireCorrectFormatOrThrow(String canonicalMoveCommand, String originalMoveCommand) {
        final var pattern = "[a-h][1-8]->[a-h][1-8]";
        if (!canonicalMoveCommand.matches(pattern)) {
            throw new IllegalArgumentException(
                    String.format("Invalid move command: `%s`", originalMoveCommand));
        }
    }

    private static Move parseFromCanonicalFormat(String canonicalMoveCommand) {
        final var positionFrom = new Position(
                canonicalMoveCommand.charAt(0) - 'a' + 1,
                canonicalMoveCommand.charAt(1) - '1' + 1
        );
        final var positionTo = new Position(
                canonicalMoveCommand.charAt(4) - 'a' + 1,
                canonicalMoveCommand.charAt(5) - '1' + 1
        );
        return new Move(positionFrom, positionTo);
    }
}
