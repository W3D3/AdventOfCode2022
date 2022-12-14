package util

fun String.splitIntoPair(vararg delimiters: String, ignoreCase: Boolean = false): Pair<String, String> {
    val split = this.split(delimiters = delimiters, ignoreCase = ignoreCase, limit = 2)
    val second = if (split.size < 2) "" else split[1]
    return split[0] to second
}

data class Coord(val x: Int, val y: Int) {
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
        return neighbors;
    }
}