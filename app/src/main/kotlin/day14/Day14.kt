package day14

import common.InputRepo
import common.readSessionCookie
import common.solve
import util.splitIntoPair

fun main(args: Array<String>) {
    val day = 14
    val input = InputRepo(args.readSessionCookie()).get(day = day)

    solve(day, input, ::solveDay14Part1, ::solveDay14Part2)
}

data class Pos(val x: Int, val y: Int)

data class Path(val a: Pos, val b: Pos) {

    private var fixedX: Boolean = false
    private var fixedAxis: Int = 0
    private var points: IntRange = 0..0

    init {
        if (a.x == b.x) {
            fixedX = true
            fixedAxis = a.x
            points = if (a.y < b.y) {
                a.y..b.y
            } else {
                b.y..a.y
            }
        } else if (a.y == b.y) {
            fixedX = false
            fixedAxis = a.y
            points = if (a.x < b.x) {
                a.x..b.x
            } else {
                b.x..a.x
            }
        } else {
            throw IllegalArgumentException("Invalid path")
        }
    }

    fun getPositions(): List<Pos> {
        return if (fixedX) {
            points.map { Pos(fixedAxis, it) }
        } else {
            points.map { Pos(it, fixedAxis) }
        }
    }

}

fun solveDay14Part1(input: List<String>): Int {
    val takenPositions = parseInput(input)

    val sandOrigin = Pos(500, 0)
    val floorLevel = takenPositions.maxOf { it.y }

    return simulateSand(takenPositions, sandOrigin, floorLevel, fallThrough = true)
}


fun solveDay14Part2(input: List<String>): Int {
    val takenPositions = parseInput(input)

    val sandOrigin = Pos(500, 0)
    val floorLevel = takenPositions.maxOf { it.y } + 2

    return simulateSand(takenPositions, sandOrigin, floorLevel, fallThrough = false) + 1 // add one for source
}

fun printCave(source: Pos, current: Pos, points: Set<Pos>, depth: Int) {
    for (y in 0..depth) {
        for (x in 400..520) {
            if (points.contains(Pos(x, y))) {
                print("#")
            } else if (current.x == x && current.y == y) {
                print("o")
            } else if (source.x == x && source.y == y) {
                print("+")
            } else {
                print(".")
            }
        }
        println()
    }
    println()
}

fun simulateSingleSandBlock(points: Set<Pos>, sandOrigin: Pos, floorLevel: Int, fallThrough: Boolean): Pos? {
    var currentSandPos = sandOrigin
    var nextSandPos = moveSand(currentSandPos, points)

    while (nextSandPos != currentSandPos) {
        currentSandPos = nextSandPos
        nextSandPos = moveSand(currentSandPos, points)

        if (nextSandPos.y > floorLevel) {
            // freefall detected
            break
        }
        if (!fallThrough && nextSandPos.y == floorLevel) {
            // cant go through floor
            return currentSandPos
        }
        if (nextSandPos == currentSandPos) {
            // we settled
            return currentSandPos
        }
        if (nextSandPos == sandOrigin) {
            return null
        }
    }
    return null
}

fun moveSand(currentSandPos: Pos, points: Set<Pos>): Pos {
    val down = Pos(currentSandPos.x, currentSandPos.y + 1)
    val downLeft = Pos(currentSandPos.x - 1, currentSandPos.y + 1)
    val downRight = Pos(currentSandPos.x + 1, currentSandPos.y + 1)

    return if (down !in points) {
        down
    } else if (downLeft !in points) {
        downLeft
    } else if (downRight !in points) {
        downRight
    } else {
        currentSandPos
    }
}

private fun simulateSand(
    takenPositions: MutableSet<Pos>, sandOrigin: Pos, floorLevel: Int, fallThrough: Boolean
): Int {
    var cnt = 0
    do {
        val block = simulateSingleSandBlock(takenPositions, sandOrigin, floorLevel, fallThrough)
        if (block != null) {
            cnt++
            if (cnt % 1000 == 0) {
                printCave(sandOrigin, block, takenPositions, depth = floorLevel + 1)
            }
            takenPositions.add(block)
        }
    } while (block != null)
    printCave(sandOrigin, sandOrigin, takenPositions, floorLevel + 1)

    return cnt
}

private fun parseInput(input: List<String>): MutableSet<Pos> {
    val takenPositions = input.flatMap {
        it.split(" -> ").map {
            val p = it.trim().splitIntoPair(",")
            Pos(p.first.toInt(), p.second.toInt())
        }.zipWithNext { a, b -> Path(a, b) }
    }.flatMap { it.getPositions().toSet() }.toMutableSet()
    return takenPositions
}