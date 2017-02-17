package format.rule

import format.rule.internal.RuleTest
import format.rule.internal.RuleTestCase

class NoUnitReturnTypeRuleTest : RuleTest(
        listOf(NoUnitReturnTypeRule()),
        RuleTestCase(
                "should not explicitly return Unit",
                "fun <A : Unit> foo(unit: Unit): Unit { }",
                "fun <A : Unit> foo(unit: Unit) { }"
        )
)
