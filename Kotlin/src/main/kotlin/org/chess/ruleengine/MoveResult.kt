package org.chess.ruleengine

import org.chess.domain.GameState
import org.chess.domain.RuleViolation

data class MoveResult(val gameState: GameState, val violations: Collection<RuleViolation>)
