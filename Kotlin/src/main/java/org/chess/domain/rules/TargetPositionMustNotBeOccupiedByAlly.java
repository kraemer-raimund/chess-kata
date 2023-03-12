package org.chess.domain.rules;

import org.chess.domain.ChessPiece;
import org.chess.domain.GameState;
import org.chess.domain.Move;
import org.chess.domain.RuleViolation;

import java.util.Optional;

public class TargetPositionMustNotBeOccupiedByAlly implements Rule {

    @Override
    public Optional<RuleViolation> execute(Move move, GameState gameState) {
        final ChessPiece chessPieceAtTargetPosition = gameState
                .chessPiecePositions()
                .getOrDefault(move.to(), null);

        if (chessPieceAtTargetPosition != null && chessPieceAtTargetPosition.color() == gameState.currentPlayer()) {
            return Optional.of(RuleViolation.TARGET_POSITION_OCCUPIED_BY_ALLY);
        }
        return Optional.empty();
    }
}
