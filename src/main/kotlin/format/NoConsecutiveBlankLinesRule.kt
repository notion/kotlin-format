package format

import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.PsiWhiteSpace
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.LeafPsiElement

class NoConsecutiveBlankLinesRule : Rule {

    override fun visit(node: ASTNode): ASTNode {
        if (node is PsiWhiteSpace) {
            val lines = node.getText().split("\n")
            if (lines.size > 3) {
                return (node as LeafPsiElement).replaceWithText("${lines.first()}\n\n${lines.last()}")
            }
        }
        return node
    }

}
