package format.rule.internal

fun loadResource(path: String) = ClassLoader
		.getSystemClassLoader()
		.getResourceAsStream(path)
		.bufferedReader()
		.readText()
