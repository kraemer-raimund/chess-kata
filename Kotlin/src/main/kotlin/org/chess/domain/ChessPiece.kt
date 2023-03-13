package org.chess.domain

data class ChessPiece(val name: ChessPieceName, val color: PlayerColor) {

    // Temporary backwards compatibility for usages from Java code that expect
    // an implicit get method from a Java record.
    fun name() = name
    fun color() = color
}
