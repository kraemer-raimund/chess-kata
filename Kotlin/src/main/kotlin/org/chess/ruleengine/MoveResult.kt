package org.chess.ruleengine

import org.chess.domain.GameState
import org.chess.domain.RuleViolation

data class MoveResult(val gameState: GameState, val violations: Collection<RuleViolation>) {

    // Temporary backwards compatibility for usages from Java code that expect
    // an implicit get method from a Java record.
    fun gameState() = gameState
    fun violations() = violations
}
