package format.rule.internal

import format.internal.Formatter
import format.internal.Parser
import format.Rule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.CompositeElement
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@RunWith(JUnitPlatform::class)
abstract class RuleTest(
    val rules: List<Rule>,
    vararg val cases: RuleTestCase) : Spek({

    given("application of the rule") {

        val formatter = Formatter(Parser())

        cases.forEach { case ->
            it(case.description) {
                val formatted = formatter.format(case.input, rules)
                assertEquals(case.expected, formatted)
            }
        }

        it("should fold other rules") {
            val formatted = formatter.format(
                loadResource("format/HelloWorld"),
                rules + object : Rule {
                    override fun visit(node: ASTNode): ASTNode {
                        if (node is CompositeElement) {
                            node.removeAllChildren()
                        }
                        return node
                    }
                })

            assertEquals("", formatted)
        }

    }

})
