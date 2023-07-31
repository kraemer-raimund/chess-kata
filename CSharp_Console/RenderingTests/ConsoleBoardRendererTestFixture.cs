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
                var gameState = GameState.WithEmptyBoard();

                var actual = renderer.Render(gameState);

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

            [Test]
            public void RendersAWhiteQueenOnTheBoard()
            {
                var renderer = new ConsoleBoardRenderer();
                GameState gameState = new(
                    new Dictionary<Position, ChessPiece>()
                    {
                        { new(2, 7), new ChessPiece(ChessPieceName.Queen, PlayerColor.White) }
                    }
                );

                var actual = renderer.Render(gameState);

                // Note:
                // The width of some unicode characters might look wrong in the
                // source code (or in any text editor with a monospaced font),
                // but should still look correct when displayed in the console.
                var expected = """
                     ╔═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╗
                  8  ║     ║     ║     ║     ║     ║     ║     ║     ║
                     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
                  7  ║     ║  ♕  ║     ║     ║     ║     ║     ║     ║
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

            [Test]
            public void RendersTheInitialChessPiecePositions()
            {
                var renderer = new ConsoleBoardRenderer();
                var gameState = GameState.InitialState();

                var actual = renderer.Render(gameState);

                // Note:
                // The width of some unicode characters might look wrong in the
                // source code (or in any text editor with a monospaced font),
                // but should still look correct when displayed in the console.
                var expected = """
                     ╔═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╗
                  8  ║  ♜  ║  ♞  ║  ♝  ║  ♛  ║  ♚  ║  ♝  ║  ♞  ║  ♜  ║
                     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
                  7  ║  ♟  ║  ♟  ║  ♟  ║  ♟  ║  ♟  ║  ♟  ║  ♟  ║  ♟  ║
                     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
                  6  ║     ║     ║     ║     ║     ║     ║     ║     ║
                     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
                  5  ║     ║     ║     ║     ║     ║     ║     ║     ║
                     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
                  4  ║     ║     ║     ║     ║     ║     ║     ║     ║
                     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
                  3  ║     ║     ║     ║     ║     ║     ║     ║     ║
                     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
                  2  ║  ♙  ║  ♙  ║  ♙  ║  ♙  ║  ♙  ║  ♙  ║  ♙  ║  ♙  ║
                     ╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣
                  1  ║  ♖  ║  ♘  ║  ♗  ║  ♕  ║  ♔  ║  ♗  ║  ♘  ║  ♖  ║
                     ╚═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╝
                        a     b     c     d     e     f     g     h
                """;

                Assert.AreEqual(expected, actual);
            }
        }
    }
}
