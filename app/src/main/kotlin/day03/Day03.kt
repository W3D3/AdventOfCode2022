package day03

import common.InputRepo
import common.readSessionCookie
import common.solve
import util.intersection

fun main(args: Array<String>) {
    val day = 3
    val input = InputRepo(args.readSessionCookie()).get(day = day)

    solve(day, input, ::solveDay03Part1, ::solveDay03Part2)
}

fun solveDay03Part1(input: List<String>): Int {
    val commonElements = input.map { it.substring(0, it.length / 2) to it.substring(it.length / 2) }
        .flatMap { (left, right) -> intersection(left.toCharArray().toSet(), right.toCharArray().toSet()) }

    return commonElements.sumOf { getPriority(it) }
}

fun solveDay03Part2(input: List<String>): Int {
    val commonElements = input.windowed(3, 3)
        .flatMap {
            intersection(
                it[0].toCharArray().toSet(),
                it[1].toCharArray().toSet(),
                it[2].toCharArray().toSet()
            )
        }
        .sumOf { getPriority(it) }

    return commonElements

}

fun getPriority(char: Char): Int {
    return if (char.isLowerCase()) {
        char.code - 96 // 97 is 'a' in ASCII
    } else {
        char.code - 65 + 27 // 65 is 'A' in ASCII
    }
}