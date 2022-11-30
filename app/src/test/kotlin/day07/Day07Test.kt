package day07

import io.kotest.core.annotation.Ignored
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day07Test : FreeSpec({

    val sampleInput: List<String> = listOf(
        "16,1,2,0,4,2,7,1,2,14"
    )

    val sampleSolutionPart1: Int = 37

    val sampleSolutionPart2: Int = 168

    "Solving day 7" - {
        "part 1 for the sample input should return the correct output" {
            solveDay07Part1(sampleInput) shouldBe sampleSolutionPart1
        }

        "part 2 for the sample input should return the correct output" {
            solveDay07Part2(sampleInput) shouldBe sampleSolutionPart2
        }
    }
})
