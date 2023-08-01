using System.Text.RegularExpressions;
using Chess.Domain;

namespace Chess.Platform
{
    public class MoveCommandParser
    {
        public bool TryParse(string userInput, out Move? move)
        {
            var canonicalFormat = ToCanonicalFormat(userInput);

            if (!IsWellFormed(canonicalFormat))
            {
                move = null;
                return false;
            }

            move = Parse(canonicalFormat);
            return true;
        }

        private static string ToCanonicalFormat(string moveCommand)
        {
            return moveCommand
                .Replace(" ", "")
                .ToLowerInvariant();
        }

        private static bool IsWellFormed(string canonicalMoveCommand)
        {
            var pattern = "[a-h][1-8]->[a-h][1-8]";
            return Regex.IsMatch(canonicalMoveCommand, pattern);
        }

        private static Move Parse(string canonicalMoveCommand)
        {
            var positionFrom = new Position(
                canonicalMoveCommand[0] - 'a' + 1,
                canonicalMoveCommand[1] - '1' + 1
            );

            var positionTo = new Position(
                canonicalMoveCommand[4] - 'a' + 1,
                canonicalMoveCommand[5] - '1' + 1
            );

            return new Move(positionFrom, positionTo);
        }
    }
}
