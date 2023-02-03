using System.Collections;
using System.Collections.Generic;
using ChessKata.Domain;
using UnityEngine;

namespace ChessKata.Presentation
{
    internal class ChessboardPresenter : MonoBehaviour
    {
        [SerializeField] private ChessPiecePrefabs _chessPiecePrefabs;

        [SerializeField] private Squares _squares;

        private readonly GameState _gameState = new();

        private readonly IDictionary<Position, Transform> _chessPiecesByPosition
            = new Dictionary<Position, Transform>();

        private IEnumerator Start()
        {
            yield return _squares.Initialize(positionToWorldMapping: ToWorldPosition);
            yield return InitializeChessPieces();
        }

        private IEnumerator InitializeChessPieces()
        {
            foreach (int row in new int[] {1, 2, 7, 8})
            {
                for (int col = 1; col <= 8; col++)
                {
                    yield return new WaitForSeconds(0.04f);

                    var position = new Position(col, row);
                    if (_gameState.ChessPiecesByPosition.TryGetValue(position, out ChessPiece chessPiece))
                    {
                        PlaceNewChessPiece(chessPiece, position);
                    }
                }
            }
        }

        private void PlaceNewChessPiece(ChessPiece chessPiece, Position position)
        {
            GameObject prefab = _chessPiecePrefabs.GetPrefabFor(chessPiece);
            GameObject chessPieceGameObject = Instantiate(prefab);
            chessPieceGameObject.transform.position = ToWorldPosition(position);
            if (chessPiece.Color == PlayerColor.Black)
            {
                chessPieceGameObject.transform.Rotate(new(0, 180, 0));
            }
            _chessPiecesByPosition.Add(position, chessPieceGameObject.transform);
        }

        private Vector3 ToWorldPosition(Position positionOnChessboard)
        {
            return new Vector3(positionOnChessboard.File, 0, positionOnChessboard.Rank);
        }
    }
}
