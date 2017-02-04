package format.rule

import format.Rule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.PsiWhiteSpace
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.LeafPsiElement

class IndentationRule : Rule {

    private val SOFT_TAB = " ".repeat(4)

    override fun visit(node: ASTNode): ASTNode {
        if (node is PsiWhiteSpace) {
            val formatted = (node as ASTNode).text
                .replace("\t", SOFT_TAB)

            return (node as LeafPsiElement).replaceWithText(formatted)
        }
        return node
    }
}
