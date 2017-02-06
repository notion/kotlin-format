package format.rule

import format.rule.internal.Inputs
import format.rule.internal.RuleTest
import format.rule.internal.RuleTestCase

class NoConsecutiveBlankLinesRuleTest : RuleTest(
    listOf(NoConsecutiveBlankLinesRule()),
    RuleTestCase(
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
