using System.Collections;
using ChessKata.Domain;
using UnityEngine;

namespace ChessKata.Presentation
{
    internal class ChessboardView : MonoBehaviour
    {
        [SerializeField] private Squares _squares;
        [SerializeField] private ChessPieces _chessPieces;

        [SerializeField] private ChessboardViewModel _viewModel;

        private IEnumerator Start()
        {
            yield return _squares.Initialize(positionToWorldMapping: ToWorldPosition);
            yield return _chessPieces.Initialize(_viewModel.ChessPiecesByPosition, ToWorldPosition);
        }

        private Vector3 ToWorldPosition(Position position) => new(position.File, 0, position.Rank);
    }
}
