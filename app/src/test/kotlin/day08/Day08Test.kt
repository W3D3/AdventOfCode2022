package day08

import io.kotest.core.annotation.Ignored
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

@Ignored
class Day08Test : FreeSpec({

    val sampleInput: List<String> = """
        1000
        2000
        3000
        
        4000
        
        5000
        6000
        
        7000
        8000
        9000
        
        10000
    """.trimIndent().split("\n")

    val sampleSolutionPart1 = 26

    val sampleSolutionPart2 = 61229

    "Solving day 8" - {
        "part 1 for the sample input should return the correct output" {
            solveDay08Part1(sampleInput) shouldBe sampleSolutionPart1
        }

        "part 2 for the sample input should return the correct output" {
            solveDay08Part2(sampleInput) shouldBe sampleSolutionPart2
        }
    }
})
