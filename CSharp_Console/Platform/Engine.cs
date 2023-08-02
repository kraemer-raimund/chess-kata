using Chess.Domain;
using Chess.Rendering;

namespace Chess.Platform
{
    internal class Engine
    {
        private readonly ConsoleBoardRenderer _renderer = new ConsoleBoardRenderer();
        private readonly MoveCommandParser _moveCommandParser = new MoveCommandParser();
        private readonly RuleEngine _ruleEngine = new RuleEngine();

        private bool _running = false;

        public void StartGame()
        {
            _running = true;
            var initialState = GameState.InitialState();
            GameLoop(initialState);
        }

        private void GameLoop(GameState initialState)
        {
            GameState gameState = initialState;
            while (_running)
            {
                ClearScreen();
                RenderToScreen(gameState);
                PromptPlayerInput();
                string playerInput = ReadPlayerInput();
                gameState = HandlePlayerInput(playerInput, gameState);
            }
        }

        private void ClearScreen()
        {
            Console.WriteLine(
                "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                "\n\n\n\n\n\n\n\n\n\n"
            );
        }

        private void RenderToScreen(GameState gameState)
        {
            Console.WriteLine(_renderer.Render(gameState));
        }

        private void PromptPlayerInput()
        {
            Console.WriteLine("Type your move in the format \"from -> to\", e.g., \"e2 -> e8\".");
        }

        private string ReadPlayerInput()
        {
            try
            {
                return Console.ReadLine();
            }
            catch (IOException)
            {
                Console.WriteLine("Failed to read player input. Exiting game.");
                _running = false;
                return "";
            }
        }

        private GameState HandlePlayerInput(string playerInput, GameState gameState)
        {
            if (!_moveCommandParser.TryParse(playerInput, out Move? move))
            {
                Console.WriteLine("Please make sure your command is in the correct format.");
                return gameState;
            }

            var moveResult = _ruleEngine.Execute(move!.Value, gameState);
            if (moveResult.Violations.Any())
            {
                Console.WriteLine("Invalid move!");
                return gameState;
            }

            return moveResult.GameState;
        }
    }
}
