package day03

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day03Test : FreeSpec({

    val sampleInput: List<String> = listOf(
        "00100",
        "11110",
        "10110",
        "10111",
        "10101",
        "01111",
        "00111",
        "11100",
        "10000",
        "11001",
        "00010",
        "01010",
    )

    val sampleSolutionPart1 = 198

    val sampleSolutionPart2 = 230

    "Solving day 2" - {
        "part 1 for the sample input should return the correct output" {
            solveDay03Part1(sampleInput) shouldBe sampleSolutionPart1
        }

        "part 2 for the sample input should return the correct output" {
            solveDay03Part2(sampleInput) shouldBe sampleSolutionPart2
        }
    }
})
