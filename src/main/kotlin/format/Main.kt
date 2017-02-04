package format

import com.beust.jcommander.JCommander
import com.beust.jcommander.Parameter
import format.rule.IndentationRule
import format.rule.NoConsecutiveBlankLinesRule
import java.io.File

object Main {

    private val RULES = arrayOf(
        IndentationRule(),
        NoConsecutiveBlankLinesRule()
    )

    private val formatter = Formatter(Parser())

    @JvmStatic
    fun main(args: Array<String>) {
        val options = Options()
        val jCommander = JCommander(options, *args)
        jCommander.setProgramName("kotlin-format")

        if (options.version == true) {
            println("Kotlin Formatter version 0.1.0")
            return
        }

        if (options.help == true || options.paths.isEmpty()) {
            jCommander.usage()
            return
        }

        val files = getFiles(options.paths)
        files.forEach { formatFile(it, options) }
    }

    private fun formatFile(file: File, options: Options) {
        val formatted = formatter.format(file.readText(), RULES)
        if (options.write == true) file.writeText(formatted) else println(formatted)
    }

    private fun getFiles(paths: List<String>) = getFiles(paths.map(::File).toTypedArray())

    private fun getFiles(files: Array<File>): List<File> {
        return files
            .flatMap { file ->
                when {
                    file.isDirectory -> getFiles(file.listFiles { file ->
                        file.isDirectory || file.name.endsWith(".kt") || file.name.endsWith(".kts")
                    })
                    else -> listOf(file)
                }
            }
    }

    private class Options {
        @Parameter(description = "[paths ...]")
        val paths: List<String> = mutableListOf()
        @Parameter(names = arrayOf("-w", "--write"), description = "Write result to source file")
        val write: Boolean? = null
        @Parameter(names = arrayOf("-h", "--help"), description = "Show this help text", help = true)
        val help: Boolean? = null
        @Parameter(names = arrayOf("-v", "--version"), description = "Display version")
        val version: Boolean? = null
    }

}
