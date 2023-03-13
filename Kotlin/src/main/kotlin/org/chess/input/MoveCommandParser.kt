package org.chess.input

import org.chess.domain.Move
import org.chess.domain.Position

class MoveCommandParser {

    fun parse(moveCommand: String): Move {
        val canonicalFormat = toCanonicalFormat(moveCommand)
        requireCorrectFormatOrThrow(canonicalFormat, moveCommand)
        return parseFromCanonicalFormat(canonicalFormat)
    }

    private fun toCanonicalFormat(moveCommand: String): String {
        return moveCommand
            .replace(" ", "")
            .lowercase()
    }

    private fun requireCorrectFormatOrThrow(canonicalMoveCommand: String, originalMoveCommand: String) {
        val pattern = "[a-h][1-8]->[a-h][1-8]"
        require(canonicalMoveCommand.matches(pattern.toRegex())) {
            String.format("Invalid move command: `%s`", originalMoveCommand)
        }
    }

    private fun parseFromCanonicalFormat(canonicalMoveCommand: String): Move {
        val positionFrom = Position(
            canonicalMoveCommand[0].code - 'a'.code + 1,
            canonicalMoveCommand[1].code - '1'.code + 1
        )
        val positionTo = Position(
            canonicalMoveCommand[4].code - 'a'.code + 1,
            canonicalMoveCommand[5].code - '1'.code + 1
        )
        return Move(positionFrom, positionTo)
    }
}
