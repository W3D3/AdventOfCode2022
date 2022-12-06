package day06

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day06Test : FreeSpec({

    val sampleInput1 = listOf("mjqjpqmgbljsphdztnvjfqwrcgsmlb")
    val sampleInput2 = listOf("bvwbjplbgvbhsrlpgdmjqwftvncz")
    val sampleInput3 = listOf("nppdvjthqldpwncqszvftbrmjlhg")
    val sampleInput4 = listOf("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")
    val sampleInput5 = listOf("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")

    val sampleSolution1Part1Solution = 7
    val sampleSolution2Part1Solution = 5
    val sampleSolution3Part1Solution = 6
    val sampleSolution4Part1Solution = 10
    val sampleSolution5Part1Solution = 11

    val sampleSolution1Part2Solution = 19
    val sampleSolution2Part2Solution = 23
    val sampleSolution3Part2Solution = 23
    val sampleSolution4Part2Solution = 29
    val sampleSolution5Part2Solution = 26

    "Solving day 6" - {
        "part 1 for sample input 1 should return the correct output" {
            solveDay06Part1(sampleInput1) shouldBe sampleSolution1Part1Solution
        }

        "part 1 for sample input 2 should return the correct output" {
            solveDay06Part1(sampleInput2) shouldBe sampleSolution2Part1Solution
        }

        "part 1 for sample input 3 should return the correct output" {
            solveDay06Part1(sampleInput3) shouldBe sampleSolution3Part1Solution
        }

        "part 1 for sample input 4 should return the correct output" {
            solveDay06Part1(sampleInput4) shouldBe sampleSolution4Part1Solution
        }

        "part 1 for sample input 5 should return the correct output" {
            solveDay06Part1(sampleInput5) shouldBe sampleSolution5Part1Solution
        }

        "part 2 for sample input 1 should return the correct output" {
            solveDay06Part2(sampleInput1) shouldBe sampleSolution1Part2Solution
        }

        "part 2 for sample input 2 should return the correct output" {
            solveDay06Part2(sampleInput2) shouldBe sampleSolution2Part2Solution
        }

        "part 2 for sample input 3 should return the correct output" {
            solveDay06Part2(sampleInput3) shouldBe sampleSolution3Part2Solution
        }

        "part 2 for sample input 4 should return the correct output" {
            solveDay06Part2(sampleInput4) shouldBe sampleSolution4Part2Solution
        }

        "part 2 for sample input 5 should return the correct output" {
            solveDay06Part2(sampleInput5) shouldBe sampleSolution5Part2Solution
        }
    }
})
