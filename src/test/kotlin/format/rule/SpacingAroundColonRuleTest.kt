package format.rule

import format.rule.internal.Inputs
import format.rule.internal.RuleTest
import format.rule.internal.RuleTestCase

class SpacingAroundColonRuleTest : RuleTest(
    listOf(SpacingAroundColonRule()),
    RuleTestCase(
        "should contain space after identifier",
        """
        interface Foo<out T: Any> : Bar {
            fun foo(a : Int) : T
        }
        """.trimIndent(),
        Inputs.SPACING_AROUND_COLON
    ),
    RuleTestCase(
        "should contain space after identifier colon",
        """
        interface Foo<out T : Any> : Bar {
            fun foo(a:Int):T
        }
        """.trimIndent(),
        Inputs.SPACING_AROUND_COLON
    ),
    RuleTestCase(
        "should not contain space before parameter colon",
        """
        interface Foo<out T : Any> : Bar {
            fun foo(a : Int) : T
        }
        """.trimIndent(),
        Inputs.SPACING_AROUND_COLON
    )
)
