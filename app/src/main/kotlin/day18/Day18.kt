package day18

import common.InputRepo
import common.readSessionCookie
import common.solve
import util.Pos3D

fun main(args: Array<String>) {
    val day = 18
    val input = InputRepo(args.readSessionCookie()).get(day = day)

    solve(day, input, ::solveDay18Part1, ::solveDay18Part2)
}

fun solveDay18Part1(input: List<String>): Int {
    val cubes = input.map { line ->
        line.split(",").let { Cube(Pos3D(it[0].toLong(), it[1].toLong(), it[2].toLong())) }
    }

    return cubes.sumOf { it.countExposedSides((cubes - it).toSet()) }
}

fun solveDay18Part2(input: List<String>): Int {
    val cubes = input.map { line ->
        line.split(",").let { Cube(Pos3D(it[0].toLong(), it[1].toLong(), it[2].toLong())) }
    }

    val visitedPositions = floodFill(cubes.toSet())

    return cubes.sumOf { it.countTouchingSidesPos(visitedPositions) }
}

fun floodFill(cubes: Set<Cube>): Set<Pos3D> {
    val xRange = cubes.minOf { it.pos.x } - 1..cubes.maxOf { it.pos.x } + 1
    val yRange = cubes.minOf { it.pos.y } - 1..cubes.maxOf { it.pos.y } + 1
    val zRange = cubes.minOf { it.pos.z } - 1..cubes.maxOf { it.pos.z } + 1

    val startPos = Pos3D(xRange.first, yRange.first, zRange.first)
    val visitedPositions = mutableSetOf(startPos)

    var basePositions = setOf(startPos)
    val exploredPositions: MutableList<Pos3D> = mutableListOf()
    do {
        for (basePos in basePositions) {
            exploredPositions += basePos.directNeighbors
                .filter { it.x in xRange && it.y in yRange && it.z in zRange }
                .filter { floodPos -> floodPos !in cubes.map { it.pos } }
                .toSet()
        }
        basePositions = (exploredPositions - visitedPositions).toSet()
        visitedPositions.addAll(basePositions)
    } while (basePositions.isNotEmpty())

    return visitedPositions
}

data class Cube(val pos: Pos3D) {
    fun countExposedSides(others: Set<Cube>): Int {
        return 6 - countTouchingSides(others)
    }

    fun countTouchingSides(others: Set<Cube>): Int {
        return countTouchingSidesPos(others.map { it.pos }.toSet())
    }

    fun countTouchingSidesPos(others: Set<Pos3D>): Int {
        return others.count { otherPos -> this.pos.isDirectNeighbor(otherPos) }
    }
}