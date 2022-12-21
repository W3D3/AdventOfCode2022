package day18

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day18Test : FreeSpec({

    val sampleInput: List<String> = """
        2,2,2
        1,2,2
        3,2,2
        2,1,2
        2,3,2
        2,2,1
        2,2,3
        2,2,4
        2,2,6
        1,2,5
        3,2,5
        2,1,5
        2,3,5
    """.trimIndent().split("\n")

    val sampleSolutionPart1 = 64

    val sampleSolutionPart2 = 58

    "Solving day 18" - {
        "part 1 for the sample input should return the correct output" {
            solveDay18Part1(sampleInput) shouldBe sampleSolutionPart1
        }

        "part 2 for the sample input should return the correct output" {
            solveDay18Part2(sampleInput) shouldBe sampleSolutionPart2
        }
    }
})
