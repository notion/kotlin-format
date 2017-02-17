package format.rule

import format.rule.internal.RuleTest
import format.rule.internal.RuleTestCase

class SpacingAroundParensRuleTest : RuleTest(
        listOf(SpacingAroundParensRule()),
        RuleTestCase(
                "should contain space between block expression and left paren",
                "fun foo() { if(true) { } }",
                "fun foo() { if (true) { } }"
        ),
        RuleTestCase(
                "should contain space before value parameter list",
                "fun foo () { }",
                "fun foo() { }"
        ),
        RuleTestCase(
                "should not contain space after left paren",
                "fun foo( a: Int) { }",
                "fun foo(a: Int) { }"
        ),
        RuleTestCase(
                "should not contain space before right paren",
                "fun foo(a: Int ) { }",
                "fun foo(a: Int) { }"
        )
)
