package day12

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe


class Day12Test : FreeSpec({

val smallSampleInput: List<String> = """
    start-A
    start-b
    A-c
    A-b
    b-d
    A-end
    b-end
    """.trimIndent().split("\n")

    val mediumSizeInput: List<String> = """
    dc-end
    HN-start
    start-kj
    dc-start
    dc-HN
    LN-dc
    HN-end
    kj-sa
    kj-HN
    kj-dc
    """.trimIndent().split("\n")

val largeInput: List<String> = """
    fs-end
    he-DX
    fs-he
    start-DX
    pj-DX
    end-zg
    zg-sl
    zg-pj
    pj-he
    RW-he
    fs-DX
    pj-RW
    zg-RW
    start-pj
    he-WI
    zg-he
    pj-fs
    start-RW
    """.trimIndent().split("\n")

    val smallSampleSolutionPart1 = 10
    val mediumSampleSolutionPart1 = 19
    val largeSampleSolutionPart1 = 226

    val smallSampleSolutionPart2 = 36
    val mediumSampleSolutionPart2 = 103
    val largeSampleSolutionPart2 = 3509

    "Solving day 12" - {
        "part 1 for the small sample input should return the correct output" {
            solveDay12Part1(smallSampleInput) shouldBe smallSampleSolutionPart1
        }
        "part 1 for the medium sample input should return the correct output" {
            solveDay12Part1(mediumSizeInput) shouldBe mediumSampleSolutionPart1
        }
        "part 1 for the large sample input should return the correct output" {
            solveDay12Part1(largeInput) shouldBe largeSampleSolutionPart1
        }

        "part 2 for the small sample input should return the correct output" {
            solveDay12Part2(smallSampleInput) shouldBe smallSampleSolutionPart2
        }
        "part 2 for the medium sample input should return the correct output" {
            solveDay12Part2(mediumSizeInput) shouldBe mediumSampleSolutionPart2
        }
        "part 2 for the large sample input should return the correct output" {
            solveDay12Part2(largeInput) shouldBe largeSampleSolutionPart2
        }
    }
})
