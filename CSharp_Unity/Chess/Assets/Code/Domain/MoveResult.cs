using System.Collections.Generic;

namespace ChessKata.Domain
{
    public class MoveResult
    {
        public MoveResult(IEnumerable<RuleViolation> violations, GameState gameState)
        {
            Violations = violations;
            GameState = gameState;
        }

        public IEnumerable<RuleViolation> Violations { get; }
        public GameState GameState { get; }
    }
}