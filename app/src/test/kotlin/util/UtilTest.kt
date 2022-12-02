package util

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class UtilTest : FreeSpec({

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

    val splitByEmpty = listOf<Collection<String>>(
        listOf("1000", "2000", "3000"),
        listOf("4000"),
        listOf("5000", "6000"),
        listOf("7000", "8000", "9000"),
        listOf("10000"),
    )
    val sampleSolutionPart2 = 5

    "ListTransformer" - {
        "splits correctly" {
            sampleInput.split({ i -> i.isEmpty() }) shouldBe splitByEmpty
        }
    }
})
