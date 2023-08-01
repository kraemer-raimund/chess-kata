namespace Chess.Platform
{
    public class MoveCommandParser
    {
        public void Parse(string userInput)
        {
            throw new ArgumentException(
                $"Invalid move command `{userInput}`",
                nameof(userInput)
            );
        }
    }
}
