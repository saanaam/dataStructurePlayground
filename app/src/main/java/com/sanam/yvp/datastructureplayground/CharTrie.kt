package com.sanam.yvp.datastructureplayground

fun dictionary() {
    val dictionary = CharTrie()
    dictionary.insert("cat")
    dictionary.insert("can")
    dictionary.insert("cow")
    dictionary.insert("Bowl")
    println("Done!")
}

class CharTrie {
    private val root = Node(' ')
    private class Node(val value: Char) {
        companion object {
            const val ALPHABET_SIZE = 26
        }
        val children: Array<Node?> = arrayOfNulls(ALPHABET_SIZE)
    }
    fun insert(word: String) {
        var current = root
        word.forEach {
            val index = it.lowercaseChar() - 'a' + 1
            if (current.children[index] == null) {
                current.children[index] = Node(it)
            }
            current = current.children[index]!!
        }
    }
}