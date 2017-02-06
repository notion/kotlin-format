package format.rule

import format.rule.internal.RuleTest
import format.rule.internal.RuleTestCase
import format.rule.internal.loadResource

class NoTabIndentationRuleTest : RuleTest(
		listOf(NoTabIndentationRule()),
		RuleTestCase(
				"must indent using four spaces",
				loadResource("format/rule/noTabIndentation/Case1"),
				loadResource("format/rule/noTabIndentation/Expected")
		)
)
