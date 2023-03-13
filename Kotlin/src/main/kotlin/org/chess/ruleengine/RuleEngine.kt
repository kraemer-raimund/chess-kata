package org.chess.ruleengine

import org.chess.domain.GameState
import org.chess.domain.Move
import org.chess.domain.PlayerColor
import org.chess.domain.RuleViolation
import org.chess.domain.rules.SourcePositionMustNotBeEmpty
import org.chess.domain.rules.TargetPositionMustNotBeOccupiedByAlly

class RuleEngine {

    private val rules = listOf(
        SourcePositionMustNotBeEmpty(),
        TargetPositionMustNotBeOccupiedByAlly()
    )

    fun execute(move: Move, gameState: GameState): MoveResult {
        val ruleViolations = applyRules(move, gameState)
        if (!ruleViolations.isEmpty()) {
            return MoveResult(gameState, ruleViolations)
        }
        val gameStateAfter = movePiece(move, gameState)
        return MoveResult(gameStateAfter, listOf())
    }

    private fun applyRules(move: Move, gameState: GameState): Collection<RuleViolation> {
        return rules.mapNotNull { it.execute(move, gameState) }
    }

    private fun movePiece(move: Move, gameState: GameState): GameState {
        val chessPiecePositions = gameState.chessPiecePositions.toMutableMap()

        // Assert not null, since a missing chess piece at that position must
        // have already been caught by a rule.
        val chessPiece = chessPiecePositions[move.from]!!

        chessPiecePositions.remove(move.from)
        chessPiecePositions[move.to] = chessPiece

        return GameState(chessPiecePositions, nextPlayer(gameState.currentPlayer))
    }

    private fun nextPlayer(currentPlayer: PlayerColor): PlayerColor {
        return if (currentPlayer === PlayerColor.WHITE) PlayerColor.BLACK else PlayerColor.WHITE
    }
}
