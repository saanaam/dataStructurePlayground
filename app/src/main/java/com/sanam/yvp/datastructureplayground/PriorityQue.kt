package com.sanam.yvp.datastructureplayground

import java.lang.IllegalStateException
import java.util.Stack

//when u want to order que based on priority
class PriorityQue(private val capacity: Int) {
    private var items = arrayOfNulls<Int>(capacity)
    private var count = 0
    private var insertedItem: Int? = null

    fun reverseOrderOfKElement(k: Int) {
        // Q[10 , 20 , 30 , 40, 50]  k= 3
        // Q[30, 20, 10 , | 40, 50]
        val stack = Stack<Int>()
        var i = 0
        //create stack
        for (item in 0 until k) {
            stack.push(items[item])
        }
        //reverse list
        while (stack.isNotEmpty()) {
            items[i++] = stack.pop()
        }
    }

    fun enqueue(value: Int) {
        if (count == items.size) {
            throw IllegalStateException()
        }
        if (count == 0) {
            items[count++] = value
        } else {
            for (index in (count - 1) downTo 0) {
                //shifting items
                if ((items[index] ?: continue) > value) {
                    items[index + 1] = items[index]
                    insertedItem = index
                } else break
            }
            insertedItem?.let {
                items[it] = value
                count++
                insertedItem = null
            } ?: run {
                items[count++] = value
            }
        }
    }

    fun deque() {
        for ((i, index) in (1 until items.size).withIndex()) {
            items[i] = items[count--]
            count--
        }
    }

    override fun toString(): String {
        return items.joinToString()
    }
}