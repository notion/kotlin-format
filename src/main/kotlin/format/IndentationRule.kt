package format

import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.PsiWhiteSpace
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.LeafPsiElement

class IndentationRule : Rule {

    private val SOFT_TAB = " ".repeat(4)

    override fun visit(node: ASTNode) {
        if (node is PsiWhiteSpace) {
            val formatted = (node as ASTNode).text
                .replace("\t", SOFT_TAB)
                .replace("\n\n\n", "\n\n")

            (node as LeafPsiElement).replaceWithText(formatted)
        }
    }
}
