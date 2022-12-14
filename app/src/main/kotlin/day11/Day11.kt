package day11

import com.github.keelar.exprk.Expressions
import common.InputRepo
import common.readSessionCookie
import common.solve
import util.split
import java.math.BigInteger

data class MonkeyTest(val divisor: Int, val positive: Int, val negative: Int)
data class Monkey(
    val id: Int,
    val items: MutableList<BigInteger>,
    val operation: String,
    val test: MonkeyTest
) {

    var inspects = 0L

    fun executeOperation(worryLevel: BigInteger): BigInteger {
        inspects++
        return Expressions()
            .define("old", worryLevel.toBigDecimal())
            .eval(operation)
            .toBigInteger()
    }

    fun executeTest(worryLevel: BigInteger): Int {
        return if (worryLevel % test.divisor.toBigInteger() == BigInteger.ZERO) {
            test.positive
        } else {
            test.negative
        }
    }
}

fun main(args: Array<String>) {
    val day = 11
    val input = InputRepo(args.readSessionCookie()).get(day = day)

    solve(day, input, ::solveDay11Part1, ::solveDay11Part2)
}

fun solveDay11Part1(input: List<String>): Long {
    val monkeys = input.split({ it.isBlank() })
        .map { convertToMonkey(it) }

    println(monkeys)

    repeat(20, action = {
        for (monkey in monkeys) {
            for (item in monkey.items) {
                println("${monkey.id} inspects item with worry level ${item}")
                val worry = monkey.executeOperation(item) / BigInteger.valueOf(3L)
                val target = monkey.executeTest(worry)
                println("throw to $target")
                monkeys.first { it.id == target }.items.add(worry)
            }
            monkey.items.clear()
        }
    })

    val mostActive = monkeys.sortedByDescending { it.inspects }
    return mostActive[0].inspects * mostActive[1].inspects
}

fun convertToMonkey(monkeyDefinition: Collection<String>): Monkey {
    val startingItemsPrefix = "Starting items:"
    val opPrefix = "Operation:"

    var id = 0
    var list: MutableList<BigInteger> = emptyList<BigInteger>().toMutableList()
    var operation = ""
    var divisor = 0
    var pos = 0
    var neg = 0
    // this is why I just should have used RegEx.
    for (line in monkeyDefinition.map { it.trim() }) {
        if (line.startsWith("Monkey")) {
            id = line.removePrefix("Monkey")
                .trim()
                .removeSuffix(":")
                .toInt()
        }
        if (line.startsWith(startingItemsPrefix)) {
            list = line.removePrefix(startingItemsPrefix)
                .trim()
                .split(", ").map { BigInteger.valueOf(it.toLong()) }.toMutableList()
        }
        if (line.startsWith(opPrefix)) {
            operation = line.removePrefix(opPrefix)
                .trim()
                .removePrefix("new =")
                .trim()
        }
        if (line.startsWith("Test:")) {
            divisor = line.removePrefix("Test: divisible by")
                .trim()
                .toInt()
        }
        if (line.startsWith("Test:")) {
            divisor = line.removePrefix("Test: divisible by")
                .trim()
                .toInt()
        }
        if (line.startsWith("If true: throw to monkey ")) {
            pos = line.removePrefix("If true: throw to monkey ")
                .trim()
                .toInt()
        }
        if (line.startsWith("If false: throw to monkey ")) {
            neg = line.removePrefix("If false: throw to monkey ")
                .trim()
                .toInt()
        }
    }

    return Monkey(id, list, operation, MonkeyTest(divisor, pos, neg))

}

fun solveDay11Part2(input: List<String>): Long {
    val monkeys = input.split({ it.isBlank() })
        .map { convertToMonkey(it) }

    // gcd for all primes is just multiplication
    val gcd = monkeys.map { it.test.divisor }.reduce { acc: Int, current: Int -> acc * current }

    repeat(10000, action = {
        for (monkey in monkeys) {
            for (item in monkey.items) {
                val worry = monkey.executeOperation(item) % gcd.toBigInteger()

                val target = monkey.executeTest(worry)
                monkeys.first { it.id == target }.items.add(worry)
            }
            monkey.items.clear()
        }
    })

    val mostActive = monkeys.sortedByDescending { it.inspects }

    mostActive.forEach { println("${it.id}: ${it.inspects}") }
    return mostActive[0].inspects * mostActive[1].inspects
}