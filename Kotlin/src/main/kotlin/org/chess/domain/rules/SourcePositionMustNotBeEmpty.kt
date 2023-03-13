package org.chess.domain.rules

import org.chess.domain.GameState
import org.chess.domain.Move
import org.chess.domain.RuleViolation
import java.util.*

class SourcePositionMustNotBeEmpty : Rule {

    override fun execute(move: Move, gameState: GameState): Optional<RuleViolation> {
        if (!gameState.chessPiecePositions.containsKey(move.from())) {
            return Optional.of(RuleViolation.NO_PIECE_AT_SOURCE_POSITION)
        }

        return Optional.empty()
    }
}
