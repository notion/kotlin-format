package format.rule

import format.Rule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.PsiWhiteSpace
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.LeafPsiElement
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.PsiWhiteSpaceImpl
import org.jetbrains.kotlin.com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.kotlin.psi.KtTypeParameter

class SpacingAroundColonRule : Rule {

    override fun visit(node: ASTNode): ASTNode {
        if (node is LeafPsiElement && node.textMatches(":") /*&& !node.isPartOfString()*/) {
            val prevLeaf = PsiTreeUtil.prevLeaf(node) as LeafPsiElement
            val nextLeaf = PsiTreeUtil.nextLeaf(node) as LeafPsiElement
            val parent = node.parent

            val extraSpacingBefore = prevLeaf is PsiWhiteSpace
            val missingSpacingBefore = prevLeaf !is PsiWhiteSpace && parent is KtTypeParameter
            val missingSpacingAfter = nextLeaf !is PsiWhiteSpace && parent is KtTypeParameter

            if (extraSpacingBefore) {
                prevLeaf?.delete()
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
