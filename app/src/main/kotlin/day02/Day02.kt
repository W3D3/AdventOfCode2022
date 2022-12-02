package day02

import common.InputRepo
import common.readSessionCookie
import common.solve
import util.splitIntoPair


fun main(args: Array<String>) {
    val day = 2
    val input = InputRepo(args.readSessionCookie()).get(day = day)

    solve(day, input, ::solveDay02Part1, ::solveDay02Part2)
}

fun solveDay02Part1(input: List<String>): Int {
    val games = input.map { s -> s.splitIntoPair(" ") }
        .map { (opponentChar, playerChar) -> mapOpponentShape(opponentChar) to mapPlayerShape(playerChar) }

    return games.sumOf { (opponentMove, playerMove) ->
        playGame(
            playerMove, opponentMove
        ).score + playerMove.shapeScore
    }
}

fun solveDay02Part2(input: List<String>): Int {
    val desiredOutcomes = input.map { s -> s.split(" ")[0] to s.split(" ")[1] }
        .map { pair -> mapOpponentShape(pair.first) to mapGameOutcome(pair.second) }
    val games = desiredOutcomes.map { (opponentMove, desiredOutcome) ->
        opponentMove to getPlayerShape(opponentMove, desiredOutcome)
    }

    return games.sumOf { (opponentMove, playerMove) ->
        playGame(
            playerMove, opponentMove
        ).score + playerMove.shapeScore
    }
}

enum class Shape(val shapeScore: Int) {
    ROCK(1), PAPER(2), SCISSORS(3),
}

enum class GameOutcome(val score: Int) {
    LOSE(0), DRAW(3), WIN(6),
}

fun mapOpponentShape(char: String): Shape {
    return when (char) {
        "A" -> Shape.ROCK
        "B" -> Shape.PAPER
        "C" -> Shape.SCISSORS
        else -> throw IllegalArgumentException()
    }
}

fun mapPlayerShape(char: String): Shape {
    return when (char) {
        "X" -> Shape.ROCK
        "Y" -> Shape.PAPER
        "Z" -> Shape.SCISSORS
        else -> throw IllegalArgumentException()
    }
}

fun mapGameOutcome(char: String): GameOutcome {
    return when (char) {
        "X" -> GameOutcome.LOSE
        "Y" -> GameOutcome.DRAW
        "Z" -> GameOutcome.WIN
        else -> throw IllegalArgumentException()
    }
}

fun playGame(playerShape: Shape, opponentShape: Shape): GameOutcome {
    if (playerShape == opponentShape) {
        return GameOutcome.DRAW
    }
    return if ((playerShape.ordinal + 2) % 3 == opponentShape.ordinal) {
        GameOutcome.WIN
    } else {
        GameOutcome.LOSE
    }
}

fun getPlayerShape(opponentShape: Shape, outcome: GameOutcome): Shape {
    return when (outcome) {
        GameOutcome.DRAW -> {
            opponentShape
        }

        GameOutcome.WIN -> {
            Shape.values()[(opponentShape.ordinal + 1) % 3]
        }

        else -> {
            Shape.values()[(opponentShape.ordinal + 2) % 3]
        }
    }

}
