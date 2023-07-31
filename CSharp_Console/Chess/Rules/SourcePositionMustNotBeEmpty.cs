namespace Chess.Domain.Rules
{
    public class SourcePositionMustNotBeEmpty : IRule
    {
        public RuleViolation? Execute(Move move, GameState gameState)
        {
            bool isSourcePositionEmpty = !gameState.ChessPiecesByPosition.ContainsKey(move.From);

            return isSourcePositionEmpty
                ? RuleViolation.NoPieceAtSourcePosition
                : null;
        }
    }
}
