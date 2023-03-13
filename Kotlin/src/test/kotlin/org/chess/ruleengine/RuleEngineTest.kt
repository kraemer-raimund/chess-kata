package org.chess.ruleengine

import org.assertj.core.api.Assertions.assertThat
import org.chess.domain.*
import org.junit.jupiter.api.Test

internal class RuleEngineTest {

    @Test
    fun shouldRejectMoveIfAnyRuleIsViolated() {
        val ruleEngine = RuleEngine()
        val gameStateBefore = GameState(emptyMap())

        // A guaranteed illegal move (there is no piece at that position, and the target position
        // is the same as the source position). At this abstraction level, we don't care about how
        // the rule is checked or which rules exist, only that the rule engine rejects illegal
        // moves. Concrete rules are checked individually at the lower abstraction level. We do not
        // want to have to change existing higher level tests when adding lower level rules.
        val move = Move(Position(0, 0), Position(0, 0))
        val (gameState, violations) = ruleEngine.execute(move, gameStateBefore)

        assertThat(violations).isNotEmpty
        assertThat(gameState).isEqualTo(gameStateBefore)
    }

    @Test
    fun shouldMoveChessPieceIfMoveIsValid() {
        val whitePawn = ChessPiece(ChessPieceName.PAWN, PlayerColor.WHITE)
        val currentPlayerBefore = PlayerColor.WHITE
        val positionBefore = Position(1, 2)
        val positionAfter = Position(1, 3)
        val gameStateBefore = GameState(
            mapOf(Pair(positionBefore, whitePawn)), currentPlayerBefore
        )

        val move = Move(positionBefore, positionAfter)
        val ruleEngine = RuleEngine()
        val (gameState, violations) = ruleEngine.execute(move, gameStateBefore)

        assertThat(violations).isEmpty()

        val chessPiecePositionsAfter = gameState.chessPiecePositions
        assertThat(chessPiecePositionsAfter).containsKey(positionAfter)
        assertThat(chessPiecePositionsAfter).doesNotContainKey(positionBefore)

        val chessPieceAfter = chessPiecePositionsAfter[positionAfter]
        val chessPieceBefore = gameStateBefore.chessPiecePositions[positionBefore]
        assertThat(chessPieceAfter).isEqualTo(chessPieceBefore)
    }

    @Test
    fun shouldTogglePlayersAfterValidMove() {
        val whitePawn = ChessPiece(ChessPieceName.PAWN, PlayerColor.WHITE)
        val currentPlayerBefore = PlayerColor.WHITE
        val positionBefore = Position(1, 2)
        val positionAfter = Position(1, 3)
        val gameStateBefore = GameState(
            mapOf(Pair(positionBefore, whitePawn)), currentPlayerBefore
        )

        val move = Move(positionBefore, positionAfter)
        val ruleEngine = RuleEngine()
        val (gameState) = ruleEngine.execute(move, gameStateBefore)

        assertThat(gameState.currentPlayer).isNotEqualTo(currentPlayerBefore)
    }
}
