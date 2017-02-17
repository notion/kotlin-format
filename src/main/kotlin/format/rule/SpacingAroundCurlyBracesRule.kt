package format.rule

import format.Rule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.PsiWhiteSpace
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.LeafPsiElement
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.PsiWhiteSpaceImpl
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.psiUtil.nextLeaf
import org.jetbrains.kotlin.psi.psiUtil.prevLeaf

class SpacingAroundCurlyBracesRule : Rule {

    override fun visit(node: ASTNode): ASTNode {
        if (node is LeafPsiElement && (node.elementType == KtTokens.LBRACE || node.elementType == KtTokens.RBRACE)) {
            val prevLeaf = node.prevLeaf() as? LeafPsiElement
            val nextLeaf = node.nextLeaf() as? LeafPsiElement

            val missingSpacingBefore = prevLeaf != null && prevLeaf !is PsiWhiteSpace
            val missingSpacingAfter = nextLeaf != null && nextLeaf !is PsiWhiteSpace

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
