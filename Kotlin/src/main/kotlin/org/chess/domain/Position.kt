package org.chess.domain

data class Position(val column: Int, val row: Int) {

    // Temporary backwards compatibility for usages from Java code that expect
    // an implicit get method from a Java record.
    fun column() = column
    fun row() = row
}
