using System;
using System.Collections.Generic;
using System.Linq;
using ChessKata.Domain;
using UnityEngine;

namespace ChessKata.Presentation
{
    internal class ChessboardViewModel : MonoBehaviour
    {
        private readonly RuleEngine _ruleEngine = new();
        private GameState _gameState = new();
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
            if (IsPositionOccupiedByCurrentPlayer(clickedPosition))
            {
                SelectedPosition = clickedPosition;
            }
            else if (IsSomethingSelected() && !IsPositionOccupiedByCurrentPlayer(clickedPosition))
            {
                Move move = new(SelectedPosition.Value, clickedPosition);
                MoveResult moveResult = _ruleEngine.Execute(move, _gameState);
                HandleMoveResult(moveResult);
            }
        }

        private void HandleMoveResult(MoveResult moveResult)
        {
            if (moveResult.Violations.Any())
            {
                Debug.Log("Invalid move: " + string.Join(", ", moveResult.Violations));
            }
            else
            {
                _gameState = moveResult.GameState;
            }
        }

        private bool IsSomethingSelected() => _selectedPosition.HasValue;

        private bool IsPositionOccupiedByCurrentPlayer(Position position)
        {
            if (!_gameState.ChessPiecesByPosition.ContainsKey(position))
            {
                return false;
            }

            ChessPiece chessPiece = _gameState.ChessPiecesByPosition[position];
            PlayerColor currentPlayer = _gameState.CurrentPlayer;

            return chessPiece.Color == currentPlayer;
        }
    }
}
