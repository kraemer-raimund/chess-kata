using System;
using System.Collections;
using System.Collections.Generic;
using ChessKata.Domain;
using UnityEngine;

namespace ChessKata.Presentation
{
    internal class ChessPieces : MonoBehaviour
    {
        [SerializeField] private ChessPiecePrefabs _chessPiecePrefabs;

        private readonly IDictionary<Position, Transform> _chessPiecesByPosition
            = new Dictionary<Position, Transform>();

        public IEnumerator Initialize(
            IReadOnlyDictionary<Position, ChessPiece> chessPiecePositions,
            Func<Position, Vector3> positionToWorldMapping
        )
        {
            foreach (int row in new int[] { 1, 2, 7, 8 })
            {
                for (int col = 1; col <= 8; col++)
                {
                    yield return new WaitForSeconds(0.04f);

                    var position = new Position(col, row);
                    if (chessPiecePositions.TryGetValue(position, out ChessPiece chessPiece))
                    {
                        PlaceNewChessPiece(chessPiece, position, positionToWorldMapping);
                    }
                }
            }
        }

        public void UpdateChessPieces(
            IReadOnlyDictionary<Position, ChessPiece> newChessPiecePositions,
            Func<Position, Vector3> positionToWorldMapping
        )
        {
            ClearChessPieces();
            PlaceChessPieces(newChessPiecePositions, positionToWorldMapping);
        }

        private void ClearChessPieces()
        {
            foreach (Transform piece in _chessPiecesByPosition.Values)
            {
                Destroy(piece.gameObject);
            }

            _chessPiecesByPosition.Clear();
        }

        private void PlaceChessPieces(
            IReadOnlyDictionary<Position, ChessPiece> chessPiecePositions,
            Func<Position, Vector3> positionToWorldMapping
        )
        {
            foreach (KeyValuePair<Position, ChessPiece> entry in chessPiecePositions)
            {
                Position position = entry.Key;
                ChessPiece chessPiece = entry.Value;
                PlaceNewChessPiece(chessPiece, position, positionToWorldMapping);
            }
        }

        private void PlaceNewChessPiece(
            ChessPiece chessPiece,
            Position position,
            Func<Position, Vector3> positionToWorldMapping
        )
        {
            GameObject prefab = _chessPiecePrefabs.GetPrefabFor(chessPiece);
            GameObject chessPieceGameObject = Instantiate(prefab);
            chessPieceGameObject.transform.position = positionToWorldMapping(position);
            if (chessPiece.Color == PlayerColor.Black)
            {
                chessPieceGameObject.transform.Rotate(new(0, 180, 0));
            }
            _chessPiecesByPosition.Add(position, chessPieceGameObject.transform);
        }
    }
}
