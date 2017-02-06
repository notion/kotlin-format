package format

import format.rule.ImportSortingRule
import format.rule.IndentationRule
import format.rule.NoConsecutiveBlankLinesRule
import format.rule.OmitUnitReturnTypeRule
import format.rule.SpacingAroundColonRule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode

interface Rule {

    fun visit(node: ASTNode): ASTNode

    companion object {

        val STANDARD_RULES = listOf(
            ImportSortingRule(),
            IndentationRule(),
            NoConsecutiveBlankLinesRule(),
            OmitUnitReturnTypeRule(),
            SpacingAroundColonRule()
        )

    }

}
