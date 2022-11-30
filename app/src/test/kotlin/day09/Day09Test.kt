package day09

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day09Test : FreeSpec({

    val sampleInput: List<String> = """
        2199943210
        3987894921
        9856789892
        8767896789
        9899965678
    """.trimIndent().split("\n")

    val sampleSolutionPart1 = 15

    val sampleSolutionPart2 = 1134

    "Solving day 9" - {
        "part 1 for the sample input should return the correct output" {
            solveDay09Part1(sampleInput) shouldBe sampleSolutionPart1
        }

        "part 2 for the sample input should return the correct output" {
            solveDay09Part2(sampleInput) shouldBe sampleSolutionPart2
        }
    }
})
