package format.rule

import format.rule.internal.RuleTest
import format.rule.internal.RuleTestCase
import format.rule.internal.loadResource

class SortedImportsTest : RuleTest(
		listOf(ImportSortingRule()),
		RuleTestCase(
				"must be sorted alphabetically",
				loadResource("format/rule/sortedImports/Case1"),
				loadResource("format/rule/sortedImports/Expected")
		)
)
