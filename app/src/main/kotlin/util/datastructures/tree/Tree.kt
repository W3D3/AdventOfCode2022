package util.datastructures.tree

class Tree(var value: Int) {
    private val children: MutableList<Tree> = mutableListOf()

    fun size(): Int {
        return children.fold(1) { size, child -> size + child.size() }
    }

    fun height(): Int {
        return 1 + (children.maxOfOrNull { it.size() } ?: 0)
    }

    fun add(value: Int) {
        children.add(Tree(value))
    }
}
