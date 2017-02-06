package format

import org.jetbrains.kotlin.com.intellij.lang.ASTNode

class Formatter(private val parser: Parser) {

    fun format(input: String, rules: List<Rule>): String {
        val node = parser.parse(input)
        val formattedNode = applyRules(node, rules)
        return formattedNode.text
    }

    private fun applyRules(node: ASTNode, rules: List<Rule>): ASTNode {
        rules.fold(node) { node, rule -> rule.visit(node) }
        node.getChildren(null).forEach { child -> applyRules(child, rules) }
        return node
    }

}
