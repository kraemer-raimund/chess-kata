package org.chess.ruleengine;

import org.chess.domain.GameState;
import org.chess.domain.Move;
import org.chess.domain.RuleViolation;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class RuleEngine {

    public MoveResult execute(Move move, GameState gameState) {
        var ruleViolations = applyRules(move, gameState);
        if (!ruleViolations.isEmpty()) {
            return new MoveResult(gameState, ruleViolations);
        }

        var gameStateAfter = movePiece(move, gameState);
        return new MoveResult(gameStateAfter, List.of());
    }

    private Collection<RuleViolation> applyRules(Move move, GameState gameState) {
        if (!gameState.chessPiecePositions().containsKey(move.from())) {
            return List.of(RuleViolation.NO_PIECE_AT_SOURCE_POSITION);
        }
        return List.of();
    }

    private GameState movePiece(Move move, GameState gameState) {
        var chessPiecePositions = new HashMap<>(gameState.chessPiecePositions());
        var chessPiece = chessPiecePositions.get(move.from());
        chessPiecePositions.remove(move.from());
        chessPiecePositions.put(move.to(), chessPiece);
        return new GameState(chessPiecePositions);
    }
}
