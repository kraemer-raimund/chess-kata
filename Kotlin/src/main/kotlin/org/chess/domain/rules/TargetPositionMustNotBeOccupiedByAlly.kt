package org.chess.domain.rules

import org.chess.domain.GameState
import org.chess.domain.Move
import org.chess.domain.RuleViolation

class TargetPositionMustNotBeOccupiedByAlly : Rule {

    override fun execute(move: Move, gameState: GameState): RuleViolation? {
        val chessPieceAtTargetPosition = gameState
            .chessPiecePositions
            .getOrDefault(move.to, null)
            ?: return null

        return if (chessPieceAtTargetPosition.color === gameState.currentPlayer) {
            RuleViolation.TARGET_POSITION_OCCUPIED_BY_ALLY
        } else {
            null
        }
    }
}
