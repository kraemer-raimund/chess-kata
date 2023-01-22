package org.chess.domain.rules;

import org.chess.domain.*;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class TargetPositionMustNotBeOccupiedByAllyTest {

    @Test
    void shouldRejectMoveToPositionOccupiedByAlly() {
        final var positionFrom = new Position(3, 3);
        final var positionTo = new Position(4, 4);
        final var whiteBishopToMove = new ChessPiece(ChessPieceName.BISHOP, PlayerColor.WHITE);
        final var whitePawn = new ChessPiece(ChessPieceName.PAWN, PlayerColor.WHITE);

        final var gameState = new GameState(Map.of(
                positionFrom, whiteBishopToMove,
                positionTo, whitePawn));
        final var move = new Move(positionFrom, positionTo);

        final var rule = new TargetPositionMustNotBeOccupiedByAlly();
        final var ruleViolation = rule.execute(move, gameState);

        assertThat(ruleViolation).isPresent();
        assertThat(ruleViolation.get()).isEqualTo(RuleViolation.TARGET_POSITION_OCCUPIED_BY_ALLY);
    }

    @Test
    void shouldRejectMoveToEmptyPosition() {
        final var positionFrom = new Position(3, 3);
        final var positionTo = new Position(4, 4);
        final var whiteBishopToMove = new ChessPiece(ChessPieceName.BISHOP, PlayerColor.WHITE);

        final var gameState = new GameState(Map.of(positionFrom, whiteBishopToMove));
        final var move = new Move(positionFrom, positionTo);

        final var rule = new TargetPositionMustNotBeOccupiedByAlly();
        final var ruleViolation = rule.execute(move, gameState);

        assertThat(ruleViolation).isNotPresent();
    }
}
