package day05

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day05Test : FreeSpec({

    val sampleInput: List<String> =
        """
            0,9 -> 5,9
            8,0 -> 0,8
            9,4 -> 3,4
            2,2 -> 2,1
            7,0 -> 7,4
            6,4 -> 2,0
            0,9 -> 2,9
            3,4 -> 1,4
            0,0 -> 8,8
            5,5 -> 8,2
        """.trimIndent().split("\n")

    val sampleSolutionPart1 = 5

    val sampleSolutionPart2 = 12

    "Solving day 5" - {
        "part 1 for the sample input should return the correct output" {
            solveDay05Part1(sampleInput) shouldBe sampleSolutionPart1
        }

        "part 2 for the sample input should return the correct output" {
            solveDay05Part2(sampleInput) shouldBe sampleSolutionPart2
        }
    }
})
