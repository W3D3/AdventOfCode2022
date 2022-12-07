package day07

class FileNode<T>(val value: T, val parent: FileNode<T>?, val isDirectory: Boolean = true, fileSize: Int? = null) {
    val children: MutableList<FileNode<T>> = mutableListOf()

    private var _size: Int? = null
    var size: Int
        get() {
            return if (this._size == null) {
                children.sumOf { child -> child.size }
            } else {
                _size
            }!!
        }
        set(value) {
            _size = value
        }

    init {
        if (fileSize != null) {
            this.size = fileSize
        }
    }

    fun toPath(): String {
        return buildString {
            append(parent?.toPath() ?: "")
            append(value)
            if (parent != null && isDirectory) append("/")
        }
    }

    fun moveIntoChildren(value: T): FileNode<T> {
        for (child in children) {
            if (child.value == value) {
                return child
            }
        }
        throw IllegalArgumentException("Children with value $value does not exist")
    }

    override fun toString(): String {
        return value.toString()
    }

    fun toTreeString(depth: Int = 0): String {
        return buildString {
            for (i in 0 until depth) append("  ")
            append(toPath())
            if (isDirectory) append(" (dir)")
            append(" (size $size)")
            append("\n")
            for (child in children) {
                append(child.toTreeString(depth + 1))
            }
        }
    }
}