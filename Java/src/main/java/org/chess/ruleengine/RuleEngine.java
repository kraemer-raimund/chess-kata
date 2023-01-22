package org.chess.ruleengine;

import org.chess.domain.GameState;
import org.chess.domain.Move;
import org.chess.domain.RuleViolation;
import org.chess.domain.rules.Rule;
import org.chess.domain.rules.SourcePositionMustNotBeEmpty;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class RuleEngine {

    private final List<Rule> rules = List.of(
            new SourcePositionMustNotBeEmpty()
    );

    public MoveResult execute(Move move, GameState gameState) {
        var ruleViolations = applyRules(move, gameState);
        if (!ruleViolations.isEmpty()) {
            return new MoveResult(gameState, ruleViolations);
        }

        var gameStateAfter = movePiece(move, gameState);
        return new MoveResult(gameStateAfter, List.of());
    }

    private Collection<RuleViolation> applyRules(Move move, GameState gameState) {
        var ruleViolations = rules.stream()
                .map(rule -> rule.execute(move, gameState))
                .filter(Optional::isPresent)
                .map(Optional::get);
        return ruleViolations.toList();
    }

    private GameState movePiece(Move move, GameState gameState) {
        var chessPiecePositions = new HashMap<>(gameState.chessPiecePositions());
        var chessPiece = chessPiecePositions.get(move.from());
        chessPiecePositions.remove(move.from());
        chessPiecePositions.put(move.to(), chessPiece);
        return new GameState(chessPiecePositions);
    }
}
