package format.rule

import format.Formatter
import format.Parser
import format.Rule
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@RunWith(JUnitPlatform::class)
abstract class RuleTest(
    val rule: Rule,
    val cases: Array<Case>) : Spek({

    given("application of the rule") {
        cases.forEach { case ->
            it(case.description) {
                val formatter = Formatter(Parser())
                val formatted = formatter.format(case.input, arrayOf(rule))
                assertEquals(case.expected, formatted)
            }
        }
    }

})
