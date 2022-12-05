package day05

import common.InputRepo
import common.readSessionCookie
import common.solve
import util.split

fun main(args: Array<String>) {
    val day = 5
    val input = InputRepo(args.readSessionCookie()).get(day = day)

    solve(day, input, ::solveDay05Part1, ::solveDay05Part2)
}

fun solveDay05Part1(input: List<String>): String {
    val (initialConditions, instructions) = input.split({ it.isBlank() })
    val map = generateStackMap(initialConditions)

    for (instructionLine in instructions) {
        val instruction = parseInstruction(instructionLine)

        repeat(instruction.repetitions) {
            val removed = map[instruction.origin]!!.removeLast()
            map[instruction.target]?.addLast(removed)
        }
    }

    return map.values.mapNotNull { it.lastOrNull() }
        .joinToString(separator = "") { it.toString() }
}

private fun generateStackMap(split: Collection<String>): MutableMap<Int, ArrayDeque<Char>> {
    val initialStrings = split.reversed()

    var numOfStacks: Int
    val map = mutableMapOf<Int, ArrayDeque<Char>>()
    for ((index, initialString) in initialStrings.withIndex()) {
        if (index == 0) {
            numOfStacks = initialString.split("""\w""".toRegex()).count()
            for (i in 0..numOfStacks) {
                map[i] = ArrayDeque()
            }
        } else {
            initialString.windowed(3, 4)
                .map { println(it); it }
                .mapIndexed { i, s ->
                    println("""${i + 1} $s""")
                    if (s.isNotBlank()) {
                        map[i]?.addLast(getChar(s))
                    }
                }
            println(map)
        }
    }
    return map
}

private fun getChar(boxSyntax: String): Char {
    return boxSyntax.toCharArray()[1]
}

data class Instruction(val repetitions: Int, val origin: Int, val target: Int)

private fun parseInstruction(line: String): Instruction {
    val moveRegex = """move (\d+) from (\d+) to (\d+)""".toRegex()
    return moveRegex.matchEntire(line)
        ?.destructured
        ?.let { (repetitions, origin, target) ->
            Instruction(repetitions.toInt(), origin.toInt() - 1, target.toInt() - 1)
        }
        ?: throw IllegalArgumentException("Bad input '$line'")
}


fun solveDay05Part2(input: List<String>): String {
    println(input)
    val (initialConditions, instructions) = input.split({ it.isBlank() })
    val map = generateStackMap(initialConditions)

    for (instructionLine in instructions) {
        val instruction = parseInstruction(instructionLine)

        val removedList = mutableListOf<Char>()
        repeat(instruction.repetitions) {
            removedList.add(map[instruction.origin]!!.removeLast())
        }
        removedList.reversed().forEach {
            map[instruction.target]?.add(it)
        }
    }

    return map.values.mapNotNull { it.lastOrNull() }
        .joinToString(separator = "") { it.toString() }
}