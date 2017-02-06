package format.rule

import format.Rule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.PsiWhiteSpace
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.LeafPsiElement
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.PsiWhiteSpaceImpl
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtParameter

class SpacingAroundColonRule : Rule {

    override fun visit(node: ASTNode): ASTNode {
        if (node is LeafPsiElement && node.textMatches(":") /*&& !node.isPartOfString()*/) {
            val prevSibling = node.prevSibling
            val nextSibling = node.nextSibling
            val parent = node.parent

            val extraSpacingBefore = prevSibling is PsiWhiteSpace && (parent is KtParameter || parent is KtFunction)
            val missingSpacingBefore = prevSibling !is PsiWhiteSpace && parent is KtClassOrObject
            val missingSpacingAfter = nextSibling !is PsiWhiteSpace

            if (extraSpacingBefore) {
                prevSibling.delete()
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
