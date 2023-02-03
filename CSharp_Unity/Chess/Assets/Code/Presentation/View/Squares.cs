using System;
using System.Collections;
using System.Collections.Generic;
using ChessKata.Domain;
using UnityEngine;

namespace ChessKata.Presentation
{
    internal class Squares : MonoBehaviour
    {
        [SerializeField] private GameObject _whiteSquarePrefab;
        [SerializeField] private GameObject _blackSquarePrefab;

        private readonly IDictionary<Position, Transform> _boardSquaresByPosition
            = new Dictionary<Position, Transform>();

        public IEnumerator Initialize(Func<Position, Vector3> positionToWorldMapping)
        {
            for (int row = 1; row <= 8; row++)
            {
                yield return new WaitForSeconds(0.08f);

                for (int col = 1; col <= 8; col++)
                {
                    bool isOddColumn = col % 2 == 1;
                    bool isOddRow = row % 2 == 1;
                    bool isBlackSquare = isOddRow == isOddColumn;

                    GameObject squarePrefab = isBlackSquare ? _blackSquarePrefab : _whiteSquarePrefab;
                    GameObject square = Instantiate(squarePrefab);
                    square.transform.position = positionToWorldMapping(new Position(col, row));
                }
            }
        }
    }
}
