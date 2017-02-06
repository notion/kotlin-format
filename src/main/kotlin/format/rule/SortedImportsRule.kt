package format.rule

import format.Rule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.PsiWhiteSpaceImpl
import org.jetbrains.kotlin.psi.stubs.elements.KtStubElementTypes

class SortedImportsRule : Rule {

    override fun visit(node: ASTNode): ASTNode {
        if (node.elementType == KtStubElementTypes.IMPORT_LIST) {
            val children = node.getChildren(null)
            if (children.isNotEmpty()) {
                val sortedImports = children
                    .filter { it.elementType == KtStubElementTypes.IMPORT_DIRECTIVE }
                    .sortedBy { it.text }

                node.removeRange(node.firstChildNode, node.lastChildNode)

                sortedImports.forEach {
                    node.addChild(it, null)
                    node.addChild(PsiWhiteSpaceImpl("\n"), null)
                }

                node.removeChild(node.lastChildNode)
            }
        }
        return node
    }

}
