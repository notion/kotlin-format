package format.rule

import format.rule.internal.RuleTest
import format.rule.internal.RuleTestCase
import format.rule.internal.loadResource

class NoTrailingSpacesRuleTest : RuleTest(
        listOf(NoTrailingSpacesRule()),
        RuleTestCase(
                "should remove spaces before a newline",
                loadResource("format/rule/noTrailingSpaces/Case1"),
                loadResource("format/rule/noTrailingSpaces/Expected")
        ),
        RuleTestCase(
                "should remove spaces before a newline 2",
                loadResource("format/rule/noTrailingSpaces/Case1"),
                loadResource("format/rule/noTrailingSpaces/Expected")
        ),
        RuleTestCase(
                "should remove spaces before a newline 3",
                loadResource("format/rule/noTrailingSpaces/Case1"),
                loadResource("format/rule/noTrailingSpaces/Expected")
        )
)
