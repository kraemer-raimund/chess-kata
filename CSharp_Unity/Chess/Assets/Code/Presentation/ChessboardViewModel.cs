using System;
using System.Collections.Generic;
using ChessKata.Domain;
using UnityEngine;

namespace ChessKata.Presentation
{
    internal class ChessboardViewModel : MonoBehaviour
    {
        private readonly GameState _gameState = new();
        private Position? _selectedPosition;

        public event Action<Position?> SelectedPositionChanged;

        public IReadOnlyDictionary<Position, ChessPiece> ChessPiecesByPosition => _gameState.ChessPiecesByPosition;

        public Position? SelectedPosition
        {
            get => _selectedPosition;
            set
            {
                _selectedPosition = value;
                SelectedPositionChanged?.Invoke(_selectedPosition);
            }
        }

        public void OnClickedPosition(Position clickedPosition)
        {
            if (IsPositionOccupied(clickedPosition) && !_selectedPosition.HasValue)
            {
                SelectedPosition = clickedPosition;
            }
        }

        private bool IsPositionOccupied(Position position) => _gameState.ChessPiecesByPosition.ContainsKey(position);
    }
}
