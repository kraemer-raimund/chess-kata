package org.chess.domain.rules;

import org.chess.domain.*;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class SourcePositionMustNotBeEmptyTest {

    @Test
    void shouldRejectMoveWithEmptySourcePosition() {
        // No chess pieces at any position.
        var gameState = new GameState(Map.of());
        var rule = new SourcePositionMustNotBeEmpty();

        var move = new Move(new Position(0, 0), new Position(0, 0));
        var ruleViolation = rule.execute(move, gameState);

        assertThat(ruleViolation).isPresent();
        assertThat(ruleViolation.get()).isEqualTo(RuleViolation.NO_PIECE_AT_SOURCE_POSITION);
    }

    @Test
    void shouldNotRejectMoveWithChessPieceAtSourcePosition() {
        var gameState = new GameState(Map.of(
                new Position(0, 1), new ChessPiece(ChessPieceName.PAWN, PlayerColor.WHITE)));
        var rule = new SourcePositionMustNotBeEmpty();

        var move = new Move(new Position(0, 1), new Position(0, 5));
        var ruleViolation = rule.execute(move, gameState);

        assertThat(ruleViolation).isNotPresent();
    }
}
