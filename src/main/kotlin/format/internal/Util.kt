package format.internal

import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.com.intellij.psi.util.PsiTreeUtil
import kotlin.reflect.KClass

internal fun PsiElement.isChildOfType(cls: KClass<out PsiElement>) =
        PsiTreeUtil.getParentOfType(this, cls.java, false) != null
