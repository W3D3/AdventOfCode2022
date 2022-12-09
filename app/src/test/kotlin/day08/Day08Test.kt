package day08

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day08Test : FreeSpec({

    val sampleInput = """
        30373
        25512
        65332
        33549
        35390
    """.trimIndent().split("\n")

    val sampleSolutionPart1 = 21

    val sampleSolutionPart2 = 8

    "Solving day 8" - {
        "part 1 for the sample input should return the correct output" {
            solveDay08Part1(sampleInput) shouldBe sampleSolutionPart1
        }

        "part 2 for the sample input should return the correct output" {
            solveDay08Part2(sampleInput) shouldBe sampleSolutionPart2
        }
    }
})
