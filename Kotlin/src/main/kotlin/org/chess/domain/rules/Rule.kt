package org.chess.domain.rules

import org.chess.domain.GameState
import org.chess.domain.Move
import org.chess.domain.RuleViolation
import java.util.Optional

interface Rule {

    fun execute(move: Move, gameState: GameState) : Optional<RuleViolation>
}
