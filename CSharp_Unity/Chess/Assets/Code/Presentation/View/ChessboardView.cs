using System.Collections;
using ChessKata.Domain;
using UnityEngine;

namespace ChessKata.Presentation
{
    internal class ChessboardView : MonoBehaviour
    {
        [SerializeField] private ChessboardViewModel _viewModel;

        [SerializeField] private Squares _squares;
        [SerializeField] private ChessPieces _chessPieces;

        [SerializeField] private PlayerInput _playerInput;

        private IEnumerator Start()
        {
            _playerInput.enabled = false;

            yield return _squares.Initialize(positionToWorldMapping: ToWorldPosition);
            yield return _chessPieces.Initialize(_viewModel.ChessPiecesByPosition, ToWorldPosition);

            _playerInput.ClickedObject += OnPlayerClickedObject;
            _playerInput.enabled = true;
        }

        private void OnPlayerClickedObject(object sender, GameObject clickedObject)
        {
            Debug.Log("Clicked an object!");
        }

        private Vector3 ToWorldPosition(Position position) => new(position.File, 0, position.Rank);
    }
}
