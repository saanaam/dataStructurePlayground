package com.sanam.yvp.datastructureplayground

import java.lang.IllegalStateException
import java.util.*
import java.util.Stack
import kotlin.collections.ArrayDeque

// it is FIFO: first in first out

//use queues when there is a resource that must be shared amongst consumers , and resource cant give service to every one at same time.examples : printers, operating systems, web servers, live support systems
//when u want maintain the order
//add item to end of the que  (enqueue).             o(1)
//remove the item at the front of the que (deque)  o(1)
//get the item of in front of que (peek ).         o(1)
//how to generate it ? by LinkedList or ArrayDeque
//val que : Queue<Int> = ArrayDeque()
//ArrayDeque use reusable arrays, short for double que with 2 ends
//means u can add or remove from first or tail
//circular technique when dont want to increase the capacity
class Que<T>(private val capacity: Int) {

    private var que = arrayOfNulls<Any?>(capacity) as Array<T>
    private var F = 0
    private var R = 0
    private var count = 0

    fun enque(value: T) {
        if (count == capacity) {
            throw IllegalStateException()
        }
        //this is by fix size or implementing by increasing arraySize like what we have done in
        // stack
//        que[R++] = value

        //now with circular array
        //[0 , 1 , ]
        que[R] = value
        R = (R + 1) % que.size
        count++
    }

    fun deque(value : T) {
        if (que.isEmpty()) {
            throw IllegalStateException()
        }
//        val newque = arrayOfNulls<Any?>(capacity) as Array<T>
//        que.forEachIndexed { index, t ->
//            if (index != 0) {
//                newque[index] = t
//            } else {
//                F++
//            }
//        }
//        que = newque

        //try by circular arrayList
        que[F] = value
        F = (F + 1) % que.size
        count--

    }

    fun peek(): Any? {
        if (que.isEmpty()) {
            throw IllegalStateException()
        }
        return que[F]
    }

    override fun toString(): String {
        return que.contentToString()
    }

    fun revers(value: Queue<T>): String {
        val stack = Stack<T>()
        val que = ArrayDeque<T>()

        while (!value.isEmpty()) {
            stack.push(value.remove())
        }

        while (!stack.isEmpty()) {
            que.add(stack.pop())
        }
        return que.toString()

    }
}