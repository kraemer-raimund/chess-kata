package org.chess.domain.rules

import org.assertj.core.api.Assertions
import org.chess.domain.*
import org.junit.jupiter.api.Test

internal class SourcePositionMustNotBeEmptyTest {

    @Test
    fun shouldRejectMoveWithEmptySourcePosition() {
        // No chess pieces at any position.
        val gameState = GameState(emptyMap())

        val rule = SourcePositionMustNotBeEmpty()
        val move = Move(Position(0, 0), Position(0, 0))
        val ruleViolation = rule.execute(move, gameState)

        Assertions.assertThat(ruleViolation).isPresent
        Assertions.assertThat(ruleViolation.get()).isEqualTo(RuleViolation.NO_PIECE_AT_SOURCE_POSITION)
    }

    @Test
    fun shouldNotRejectMoveWithChessPieceAtSourcePosition() {
        val position = Position(0, 1)
        val chessPiece = ChessPiece(ChessPieceName.PAWN, PlayerColor.WHITE)
        val gameState = GameState(
            mapOf(Pair(position, chessPiece))
        )

        val rule = SourcePositionMustNotBeEmpty()
        val move = Move(Position(0, 1), Position(0, 5))
        val ruleViolation = rule.execute(move, gameState)

        Assertions.assertThat(ruleViolation).isNotPresent
    }
}
