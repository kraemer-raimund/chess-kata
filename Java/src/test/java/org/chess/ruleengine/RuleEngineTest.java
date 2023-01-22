package org.chess.ruleengine;

import org.chess.domain.*;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RuleEngineTest {

    @Test
    void shouldRejectMoveIfAnyRuleIsViolated() {
        final var ruleEngine = new RuleEngine();
        final var gameStateBefore = new GameState(Map.of());

        // A guaranteed illegal move (there is no piece at that position, and the target position
        // is the same as the source position). At this abstraction level, we don't care about how
        // the rule is checked or which rules exist, only that the rule engine rejects illegal
        // moves. Concrete rules are checked individually at the lower abstraction level. We do not
        // want to have to change existing higher level tests when adding lower level rules.
        final var move = new Move(new Position(0, 0), new Position(0, 0));
        final var moveResult = ruleEngine.execute(move, gameStateBefore);

        assertThat(moveResult.violations()).isNotEmpty();
        assertThat(moveResult.gameState()).isEqualTo(gameStateBefore);
    }

    @Test
    void shouldMoveChessPieceIfMoveIsValid() {
        final var whitePawn = new ChessPiece(ChessPieceName.PAWN, PlayerColor.WHITE);
        final var currentPlayerBefore = PlayerColor.WHITE;
        final var positionBefore = new Position(1, 2);
        final var positionAfter = new Position(1, 3);
        final var gameStateBefore = new GameState(
                Map.of(positionBefore, whitePawn), currentPlayerBefore);

        final var move = new Move(positionBefore, positionAfter);

        final var ruleEngine = new RuleEngine();
        final var moveResult = ruleEngine.execute(move, gameStateBefore);

        assertThat(moveResult.violations()).isEmpty();

        final var chessPiecePositionsAfter = moveResult.gameState().chessPiecePositions();
        assertThat(chessPiecePositionsAfter).containsKey(positionAfter);
        assertThat(chessPiecePositionsAfter).doesNotContainKey(positionBefore);

        final var chessPieceAfter = chessPiecePositionsAfter.get(positionAfter);
        final var chessPieceBefore = gameStateBefore.chessPiecePositions().get(positionBefore);
        assertThat(chessPieceAfter).isEqualTo(chessPieceBefore);
    }

    @Test
    void shouldTogglePlayersAfterValidMove() {
        final var whitePawn = new ChessPiece(ChessPieceName.PAWN, PlayerColor.WHITE);
        final var currentPlayerBefore = PlayerColor.WHITE;
        final var positionBefore = new Position(1, 2);
        final var positionAfter = new Position(1, 3);
        final var gameStateBefore = new GameState(
                Map.of(positionBefore, whitePawn), currentPlayerBefore);

        final var move = new Move(positionBefore, positionAfter);

        final var ruleEngine = new RuleEngine();
        final var moveResult = ruleEngine.execute(move, gameStateBefore);

        assertThat(moveResult.gameState().currentPlayer())
                .isNotEqualByComparingTo(currentPlayerBefore);
    }
}
