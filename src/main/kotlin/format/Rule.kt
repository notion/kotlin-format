package format

import format.rule.NoConsecutiveBlankLinesRule
import format.rule.NoTabIndentationRule
import format.rule.NoUnitReturnTypeRule
import format.rule.SortedImportsRule
import format.rule.SpacingAroundBinaryOperationsRule
import format.rule.SpacingAroundColonRule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode

interface Rule {

    fun visit(node: ASTNode): ASTNode

    companion object {

        val STANDARD_RULES = listOf(
                NoTabIndentationRule(),
                NoConsecutiveBlankLinesRule(),
                NoUnitReturnTypeRule(),
                SortedImportsRule(),
                SpacingAroundBinaryOperationsRule(),
                SpacingAroundColonRule()
        )

    }

}
