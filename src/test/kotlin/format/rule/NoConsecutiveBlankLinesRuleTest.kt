package format.rule

import format.rule.internal.Inputs
import format.rule.internal.RuleTest
import format.rule.internal.RuleTestCase
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class NoConsecutiveBlankLinesRuleTest : RuleTest(
    NoConsecutiveBlankLinesRule(),
    arrayOf(
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
)
