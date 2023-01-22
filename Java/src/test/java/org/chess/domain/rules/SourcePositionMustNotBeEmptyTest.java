package org.chess.domain.rules;

import org.chess.domain.GameState;
import org.chess.domain.Move;
import org.chess.domain.Position;
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
    }
}
