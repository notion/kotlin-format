package format.rule

import format.rule.internal.RuleTest
import format.rule.internal.RuleTestCase

class NoTrailingSpacesRuleTest : RuleTest(
        listOf(NoTrailingSpacesRule()),
        RuleTestCase(
                "",
                "",
                ""
        )
)
