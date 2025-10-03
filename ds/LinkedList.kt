package ds

data class DataBlock(
    val number: Int,
)

data class Node(
    val data: DataBlock,
    var next: Node?
)

data class LinkedList(
    var head: Node?,
) {
    fun createNode(data: DataBlock) {
        this.head = Node(data, null)
    }

    fun addTail(data: DataBlock) {
        if (this.head == null) {
            createNode(data)
            return
        }
        val newNode = Node(data, null)
        var currentNode = this.head
        while (currentNode?.next != null) {
            currentNode = currentNode.next
        }
        currentNode?.next = newNode
    }

    fun addHead(data: DataBlock) {
        if (this.head == null) {
            createNode(data)
            return
        }
        val newNode = Node(data, head)
        this.head = newNode
    }

    fun removeTail() {
        if (this.head == null) {
            return
        }

        var current = this.head
        while (current?.next?.next != null) {
            current = current.next
        }

        current?.next = null
    }

    fun removeHead() {
        if (this.head == null) {
            return
        }

        this.head = this.head?.next
    }

    fun addAtPosition(data: DataBlock, position: Int) {
        if (this.head == null){
            return
        }

        var index = 0
        var current = this.head
        while (current != null && index != position) {
            current = current.next
            index++
        }

        if (index != position) {
            addTail(data)
            return
        }

        val newNode = Node(data, current?.next)
        current?.next = newNode
    }

    fun removeAtPosition(position: Int) {
        if (this.head == null){
            return
        }

        var index = 0
        var current = this.head
        while (current != null && index != position) {
            current = current.next
            index++
        }

        if (index != position) {
            removeTail()
            return
        }

        current?.next = current.next?.next
    }

    fun print() {
        var current = this.head
        while (current != null) {
            print("${current.data}\t")
            current = current.next
        }
    }
}