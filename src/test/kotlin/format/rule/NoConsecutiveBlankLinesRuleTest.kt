package format.rule

import format.rule.internal.RuleTest
import format.rule.internal.RuleTestCase
import format.rule.internal.loadResource

class NoConsecutiveBlankLinesRuleTest : RuleTest(
    listOf(NoConsecutiveBlankLinesRule()),
    RuleTestCase(
        "should not contain consecutive blank lines",
        loadResource("format/rule/noConsecutiveBlankLines/Case1"),
        loadResource("format/rule/noConsecutiveBlankLines/Expected")
    )
)
