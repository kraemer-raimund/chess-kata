using System.Collections;
using ChessKata.Domain;
using UnityEngine;

namespace ChessKata.Presentation
{
    internal class ChessboardViewModel : MonoBehaviour
    {
        [SerializeField] private Squares _squares;
        [SerializeField] private ChessPieces _chessPieces;

        private readonly GameState _gameState = new();

        private IEnumerator Start()
        {
            yield return _squares.Initialize(positionToWorldMapping: ToWorldPosition);
            yield return _chessPieces.Initialize(_gameState.ChessPiecesByPosition, ToWorldPosition);
        }

        private Vector3 ToWorldPosition(Position position) => new(position.File, 0, position.Rank);
    }
}
