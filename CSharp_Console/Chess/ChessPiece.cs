namespace Chess.Domain
{
    public readonly struct ChessPiece
    {
        public ChessPiece(ChessPieceName name, PlayerColor color)
        {
            Name = name;
            Color = color;
        }

        public ChessPieceName Name { get; }
        public PlayerColor Color { get; }
    }
}
