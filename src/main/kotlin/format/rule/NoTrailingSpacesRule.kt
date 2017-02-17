package format.rule

import format.Rule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.PsiWhiteSpace
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.LeafPsiElement
import org.jetbrains.kotlin.psi.KtFile

class NoTrailingSpacesRule : Rule {

    override fun visit(node: ASTNode): ASTNode {
        if (node is PsiWhiteSpace && node is LeafPsiElement) {
            val formattedText = if (node.textContains('\n')) {
                val lines = node.getText().split('\n')
                lines.take(lines.size - 1)
                        .map { it.trim() }
                        .plus(lines.last())
                        .joinToString("\n")
            }
            else if (node.getParent() is KtFile) EMPTY
            else SPACE

            if (formattedText != node.getText()) {
                node.replaceWithText(formattedText)
            }
        }
        return node
    }

    companion object {
        private const val SPACE = " "
        private const val EMPTY = ""
    }

}
