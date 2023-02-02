using System;
using System.Collections;
using System.Collections.Generic;
using ChessKata.Domain;
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

        private readonly GameState _gameState = new();

        private readonly IDictionary<Position, Transform> _chessPiecesByPosition
            = new Dictionary<Position, Transform>();

        private IEnumerator Start()
        {
            yield return InitializeSquares();
            yield return InitializeChessPieces();
        }

        private IEnumerator InitializeSquares()
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
                    square.transform.position = ToWorldPosition(new Position(col, row));
                }
            }
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
            GameObject chessPieceGameObject = Instantiate(ToChessPiecePrefab(chessPiece));
            chessPieceGameObject.transform.position = ToWorldPosition(position);
            if (chessPiece.Color == PlayerColor.Black)
            {
                chessPieceGameObject.transform.Rotate(new(0, 180, 0));
            }
            _chessPiecesByPosition.Add(position, chessPieceGameObject.transform);
        }

        private GameObject ToChessPiecePrefab(ChessPiece chessPiece)
        {
            return chessPiece.Name switch
            {
                ChessPieceName.Pawn => chessPiece.Color == PlayerColor.White ? _whitePawn : _blackPawn,
                ChessPieceName.Rook => chessPiece.Color == PlayerColor.White ? _whiteRook : _blackRook,
                ChessPieceName.Knight => chessPiece.Color == PlayerColor.White ? _whiteKnight : _blackKnight,
                ChessPieceName.Bishop => chessPiece.Color == PlayerColor.White ? _whiteBishop : _blackBishop,
                ChessPieceName.Queen => chessPiece.Color == PlayerColor.White ? _whiteQueen : _blackQueen,
                ChessPieceName.King => chessPiece.Color == PlayerColor.White ? _whiteKing : _blackKing,
                _ => throw new ArgumentException()
            };
        }

        private Vector3 ToWorldPosition(Position positionOnChessboard)
        {
            return new Vector3(positionOnChessboard.File, 0, positionOnChessboard.Rank);
        }
    }
}
