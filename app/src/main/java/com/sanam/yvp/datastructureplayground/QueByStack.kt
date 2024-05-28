package com.sanam.yvp.datastructureplayground

class QueByStack<T> {
    val stack = java.util.Stack<T>()
    val outerStack = java.util.Stack<T>()

    fun enque(value: T) {
        stack.push(value)
    }

    fun deque() {
        //[0 , 1,3] ->reverse  [3 ,1 , 0] > pop [3 , 1 ] > push to new stack [1 , 3]

        while (stack.isNotEmpty()) {
            outerStack.push(stack.pop())
        }

        if (outerStack.isNotEmpty()) {
            outerStack.pop()
        }

        while (outerStack.isNotEmpty()) {
            stack.push(outerStack.pop())
        }
    }

    override fun toString(): String {
        return stack.toString()
    }
}