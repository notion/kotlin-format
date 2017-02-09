package format.rule

import format.Rule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.PsiWhiteSpace
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.LeafPsiElement

class NoTabIndentationRule : Rule {

    override fun visit(node: ASTNode): ASTNode {
        if (node is PsiWhiteSpace && node is LeafPsiElement && node.textContains('\t')) {
            val formatted = node.getText().replace("\t", SOFT_TAB)
            node.replaceWithText(formatted)
        }
        return node
    }

    companion object {
        private val SOFT_TAB = " ".repeat(4)
    }

}
