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
        [SerializeField] private SelectionEffect _selectionEffect;
        [SerializeField] private PlayerInput _playerInput;

        private IEnumerator Start()
        {
            _viewModel.SelectedPositionChanged += HandleSelectionChanged;

            _playerInput.enabled = false;

            yield return _squares.Initialize(positionToWorldMapping: ToWorldPosition);
            yield return _chessPieces.Initialize(_viewModel.ChessPiecesByPosition, ToWorldPosition);

            _playerInput.ClickedObject += HandlePlayerClickedObject;
            _playerInput.enabled = true;
        }

        private void HandlePlayerClickedObject(GameObject clickedObject)
        {
            Position? clickedPositionOrNull = _squares.ClickedPositionOrNull(clickedObject);
            
            if (clickedPositionOrNull != null)
            {
                _viewModel.OnClickedPosition(clickedPositionOrNull.Value);
            }
        }

        private void HandleSelectionChanged(Position? selectedPosition)
        {
            if (selectedPosition.HasValue)
            {
                _selectionEffect.OnSelectChesspiece(selectedPosition.Value, ToWorldPosition);
            }
            else
            {
                _selectionEffect.OnUnselectChesspiece();
            }
        }

        private Vector3 ToWorldPosition(Position position) => new(position.File, 0, position.Rank);
    }
}
