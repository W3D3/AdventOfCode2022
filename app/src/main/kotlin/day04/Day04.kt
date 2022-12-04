package day04

import common.InputRepo
import common.readSessionCookie
import common.solve
import util.fullyContains
import util.splitIntoPair

fun main(args: Array<String>) {
    val day = 4
    val input = InputRepo(args.readSessionCookie()).get(day = day)

    solve(day, input, ::solveDay04Part1, ::solveDay04Part2)
}

fun solveDay04Part1(input: List<String>): Int {
    val sum: Int = mapToRanges(input)
        .map { (range1, range2) ->
            range1.fullyContains(range2) || range2.fullyContains(range1)
        }
        .count { it }
    return sum
}

fun solveDay04Part2(input: List<String>): Int {
    return mapToRanges(input)
        .map { (range1, range2) -> range1.any { range2.contains(it) } }
        .count { it }
}

private fun mapToRanges(input: List<String>) = input.map { it.splitIntoPair(",") }
    .map { (left, right) ->
        val leftPair = left.splitIntoPair("-")
        val rightPair = right.splitIntoPair("-")

        IntRange(leftPair.first.toInt(), leftPair.second.toInt()) to
                IntRange(rightPair.first.toInt(), rightPair.second.toInt())
    }