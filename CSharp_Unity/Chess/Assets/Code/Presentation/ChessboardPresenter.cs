using System.Collections;
using UnityEngine;

namespace ChessKata.Presentation
{
    public class ChessboardPresenter : MonoBehaviour
    {
        [SerializeField] private GameObject _whiteSquarePrefab;
        [SerializeField] private GameObject _blackSquarePrefab;

        [SerializeField] private GameObject _whitePawn;
        [SerializeField] private GameObject _whiteRook;
        [SerializeField] private GameObject _whiteKnight;
        [SerializeField] private GameObject _whiteBishop;
        [SerializeField] private GameObject _whiteQueen;
        [SerializeField] private GameObject _whiteKing;

        [SerializeField] private GameObject _blackPawn;
        [SerializeField] private GameObject _blackRook;
        [SerializeField] private GameObject _blackKnight;
        [SerializeField] private GameObject _blackBishop;
        [SerializeField] private GameObject _blackQueen;
        [SerializeField] private GameObject _blackKing;

        private IEnumerator Start()
        {
            yield return InitializeSquares();
        }

        private IEnumerator InitializeSquares()
        {
            for (int row = 1; row <= 8; row++)
            {
                for (int col = 1; col <= 8; col++)
                {
                    yield return new WaitForSeconds(0.02f);

                    bool isOddColumn = col % 2 == 1;
                    bool isOddRow = row % 2 == 1;
                    bool isBlackSquare = isOddRow == isOddColumn;

                    GameObject squarePrefab = isBlackSquare ? _blackSquarePrefab : _whiteSquarePrefab;
                    GameObject square = Instantiate(squarePrefab);
                    square.transform.position = new Vector3(col, 0, row);
                }
            }
        }
    }
}
