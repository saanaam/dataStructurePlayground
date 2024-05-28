package com.sanam.yvp.datastructureplayground

// insert to head (push )    o(1)
// insert to tail  (append ) o(1)
// insert after index        o(1)

//remove from head (pop) o(1)
//remove from tail       o(n)
//remove after index     o(1)

class LinkedList<T> {
    private var first: Node<T>? = null
    private var last: Node<T>? = null
    private var size = 0

    fun isEmpty(): Boolean {
        return size == 0
    }

    //addFirst
    fun addFirst(value: T) {
        first = Node(value = value, next = first)
        //init last for first time
        if (first == null) {
            last = first
        }
        size++
    }

    fun size() = size

    //add to last item
    fun addLast(value: T) {
        //init item for first time
        if (isEmpty()) {
            addFirst(value)
            return
        }
        last?.next = Node(value = value)
        last = last?.next
        size++
    }

    private fun deleteFirst() {
        if (!isEmpty()) {
            size--
            first = first?.next
        }
    }

    fun deleteLast() {
        var previous = first
        var current = previous?.next
        var next = current?.next
        if (first?.next == null) {
            return deleteFirst()
        }
        size--
        if (!isEmpty()) {
            while (next != null) {
                previous = current
                current = previous?.next
                next = current?.next
            }
            previous?.next = null
            last = previous
        }
    }

    fun contains(value: T): Boolean {
        if (isEmpty()) {
            return false
        }
        var current = first
        while (current != null) {
            if (current.value == value)
                return true
            current = current.next
        }
        return false
    }

    fun indexOf(value: T): Int {
        var index = 0
        var current = first
        while (current != null) {
            if (current.value == value) {
                return index
            }
            current = current.next
            index++
        }
        return -1
    }

    override fun toString(): String {
        return if (isEmpty()) {
            "empty list"
        } else {
            first.toString()
        }
    }

    fun reverse() {
        if (isEmpty()) return
        val previous = first
        var current = first?.next
        var next = current?.next
        while (current != null) {
            addFirst(current.value)
            previous?.next = next
            current = current.next
            next = current?.next
        }
    }

    fun reveresByMosh() {
        var p = first
        var c = p?.next
        while (c != null) {
            val n = c.next
            c.next = p
            p = c
            c = n
        }
        last = first
        last?.next = null
        first = p
    }

    fun getKthFromTheEnd(k: Int): T? {
        // 10 > 20 > +30 > 40 > 50
        // find 3kth from the end
        //so distance is 2
        if (isEmpty()) return null
        var distance = 0
        var indext = 0
        var fast = first
        var slow = first
        while (fast?.next != null) {
            indext++
            fast = fast?.next
            if (distance < k) {
                distance++
            }
            if (distance == k) {
                slow = slow?.next
            }
        }
        if (k > indext) {
            return null
        }
        return slow?.value
    }
}