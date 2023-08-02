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
                RenderToScreen(gameState);
                PromptPlayerInput(gameState);
                string playerInput = ReadPlayerInput();
                gameState = HandlePlayerInput(playerInput, gameState);
            }
        }

        private void RenderToScreen(GameState gameState)
        {
            Console.WriteLine(_renderer.Render(gameState));
        }

        private void PromptPlayerInput(GameState gameState)
        {
            string playerName = ToPlayerName(gameState.CurrentPlayer);
            Console.WriteLine($"{playerName}'s turn.\n");
            Console.WriteLine("Type your move in the format \"from -> to\", e.g., \"e2 -> e8\".\n");
        }

        private string ToPlayerName(PlayerColor playerColor)
        {
            return playerColor switch
            {
                PlayerColor.White => "White",
                PlayerColor.Black => "Black",
                _ => throw new ArgumentOutOfRangeException(nameof(playerColor), playerColor, "Unhandled enum value.")
            };
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
                Console.WriteLine("\nInvalid move!\n");
                return gameState;
            }

            return moveResult.GameState;
        }
    }
}
