package day02

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day02Test : FreeSpec({

    val sampleInput: List<String> = """
        A Y
        B X
        C Z
    """.trimIndent().split("\n")

    val scissorsVsRock: List<String> = listOf("C X")

    val sampleSolutionPart1 = 15

    val winRockAgainstScissors = 7

    val sampleSolutionPart2 = 12

    "Solving day 2" - {
        "part 1 for the sample input should return the correct output" {
            solveDay02Part1(sampleInput) shouldBe sampleSolutionPart1
        }

        "part 1 should work for scissorsVsRock" {
            solveDay02Part1(scissorsVsRock) shouldBe winRockAgainstScissors
        }

        "part 2 for the sample input should return the correct output" {
            solveDay02Part2(sampleInput) shouldBe sampleSolutionPart2
        }
    }
})
