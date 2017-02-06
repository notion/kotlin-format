package format.rule

import format.rule.internal.RuleTest
import format.rule.internal.RuleTestCase
import format.rule.internal.loadResource

class NoUnitReturnTypeRuleTest : RuleTest(
		listOf(OmitUnitReturnTypeRule()),
		RuleTestCase(
				"should not explicitly return Unit",
				loadResource("format/rule/noUnitReturnTypes/Case1"),
				loadResource("format/rule/noUnitReturnTypes/Expected")
		)
)
