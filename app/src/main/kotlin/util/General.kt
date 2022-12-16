package util

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun String.splitIntoPair(vararg delimiters: String, ignoreCase: Boolean = false): Pair<String, String> {
    val split = this.split(delimiters = delimiters, ignoreCase = ignoreCase, limit = 2)
    val second = if (split.size < 2) "" else split[1]
    return split[0] to second
}

data class Coord(val x: Int, val y: Int) {

    operator fun plus(other: Coord): Coord {
        return Coord(x + other.x, y + other.y)
    }

    fun getDirectNeighbors(dimX: Int, dimY: Int): Collection<Coord> {
        val neighbors = mutableListOf<Coord>()
        if (x > 0) {
            neighbors.add(Coord(x - 1, y))
        }
        if (x < dimX - 1) {
            neighbors.add(Coord(x + 1, y))
        }
        if (y > 0) {
            neighbors.add(Coord(x, y - 1))
        }
        if (y < dimY - 1) {
            neighbors.add(Coord(x, y + 1))
        }
        return neighbors
    }

    fun manhattanDistance(other: Coord): Int {
        return abs(x - other.x) + abs(y - other.y)
    }

    fun manhattanRange(distance: Int): Set<Coord> {
        val validCoords = (0..distance).flatMap { maxDist ->
            (0..maxDist).map { Coord(it, maxDist - it) } union
                    (0..maxDist).map { Coord(-it, -maxDist + it) } union
                    (0..maxDist).map { Coord(maxDist - it, -it) } union
                    (0..maxDist).map { Coord(-maxDist + it, it) }
        }
            .map { this + it }
        return validCoords.toSet()
    }

    fun getCoordsInExactDistance(distance: Int): Set<Coord> {
        val validCoords = (0..distance).flatMap {
            setOf(
                Coord(it, distance - it), Coord(-it, -distance + it),
                Coord(it, -distance + it),
                Coord(-it, distance - it)
            )
        }
            .map { this + it }
        return validCoords.toSet()
    }

    fun limit(lowerLimit: Int, upperLimit: Int): Coord {
        return Coord(min(max(this.x, lowerLimit), upperLimit), min(max(this.y, lowerLimit), upperLimit))
    }
}