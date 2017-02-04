package format

import org.jetbrains.kotlin.com.intellij.lang.ASTNode

class Formatter(private val parser: Parser) {

    fun format(input: String, rules: Array<Rule>): String {
        val node = parser.parse(input)
        val formattedNode = applyRules(node, rules)
        return formattedNode.text
    }

    private fun applyRules(node: ASTNode, rules: Array<Rule>): ASTNode {
        rules.forEach { rule -> rule.visit(node) }
        return node.apply {
            getChildren(null)
                .forEach { child -> applyRules(child, rules) }
        }
    }

}
