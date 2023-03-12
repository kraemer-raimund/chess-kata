package org.chess.domain.rules;

import org.chess.domain.GameState;
import org.chess.domain.Move;
import org.chess.domain.RuleViolation;

import java.util.Optional;

public class SourcePositionMustNotBeEmpty implements Rule {

    @Override
    public Optional<RuleViolation> execute(Move move, GameState gameState) {
        if (!gameState.chessPiecePositions().containsKey(move.from())) {
            return Optional.of(RuleViolation.NO_PIECE_AT_SOURCE_POSITION);
        }
        return Optional.empty();
    }
}
