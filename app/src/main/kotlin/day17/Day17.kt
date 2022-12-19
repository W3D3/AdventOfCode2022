package day17

import common.InputRepo
import common.readSessionCookie
import common.solve
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.ndarray
import org.jetbrains.kotlinx.multik.api.ones
import org.jetbrains.kotlinx.multik.ndarray.data.D2Array
import org.jetbrains.kotlinx.multik.ndarray.operations.forEachMultiIndexed
import java.lang.Long.max

fun main(args: Array<String>) {
    val day = 17
    val input = InputRepo(args.readSessionCookie()).get(day = day)

    solve(day, input, ::solveDay17Part1, ::solveDay17Part2)
}

sealed class Shape(val colliderMatrix: D2Array<Int>) {

    var pos = Coord(0, 0)

    private fun getOccupiedPositions(base: Coord): Set<Coord> {
        val occupiedPositions = mutableSetOf<Coord>()
        colliderMatrix.forEachMultiIndexed { index, occupied ->
            val x = index[0]
            val y = index[1]
            if (occupied == 1) {
                occupiedPositions.add(Coord(x.toLong(), y.toLong()) + base)
            }
        }
        return occupiedPositions
    }

    fun getOccupiedPositions(): Set<Coord> {
        return getOccupiedPositions(pos)
    }

    fun movePositions(direction: Coord): Set<Coord> {
        return getOccupiedPositions(this.pos + direction)
    }

    fun move(direction: Coord) {
        this.pos += direction
    }

    object HORIZONTAL_LINE : Shape(colliderMatrix = mk.ones(4, 1))

    object PLUS : Shape(
        colliderMatrix = mk.ndarray(
            listOf(
                listOf(0, 1, 0),
                listOf(1, 1, 1),
                listOf(0, 1, 0),
            )
        )
    )

    object MIRRORED_L : Shape(
        colliderMatrix = mk.ndarray(
            listOf(
                listOf(1, 0, 0),
                listOf(1, 0, 0),
                listOf(1, 1, 1),
            )
        )
    )

    object VERTICAL_LINE : Shape(colliderMatrix = mk.ones(1, 4))
    object BOX : Shape(colliderMatrix = mk.ones(2, 2))

}

fun solveDay17Part1(input: List<String>): Long {
    val possibleShapes = listOf(Shape.HORIZONTAL_LINE, Shape.PLUS, Shape.MIRRORED_L, Shape.VERTICAL_LINE, Shape.BOX)
    val airPushes = parseAirPushes(input)

    val occupiedPositions = mutableSetOf<Coord>()
    var j = 0
    for (i in 0 until 2022) {
        val shape = possibleShapes[i % possibleShapes.size]
        val startX = 3L
        val startY = (occupiedPositions.maxOfOrNull { it.y } ?: 0) + 4
        shape.pos = Coord(startX, startY)

        do {
            val airPush = airPushes[j % airPushes.size]
            j++

            tryMove(shape, occupiedPositions, airPush)

            if (!tryMove(shape, occupiedPositions, Coord.DOWN)) break

        } while (true)
        occupiedPositions.addAll(shape.getOccupiedPositions())
    }

    return occupiedPositions.map { it.y }.max()
}

private const val RIGHT_WALL_X = 8L

private const val LEFT_WALL_X = 0L
private const val FLOOR_Y = 0L

private fun tryMove(shape: Shape, occupiedPositions: MutableSet<Coord>, dir: Coord): Boolean {
    val possiblePosition = shape.movePositions(dir)
    when {
        possiblePosition.intersect(occupiedPositions)
            .isNotEmpty() -> {
            return false
        }

        possiblePosition.any { it.y == FLOOR_Y || it.x == LEFT_WALL_X || it.x == RIGHT_WALL_X } -> {
            return false
        }

        else -> {
            shape.move(dir)
        }
    }
    return true
}

fun parseAirPushes(input: List<String>): List<Coord> {
    return input.first().map {
        when (it) {
            '>' -> {
                Coord.RIGHT
            }

            '<' -> {
                Coord.LEFT
            }

            else -> {
                throw IllegalArgumentException("Unsupported move")
            }
        }
    }.toList()
}

