namespace Chess.Platform
{
    public class Program
    {
        public static void Main()
        {
            Console.OutputEncoding = System.Text.Encoding.UTF8;
            Console.WriteLine(
                "The game is about to begin. Please make sure your " +
                "console is set to a font which can display unicode " +
                "special characters. For example DejaVu Sans Mono " +
                "on Windows." +
                "\n\n" +
                "Press Enter to start the game."
            );

            Console.ReadLine();

            var chessEngine = new Engine();
            chessEngine.StartGame();
        }
    }
}
