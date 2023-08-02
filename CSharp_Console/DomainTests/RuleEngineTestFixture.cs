﻿using NUnit.Framework;

namespace Chess.Domain.Tests
{
    internal class RuleEngineTestFixture
    {
        internal class TheRuleEngine
        {
            [Test]
            public void RejectsIllegalMoves()
            {
                RuleEngine ruleEngine = new();
                GameState gameStateBefore = GameState.InitialState();

                /*
                A guaranteed illegal move (there is no piece at that position, and the target position
                is the same as the source position). At this abstraction level, we don't care about how
                the rule is checked or which rules exist, only that the rule engine rejects illegal
                moves. Concrete rules are checked individually at the lower abstraction level. We do
                not want to have to change existing higher level tests when adding lower level rules.
                */
                var move = new Move(new(4, 4), new(4, 4));
                var moveResult = ruleEngine.Execute(move, gameStateBefore);

                Assert.IsNotEmpty(moveResult.Violations);
                Assert.AreEqual(gameStateBefore, moveResult.GameState);
            }
        }
    }
}
