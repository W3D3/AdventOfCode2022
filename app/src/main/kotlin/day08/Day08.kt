package day08

import common.InputRepo
import common.readSessionCookie
import common.solve
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.ndarray
import org.jetbrains.kotlinx.multik.ndarray.data.D2
import org.jetbrains.kotlinx.multik.ndarray.data.D2Array
import org.jetbrains.kotlinx.multik.ndarray.data.NDArray
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.operations.forEachMultiIndexed
import org.jetbrains.kotlinx.multik.ndarray.operations.mapMultiIndexed
import org.jetbrains.kotlinx.multik.ndarray.operations.toList

fun main(args: Array<String>) {
    val day = 8
    val input = InputRepo(args.readSessionCookie()).get(day = day)

    solve(day, input, ::solveDay08Part1, ::solveDay08Part2)
}

fun solveDay08Part1(input: List<String>): Int {
    val nestedList: List<List<Int>> = input.map { it.toCharArray().map { d -> d.digitToInt() }.toList() }
    println(nestedList)
    val treeMap = mk.ndarray(nestedList)

    println(treeMap)

    var cnt = 0
    treeMap.forEachMultiIndexed { index, i ->
        println("> Checking  ${index.toList()} $i")

        if (isEdge(treeMap, index[0], index[1])) {
            println("""isEdge: ${index.toList()} $i""")
            cnt++
        } else {
            val topVisible = (0 until index[0]).all { x ->
                println("""top check [${x}, ${index[1]}] ${treeMap.get(x, index[1])}""")
                i > treeMap.get(x, index[1])
            }

            val bottomVisible = (index[0] + 1 until treeMap.shape[0]).all { x ->
                println("""bottom check [${x}, ${index[1]}] ${treeMap.get(x, index[1])}""")
                i > treeMap.get(x, index[1])
            }

            val leftVisible = (0 until index[1]).all { y ->
                println("""left check [${index[0]}, ${y}] ${treeMap.get(index[0], y)}""")
                i > treeMap.get(index[0], y)
            }

            val rightVisible = (index[1] + 1 until treeMap.shape[0]).all { y ->
                println("""right check [${index[0]}, ${y}] ${treeMap.get(index[0], y)}""")
                i > treeMap.get(index[0], y)
            }

            if (topVisible || bottomVisible || leftVisible || rightVisible) {
                println("""VISIBLE: [${index[0]}, ${index[1]}] ${treeMap.get(index[0], index[1])}""")
                cnt++
            }
        }
    }

    return cnt
}

fun isEdge(array: D2Array<Int>, x: Int, y: Int): Boolean {
    if (x == 0 || y == 0) {
        return true
    }
    if (x == array.shape[0] - 1 || y == array.shape[1] - 1) {
        return true
    }
    return false
}

fun solveDay08Part2(input: List<String>): Int {
    val nestedList: List<List<Int>> = input.map { it.toCharArray().map { d -> d.digitToInt() }.toList() }
    println(nestedList)
    val treeMap = mk.ndarray(nestedList)

    println(treeMap)

    val filtered: NDArray<Int, D2> = treeMap.mapMultiIndexed { index, i ->
        println("> Checking  ${index.toList()} $i")

        if (isEdge(treeMap, index[0], index[1])) {
            println("""isEdge: ${index.toList()} $i""")
            return@mapMultiIndexed 0
        } else {
            var topVisible = 0
            for (x in index[0] - 1 downTo 0) {
                if (i > treeMap.get(x, index[1])) {
                    topVisible++
                } else {
                    topVisible++
                    break
                }
            }
            println("top of [${index[0]}, ${index[1]}]: $topVisible")

            var bottomVisible = 0
            for (x in index[0] + 1 until treeMap.shape[0]) {
                if (i > treeMap.get(x, index[1])) {
                    bottomVisible++
                } else {
                    bottomVisible++
                    break
                }
            }
            println("bottom of [${index[0]}, ${index[1]}]: $bottomVisible")

            var leftVisible = 0
            for (y in index[1] - 1 downTo 0) {
                if (i > treeMap.get(index[0], y)) {
                    leftVisible++
                } else {
                    leftVisible++
                    break
                }
            }
            println("left of [${index[0]}, ${index[1]}]: $leftVisible")

            var rightVisible = 0
            for (y in index[1] + 1 until treeMap.shape[0]) {
                if (i > treeMap.get(index[0], y)) {
                    rightVisible++
                } else {
                    rightVisible++
                    break
                }
            }
            println("right of [${index[0]}, ${index[1]}]: $rightVisible")


            return@mapMultiIndexed topVisible * bottomVisible * leftVisible * rightVisible
        }

    }

    println(filtered)

    return filtered.toList().maxOf { it }
}
