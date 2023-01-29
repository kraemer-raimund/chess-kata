using System.Collections.Generic;

namespace ChessKata.Domain
{
    public class GameState
    {
        public IReadOnlyDictionary<Position, object> ChessPiecesByPosition { get; }
            = new Dictionary<Position, object>();
    }
}
