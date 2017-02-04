package format.rule

import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class NoConsecutiveBlankLinesRuleTest : RuleTest(
    NoConsecutiveBlankLinesRule(),
    arrayOf(
        Case(
            "should not contain consecutive blank lines",
            """
            package main


            fun main(args: Array<String>) {
                println("Hello, World!")
            }
            """.trimIndent(),
            Inputs.HELLO_WORLD
        )
    )
)
