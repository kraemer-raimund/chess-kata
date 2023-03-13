package org.chess.domain.rules

import org.assertj.core.api.Assertions.*
import org.chess.domain.*
import org.junit.jupiter.api.Test

internal class TargetPositionMustNotBeOccupiedByAllyTest {

    @Test
    fun shouldRejectMoveToPositionOccupiedByAlly() {
        val positionFrom = Position(3, 3)
        val positionTo = Position(4, 4)
        val whiteBishopToMove = ChessPiece(ChessPieceName.BISHOP, PlayerColor.WHITE)
        val whitePawn = ChessPiece(ChessPieceName.PAWN, PlayerColor.WHITE)
        val gameState = GameState(
            mapOf(
                Pair(positionFrom, whiteBishopToMove),
                Pair(positionTo, whitePawn)
            )
        )

        val move = Move(positionFrom, positionTo)
        val rule = TargetPositionMustNotBeOccupiedByAlly()
        val ruleViolation = rule.execute(move, gameState)

        assertThat(ruleViolation).isEqualTo(RuleViolation.TARGET_POSITION_OCCUPIED_BY_ALLY)
    }

    @Test
    fun shouldNotRejectMoveToEmptyPosition() {
        val positionFrom = Position(3, 3)
        val positionTo = Position(4, 4)
        val whiteBishopToMove = ChessPiece(ChessPieceName.BISHOP, PlayerColor.WHITE)
        val gameState = GameState(
            mapOf(
                Pair(positionFrom, whiteBishopToMove)
            )
        )

        val move = Move(positionFrom, positionTo)
        val rule = TargetPositionMustNotBeOccupiedByAlly()
        val ruleViolation = rule.execute(move, gameState)

        assertThat(ruleViolation).isNull()
    }
}
