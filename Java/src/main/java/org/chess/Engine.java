package org.chess;

import org.chess.domain.GameState;
import org.chess.domain.Move;
import org.chess.input.MoveCommandParser;
import org.chess.rendering.ConsoleRenderer;
import org.chess.ruleengine.RuleEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Engine {

    private final ConsoleRenderer renderer = new ConsoleRenderer();
    private final MoveCommandParser moveCommandParser = new MoveCommandParser();
    private final RuleEngine ruleEngine = new RuleEngine();

    private boolean running = false;

    public void start() {
        running = true;
        var initialState = GameState.initialState();
        var consoleReader = new BufferedReader(new InputStreamReader(System.in));
        gameLoop(initialState, consoleReader);
    }

    private void gameLoop(GameState initialState, BufferedReader reader) {
        GameState gameState = initialState;
        while (running) {
            renderToScreen(gameState);
            promptPlayerInput();
            final String playerInput = readPlayerInput(reader);
            gameState = handlePlayerInput(playerInput, gameState);
        }
    }

    private void renderToScreen(GameState gameState) {
        System.out.println(renderer.render(gameState));
    }

    private void promptPlayerInput() {
        System.out.println("Type your move in the format \"from -> to\", e.g., \"e2 -> e8\".");
    }

    private String readPlayerInput(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Failed to read player input. Exiting game.");
            e.printStackTrace();
            running = false;
            return "";
        }
    }

    private GameState handlePlayerInput(String playerInput, GameState gameState) {
        final Move move;

        try {
            move = moveCommandParser.parse(playerInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Please make sure your command is in the correct format.");
            return gameState;
        }

        final var moveResult = ruleEngine.execute(move, gameState);
        if (!moveResult.violations().isEmpty()) {
            System.out.println("Invalid move!");
            return gameState;
        }

        return moveResult.gameState();
    }
}
