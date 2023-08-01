using Chess.Domain;
using NUnit.Framework;
using NUnit.Framework.Interfaces;

namespace Chess.Platform.Tests
{
    internal class MoveCommandParserTestFixture
    {
        internal class TheParser
        {
            [TestCase("asdf")]
            [TestCase("e0 -> e8")]
            [TestCase("e1 -> e9)")]
            [TestCase("c1 -> i5")]
            [TestCase("c1 <- i5")]
            public void RejectsInvalidInput(string userInput)
            {
                var parser = new MoveCommandParser();

                Assert.IsFalse(parser.TryParse(userInput, out _));
            }

            [System.Diagnostics.CodeAnalysis.SuppressMessage(
                "CodeQuality", "IDE0051:Remove unused private members",
                Justification = "Used at runtime by the TestCaseSource.")]
            private static IEnumerable<ITestCaseData> ValidMoveCommands
            {
                get
                {
                    yield return new TestCaseData("a1 -> a2", new Move(new(1, 1), new(1, 2)));
                    yield return new TestCaseData("f3 ->a7", new Move(new(6, 3), new(1, 7)));
                    yield return new TestCaseData("h7-> h7", new Move(new(8, 7), new(8, 7)));
                    yield return new TestCaseData("f3->a7", new Move(new(6, 3), new(1, 7)));
                }
            }

            [TestCaseSource("ValidMoveCommands")]
            public void ParsesValidMoveCommands(string userInput, Move expectedMove)
            {
                var parser = new MoveCommandParser();

                Assert.IsTrue(parser.TryParse(userInput, out Move? move));
                Assert.AreEqual(expectedMove, move);
            }
        }
    }
}
