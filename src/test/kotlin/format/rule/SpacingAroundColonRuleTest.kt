package format.rule

import format.rule.internal.RuleTest
import format.rule.internal.RuleTestCase

class SpacingAroundColonRuleTest : RuleTest(
        listOf(SpacingAroundColonRule()),
        RuleTestCase(
                "should contain space between colon and class name",
                "class Foo: Bar",
                "class Foo : Bar"
        ),
        RuleTestCase(
                "should contain space between colon and class supertype",
                "class Foo :Bar",
                "class Foo : Bar"
        ),
        RuleTestCase(
                "should preserve colon spacing in class definition",
                "class Foo : Bar",
                "class Foo : Bar"
        ),
        RuleTestCase(
                "should contain space between colon and type parameter name",
                "class Foo<A: Any> : Bar",
                "class Foo<A : Any> : Bar"
        ),
        RuleTestCase(
                "should contain space between colon and type parameter supertype",
                "class Foo<A :Any> : Bar",
                "class Foo<A : Any> : Bar"
        ),
        RuleTestCase(
                "should preserve colon spacing in type parameter",
                "class Foo<A : Any> : Bar",
                "class Foo<A : Any> : Bar"
        ),
        RuleTestCase(
                "should contain space between colon and object name",
                "object Foo: Bar",
                "object Foo : Bar"
        ),
        RuleTestCase(
                "should contain space between colon and object supertype",
                "object Foo :Bar",
                "object Foo : Bar"
        ),
        RuleTestCase(
                "should preserve colon spacing in object definition",
                "object Foo : Bar",
                "object Foo : Bar"
        ),
        RuleTestCase(
                "should not contain space between colon and instance declaration name",
                "class Foo { val a : Int = 0 }",
                "class Foo { val a: Int = 0 }"
        ),
        RuleTestCase(
                "should contain space between colon and instance declaration type",
                "class Foo { val a:Int = 0 }",
                "class Foo { val a: Int = 0 }"
        ),
        RuleTestCase(
                "should preserve colon spacing in property",
                "class Foo { val a: Int = 0 }",
                "class Foo { val a: Int = 0 }"
        ),
        RuleTestCase(
                "should not contain space between colon and function value list",
                "interface Foo { fun a() : Int }",
                "interface Foo { fun a(): Int }"
        ),
        RuleTestCase(
                "should contain space between colon and function return type",
                "interface Foo { fun a():Int }",
                "interface Foo { fun a(): Int }"
        ),
        RuleTestCase(
                "should preserve colon spacing in function definition",
                "interface Foo { fun a(): Int }",
                "interface Foo { fun a(): Int }"
        ),
        RuleTestCase(
                "should not affect strings",
                "val a = \":\"",
                "val a = \":\""
        ),
        RuleTestCase(
                "should not affect comments",
                "// TODO: implement",
                "// TODO: implement"
        )
)
