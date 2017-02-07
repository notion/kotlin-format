package format.rule

import format.Rule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.PsiWhiteSpace
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.LeafPsiElement
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.PsiWhiteSpaceImpl
import org.jetbrains.kotlin.com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.kotlin.lexer.KtTokens.IDENTIFIER
import org.jetbrains.kotlin.psi.stubs.elements.KtStubElementTypes.OBJECT_DECLARATION
import org.jetbrains.kotlin.psi.stubs.elements.KtStubElementTypes.TYPE_PARAMETER_LIST
import org.jetbrains.kotlin.psi.stubs.elements.KtStubElementTypes.VALUE_PARAMETER
import org.jetbrains.kotlin.psi.stubs.elements.KtStubElementTypes.VALUE_PARAMETER_LIST

class SpacingAroundColonRule : Rule {

    override fun visit(node: ASTNode): ASTNode {
        if (node is LeafPsiElement && node.textMatches(":")) {
            val prevLeaf = PsiTreeUtil.prevLeaf(node) as LeafPsiElement
            val nextLeaf = PsiTreeUtil.nextLeaf(node) as LeafPsiElement

            val prevPrevParent = (PsiTreeUtil.prevLeaf(prevLeaf) as LeafPsiElement).treeParent

            val extraSpacingBefore = prevLeaf is PsiWhiteSpace
                    && (prevPrevParent.elementType == VALUE_PARAMETER_LIST
                    || prevPrevParent.elementType == VALUE_PARAMETER)

            val missingSpacingBefore = prevLeaf.treeParent.elementType == TYPE_PARAMETER_LIST
                    || (prevLeaf !is PsiWhiteSpace
                    && prevLeaf.treeParent.elementType == OBJECT_DECLARATION)
                    || (prevLeaf.elementType == IDENTIFIER
                    && node.treeParent.elementType != VALUE_PARAMETER)

            val missingSpacingAfter = nextLeaf.elementType == IDENTIFIER

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
