package format.rule

import format.rule.internal.RuleTest
import format.rule.internal.RuleTestCase

class NoConsecutiveBlankLinesRuleTest : RuleTest(
        listOf(NoConsecutiveBlankLinesRule()),
        RuleTestCase(
                "should not contain consecutive blank lines",
                "\n\n\n",
                "\n\n"
        )
)
