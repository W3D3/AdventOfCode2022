package day21

import common.InputRepo
import common.readSessionCookie
import common.solve

fun main(args: Array<String>) {
    val day = 21
    val input = InputRepo(args.readSessionCookie()).get(day = day)

    solve(day, input, ::solveDay21Part1, ::solveDay21Part2)
}


fun solveDay21Part1(input: List<String>): Long {
    val expressions = input.map { parseJob(it) }.associateBy { it.name }

    val eval = expressions["root"]!!.eval(expressions)

    return eval
}

abstract class Expression(val name: String) {
    abstract fun eval(expressions: Map<String, Expression>): Long
    abstract fun print(expressions: Map<String, Expression>): String
}

class OperationExpression(name: String, val param1: String, val op: Char, val param2: String) : Expression(name) {
    override fun eval(expressions: Map<String, Expression>): Long {
        val expr1 = expressions[param1]!!.eval(expressions)
        val expr2 = expressions[param2]!!.eval(expressions)
        return when (op) {
            '+' -> expr1 + expr2
            '-' -> expr1 - expr2
            '/' -> expr1 / expr2
            '*' -> expr1 * expr2
            else -> throw IllegalArgumentException("aaaa")
        }
    }

    override fun print(expressions: Map<String, Expression>): String {
        val expr1 = expressions[param1]!!.print(expressions)
        val expr2 = expressions[param2]!!.print(expressions)
        return when (op) {
            '+' -> """($expr1 + $expr2)"""
            '-' -> """($expr1 - $expr2)"""
            '/' -> """($expr1 / $expr2)"""
            '*' -> """($expr1 * $expr2)"""
            '=' -> """($expr1) = ($expr2)"""
            else -> throw IllegalArgumentException("aaaa")
        }
    }
}

class LiteralExpression(name: String, private val literal: Long) : Expression(name) {
    override fun eval(expressions: Map<String, Expression>): Long {
        return literal
    }

    override fun print(expressions: Map<String, Expression>): String {
        return if (this.name == "humn") {
            "x"
        } else {
            literal.toString()
        }
    }
}

fun parseJob(line: String): Expression {
    val moveRegex = """^(\w+): (?:(\w+) ([+-/*]) (\w+)|(\d+))$""".toRegex()
    return moveRegex.matchEntire(line)
        ?.destructured
        ?.let { (name, param1, op, param2, literal) ->
            if (literal.isEmpty()) {
                OperationExpression(name, param1, op.first(), param2)
            } else {
                LiteralExpression(name, literal.toLong())
            }
        }
        ?: throw IllegalArgumentException("Bad input '$line'")
}

fun solveDay21Part2(input: List<String>): Int {
    val expressions = input.map { parseJob(it) }.associateBy { it.name }.toMutableMap()
    val root = expressions["root"] as OperationExpression
    expressions["root"] = OperationExpression(root.name, root.param1, '=', root.param2)
    val eval = expressions["root"]!!.print(expressions)

    println(eval)

    // TODO solve the equation in code
    // i just used https://www.mathpapa.com/algebra-calculator.html

    return -1
}