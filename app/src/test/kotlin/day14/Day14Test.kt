package day14

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day14Test : FreeSpec({

    val sampleInput: List<String> = """
    498,4 -> 498,6 -> 496,6
    503,4 -> 502,4 -> 502,9 -> 494,9
    """.trimIndent().split("\n")

    val sampleSolutionPart1 = 24

    val sampleSolutionPart2 = 93

    "Solving day 14" - {
        "part 1 for the sample input should return the correct output" {
            solveDay14Part1(sampleInput) shouldBe sampleSolutionPart1
        }

        "part 2 for the sample input should return the correct output" {
            solveDay14Part2(sampleInput) shouldBe sampleSolutionPart2
        }
    }
})
