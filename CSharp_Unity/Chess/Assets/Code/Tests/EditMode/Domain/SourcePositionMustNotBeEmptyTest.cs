using ChessKata.Domain;
using ChessKata.Domain.Rules;
using NUnit.Framework;

namespace ChessKata.Tests
{
    internal class SourcePositionMustNotBeEmptyTest
    {
        [Test]
        public void ShouldRejectMoveWithEmptySourcePosition()
        {
            GameState gameState = new();
            SourcePositionMustNotBeEmpty rule = new();

            var move = new Move(new(4, 4), new(4, 4));
            RuleViolation? ruleViolation = rule.Execute(move, gameState);

            Assert.IsNotNull(ruleViolation);
        }
    }
}
