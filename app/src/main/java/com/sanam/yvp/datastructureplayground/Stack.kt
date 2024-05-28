package com.sanam.yvp.datastructureplayground

import java.lang.IllegalStateException

// it is LiFO : last in first out
//for reverse or go back or balance questions stack is the best friend
// o(1)
// u can use array or linkedList to create stack

class Stack<T> {
    var capacity = 10
    var stack = arrayOfNulls<Any?>(capacity) as Array<T>
    private var index = 0

    fun push(value: T) {
        if (index == capacity) {
            increaseCapacity()
        }
        stack[index++] = value
    }

    fun pop(): T {
        if (index == 0) {
            throw IllegalStateException()
        }
        return stack[--index]
    }

    private fun increaseCapacity() {
        capacity += capacity
        var index = 0;
        val newStack = arrayOfNulls<Any?>(capacity) as Array<T>
        stack.forEach {
            newStack[index++] = it
        }
        stack = newStack
    }

    override fun toString(): String {
        var items = stack.copyOfRange(0, index)
        return items.contentToString()
    }

}