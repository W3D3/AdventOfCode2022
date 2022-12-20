package day13

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day13Test : FreeSpec({

    val sampleInput: List<String> = """
    [1,1,3,1,1]
    [1,1,5,1,1]
    
    [[1],[2,3,4]]
    [[1],4]
    
    [9]
    [[8,7,6]]
    
    [[4,4],4,4]
    [[4,4],4,4,4]
    
    [7,7,7,7]
    [7,7,7]
    
    []
    [3]
    
    [[[]]]
    [[]]
    
    [1,[2,[3,[4,[5,6,7]]]],8,9]
    [1,[2,[3,[4,[5,6,0]]]],8,9]
    """.trimIndent().split("\n")

    val sampleSolutionPart1 = 13

    val sampleSolutionPart2 = 140

    "Solving day 13" - {
        "part 1 for the sample input should return the correct output" {
            solveDay13Part1(sampleInput) shouldBe sampleSolutionPart1
        }

        "part 2 for the sample input should return the correct output" {
            solveDay13Part2(sampleInput) shouldBe sampleSolutionPart2
        }
    }
})
