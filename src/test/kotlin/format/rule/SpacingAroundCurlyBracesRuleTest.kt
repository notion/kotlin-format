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
                "should contain no more than one space before curly brace",
                "fun foo()  { }",
                "fun foo() { }"
        ),
        RuleTestCase(
                "should contain no non-space whitespace before curly brace",
                "fun foo()\t{ }",
                "fun foo() { }"
        ),
        RuleTestCase(
                "should preserve newlines after left curly brace",
                """
                fun foo() {
                }""".trimIndent(),
                """
                fun foo() {
                }""".trimIndent()
        ),
        RuleTestCase(
                "should preserve spaces around curly brace",
                "fun foo() { }",
                "fun foo() { }"
        )
)
