using NUnit.Framework;

namespace Chess.Domain.Rules.Tests
{
    internal class SourcePositionMustNotBeEmptyTest
    {
        [Test]
        public void RejectsMoveWithEmptySourcePosition()
        {
            GameState gameState = GameState.InitialState();
            SourcePositionMustNotBeEmpty rule = new();

            var move = new Move(new(4, 4), new(4, 4));
            RuleViolation? ruleViolation = rule.Execute(move, gameState);

            Assert.IsNotNull(ruleViolation);
        }
    }
}
