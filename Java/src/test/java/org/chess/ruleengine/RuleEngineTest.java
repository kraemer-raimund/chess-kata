package org.chess.ruleengine;

import org.chess.domain.GameState;
import org.chess.domain.Move;
import org.chess.domain.Position;
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
}
