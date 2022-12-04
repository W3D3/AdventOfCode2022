package day03

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day03Test : FreeSpec({

    val sampleInput: List<String> = """
        vJrwpWtwJgWrhcsFMMfFFhFp
        jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
        PmmdzqPrVvPwwTWBwg
        wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
        ttgJtRGJQctTZtZT
        CrZsJsPPZsGzwwsLwLmpwMDw
    """.trimIndent().split("\n")

    val sampleSolutionPart1 = 157

    val sampleSolutionPart2 = 70

    "Solving day 2" - {
        "part 1 for the sample input should return the correct output" {
            solveDay03Part1(sampleInput) shouldBe sampleSolutionPart1
        }

        "part 2 for the sample input should return the correct output" {
            solveDay03Part2(sampleInput) shouldBe sampleSolutionPart2
        }
    }
})
