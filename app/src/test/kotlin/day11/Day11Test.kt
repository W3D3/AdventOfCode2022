package day11

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day11Test : FreeSpec({

val sampleInput: List<String> = """
    5483143223
    2745854711
    5264556173
    6141336146
    6357385478
    4167524645
    2176841721
    6882881134
    4846848554
    5283751526
    """.trimIndent().split("\n")

    val sampleSolutionPart1 = 1656

    val sampleSolutionPart2 = 195

    "Solving day 11" - {
        "part 1 for the sample input should return the correct output" {
            solveDay11Part1(sampleInput) shouldBe sampleSolutionPart1
        }

        "part 2 for the sample input should return the correct output" {
            solveDay11Part2(sampleInput) shouldBe sampleSolutionPart2
        }
    }
})
