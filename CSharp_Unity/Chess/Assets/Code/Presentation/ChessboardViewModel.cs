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
        public event Action<IReadOnlyDictionary<Position, ChessPiece>> PositionsChanged;

        public IReadOnlyDictionary<Position, ChessPiece> ChessPiecesByPosition => GameState.ChessPiecesByPosition;

        public Position? SelectedPosition
        {
            get => _selectedPosition;
            set
            {
                _selectedPosition = value;
                SelectedPositionChanged?.Invoke(_selectedPosition);
            }
        }

        private GameState GameState
        {
            get => _gameState;
            set
            {
                _gameState = value;
                PositionsChanged?.Invoke(ChessPiecesByPosition);
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
                MoveResult moveResult = _ruleEngine.Execute(move, GameState);
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
                SelectedPosition = null;
                GameState = moveResult.GameState;
            }
        }

        private bool IsSomethingSelected() => SelectedPosition.HasValue;

        private bool IsPositionOccupiedByCurrentPlayer(Position position)
        {
            if (!GameState.ChessPiecesByPosition.ContainsKey(position))
            {
                return false;
            }

            ChessPiece chessPiece = GameState.ChessPiecesByPosition[position];
            PlayerColor currentPlayer = GameState.CurrentPlayer;

            return chessPiece.Color == currentPlayer;
        }
    }
}
