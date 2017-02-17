package format.rule

import format.Rule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.PsiWhiteSpace

class NoTrailingSpacesRule : Rule {

    override fun visit(node: ASTNode): ASTNode {
        if (node is PsiWhiteSpace) {
            val lines = node.getText ().split ('\n')
        }
        return node
    }

}
