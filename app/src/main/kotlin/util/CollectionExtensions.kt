package util

fun <T> List<T>.copyOf(): List<T> {
    return mutableListOf<T>().also { it.addAll(this) }
}

fun <T> List<T>.mutableCopyOf(): MutableList<T> {
    return mutableListOf<T>().also { it.addAll(this) }
}

fun <T> Collection<T>.split(
    condition: (element: T) -> Boolean,
    discardSplit: Boolean = true
): Collection<Collection<T>> {
    val result = mutableListOf<Collection<T>>()
    val list = mutableListOf<T>()
    for (element: T in this) {
        if (condition.invoke(element)) {
            if (!discardSplit) {
                list.add(element)
            }
            result.add(list.copyOf())
            list.clear()
        } else {
            list.add(element)
        }
    }
    if (list.isNotEmpty()) {
        result.add(list)
    }
    return result
}