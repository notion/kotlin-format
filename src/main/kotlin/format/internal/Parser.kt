package format.internal

import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.mock.MockProject
import org.jetbrains.kotlin.com.intellij.openapi.Disposable
import org.jetbrains.kotlin.com.intellij.openapi.extensions.ExtensionPoint
import org.jetbrains.kotlin.com.intellij.openapi.extensions.Extensions.getArea
import org.jetbrains.kotlin.com.intellij.openapi.util.UserDataHolderBase
import org.jetbrains.kotlin.com.intellij.pom.PomModel
import org.jetbrains.kotlin.com.intellij.pom.PomModelAspect
import org.jetbrains.kotlin.com.intellij.pom.PomTransaction
import org.jetbrains.kotlin.com.intellij.pom.impl.PomTransactionBase
import org.jetbrains.kotlin.com.intellij.pom.tree.TreeAspect
import org.jetbrains.kotlin.com.intellij.psi.PsiFileFactory
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.TreeCopyHandler
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.idea.KotlinLanguage
import sun.reflect.ReflectionFactory

internal class Parser {

    private val psiFileFactory = createPsiFileFactory()

    fun parse(input: String): ASTNode {
        val psiFile = psiFileFactory.createFileFromText("", KotlinLanguage.INSTANCE, input)
        return psiFile.node
    }

    private fun createPsiFileFactory(): PsiFileFactory {
        val environment = KotlinCoreEnvironment.createForProduction(
                Disposable { },
                CompilerConfiguration(),
                emptyList()
        )
        val project = environment.project

        arrayOf(getArea(project), getArea(null))
                .filterNot { it.hasExtensionPoint(EXTENSION_POINT) }
                .forEach {
                    it.registerExtensionPoint(
                            EXTENSION_POINT,
                            EXTENSION_CLASS_NAME,
                            ExtensionPoint.Kind.INTERFACE
                    )
                }

        (project as MockProject).registerService(PomModel::class.java, ParserPomModel())

        return PsiFileFactory.getInstance(project)
    }

    private class ParserPomModel : UserDataHolderBase(), PomModel {

        override fun runTransaction(transaction: PomTransaction) {
            (transaction as PomTransactionBase).run()
        }

        override fun <T : PomModelAspect?> getModelAspect(aspect: Class<T>): T? {
            if (TreeAspect::class.java.isAssignableFrom(aspect)) {
                val constructor = ReflectionFactory
                        .getReflectionFactory()
                        .newConstructorForSerialization(
                                aspect,
                                Any::class.java.getDeclaredConstructor(*arrayOfNulls<Class<*>>(0))
                        )
                @Suppress("UNCHECKED_CAST")
                return constructor.newInstance(*emptyArray()) as T
            }
            return null
        }

    }

    companion object {
        private val EXTENSION_POINT = "org.jetbrains.kotlin.com.intellij.treeCopyHandler"
        private val EXTENSION_CLASS_NAME = TreeCopyHandler::class.java.name
    }

}
