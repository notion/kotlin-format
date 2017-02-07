package format.rule

import format.rule.internal.RuleTest
import format.rule.internal.RuleTestCase
import format.rule.internal.loadResource

class SpacingAroundColonRuleTest : RuleTest(
        listOf(SpacingAroundColonRule()),
        RuleTestCase(
                "should contain space before colon",
                loadResource("format/rule/spacingAroundColon/Case1"),
                loadResource("format/rule/spacingAroundColon/Expected")
        ),
        RuleTestCase(
                "should contain space after colon",
                loadResource("format/rule/spacingAroundColon/Case2"),
                loadResource("format/rule/spacingAroundColon/Expected")
        ),
        RuleTestCase(
                "should not contain space between value parameter and colon",
                loadResource("format/rule/spacingAroundColon/Case3"),
                loadResource("format/rule/spacingAroundColon/Expected")
        )
)
