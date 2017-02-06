package format.rule

import format.Rule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.PsiWhiteSpace
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.LeafPsiElement
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.PsiWhiteSpaceImpl
import org.jetbrains.kotlin.com.intellij.psi.tree.TokenSet
import org.jetbrains.kotlin.lexer.KtTokens

class SpacingAroundBinaryOperationsRule : Rule {

    override fun visit(node: ASTNode): ASTNode {
        if (node is LeafPsiElement && OPERATIONS.contains(node.elementType)) {
            val whitespaceBefore = node.prevSibling is PsiWhiteSpace
            val whitespaceAfter = node.nextSibling is PsiWhiteSpace

            if (!whitespaceBefore) {
                node.rawInsertBeforeMe(PsiWhiteSpaceImpl(" "))
            }
            if (!whitespaceAfter) {
                node.rawInsertAfterMe(PsiWhiteSpaceImpl(" "))
            }
        }
        return node
    }

    companion object {
        private val OPERATIONS = TokenSet.create(
            KtTokens.MUL,
            KtTokens.PLUS,
            KtTokens.MINUS,
            KtTokens.DIV,
            KtTokens.PERC,
            KtTokens.LT,
            KtTokens.GT,
            KtTokens.LTEQ,
            KtTokens.GTEQ,
            KtTokens.EQEQEQ,
            KtTokens.EXCLEQEQEQ,
            KtTokens.EQEQ,
            KtTokens.EXCLEQ,
            KtTokens.ANDAND,
            KtTokens.OROR,
            KtTokens.ELVIS,
            KtTokens.EQ,
            KtTokens.MULTEQ,
            KtTokens.DIVEQ,
            KtTokens.PERCEQ,
            KtTokens.PLUSEQ,
            KtTokens.MINUSEQ,
            KtTokens.ARROW
        )
    }

}