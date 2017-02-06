package format.rule

import format.rule.internal.RuleTest
import format.rule.internal.RuleTestCase

class ImportSortingRuleTest : RuleTest(
    listOf(ImportSortingRule()),
    RuleTestCase(
        "must be sorted alphabetically",
        """
        import c
        import a
        import b
        """.trimIndent(),
        """
        import a
        import b
        import c
        """.trimIndent()
    )
)
