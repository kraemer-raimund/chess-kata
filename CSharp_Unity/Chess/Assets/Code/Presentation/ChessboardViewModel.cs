using System.Collections;
using System.Collections.Generic;
using ChessKata.Domain;
using UnityEngine;

namespace ChessKata.Presentation
{
    internal class ChessboardViewModel : MonoBehaviour
    {
        private readonly GameState _gameState = new();

        public IReadOnlyDictionary<Position, ChessPiece> ChessPiecesByPosition => _gameState.ChessPiecesByPosition;
    }
}
