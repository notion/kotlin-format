package format.rule

import format.Rule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.PsiWhiteSpace
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.LeafPsiElement

class NoTrailingSpacesRule : Rule {

    override fun visit(node: ASTNode): ASTNode {
        if (node is PsiWhiteSpace && node is LeafPsiElement && node.textContains('\n')) {
            val lines = node.getText().split('\n')
            val formattedText = lines
                    .mapIndexed { i: Int, line: String ->
                        if (i < lines.size - 1) line.trim() else line
                    }
                    .joinToString("\n")

            if (formattedText != node.getText()) {
                node.replaceWithText(formattedText)
            }
        }
        return node
    }

}
