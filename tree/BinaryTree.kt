package Kotlin_DSA.tree

import java.util.*
import kotlin.math.max

enum class TreeSearch {
    LNR,
    NLR,
    RNL,
}

data class Node(
    val data: Int,
    var left: Node?,
    var right: Node?,
)

data class BinaryTree(
    var root: Node?
) {
    private fun createBinaryTree(data: Int) {
        val node = Node(data, null, null)
        if (root == null) {
            root = node
        }
    }

    fun addNode(data: Int) {
        if (root == null) {
            createBinaryTree(data)
            return
        }

        var currentNode = root
        while (currentNode != null) {
            if (data <= currentNode.data && currentNode.left == null) {
                currentNode.left = Node(data, null, null)
                return
            }
            if (data > currentNode.data && currentNode.right == null) {
                currentNode.right = Node(data, null, null)
                return
            }
            if (data <= currentNode.data) {
                currentNode = currentNode.left
                continue
            }
            currentNode = currentNode.right
        }
    }

    fun inorder(node: Node, treeSearch: TreeSearch = TreeSearch.LNR) {
        when (treeSearch) {
            TreeSearch.LNR -> {
                node.left?.let { inorder(it) }
                print("${node.data}\t")
                node.right?.let { inorder(it) }
            }

            TreeSearch.NLR -> {
                print("${node.data}\t")
                node.left?.let { inorder(it) }
                node.right?.let { inorder(it) }
            }

            TreeSearch.RNL -> {
                node.right?.let { inorder(it) }
                print("${node.data}\t")
                node.left?.let { inorder(it) }
            }
        }
    }

    fun search(node: Node?, data: Int): Boolean {
        if (node == null) return false
        if (node.data == data) return true
        if (data <= node.data) {
            search(node.left, data)
        } else {
            search(node.right, data)
        }
        return false
    }

    fun printTreePretty(node: Node?, prefix: String = "", isLeft: Boolean = true) {
        if (node == null) return

        println(prefix + (if (isLeft) "├── " else "└── ") + node.data)
        printTreePretty(node.left, prefix + if (isLeft) "│   " else "    ", true)
        printTreePretty(node.right, prefix + if (isLeft) "│   " else "    ", false)
    }

    fun dfs(root: Node?) {
        if (root == null) return

        val stack = Stack<Node>()
        stack.add(root)

        while (stack.isNotEmpty()) {
            val node = stack.removeLast()
            print("${node.data} ")

            node.right?.let { stack.add(it) }
            node.left?.let { stack.add(it) }
        }
    }

    fun bfs(root: Node?) {
        if (root == null) return

        val stack = LinkedList<Node>()
        stack.add(root)

        while (stack.isNotEmpty()) {
            val node = stack.removeLast()
            print("${node.data} ")

            node.right?.let { stack.add(it) }
            node.left?.let { stack.add(it) }
        }
    }

    fun calculateDepth(root: Node?, depth: Int = 0): Int {
        if (root == null) return depth
        if (root.left == null && root.right == null) return depth + 1
        return max(calculateDepth(root.left, depth + 1), calculateDepth(root.right, depth + 1))
    }

    fun treeDepthBFS(root: Node?): Int {
        if (root == null) return 0

        val queue: LinkedList<Node> = LinkedList()
        queue.add(root)
        var depth = 0

        while (queue.isNotEmpty()) {
            val levelSize = queue.size

            repeat(levelSize) {
                val node = queue.removeFirst()
                node.left?.let { queue.add(it) }
                node.right?.let { queue.add(it) }
            }

            depth++
        }

        return depth
    }
}