fun printGrid(n: Long, m: Long, marker: Set<Coord>) {
    for (y in m downTo 0) {
        for (x in 0..n) {
            val coord = Coord(x, y)
            if (coord.x == LEFT_WALL_X || coord.x == RIGHT_WALL_X) {
                print("|")
            } else if (coord.y == LEFT_WALL_X) {
                print("-")
            } else if (marker.contains(coord)) {
                print("@")
            } else {
                print(".")
            }
        }
        println()
    }
    println()
}

fun solveDay17Part2(input: List<String>): Long {
    val possibleShapes = listOf(Shape.HORIZONTAL_LINE, Shape.PLUS, Shape.MIRRORED_L, Shape.VERTICAL_LINE, Shape.BOX)
    val airPushes = parseAirPushes(input)

    var height = LEFT_WALL_X
    val stateMap = mutableMapOf<State, StateMeta>()
    val occupiedPositions = mutableSetOf<Coord>()
    var j = LEFT_WALL_X
    val TOTAL_SHAPES = 1_000_000_000_000
//    val TOTAL_SHAPES = 2022
    var skippedHeight = LEFT_WALL_X;

    var i = LEFT_WALL_X;
    while (i in 0 until TOTAL_SHAPES) {
//    for (i in 0 until TOTAL_SHAPES) {
        val shapeIndex = (i % possibleShapes.size).toInt()
        val shape = possibleShapes[shapeIndex]
        val startX = 3L
        val startY = (occupiedPositions.maxOfOrNull { it.y } ?: 0) + 4 //+ shape.height - 1
        shape.pos = Coord(startX, startY)

//        println("${shape} starts falling at ${shape.pos}")
//        printGrid(8, startY, droppedShapes.flatMap { it.getOccupiedPositions() }.toSet())
        var airPushIndex: Int
        do {
            airPushIndex = (j % airPushes.size).toInt()
            val airPush = airPushes[airPushIndex]
            j++

            tryMove(shape, occupiedPositions, airPush)

//            println("${shape.name} got moved $airPush: $movedAir")
//            printGrid(8, startY, droppedShapes.flatMap { it.getOccupiedPositions() }.toSet())
            if (!tryMove(shape, occupiedPositions, Coord.DOWN)) break

//            println("${shape.name} got moved down by gravity.")
//            printGrid(8, startY, droppedShapes.flatMap { it.getOccupiedPositions() }.toSet())

        } while (true)
        // shape has settled
        occupiedPositions.addAll(shape.getOccupiedPositions())
        height = max(shape.getOccupiedPositions().maxOf { it.y }, height)
        println(height)

        val stateHash = stateHash(airPushIndex, shapeIndex, occupiedPositions, height)
        if (stateMap.contains(stateHash)) {
            // found loop!
            println("found loop")
            val (previousHeight, shapesDropped) = stateMap[stateHash]!!
            val cycleHeight = height - previousHeight;
            val cycleShapeCount = i - shapesDropped
            val remainingShapes = (TOTAL_SHAPES - i) / cycleShapeCount

            i += remainingShapes * cycleShapeCount;
            skippedHeight += cycleHeight * remainingShapes
        } else {
            stateMap[stateHash] = StateMeta(height, i)
        }
//        println("${shape.name} settled in at ${shape.pos}.")
//        printGrid(8, startY, droppedShapes.flatMap { it.getOccupiedPositions() }.toSet())
        i++
    }

    return height + skippedHeight
}

data class StateMeta(val height: Long, val shapesDropped: Long)

fun stateHash(airIndex: Int, shapeIndex: Int, occupiedPositions: Set<Coord>, height: Long, lookback: Int = 10): State {
    val lastNLines = mutableListOf<Boolean>()
    for (y in height downTo height - lookback) {
        for (x in 1L..7L) {
            lastNLines.add(occupiedPositions.contains(Coord(x, y)))
        }
    }

//    println(State(airIndex, shapeIndex, lastNLines))
    return State(airIndex, shapeIndex, lastNLines)
}

data class State(val airIndex: Int, val shapeIndex: Int, val lastPositions: List<Boolean>)

data class Coord(val x: Long, val y: Long) {

    operator fun plus(other: Coord): Coord {
        return Coord(x + other.x, y + other.y)
    }

    companion object {
        val DOWN: Coord = Coord(0, -1)
        val UP: Coord = Coord(0, 1)
        val LEFT: Coord = Coord(-1, 0)
        val RIGHT: Coord = Coord(1, 0)
    }
}