package format.rule

import format.rule.internal.RuleTest
import format.rule.internal.RuleTestCase

class SpacingAroundBinaryOperationsRuleTest : RuleTest(
    listOf(SpacingAroundBinaryOperationsRule()),
    RuleTestCase(
        "must contain space before operator",
        """
        val a= 1
        """.trimIndent(),
        """
        val a = 1
        """.trimIndent()
    ),
    RuleTestCase(
        "must contain space after operator",
        """
        val a =1
        """.trimIndent(),
        """
        val a = 1
        """.trimIndent()
    ),
    RuleTestCase(
        "must contain space before and after operator",
        """
        val a=1
        """.trimIndent(),
        """
        val a = 1
        """.trimIndent()
    )
)
