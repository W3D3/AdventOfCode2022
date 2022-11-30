package day06

import io.kotest.core.annotation.Ignored
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day06Test : FreeSpec({

    val sampleInput: List<String> = listOf(
        "3,4,3,1,2"
    )

    val sampleSolutionPart1 = 5934

    val sampleSolutionPart2 = 26_984_457_539

    "Solving day 6" - {
        "part 1 for the sample input should return the correct output" {
            solveDay06Part1(sampleInput) shouldBe sampleSolutionPart1
        }

        "part 2 for the sample input should return the correct output" {
            solveDay06Part2(sampleInput) shouldBe sampleSolutionPart2
        }
    }
})
