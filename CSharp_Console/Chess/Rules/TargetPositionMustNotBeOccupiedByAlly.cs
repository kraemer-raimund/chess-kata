namespace Chess.Domain.Rules
{
    public class TargetPositionMustNotBeOccupiedByAlly : IRule
    {
        public RuleViolation? Execute(Move move, GameState gameState)
        {
            bool isSourcePositionEmpty = !gameState.ChessPiecesByPosition.ContainsKey(move.From);
            if (isSourcePositionEmpty)
            {
                return null;
            }

            bool isTargetPositionEmpty = !gameState.ChessPiecesByPosition.ContainsKey(move.To);
            if (isTargetPositionEmpty)
            {
                return null;
            }

            ChessPiece movingPiece = gameState.ChessPiecesByPosition[move.From];
            ChessPiece targetPiece = gameState.ChessPiecesByPosition[move.To];
            if (movingPiece.Color == targetPiece.Color)
            {
                return RuleViolation.TargetPositionOccupiedByAlly;
            }

            return null;
        }
    }
}
