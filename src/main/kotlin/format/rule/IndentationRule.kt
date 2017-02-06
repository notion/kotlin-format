package format.rule

import format.Rule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode

class IndentationRule : Rule {

	private val SOFT_TAB = " ".repeat(4)

	override fun visit(node: ASTNode): ASTNode {
		return node
	}
}
