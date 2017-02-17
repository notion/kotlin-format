package format.rule

import format.rule.internal.RuleTest
import format.rule.internal.RuleTestCase

class SpacingAroundOperatorsRuleTest : RuleTest(
        listOf(SpacingAroundOperatorsRule()),
        RuleTestCase(
                "must contain space before binary expression",
                "val a = 10* 10",
                "val a = 10 * 10"
        ),
        RuleTestCase(
                "must contain space after binary expression",
                "val a = 10 *10",
                "val a = 10 * 10"
        ),
        RuleTestCase(
                "must contain space before property",
                "val a= 10",
                "val a = 10"
        ),
        RuleTestCase(
                "must contain space after property",
                "val a =10",
                "val a = 10"
        ),
        RuleTestCase(
                "must contain space before literal function arrow",
                "val lambda = { a: Int-> a }",
                "val lambda = { a: Int -> a }"
        ),
        RuleTestCase(
                "must contain space after literal function arrow",
                "val lambda = { a: Int ->a }",
                "val lambda = { a: Int -> a }"
        )
        ,
        RuleTestCase(
                "must contain no space before type parameter list",
                "class Foo <A : Any>",
                "class Foo<A : Any>"
        ),
        RuleTestCase(
                "must contain no space after type parameter list",
                "class Foo< A : Any>",
                "class Foo<A : Any>"
        )
)
