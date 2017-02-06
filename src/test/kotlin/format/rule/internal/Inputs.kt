package format.rule.internal

object Inputs {

    val HELLO_WORLD = """
        package main

        fun main(args: Array<String>) {
            println("Hello, World!")
        }
        """.trimIndent()

    val SPACING_AROUND_COLON = """
        interface Foo<out T : Any> : Bar {
            fun foo(a: Int): T
        }
        """.trimIndent()

    val OMIT_UNIT = """
        fun foo() {

        }
        """.trimIndent()

}
