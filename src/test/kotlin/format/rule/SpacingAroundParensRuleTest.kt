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
                "should contain no space before value parameter list",
                "fun foo () { }",
                "fun foo() { }"
        ),
        RuleTestCase(
                "should retain spacing before value parameter list",
                "fun foo() { }",
                "fun foo() { }"
        ),
        RuleTestCase(
                "should contain no space before value argument list",
                "val a = 0.toInt ()",
                "val a = 0.toInt()"
        ),
        RuleTestCase(
                "should retain spacing before value argument list",
                "val a = 0.toInt()",
                "val a = 0.toInt()"
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
        ),
        RuleTestCase(
                "should not contain space before left paren after left paren",
                "fun foo() { if ( (true)) { } }",
                "fun foo() { if ((true)) { } }"
        ),
        RuleTestCase(
                "should not contain space before right paren before right paren",
                "fun foo() { if ((true) ) { } }",
                "fun foo() { if ((true)) { } }"
        ),
        RuleTestCase(
                "should preserve newlines inside value parameter list",
                """
                fun foo(
                        a: Int
                    ) { }
                """.trimIndent(),
                """
                fun foo(
                        a: Int
                    ) { }
                """.trimIndent()
        )
)
