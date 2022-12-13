package day10

import common.InputRepo
import common.readSessionCookie
import common.solve
import util.splitIntoPair

data class AddExecution(var cycles: Int, val arg: Int) {
    fun cycle() {
        cycles--
    }
}

fun main(args: Array<String>) {
    val day = 10
    val input = InputRepo(args.readSessionCookie()).get(day = day)

    solve(day, input, ::solveDay10Part1, ::solveDay10Part2)
}

fun solveDay10Part1(input: List<String>): Int {
    var x = 1
    var cycle = 1
    val executionList = mutableListOf<AddExecution>()

    var sum = 0

    val cmds = input.map { it.splitIntoPair(" ") }.toMutableList()


    var i = 0
    while (executionList.filter { it.cycles >= 0 }.isNotEmpty() || i < cmds.size) {

        sum += signalStrengthOnCorrectCyle(x, cycle)

        if (i < cmds.size) {
            val (cmd, arg) = cmds[i]
            if (cmd == "addx") {
                executionList.add(AddExecution(1, arg.toInt()))
                cmds.add(i + 1, "fakenoop" to "")
            }
        }

        val toExecute = executionList.filter { it.cycles == 0 }
        toExecute.forEach { x += it.arg }

        executionList.forEach { it.cycle() }
        cycle++
        i++

    }

    println("$cycle | X: $x |")
    return sum
}


fun signalStrengthOnCorrectCyle(x: Int, cycle: Int): Int {
    println("$cycle | X: $x |")
    if (cycle == 20 || (cycle - 20) % 40 == 0) {
        println("!!!! $cycle | X: $x |")
        return x * cycle
    }
    return 0
}

fun solveDay10Part2(input: List<String>): Long {
    var x = 1
    var cycle = 1
    val executionList = mutableListOf<AddExecution>()

    val cmds = input.map { it.splitIntoPair(" ") }.toMutableList()

    var i = 0
    while (i < 6 * 40) {

        if (i < cmds.size) {
            val (cmd, arg) = cmds[i]
            if (cmd == "addx") {
                executionList.add(AddExecution(1, arg.toInt()))
                cmds.add(i + 1, "fakenoop" to "")
            }
        }

        // CRT code here
        crtDraw(x, i)

        val toExecute = executionList.filter { it.cycles == 0 }
        toExecute.forEach { x += it.arg }

        executionList.forEach { it.cycle() }
        cycle++
        i++

    }

    return 0
}

fun crtDraw(x: Int, cycle: Int) {
    if (cycle % 40 == 0) {
        println()
    }
    if (setOf(x - 1, x, x + 1).contains(cycle % 40)) {
        print("█")
    } else {
        print(" ")
    }
}