package util

fun String.splitIntoPair(vararg delimiters: String, ignoreCase: Boolean = false): Pair<String, String> {
    val split = this.split(delimiters = delimiters, ignoreCase = ignoreCase, limit = 2)
    val second = if (split.size < 2) "" else split[1]
    return split[0] to second
}