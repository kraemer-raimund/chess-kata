using Chess.Domain;

namespace Chess.Rendering
{
    public class ConsoleBoardRenderer
    {
        private const string EmptyBoard = """
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

        private readonly char[] _renderBuffer = new char[EmptyBoard.Length];

        public string Render(GameState gameState)
        {
            RenderEmptyBoard();
            RenderChessPieces(gameState);

            return new string(_renderBuffer);
        }

        private void RenderEmptyBoard()
        {
            Array.Copy(EmptyBoard.ToCharArray(), _renderBuffer, _renderBuffer.Length);
        }

        private void RenderChessPieces(GameState gameState)
        {
            foreach (var chessPieceByPosition in gameState.ChessPiecesByPosition)
            {
                var position = chessPieceByPosition.Key;
                var chessPiece = chessPieceByPosition.Value;

                RenderChessPiece(position, chessPiece);
            }
        }

        private void RenderChessPiece(Position position, ChessPiece chessPiece)
        {
            // Sometimes algorithms or calculations are difficult to write in a
            // clear and straight-forward way, because they are naturally
            // mathematical. In these cases, variables with named intermediary
            // results and comments can help, but the most important thing is
            // that the test specifies the intended behavior and shows that it
            // works correctly, even if the implemented logic may not be trivial
            // to understand.

            int horizontalOffsetForLabels = 5;
            int squareWidth = 6;
            int squareHeight = 2;
            int horizontalOffsetWithinSquare = 3;

            // Rows on the board are counted from bottom, but in the buffer from top.
            int rowFromTop = 8 - position.Rank;
            int initialEmptyLines = 1;
            int line = initialEmptyLines + (rowFromTop * squareHeight);
            int offsetWithinLine = horizontalOffsetForLabels
                + ((position.File - 1) * squareWidth)
                + horizontalOffsetWithinSquare;
            int charsPerLine = 56;
            int index = (line * charsPerLine) + offsetWithinLine;

            _renderBuffer[index] = ToChar(chessPiece);
        }

        private static char ToChar(ChessPiece chessPiece)
        {
            return chessPiece.Name switch
            {
                ChessPieceName.Pawn => chessPiece.Color == PlayerColor.White ? '♙' : '♟',
                ChessPieceName.Rook => chessPiece.Color == PlayerColor.White ? '♖' : '♜',
                ChessPieceName.Knight => chessPiece.Color == PlayerColor.White ? '♘' : '♞',
                ChessPieceName.Bishop => chessPiece.Color == PlayerColor.White ? '♗' : '♝',
                ChessPieceName.Queen => chessPiece.Color == PlayerColor.White ? '♕' : '♛',
                ChessPieceName.King => chessPiece.Color == PlayerColor.White ? '♔' : '♚',
                _ => throw new ArgumentOutOfRangeException(nameof(chessPiece), chessPiece, "Unhandled enum value.")
            };
        }
    }
}
