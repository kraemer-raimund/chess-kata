using System.Collections.Generic;
using ChessKata.Domain;
using NUnit.Framework;

namespace ChessKata.Tests
{
    internal class RuleEngineTest
    {
        [Test]
        public void ShouldRejectIllegalMoves()
        {
            RuleEngine ruleEngine = new();
            GameState gameStateBefore = new();

            /*
            A guaranteed illegal move (there is no piece at that position, and the target position
            is the same as the source position). At this abstraction level, we don't care about how
            the rule is checked or which rules exist, only that the rule engine rejects illegal
            moves. Concrete rules are checked individually at the lower abstraction level. We do
            not want to have to change existing higher level tests when adding lower level rules.
            */
            var move = new Move(new(4, 4), new(4, 4));
            MoveResult moveResult = ruleEngine.Execute(move, gameStateBefore);

            Assert.IsNotEmpty(moveResult.Violations);
            Assert.AreEqual(gameStateBefore, moveResult.GameState);
        }

        [Test]
        public void ShouldMoveChessPieceIfMoveIsValid()
        {
            ChessPiece whitePawn = new(ChessPieceName.Pawn, PlayerColor.White);
            Position positionBefore = new(1, 2);
            Position positionAfter = new(1, 3);

            GameState gameStateBefore = new(
                new Dictionary<Position, ChessPiece>() {
                    [positionBefore] = whitePawn
                }
            );
            Move move = new(positionBefore, positionAfter);

            RuleEngine ruleEngine = new();
            MoveResult moveResult = ruleEngine.Execute(move, gameStateBefore);

            Assert.IsEmpty(moveResult.Violations);

            Assert.IsTrue(moveResult.GameState.ChessPiecesByPosition.ContainsKey(positionAfter));
            Assert.IsFalse(moveResult.GameState.ChessPiecesByPosition.ContainsKey(positionBefore));

            ChessPiece chessPieceAfter = moveResult.GameState.ChessPiecesByPosition[positionAfter];
            ChessPiece chessPieceBefore = gameStateBefore.ChessPiecesByPosition[positionBefore];
            Assert.AreEqual(chessPieceBefore, chessPieceAfter);
        }

        [Test]
        public void ShouldTogglePlayersAfterValidMove()
        {
            ChessPiece whitePawn = new(ChessPieceName.Pawn, PlayerColor.White);
            PlayerColor currentPlayerBefore = PlayerColor.White;
            Position positionBefore = new(1, 2);
            Position positionAfter = new(1, 3);

            GameState gameStateBefore = new(
                new Dictionary<Position, ChessPiece>()
                {
                    [positionBefore] = whitePawn
                },
                currentPlayerBefore
            );
            Move move = new(positionBefore, positionAfter);

            RuleEngine ruleEngine = new();
            MoveResult moveResult = ruleEngine.Execute(move, gameStateBefore);

            Assert.AreNotEqual(currentPlayerBefore, moveResult.GameState.CurrentPlayer);
        }
    }
}
