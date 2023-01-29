namespace ChessKata.Domain
{
    /// <summary>
    /// The absolute position of a square on the chessboard,
    /// specified by a file (column, a-h) and rank (row, 1-8).
    /// </summary>
    public struct Position
    {
        /// <param name="file">
        /// The column of the chessboard. See also: <seealso cref="File"/>
        /// </param>
        /// <param name="rank">
        /// The row of the chessboard. See also: <seealso cref="Rank"/>
        /// </param>
        public Position(int file, int rank)
        {
            File = file;
            Rank = rank;
        }

        /// <summary>
        /// The column of the chessboard, usually referred to by the letters "a" to "h".
        /// From white's perspective, "a" is on the left.
        /// </summary>
        public int File { get; }

        /// <summary>
        /// The row of the chessboard, usually referred to by the numbers "1" to "8".
        /// From white's perspective, 1 is on white's side and 8 is on black's side.
        /// </summary>
        public int Rank { get; }
    }
}