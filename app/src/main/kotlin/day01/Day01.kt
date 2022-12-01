package day01

import common.InputRepo
import common.readSessionCookie
import common.solve
import util.split

fun main(args: Array<String>) {
    val day = 1
    val input = InputRepo(args.readSessionCookie()).get(day = day)

    solve(day, input, ::solveDay01Part1, ::solveDay01Part2)
}

fun solveDay01Part1(input: List<String>): Int {
    return input.split({ it.isEmpty() })
        .maxOf { it.sumOf(String::toInt) }
}

fun solveDay01Part2(input: List<String>): Int {
    return input.split({ it.isEmpty() })
        .map { it.sumOf(String::toInt) }
        .sortedDescending().subList(0, 3)
        .sum()
}
