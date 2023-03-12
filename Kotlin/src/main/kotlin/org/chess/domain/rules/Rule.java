package org.chess.domain.rules;

import org.chess.domain.GameState;
import org.chess.domain.Move;
import org.chess.domain.RuleViolation;

import java.util.Optional;

public interface Rule {

    Optional<RuleViolation> execute(Move move, GameState gameState);
}
