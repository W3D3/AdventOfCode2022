package util

fun <T> List<T>.copyOf(): List<T> {
    return mutableListOf<T>().also { it.addAll(this) }
}

@Suppress("unused")
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

fun <T> Sequence<T>.selectRecursive(recursiveSelector: T.() -> Sequence<T>): Sequence<T> = flatMap {
    sequence {
        yield(it)
        yieldAll(it.recursiveSelector().selectRecursive(recursiveSelector))
    }
}

/**
 * E.g.
 * cartesianProduct(listOf(1, 2, 3), listOf(true, false)) returns
 *  [(1, true), (1, false), (2, true), (2, false), (3, true), (3, false)]
 */
fun <T, U> cartesianProduct(c1: Collection<T>, c2: Collection<U>): List<Pair<T, U>> {
    return c1.flatMap { lhsElem -> c2.map { rhsElem -> lhsElem to rhsElem } }
}