package format.rule

import format.rule.internal.RuleTest
import format.rule.internal.RuleTestCase
import format.rule.internal.loadResource

class SpacingAroundBinaryOperationsRuleTest : RuleTest(
        listOf(SpacingAroundBinaryOperationsRule()),
        RuleTestCase(
                "must contain space before operator",
                loadResource("format/rule/spacingAroundBinaryOperations/Case1"),
                loadResource("format/rule/spacingAroundBinaryOperations/Expected")
        ),
        RuleTestCase(
                "must contain space after operator",
                loadResource("format/rule/spacingAroundBinaryOperations/Case2"),
                loadResource("format/rule/spacingAroundBinaryOperations/Expected")
        ),
        RuleTestCase(
                "must contain space before and after operator",
                loadResource("format/rule/spacingAroundBinaryOperations/Case3"),
                loadResource("format/rule/spacingAroundBinaryOperations/Expected")
        )
)
