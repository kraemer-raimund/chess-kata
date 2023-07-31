using Chess.Domain;
using NUnit.Framework;

namespace Chess.Rendering.Tests
{
    [TestFixture]
    internal class ConsoleBoardRendererTestFixture
    {
        internal class TheConsoleBoardRenderer
        {

            [Test]
            public void RendersAnEmptyBoard()
            {
                var renderer = new ConsoleBoardRenderer();
                var board = Board.Empty();

                var actual = renderer.Render(board);

                var expected = """
                                   
                     ╔═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╗
                  8  ║     ║     ║     ║     ║     ║     ║     ║     ║
                     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
                  7  ║     ║     ║     ║     ║     ║     ║     ║     ║
                     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
                  6  ║     ║     ║     ║     ║     ║     ║     ║     ║
                     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
                  5  ║     ║     ║     ║     ║     ║     ║     ║     ║
                     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
                  4  ║     ║     ║     ║     ║     ║     ║     ║     ║
                     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
                  3  ║     ║     ║     ║     ║     ║     ║     ║     ║
                     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
                  2  ║     ║     ║     ║     ║     ║     ║     ║     ║
                     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
                  1  ║     ║     ║     ║     ║     ║     ║     ║     ║
                     ╚═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╝
                        a     b     c     d     e     f     g     h
                """;

                Assert.AreEqual(expected, actual);
            }
        }
    }
}
