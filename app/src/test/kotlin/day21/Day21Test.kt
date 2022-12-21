package day21

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class Day21Test : FreeSpec({

    val sampleInput: List<String> = """
        root: pppw + sjmn
        dbpl: 5
        cczh: sllz + lgvd
        zczc: 2
        ptdq: humn - dvpt
        dvpt: 3
        lfqf: 4
        humn: 5
        ljgn: 2
        sjmn: drzm * dbpl
        sllz: 4
        pppw: cczh / lfqf
        lgvd: ljgn * ptdq
        drzm: hmdt - zczc
        hmdt: 32
    """.trimIndent().split("\n")

    val sampleSolutionPart1 = 152

    val sampleSolutionPart2: Int = -1

    "Solving day 21" - {
        "part 1 for the sample input should return the correct output" {
            solveDay21Part1(sampleInput) shouldBe sampleSolutionPart1
        }

        "part 2 for the sample input should return the correct output" {
            solveDay21Part2(sampleInput) shouldBe sampleSolutionPart2
        }
    }
})
