package format.rule

import format.Rule
import format.internal.isChildOfType
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.PsiWhiteSpace
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.LeafPsiElement
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.PsiWhiteSpaceImpl
import org.jetbrains.kotlin.com.intellij.psi.tree.TokenSet
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtImportDirective
import org.jetbrains.kotlin.psi.KtPrefixExpression
import org.jetbrains.kotlin.psi.KtTypeArgumentList
import org.jetbrains.kotlin.psi.KtTypeParameterList
import org.jetbrains.kotlin.psi.KtValueArgument
import org.jetbrains.kotlin.psi.psiUtil.nextLeaf
import org.jetbrains.kotlin.psi.psiUtil.prevLeaf

class SpacingAroundOperatorsRule : Rule {

    override fun visit(node: ASTNode): ASTNode {
        if (OPERATORS.contains(node.elementType) && node is LeafPsiElement
                && !node.isChildOfType(KtPrefixExpression::class)
                && !node.isChildOfType(KtTypeArgumentList::class)
                && !node.isChildOfType(KtValueArgument::class)
                && !node.isChildOfType(KtImportDirective::class)) {

            val prevLeaf = node.prevLeaf() as? LeafPsiElement
            val nextLeaf = node.nextLeaf() as? LeafPsiElement

            val isChildOfTypeParameterList = node.isChildOfType(KtTypeParameterList::class)

            val extraSpaceBefore = prevLeaf is PsiWhiteSpace
                    && isChildOfTypeParameterList

            val extraSpaceAfter = nextLeaf is PsiWhiteSpace
                    && isChildOfTypeParameterList

            val missingSpaceBefore = prevLeaf !is PsiWhiteSpace
                    && !isChildOfTypeParameterList

            val missingSpaceAfter = nextLeaf !is PsiWhiteSpace
                    && !isChildOfTypeParameterList

            if (extraSpaceBefore) {
                prevLeaf?.delete()
            }
            else if (missingSpaceBefore) {
                node.rawInsertBeforeMe(PsiWhiteSpaceImpl(" "))
            }

            if (extraSpaceAfter) {
                nextLeaf?.delete()
            }
            else if (missingSpaceAfter) {
                node.rawInsertAfterMe(PsiWhiteSpaceImpl(" "))
            }
        }
        return node
    }

    companion object {
        private val OPERATORS = TokenSet.create(
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
                KtTokens.ARROW,
                KtTokens.DOUBLE_ARROW,
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
                KtTokens.MINUSEQ
        )
    }

}
