package format.rule

import format.rule.internal.RuleTest
import format.rule.internal.RuleTestCase

class SpacingAroundCurlyBracesRuleTest : RuleTest(
        listOf(SpacingAroundCurlyBracesRule()),
        RuleTestCase(
                "should contain space before curly brace",
                "fun foo(){ }",
                "fun foo() { }"
        ),
        RuleTestCase(
                "should contain space after curly brace",
                "fun foo() {}",
                "fun foo() { }"
        ),
        RuleTestCase(
                "should preserve spaces around curly brace",
                "fun foo() { }",
                "fun foo() { }"
        )
)
