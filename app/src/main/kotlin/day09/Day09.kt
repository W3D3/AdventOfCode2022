package day09

import common.InputRepo
import common.readSessionCookie
import common.solve
import util.splitIntoPair
import kotlin.math.abs

fun main(args: Array<String>) {
    val day = 9
    val input = InputRepo(args.readSessionCookie()).get(day = day)

    solve(day, input, ::solveDay09Part1, ::solveDay09Part2)
}

data class Pos(val x: Int, val y: Int) {
    fun move(direction: String): Pos {
        when (direction) {
            "U" -> {
                return Pos(x, y + 1)
            }

            "D" -> {
                return Pos(x, y - 1)
            }

            "L" -> {
                return Pos(x - 1, y)
            }

            "R" -> {
                return Pos(x + 1, y)
            }
        }
        throw IllegalArgumentException("Invalid direction")
    }

    fun follow(head: Pos): Pos {

        val xDiff = head.x - x
        val yDiff = head.y - y

        if (abs(xDiff) > 1 && abs(yDiff) >= 1 || abs(xDiff) >= 1 && abs(yDiff) > 1) {
            return Pos(this.x + 1 * sign(xDiff), this.y + 1 * sign(yDiff))
        } else if (abs(xDiff) > 1) {
            return Pos(this.x + 1 * sign(xDiff), this.y)
        } else if (abs(yDiff) > 1) {
            return Pos(this.x, this.y + +1 * sign(yDiff))
        }
        return this
    }
}

fun sign(value: Int): Int {
    if (value < 0) {
        return -1
    } else if (value > 0) {
        return 1
    } else {
        return 0
    }
}

fun solveDay09Part1(input: List<String>): Int {
    val moves = input.map { it.splitIntoPair(" ") }

    var head = Pos(0, 0)
    var tail = Pos(0, 0)
    val tailVisited = mutableSetOf(tail)
    moves.forEach { (dir, repeat) ->
        repeat(repeat.toInt()) {
            head = head.move(dir)
            tail = tail.follow(head)
            tailVisited.add(tail)
        }
    }

    return tailVisited.size

}

fun printGrid(n: Int, head: Pos, tails: List<Pos>) {
    for (y in n downTo 0) {
        for (x in 0..n) {
            if (x == head.x && y == head.y) {
                print("H")
            } else if (tails.firstOrNull { it.x == x && it.y == y } != null) {
                print(tails.indexOf(tails.firstOrNull { it.x == x && it.y == y }))
//                print("T")
            } else {
                print(".")
            }
        }
        println()
    }
}

fun solveDay09Part2(input: List<String>): Int {
    val moves = input.map { it.splitIntoPair(" ") }

    var head = Pos(0, 0)
    val tails = mutableListOf(
        Pos(0, 0),
        Pos(0, 0),
        Pos(0, 0),
        Pos(0, 0),
        Pos(0, 0),
        Pos(0, 0),
        Pos(0, 0),
        Pos(0, 0),
        Pos(0, 0),
    )
    val tailVisited = tails.toMutableSet()
    moves.forEach { (dir, repeat) ->
        repeat(repeat.toInt()) {
            printGrid(5, head, tails)

            head = head.move(dir)
            for ((index, tail) in tails.withIndex()) {
                val currentFollowHead = if (index > 0) tails[index - 1] else head
                tails[index] = tail.follow(currentFollowHead)
            }

            tailVisited.add(tails[8])
        }

    }

    return tailVisited.size
}
