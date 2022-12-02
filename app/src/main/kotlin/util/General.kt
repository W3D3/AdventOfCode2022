package util

fun String.splitIntoPair(vararg delimiters: String, ignoreCase: Boolean = false): Pair<String, String> {
    val split = this.split(delimiters = delimiters, ignoreCase = ignoreCase, limit = 2)
    if (split.size < 2) {
        throw IllegalArgumentException("String cannot be split into two")
    }
    return split[0] to split[1]
}