package day13

import com.beust.klaxon.JsonArray
import com.beust.klaxon.Parser
import common.InputRepo
import common.readSessionCookie
import common.solve
import util.split

fun main(args: Array<String>) {
    val day = 13
    val input = InputRepo(args.readSessionCookie()).get(day = day)

    solve(day, input, ::solveDay13Part1, ::solveDay13Part2)
}

fun solveDay13Part1(input: List<String>): Int {
    val split = input.split({ it.isEmpty() })
    val pairs = split.map { it.toList() }
        .map { parseBrackets(it[0]) to parseBrackets(it[1]) }

    var index = 1
    var sum = 0
    for ((left, right) in pairs) {
        val compareArrays = compareArrays(left, right)
        if (compareArrays == true) {
            sum += index
        }
        index++
    }

    return sum

}

fun solveDay13Part2(input: List<String>): Int {
    val packetListInput: MutableList<JsonArray<Any>> = input.filter { it.isNotBlank() }
        .map { parseBrackets(it) }
        .toMutableList()

    val dividerPacket1: JsonArray<Any> = JsonArray(JsonArray(listOf(2)))
    val dividerPacket2: JsonArray<Any> = JsonArray(JsonArray(listOf(6)))
    packetListInput.add(dividerPacket1)
    packetListInput.add(dividerPacket2)

    val sortedArrays = packetListInput.sortedWith { o1, o2 ->
        when (compareArrays(o1, o2)) {
            true -> {
                -1
            }

            false -> {
                1
            }

            else -> {
                0
            }
        }
    }

    return (sortedArrays.indexOf(dividerPacket1) + 1) * (sortedArrays.indexOf(dividerPacket2) + 1)
}

private fun compareArrays(
    left: JsonArray<*>,
    right: JsonArray<*>
): Boolean? {
    var i = 0
    while (true) {
        var comparison: Boolean? = null
        if (i >= left.size && i < right.size) {
            // left ran out of values first
            return true
        } else if (i < left.size && i >= right.size) {
            // right ran out of values first
            return false
        } else if (i >= left.size) {
            // no decision
            return null
        }
        val leftValue = left[i]
        val rightValue = right[i]
        when {
            leftValue is Int && rightValue is Int -> {
                comparison = compare(leftValue, rightValue)
            }

            leftValue is JsonArray<*> && rightValue is JsonArray<*> -> {
                comparison = compareArrays(leftValue, rightValue)
            }

            leftValue is Int && rightValue is JsonArray<*> -> {
                comparison = compareArrays(JsonArray(listOf(leftValue)), rightValue)
            }

            leftValue is JsonArray<*> && rightValue is Int -> {
                comparison = compareArrays(leftValue, JsonArray(listOf(rightValue)))
            }
        }
        if (comparison != null) {
            println("Compare $leftValue vs $rightValue: $comparison")
            return comparison
        }
        i++
    }
}

fun compare(left: Int, right: Int): Boolean? {
    return if (left < right)
        true
    else if (left > right)
        false
    else
        null
}

fun parseBrackets(s: String): JsonArray<Any> {
    val parser: Parser = Parser.default()
    val stringBuilder: StringBuilder = StringBuilder(s)
    @Suppress("UNCHECKED_CAST")
    return parser.parse(stringBuilder) as JsonArray<Any>
}

