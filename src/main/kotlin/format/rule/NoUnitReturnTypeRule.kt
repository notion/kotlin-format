package format.rule

import format.Rule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.psi.stubs.elements.KtStubElementTypes

class NoUnitReturnTypeRule : Rule {

    override fun visit(node: ASTNode): ASTNode {
        if (node.elementType == KtStubElementTypes.TYPE_REFERENCE
                && node.treeParent.elementType == KtStubElementTypes.FUNCTION
                && node.text.contentEquals("Unit")) {

            var prevNode = node
            while (prevNode.treePrev.elementType != KtStubElementTypes.VALUE_PARAMETER_LIST) {
                prevNode = prevNode.treePrev
            }
            node.treeParent.removeRange(prevNode, node.treeNext)
        }
        return node
    }

}
