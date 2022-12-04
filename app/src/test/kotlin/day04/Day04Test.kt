package day04

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day04Test : FreeSpec({

    val sampleInput: List<String> = """
        2-4,6-8
        2-3,4-5
        5-7,7-9
        2-8,3-7
        6-6,4-6
        2-6,4-8
    """.trimIndent().split("\n")

    val sampleSolutionPart1 = 2
    val sampleSolutionPart2 = 4

    "Solving day 4" - {
        "part 1 for the sample input should return the correct output" {
            solveDay04Part1(sampleInput) shouldBe sampleSolutionPart1
        }

        "part 2 for the sample input should return the correct output" {
            solveDay04Part2(sampleInput) shouldBe sampleSolutionPart2
        }
    }
})
