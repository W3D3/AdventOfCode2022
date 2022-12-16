package day15

import common.InputRepo
import common.readSessionCookie
import common.solve
import util.Coord
import kotlin.math.abs

fun main(args: Array<String>) {
    val day = 15
    val input = InputRepo(args.readSessionCookie()).get(day = day)

    solve(day, input, ::solveDay15Part1, ::solveDay15Part2)
}

fun solveDay15Part1(input: List<String>, row: Int = 2_000_000): Int {
    val sensorInfoList = input.map { parseInstruction(it) }

    val sensorRangesForRow = sensorInfoList.map { it.getRangeForRow(row) }
    val coveredXCorrdsOnRow = sensorRangesForRow.flatMap { it.toSet() }.toSet()
    val beaconsForRow =
        sensorInfoList.filter { sensorInfo -> sensorInfo.beacon.y == row && coveredXCorrdsOnRow.contains(sensorInfo.beacon.x) }
            .map { it.beacon }.toSet().count()

    return sensorRangesForRow.flatMap { it.toSet() }.toSet().count() - beaconsForRow
}

fun solveDay15Part2(input: List<String>, upperBound: Int = 4_000_000): Long {
    val sensorInfoList = input.map { parseInstruction(it) }

    val uncovered = findUncovered(upperBound, sensorInfoList)

    return uncovered.x * 4_000_000L + uncovered.y
}

private fun findUncovered(upperBound: Int, sensorInfoList: List<SensorInfo>): Coord {
    val outOfReachCoords = sensorInfoList.flatMap { sensorInfo ->
        sensorInfo.getFirstUnreachable()
            .filter { it.x in 0..upperBound && it.y in 0..upperBound }
    }.toSet()

    val uncoveredCoords = outOfReachCoords
        .filter { candidateCoord -> sensorInfoList.none { it.isCovered(candidateCoord) } }

    return uncoveredCoords.first()
}

fun printGrid(n: Int, m: Int, marker: Set<Coord>) {
    for (y in -2..m) {
        for (x in -2..n) {
            val coord = Coord(x, y)
            if (marker.contains(coord)) {
                print("#")
            } else {
                print(".")
            }
        }
        println()
    }
    println()
}

data class SensorInfo(val sensor: Coord, val beacon: Coord) {

    private val distance = sensor.manhattanDistance(beacon)
    fun isCovered(testCoord: Coord): Boolean {
        return getRangeForRow(testCoord.y).contains(testCoord.x)
    }

    fun getRangeForRow(row: Int): IntRange {
        val distLeft = distance - abs(this.sensor.y - row)
        if (distLeft < 1) {
            return IntRange.EMPTY
        }
        return IntRange(this.sensor.x - distLeft, this.sensor.x + distLeft)
    }

    fun getFirstUnreachable(): Set<Coord> {
        return this.sensor.getCoordsInExactDistance(distance + 1)
    }
}

private fun parseInstruction(line: String): SensorInfo {
    val moveRegex = """Sensor at x=(-?\d+), y=(-?\d+): closest beacon is at x=(-?\d+), y=(-?\d+)""".toRegex()
    return moveRegex.matchEntire(line)
        ?.destructured
        ?.let { (xSensor, ySensor, xBeacon, yBeacon) ->
            SensorInfo(Coord(xSensor.toInt(), ySensor.toInt()), Coord(xBeacon.toInt(), yBeacon.toInt()))
        }
        ?: throw IllegalArgumentException("Bad sensor reading: '$line'")
}