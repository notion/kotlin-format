package format.rule

import format.rule.internal.RuleTest
import format.rule.internal.RuleTestCase
import format.rule.internal.loadResource

class NoTrailingSpacesRuleTest : RuleTest(
        listOf(NoTrailingSpacesRule()),
        RuleTestCase(
                "should not contain concurrent spaces",
                "    val a =  10",
                "val a = 10"
        ),
        RuleTestCase(
                "should not contain concurrent spaces after indentation",
                """
                fun foo(): Int {
                    return      10
                }
                """.trimIndent(),
                """
                fun foo(): Int {
                    return 10
                }
                """.trimIndent()
        ),
        RuleTestCase(
                "should not contain spaces before a newline",
                loadResource("format/rule/noTrailingSpaces/Case1"),
                loadResource("format/rule/noTrailingSpaces/Expected")
        ),
        RuleTestCase(
                "should not contain spaces before a newline 2",
                loadResource("format/rule/noTrailingSpaces/Case2"),
                loadResource("format/rule/noTrailingSpaces/Expected")
        ),
        RuleTestCase(
                "should not contain spaces before a newline 3",
                loadResource("format/rule/noTrailingSpaces/Case3"),
                loadResource("format/rule/noTrailingSpaces/Expected")
        )
)
