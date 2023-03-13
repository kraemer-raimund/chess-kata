package org.chess.ruleengine

import org.chess.domain.GameState
import org.chess.domain.Move
import org.chess.domain.PlayerColor
import org.chess.domain.RuleViolation
import org.chess.domain.rules.SourcePositionMustNotBeEmpty
import org.chess.domain.rules.TargetPositionMustNotBeOccupiedByAlly
import java.util.*

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
        val ruleViolations = rules
            .map { it.execute(move, gameState) }
            .filter { it.isPresent }
            .map { obj: Optional<RuleViolation> -> obj.get() }
        return ruleViolations.toList()
    }

    private fun movePiece(move: Move, gameState: GameState): GameState {
        val chessPiecePositions = HashMap(gameState.chessPiecePositions)
        val chessPiece = chessPiecePositions[move.from]
        chessPiecePositions.remove(move.from)
        chessPiecePositions[move.to] = chessPiece
        return GameState(
            chessPiecePositions, nextPlayer(gameState.currentPlayer)
        )
    }

    private fun nextPlayer(currentPlayer: PlayerColor): PlayerColor {
        return if (currentPlayer === PlayerColor.WHITE) PlayerColor.BLACK else PlayerColor.WHITE
    }
}
