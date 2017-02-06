package format.rule

import format.Rule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode

class NoTrailingSpacesRule : Rule {

    override fun visit(node: ASTNode): ASTNode {
        return node
    }

}
