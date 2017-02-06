package format.rule

import format.Rule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.PsiWhiteSpace
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.LeafPsiElement

class NoTabIndentationRule : Rule {

    private val SOFT_TAB = " ".repeat(4)

    override fun visit(node: ASTNode): ASTNode {
        if (node is PsiWhiteSpace && node.textContains('\t')) {
            val text = node.getText()
                    .replace("\t", SOFT_TAB)

            (node as LeafPsiElement).replaceWithText(text)
        }
        return node
    }
}
