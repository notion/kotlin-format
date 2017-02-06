package format.rule

import format.Rule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.psi.stubs.elements.KtStubElementTypes

class OmitUnitReturnTypeRule : Rule {

    override fun visit(node: ASTNode): ASTNode {
        if (node.elementType == KtStubElementTypes.TYPE_REFERENCE && node.text.contentEquals("Unit")) {
        }
        return node
    }

}
