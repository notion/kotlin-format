package format.rule

import format.rule.internal.RuleTest
import format.rule.internal.RuleTestCase

class NoTabIndentationRuleTest : RuleTest(
        listOf(NoTabIndentationRule()),
        RuleTestCase(
                "should indent using four spaces",
                "\t",
                "    "
        ),
        RuleTestCase(
                "should not affect strings",
                "val foo = \"\\t\"",
                "val foo = \"\\t\""
        )
)
