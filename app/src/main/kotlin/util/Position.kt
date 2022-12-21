package util

private val directNeighborDirections = listOf(
    Pos3D(0, 0, 1),
    Pos3D(0, 0, -1),
    Pos3D(0, 1, 0),
    Pos3D(0, -1, 0),
    Pos3D(1, 0, 0),
    Pos3D(-1, 0, 0),
)
data class Pos3D(val x: Long, val y: Long, val z: Long) {

    infix operator fun plus(other: Pos3D): Pos3D {
        return Pos3D(x + other.x, y + other.y, z + other.z)
    }

    val directNeighbors: List<Pos3D> by lazy {
        directNeighborDirections.map { it + this }
    }

    fun isDirectNeighbor(pos: Pos3D): Boolean {
        return directNeighbors.contains(pos)
    }
}

