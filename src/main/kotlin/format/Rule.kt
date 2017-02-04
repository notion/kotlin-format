package format

import org.jetbrains.kotlin.com.intellij.lang.ASTNode

interface Rule {

    fun visit(node: ASTNode)

}
