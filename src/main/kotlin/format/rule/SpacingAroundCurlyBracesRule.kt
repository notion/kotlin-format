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
        val isLeftBrace = node.elementType == KtTokens.LBRACE
        val isRightBrace = node.elementType == KtTokens.RBRACE

        if ((isLeftBrace || isRightBrace) && node is LeafPsiElement) {
            val prevLeaf = node.prevLeaf() as? LeafPsiElement
            val nextLeaf = node.nextLeaf() as? LeafPsiElement

            if (prevLeaf != null) {
                if (prevLeaf !is PsiWhiteSpace) {
                    node.rawInsertBeforeMe(PsiWhiteSpaceImpl(" "))
                }
                else if (isLeftBrace
                        && prevLeaf is PsiWhiteSpace
                        && !prevLeaf.textMatches(" ")) {

                    prevLeaf.delete()
                    node.rawInsertBeforeMe(PsiWhiteSpaceImpl(" "))
                }
            }

            if (nextLeaf != null) {
                if (nextLeaf !is PsiWhiteSpace) {
                    node.rawInsertAfterMe(PsiWhiteSpaceImpl(" "))
                }
                else if (isRightBrace
                        && nextLeaf is PsiWhiteSpace
                        && !nextLeaf.textMatches(" ")
                        && !nextLeaf.getText().startsWith("\n")) {

                    nextLeaf.delete()
                    node.rawInsertAfterMe(PsiWhiteSpaceImpl(" "))
                }
            }
        }
        return node
    }

}
