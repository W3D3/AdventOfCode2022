package day07

import common.InputRepo
import common.readSessionCookie
import common.solve
import util.selectRecursive
import util.splitIntoPair

fun main(args: Array<String>) {
    val day = 7
    val input = InputRepo(args.readSessionCookie()).get(day = day)

    solve(day, input, ::solveDay07Part1, ::solveDay07Part2)
}


fun solveDay07Part1(input: List<String>): Int {
    val root = FileNode("/", null)
    executeCommands(root, input)

    println(root.toTreeString())

    val allNodes = listOf(root).asSequence().selectRecursive { children.asSequence() }
    return allNodes.filter { it.isDirectory }
        .filter { it.size < 100_000 }
        .sumOf { it.size }
}


fun solveDay07Part2(input: List<String>): Int {
    val totalDiskSpace = 70_000_000
    val minimumFreeDiskSpace = 30_000_000

    val root = FileNode("/", null)

    executeCommands(root, input)

    val freeSpace = totalDiskSpace - root.size
    val spaceToFree = minimumFreeDiskSpace - freeSpace

    val allNodes = listOf(root).asSequence().selectRecursive { children.asSequence() }
    return allNodes.filter { it.isDirectory }
        .filter { it.size >= spaceToFree }
        .minOf { it.size }
}

fun executeCommand(cmd: String, arg: String, currentDir: FileNode<String>, root: FileNode<String>): FileNode<String> {

    when (cmd) {
        "cd" -> {
            return cd(arg, root, currentDir)
        }

        "ls" -> {
            val (info, name) = arg.splitIntoPair(" ")
            if (name.isBlank()) {
                return currentDir
            }
            ls(info, currentDir, name)
        }
    }
    return currentDir
}

private fun ls(info: String, currentDir: FileNode<String>, name: String) {
    if (info == "dir") {
        currentDir.children.add(FileNode(name, currentDir))
    } else {
        val file = FileNode(name, currentDir, isDirectory = false)
        file.size = info.toInt()
        currentDir.children.add(file)
    }
}

private fun cd(
    arg: String,
    root: FileNode<String>,
    currentDir: FileNode<String>
): FileNode<String> {
    return when (arg) {
        "/" -> {
            root
        }

        ".." -> {
            currentDir.parent!!
        }

        else -> {
            currentDir.moveIntoChildren(arg)
        }
    }
}


private fun executeCommands(root: FileNode<String>, input: List<String>) {
    var currentCommand = ""
    var currentDir = root

    for (line in input) {
        if (line.startsWith("$")) {
            currentCommand = line.removePrefix("$").trim()
            val (cmd, arg) = currentCommand.splitIntoPair(" ")
            currentDir = executeCommand(cmd, arg, currentDir, root)
        } else {
            val (cmd, _) = currentCommand.splitIntoPair(" ")
            currentDir = executeCommand(cmd, line, currentDir, root)
        }
    }
}