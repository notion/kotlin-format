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
import org.jetbrains.kotlin.psi.KtParenthesizedExpression
import org.jetbrains.kotlin.psi.KtValueArgumentList
import org.jetbrains.kotlin.psi.psiUtil.nextLeaf
import org.jetbrains.kotlin.psi.psiUtil.prevLeaf

class SpacingAroundParensRule : Rule {

    override fun visit(node: ASTNode): ASTNode {
        val isLeftParen = node.elementType == KtTokens.LPAR
        val isRightParen = node.elementType == KtTokens.RPAR

        if ((isLeftParen || isRightParen) && node is LeafPsiElement) {
            val prevLeaf = node.prevLeaf() as LeafPsiElement
            val nextLeaf = node.nextLeaf() as? LeafPsiElement

            val isChildOfParameterList = node.isChildOfType(KtParameterList::class)
            val isChildOfValueArgumentList = node.isChildOfType(KtValueArgumentList::class)

            val extraSpacingBefore = (isRightParen && prevLeaf is PsiWhiteSpace && !prevLeaf.textContains('\n'))
                    || (isLeftParen && prevLeaf is PsiWhiteSpace && (isChildOfParameterList || isChildOfValueArgumentList))

            val extraSpacingAfter = isLeftParen
                    && nextLeaf is PsiWhiteSpace
                    && !nextLeaf.textContains('\n')

            val missingSpacingBefore = isLeftParen
                    && prevLeaf !is PsiWhiteSpace
                    && node.isChildOfType(KtBlockExpression::class)
                    && !node.isChildOfType(KtParenthesizedExpression::class)
                    && !isChildOfParameterList
                    && !isChildOfValueArgumentList

            if (extraSpacingBefore) {
                prevLeaf.delete()
            }
            else if (missingSpacingBefore) {
                node.rawInsertBeforeMe(PsiWhiteSpaceImpl(" "))
            }

            if (extraSpacingAfter) {
                nextLeaf?.delete()
            }
        }
        return node
    }

}
