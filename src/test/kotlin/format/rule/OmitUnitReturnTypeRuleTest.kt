package format.rule

import format.rule.internal.Inputs
import format.rule.internal.RuleTest
import format.rule.internal.RuleTestCase

class OmitUnitReturnTypeRuleTest : RuleTest(
    listOf(OmitUnitReturnTypeRule()),
    RuleTestCase(
        "should omit Unit return type",
        """
        fun foo(): Unit {

        }
        """.trimIndent(),
        Inputs.OMIT_UNIT
    )
)
