package format

import format.rule.NoConsecutiveBlankLinesRule
import format.rule.NoTabIndentationRule
import format.rule.NoTrailingSpacesRule
import format.rule.NoUnitReturnTypeRule
import format.rule.SortedImportsRule
import format.rule.SpacingAroundOperatorsRule
import format.rule.SpacingAroundColonRule
import format.rule.SpacingAroundCurlyBracesRule
import format.rule.SpacingAroundParensRule
import org.jetbrains.kotlin.com.intellij.lang.ASTNode

interface Rule {

    fun visit(node: ASTNode): ASTNode

    companion object {

        val STANDARD_RULES = listOf(
                NoConsecutiveBlankLinesRule(),
                NoTabIndentationRule(),
                NoTrailingSpacesRule(),
                NoUnitReturnTypeRule(),
                SortedImportsRule(),
                SpacingAroundOperatorsRule(),
                SpacingAroundColonRule(),
                SpacingAroundCurlyBracesRule(),
                SpacingAroundParensRule()
        )

    }

}
