namespace Chess.Domain
{
    public class GameState
    {
        public GameState()
        {
            ChessPiecesByPosition = InitialPositions;
        }

        public IReadOnlyDictionary<Position, ChessPiece> ChessPiecesByPosition { get; }

        private static Dictionary<Position, ChessPiece> InitialPositions { get; }
            = new Dictionary<Position, ChessPiece>()
            {
                [new Position(1, 1)] = new ChessPiece(ChessPieceName.Rook, PlayerColor.White),
                [new Position(2, 1)] = new ChessPiece(ChessPieceName.Knight, PlayerColor.White),
                [new Position(3, 1)] = new ChessPiece(ChessPieceName.Bishop, PlayerColor.White),
                [new Position(4, 1)] = new ChessPiece(ChessPieceName.Queen, PlayerColor.White),
                [new Position(5, 1)] = new ChessPiece(ChessPieceName.King, PlayerColor.White),
                [new Position(6, 1)] = new ChessPiece(ChessPieceName.Bishop, PlayerColor.White),
                [new Position(7, 1)] = new ChessPiece(ChessPieceName.Knight, PlayerColor.White),
                [new Position(8, 1)] = new ChessPiece(ChessPieceName.Rook, PlayerColor.White),

                [new Position(1, 2)] = new ChessPiece(ChessPieceName.Pawn, PlayerColor.White),
                [new Position(2, 2)] = new ChessPiece(ChessPieceName.Pawn, PlayerColor.White),
                [new Position(3, 2)] = new ChessPiece(ChessPieceName.Pawn, PlayerColor.White),
                [new Position(4, 2)] = new ChessPiece(ChessPieceName.Pawn, PlayerColor.White),
                [new Position(5, 2)] = new ChessPiece(ChessPieceName.Pawn, PlayerColor.White),
                [new Position(6, 2)] = new ChessPiece(ChessPieceName.Pawn, PlayerColor.White),
                [new Position(7, 2)] = new ChessPiece(ChessPieceName.Pawn, PlayerColor.White),
                [new Position(8, 2)] = new ChessPiece(ChessPieceName.Pawn, PlayerColor.White),

                [new Position(1, 7)] = new ChessPiece(ChessPieceName.Pawn, PlayerColor.Black),
                [new Position(2, 7)] = new ChessPiece(ChessPieceName.Pawn, PlayerColor.Black),
                [new Position(3, 7)] = new ChessPiece(ChessPieceName.Pawn, PlayerColor.Black),
                [new Position(4, 7)] = new ChessPiece(ChessPieceName.Pawn, PlayerColor.Black),
                [new Position(5, 7)] = new ChessPiece(ChessPieceName.Pawn, PlayerColor.Black),
                [new Position(6, 7)] = new ChessPiece(ChessPieceName.Pawn, PlayerColor.Black),
                [new Position(7, 7)] = new ChessPiece(ChessPieceName.Pawn, PlayerColor.Black),
                [new Position(8, 7)] = new ChessPiece(ChessPieceName.Pawn, PlayerColor.Black),

                [new Position(1, 8)] = new ChessPiece(ChessPieceName.Rook, PlayerColor.Black),
                [new Position(2, 8)] = new ChessPiece(ChessPieceName.Knight, PlayerColor.Black),
                [new Position(3, 8)] = new ChessPiece(ChessPieceName.Bishop, PlayerColor.Black),
                [new Position(4, 8)] = new ChessPiece(ChessPieceName.Queen, PlayerColor.Black),
                [new Position(5, 8)] = new ChessPiece(ChessPieceName.King, PlayerColor.Black),
                [new Position(6, 8)] = new ChessPiece(ChessPieceName.Bishop, PlayerColor.Black),
                [new Position(7, 8)] = new ChessPiece(ChessPieceName.Knight, PlayerColor.Black),
                [new Position(8, 8)] = new ChessPiece(ChessPieceName.Rook, PlayerColor.Black)
            };
    }
}
