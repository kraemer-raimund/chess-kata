namespace Chess.Domain
{
    public class Board
    {
        private Board() { }

        public static Board Empty() => new();
    }
}