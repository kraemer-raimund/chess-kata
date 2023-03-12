package org.chess

import org.chess.domain.GameState
import org.chess.domain.PlayerColor
import org.chess.input.MoveCommandParser
import org.chess.rendering.ConsoleRenderer
import org.chess.ruleengine.RuleEngine
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class Engine {

    private val renderer = ConsoleRenderer()
    private val moveCommandParser = MoveCommandParser()
    private val ruleEngine = RuleEngine()

    private var running = false

    fun start() {
        running = true
        val initialState = GameState.initialState()
        val consoleReader = BufferedReader(InputStreamReader(System.`in`))
        gameLoop(initialState, consoleReader)
    }

    private fun gameLoop(initialState: GameState, reader: BufferedReader) {
        var gameState = initialState
        while (running) {
            renderToScreen(gameState)
            promptPlayerInput(gameState)
            val playerInput = readPlayerInput(reader)
            gameState = handlePlayerInput(playerInput, gameState)
        }
    }

    private fun renderToScreen(gameState: GameState) {
        println(renderer.render(gameState))
    }

    private fun promptPlayerInput(gameState: GameState) {
        val playerName = toPlayerName(gameState.currentPlayer)
        System.out.printf("%s's turn.\n", playerName)
        println("Type your move in the format \"from -> to\", e.g., \"e2 -> e8\".")
    }

    private fun toPlayerName(playerColor: PlayerColor): String {
        return when (playerColor) {
            PlayerColor.WHITE -> "White"
            PlayerColor.BLACK -> "Black"
        }
    }

    private fun readPlayerInput(reader: BufferedReader): String {
        return try {
            reader.readLine()
        } catch (e: IOException) {
            println("Failed to read player input. Exiting game.")
            e.printStackTrace()
            running = false
            ""
        }
    }

    private fun handlePlayerInput(playerInput: String, gameState: GameState): GameState {
        val move = try {
            moveCommandParser.parse(playerInput)
        } catch (e: IllegalArgumentException) {
            println("Please make sure your command is in the correct format.")
            return gameState
        }

        val moveResult = ruleEngine.execute(move, gameState)
        if (!moveResult.violations.isEmpty()) {
            println("Invalid move!")
            return gameState
        }

        return moveResult.gameState
    }
}
