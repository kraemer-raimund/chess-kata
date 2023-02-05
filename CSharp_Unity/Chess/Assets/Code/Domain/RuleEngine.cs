using System.Collections.Generic;
using System.Linq;
using ChessKata.Domain.Rules;

namespace ChessKata.Domain
{
    public class RuleEngine
    {
        private readonly IEnumerable<IRule> _rules = new List<IRule>()
        {
            new SourcePositionMustNotBeEmpty(),
            new TargetPositionMustNotBeOccupiedByAlly(),
        };

        public RuleEngine()
        {
        }

        public MoveResult Execute(Move move, GameState gameState)
        {
            IEnumerable<RuleViolation> ruleViolations = _rules
                .Select(rule => rule.Execute(move, gameState))
                .Where(ruleViolationOrNull => ruleViolationOrNull.HasValue)
                .Select(ruleViolation => ruleViolation.Value);

            if (ruleViolations.Any())
            {
                return new(ruleViolations, gameState);
            }
            else
            {
                GameState newGameState = MovePiece(move, gameState);
                return new(Enumerable.Empty<RuleViolation>(), newGameState);
            }
        }

        private GameState MovePiece(Move move, GameState gameState)
        {
            var newPositions = new Dictionary<Position, ChessPiece>(gameState.ChessPiecesByPosition);
            
            ChessPiece chessPiece = newPositions[move.From];
            _ = newPositions.Remove(move.From);
            if (!newPositions.TryAdd(move.To, chessPiece))
            {
                newPositions[move.To] = chessPiece;
            }

            return new GameState(newPositions);
        }
    }
}