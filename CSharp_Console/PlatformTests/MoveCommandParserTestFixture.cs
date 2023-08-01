using NUnit.Framework;

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

                Assert.Throws<ArgumentException>(() => parser.Parse(userInput));
            }
        }
    }
}
