package day05

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day05Test : FreeSpec({

    val sampleInput: List<String> =
        """
            [D]    
        [N] [C]    
        [Z] [M] [P]
         1   2   3 
        
        move 1 from 2 to 1
        move 3 from 1 to 3
        move 2 from 2 to 1
        move 1 from 1 to 2
        """.trimIndent().split("\n")

    val sampleSolutionPart1 = "CMZ"

    val sampleSolutionPart2 = "MCD"

    "Solving day 5" - {
        "part 1 for the sample input should return the correct output" {
            solveDay05Part1(sampleInput) shouldBe sampleSolutionPart1
        }

        "part 2 for the sample input should return the correct output" {
            solveDay05Part2(sampleInput) shouldBe sampleSolutionPart2
        }
    }
})
