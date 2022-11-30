package day10

import io.kotest.core.annotation.Ignored
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

@Ignored
class Day10Test : FreeSpec({

    val sampleInput: List<String> = """
        [({(<(())[]>[[{[]{<()<>>
        [(()[<>])]({[<{<<[]>>(
        {([(<{}[<>[]}>{[]{[(<()>
        (((({<>}<{<{<>}{[]{[]{}
        [[<[([]))<([[{}[[()]]]
        [{[{({}]{}}([{[{{{}}([]
        {<[[]]>}<{[{[{[]{()[[[]
        [<(<(<(<{}))><([]([]()
        <{([([[(<>()){}]>(<<{{
        <{([{{}}[<[[[<>{}]]]>[]]
    """.trimIndent().split("\n")

    val sampleSolutionPart1 = 26397

    val sampleSolutionPart2 = 288957

    "Solving day 10" - {
        "part 1 for the sample input should return the correct output" {
            solveDay10Part1(sampleInput) shouldBe sampleSolutionPart1
        }

        "part 2 for the sample input should return the correct output" {
            solveDay10Part2(sampleInput) shouldBe sampleSolutionPart2
        }
    }
})
