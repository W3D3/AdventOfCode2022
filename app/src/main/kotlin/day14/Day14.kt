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

data class Path(val a: Pos, val b: Pos, val isSand: Boolean = false) {

    private var fixedX: Boolean = false
    private var fixedAxis: Int = 0
    var points: IntRange = 0..0

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

    fun isOnPath(pos: Pos): Boolean {
        return if (fixedX) {
            pos.x == fixedAxis && points.contains(pos.y)
        } else {
            pos.y == fixedAxis && points.contains(pos.x)
        }
    }

    fun getPostions(): List<Pos> {
        if (fixedX) {
            return points.map { Pos(fixedAxis, it) }
        } else {
            return points.map { Pos(it, fixedAxis) }
        }
    }

}

fun solveDay14Part1(input: List<String>): Int {
    val paths = input.flatMap {
        it.split(" -> ")
            .map {
                val p = it.trim().splitIntoPair(",")
                Pos(p.first.toInt(), p.second.toInt())
            }
            .zipWithNext { a, b -> Path(a, b) }
    }.toMutableList()
    var cnt = 0

    val sandOrigin = Pos(500, 0)
    val pathBarrierDown = paths.maxOf { it.a.y.coerceAtLeast(it.b.y) }

    do {
        val block = simulateSingleSandBlock(paths, sandOrigin, pathBarrierDown)

        if (block != null) {
            cnt++
//            printCave(sandOrigin, block, paths)
            paths.add(Path(block, block, isSand = true))
        }
    } while (block != null)
//    printCave(sandOrigin, sandOrigin, paths)

    return cnt
}


fun printCave(source: Pos, current: Pos, paths: List<Path>, depth: Int) {
    for (y in 0..depth) {
        for (x in 400..520) {
            if (paths.any { !it.isSand && it.isOnPath(Pos(x, y)) }) {
                print("#")
            } else if (paths.any { it.isSand && it.isOnPath(Pos(x, y)) }) {
                print("o")
            } else if (current.x == x && current.y == y) {
                print("v")
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

fun printCave(source: Pos, current: Pos, points: Set<Pos>, depth: Int) {
    for (y in 0..depth) {
        for (x in 400..520) {
            if (points.contains(Pos(x, y))) {
                print("#")
//            } else if (paths.any { it.isSand && it.isOnPath(Pos(x, y)) }) {
//                print("o")
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

fun simulateSingleSandBlock(paths: List<Path>, sandOrigin: Pos, pathBarrierDown: Int): Pos? {
//    var pathBarrierDown = paths.maxOf { it.a.y.coerceAtLeast(it.b.y) }

    var currentSandPos = sandOrigin
    var nextSandPos = moveSand(currentSandPos, paths)
//    printCave(sandOrigin, nextSandPos, paths)
    while (nextSandPos != currentSandPos) {
        currentSandPos = nextSandPos
        nextSandPos = moveSand(currentSandPos, paths)
//        printCave(sandOrigin, nextSandPos, paths)

        if (nextSandPos.y > pathBarrierDown) {
            // freefall detected
            break
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

fun simulateSingleSandBlock(points: Set<Pos>, sandOrigin: Pos, floorLevel: Int): Pos? {
    var currentSandPos = sandOrigin
    var nextSandPos = moveSand(currentSandPos, points)

    while (nextSandPos != currentSandPos) {
        currentSandPos = nextSandPos
        nextSandPos = moveSand(currentSandPos, points)

        if (nextSandPos.y > floorLevel) {
            // freefall detected
            break
        }
        // pt2
        if (nextSandPos.y == floorLevel) {
            // cant go through
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

fun moveSand(currentSandPos: Pos, paths: List<Path>): Pos {
    val down = Pos(currentSandPos.x, currentSandPos.y + 1)
    val downLeft = Pos(currentSandPos.x - 1, currentSandPos.y + 1)
    val downRight = Pos(currentSandPos.x + 1, currentSandPos.y + 1)

    if (paths.none { it.isOnPath(down) }) {
        return down
    } else if (paths.none { it.isOnPath(downLeft) }) {
        return downLeft
    } else if (paths.none { it.isOnPath(downRight) }) {
        return downRight
    } else {
        return currentSandPos
    }
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

fun solveDay14Part2(input: List<String>): Int {
    val points = input.flatMap {
        it.split(" -> ")
            .map {
                val p = it.trim().splitIntoPair(",")
                Pos(p.first.toInt(), p.second.toInt())
            }
            .zipWithNext { a, b -> Path(a, b) }
    }.flatMap { it.getPostions().toSet() }.toMutableSet()
    var cnt = 0

    val sandOrigin = Pos(500, 0)
    val floorLevel = points.maxOf { it.y } + 2
//    points.addAll(Path(Pos(Int.MIN_VALUE, floorLevel), Pos(Int.MAX_VALUE, floorLevel)).getPostions())

    do {
        val block = simulateSingleSandBlock(points, sandOrigin, floorLevel)
        if (block != null) {
            cnt++
            if (cnt % 1000 == 0) {
                printCave(sandOrigin, block, points, depth = floorLevel + 1)
            }
            points.add(block)
        }
    } while (block != null)
//    printCave(sandOrigin, sandOrigin, points, floorLevel + 1)

    return cnt + 1 // add one for source
}