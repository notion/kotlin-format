package format.rule

import format.Rule
import format.internal.isChildOfType
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.PsiWhiteSpace
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.LeafPsiElement
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.PsiWhiteSpaceImpl
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtBlockExpression
import org.jetbrains.kotlin.psi.KtParameterList
import org.jetbrains.kotlin.psi.psiUtil.nextLeaf
import org.jetbrains.kotlin.psi.psiUtil.prevLeaf

class SpacingAroundParensRule : Rule {

    override fun visit(node: ASTNode): ASTNode {
        val isLeftParen = node.elementType == KtTokens.LPAR
        val isRightParen = node.elementType == KtTokens.RPAR

        if ((isLeftParen || isRightParen) && node is LeafPsiElement) {
            val prevLeaf = node.prevLeaf() as LeafPsiElement
            val nextLeaf = node.nextLeaf() as LeafPsiElement

            val extraSpacingBefore = (isRightParen && prevLeaf is PsiWhiteSpace)
                    || (isLeftParen && prevLeaf is PsiWhiteSpace && node.isChildOfType(KtParameterList::class))

            val extraSpacingAfter = isLeftParen
                    && nextLeaf is PsiWhiteSpace

            val missingSpacingBefore = isLeftParen
                    && prevLeaf !is PsiWhiteSpace
                    && node.isChildOfType(KtBlockExpression::class)

            val missingSpacingAfter = false

            if (extraSpacingBefore) {
                prevLeaf.delete()
            }
            if (extraSpacingAfter) {
                nextLeaf.delete()
            }
            if (missingSpacingBefore) {
                node.rawInsertBeforeMe(PsiWhiteSpaceImpl(" "))
            }
            if (missingSpacingAfter) {
                node.rawInsertAfterMe(PsiWhiteSpaceImpl(" "))
            }
        }
        return node
    }

}
