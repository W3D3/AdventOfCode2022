package day12

import common.InputRepo
import common.readSessionCookie
import common.solve
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.ndarray
import org.jetbrains.kotlinx.multik.ndarray.data.D2Array
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.operations.forEachMultiIndexed
import util.Coord
import util.graphs.NoSuchPathException
import util.graphs.directed.weighted.DWGraph
import util.graphs.directed.weighted.Dijkstra

private const val ALPHABET_LENGTH = 26

fun main(args: Array<String>) {
    val day = 12
    val input = InputRepo(args.readSessionCookie()).get(day = day)

    solve(day, input, ::solveDay12Part1, ::solveDay12Part2)
}

fun solveDay12Part1(input: List<String>): Int {
    val flatMapChars = input.flatMap { it.toCharArray().toList() }
    val startIndex = flatMapChars.indexOf('S')
    val endIndex = flatMapChars.indexOf('E')

    val nestedList = parseInput(input)
    val mountain = mk.ndarray(nestedList)
    val graph = createGraph(mountain)

    val dijkstra = Dijkstra(graph, startIndex)
    val pathTo = dijkstra.pathTo(endIndex)

    return pathTo.count()
}

fun solveDay12Part2(input: List<String>): Int {
    val flatMapChars = input.flatMap { it.toCharArray().toList() }
    val startIndices = flatMapChars.mapIndexedNotNull { index, c -> if (c == 'a') index else null }
    val endIndex = flatMapChars.indexOf('E')

    val nestedList = parseInput(input)
    val mountain = mk.ndarray(nestedList)
    val graph = createGraph(mountain)

    var min = Int.MAX_VALUE
    for (startIndex in startIndices) {
        val dijkstra = Dijkstra(graph, startIndex)
        try {
            val pathTo = dijkstra.pathTo(endIndex)
            if (pathTo.count() < min) {
                min = pathTo.count()
            }
        } catch (_: NoSuchPathException) {
            //NOSONAR Ignore exception
        }
    }

    return min
}

private fun parseInput(input: List<String>): List<List<Int>> {
    return input.map { line ->
        line.toCharArray().map { if (it == 'S') 'a' else if (it == 'E') 'z' else it }.map { it.code - 97 }.toList()
    }
}

private fun createGraph(mountain: D2Array<Int>): DWGraph {
    val graph = DWGraph(mountain.size)

    mountain.forEachMultiIndexed { index, el ->
        println("> Checking  ${index.toList()} $el")
        val currentCoord = Coord(index[0], index[1])
        val directNeighborCoords = currentCoord.getDirectNeighbors(mountain.shape[0], mountain.shape[1])
        println(directNeighborCoords)

        for ((x, y) in directNeighborCoords) {
            val directNeighbor = mountain[x, y]

            if ((directNeighbor - el) % ALPHABET_LENGTH <= 1) {
                // allowed edge
                graph.addEdge(
                    multiIndexToFlatIndex(currentCoord.x, currentCoord.y, mountain.shape),
                    multiIndexToFlatIndex(x, y, mountain.shape),
                    1.0
                )
            }
        }
    }
    return graph
}

private fun multiIndexToFlatIndex(
    x: Int, y: Int, shape: IntArray
) = shape[1] * x + y