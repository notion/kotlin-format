package format.rule

import format.rule.internal.RuleTest
import format.rule.internal.RuleTestCase
import format.rule.internal.loadResource

class IndentationRuleTest : RuleTest(
		listOf(IndentationRule()),
		RuleTestCase(
				"must indent using four spaces",
				loadResource("format/rule/indentation/Case1"),
				loadResource("format/rule/indentation/Expected")
		)
)
