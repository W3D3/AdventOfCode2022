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
): List<Collection<T>> {
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

fun <T> intersection(vararg collections: Collection<T>): Collection<T> {
    return collections.toList().reduce { acc: Collection<T>, ts -> acc.intersect(ts.toSet()) }
}

fun <T : Comparable<T>> ClosedRange<T>.fullyContains(range: ClosedRange<T>): Boolean {
    return this.contains(range.start) && this.contains(range.endInclusive)
}