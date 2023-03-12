package org.chess.ruleengine;

import org.chess.domain.GameState;
import org.chess.domain.RuleViolation;

import java.util.Collection;

public record MoveResult(GameState gameState, Collection<RuleViolation> violations) {
}
