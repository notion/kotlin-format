package format.rule

import format.Rule
import format.internal.isChildOfType
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.PsiWhiteSpace
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.LeafPsiElement
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.PsiWhiteSpaceImpl
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtStringTemplateEntry
import org.jetbrains.kotlin.psi.psiUtil.nextLeaf
import org.jetbrains.kotlin.psi.psiUtil.prevLeaf

class SpacingAroundColonRule : Rule {

    override fun visit(node: ASTNode): ASTNode {
        if (node is LeafPsiElement
                && node.textMatches(":")
                && !node.isChildOfType(KtStringTemplateEntry::class)) {

            val prevLeaf = node.prevLeaf() as LeafPsiElement
            val nextLeaf = node.nextLeaf() as LeafPsiElement

            val isChildOfProperty = node.isChildOfType(KtProperty::class)
            val isChildOfClassOrObject = node.isChildOfType(KtClassOrObject::class)
            val isChildOfFunction = node.isChildOfType(KtFunction::class)

            val extraSpacingBefore = prevLeaf is PsiWhiteSpace
                    && (isChildOfProperty
                    || isChildOfFunction)

            val missingSpacingBefore = prevLeaf !is PsiWhiteSpace
                    && !isChildOfProperty
                    && !isChildOfFunction
                    && isChildOfClassOrObject

            val missingSpacingAfter = nextLeaf !is PsiWhiteSpace
                    && (isChildOfProperty
                    || isChildOfClassOrObject
                    || isChildOfFunction)

            if (extraSpacingBefore) {
                prevLeaf.delete()
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
