package org.chess.domain

data class Move(val from: Position, val to: Position) {

    // Temporary backwards compatibility for usages from Java code that expect
    // an implicit get method from a Java record.
    fun from() = from
    fun to() = to
}
