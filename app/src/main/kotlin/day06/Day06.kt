package day06

import common.InputRepo
import common.readSessionCookie
import common.solve

fun main(args: Array<String>) {
    val day = 6
    val input = InputRepo(args.readSessionCookie()).get(day = day)

    solve(day, input, ::solveDay06Part1, ::solveDay06Part2)
}

private fun readCharsUntilAllUnique(list: List<Char>, windowSize: Int): Int {
    return list.windowed(windowSize, 1).indexOfFirst { it.toSet().size == it.size } + windowSize
}

fun solveDay06Part1(input: List<String>): Int {
    return readCharsUntilAllUnique(input.first().toCharArray().toList(), 4)
}

fun solveDay06Part2(input: List<String>): Int {
    return readCharsUntilAllUnique(input.first().toCharArray().toList(), 14)
}