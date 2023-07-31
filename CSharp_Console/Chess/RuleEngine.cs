namespace Chess.Domain
{
    public class RuleEngine
    {
        public RuleEngine()
        {
        }

        public MoveResult Execute(Move move, GameState gameState)
        {
            if (!gameState.ChessPiecesByPosition.ContainsKey(move.From))
            {
                var ruleViolations = new List<RuleViolation> { RuleViolation.NoPieceAtSourcePosition };
                return new(ruleViolations, gameState);
            }

            return new(Enumerable.Empty<RuleViolation>(), gameState);
        }
    }
}
