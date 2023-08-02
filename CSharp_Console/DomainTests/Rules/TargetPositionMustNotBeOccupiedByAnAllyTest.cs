using NUnit.Framework;

namespace Chess.Domain.Rules.Tests
{
    internal class TargetPositionMustNotBeOccupiedByAnAllyTest
    {
        [Test]
        public void RejectsMoveToPositionOccupiedByAlly()
        {
            Position from = new(3, 3);
            Position to = new(4, 4);
            ChessPiece whiteBishopToMove = new(ChessPieceName.Bishop, PlayerColor.White);
            ChessPiece whitePawn = new(ChessPieceName.Pawn, PlayerColor.White);

            GameState gameState = new(
                new Dictionary<Position, ChessPiece>()
                {
                        { from, whiteBishopToMove },
                        { to, whitePawn }
                }
            );

            var rule = new TargetPositionMustNotBeOccupiedByAlly();
            var move = new Move(from, to);
            RuleViolation? ruleViolation = rule.Execute(move, gameState);

            Assert.IsNotNull(ruleViolation);
            Assert.AreEqual(RuleViolation.TargetPositionOccupiedByAlly, ruleViolation!.Value);
        }
    }
}
