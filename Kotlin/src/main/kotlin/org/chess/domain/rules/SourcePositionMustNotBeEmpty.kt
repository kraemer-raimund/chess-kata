package org.chess.domain.rules

import org.chess.domain.GameState
import org.chess.domain.Move
import org.chess.domain.RuleViolation

class SourcePositionMustNotBeEmpty : Rule {

    override fun execute(move: Move, gameState: GameState): RuleViolation? {
        if (!gameState.chessPiecePositions.containsKey(move.from)) {
            return RuleViolation.NO_PIECE_AT_SOURCE_POSITION
        }

        return null
    }
}
