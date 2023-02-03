using System;
using ChessKata.Domain;
using UnityEngine;

namespace ChessKata.Presentation
{
    internal class ChessPiecePrefabs : MonoBehaviour
    {
        [Header("White")]
        [SerializeField] private GameObject _whitePawn;
        [SerializeField] private GameObject _whiteRook;
        [SerializeField] private GameObject _whiteKnight;
        [SerializeField] private GameObject _whiteBishop;
        [SerializeField] private GameObject _whiteQueen;
        [SerializeField] private GameObject _whiteKing;

        [Header("Black")]
        [SerializeField] private GameObject _blackPawn;
        [SerializeField] private GameObject _blackRook;
        [SerializeField] private GameObject _blackKnight;
        [SerializeField] private GameObject _blackBishop;
        [SerializeField] private GameObject _blackQueen;
        [SerializeField] private GameObject _blackKing;

        public GameObject GetPrefabFor(ChessPiece chessPiece)
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
    }
}
