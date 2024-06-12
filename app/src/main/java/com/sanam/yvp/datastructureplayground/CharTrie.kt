package com.sanam.yvp.datastructureplayground

import java.util.*
import kotlin.collections.HashMap

fun dictionary() {
    val dictionary = CharTrieByHashtable()
    dictionary.insert("cat")
    dictionary.insert("cow")
    dictionary.insert("canada")
    dictionary.insert("Bowl")
    println("Done!")
    println("contains cat? ${dictionary.contains("cat")}")
    println("contains can? ${dictionary.contains("can")}")
    println("contains canada? ${dictionary.contains("canada")}")
    println("contains ..? ${dictionary.contains("..")}")
    println("contains null? ${dictionary.contains(null)}")
    println("contains bow? ${dictionary.contains("bow")}")
    println("contains bowl? ${dictionary.contains("bowl")}")
}

fun testTrieTraversal() {
    val dictionary = CharTrieByHashtable()
    dictionary.insert("care")
    dictionary.insert("cat")
    dictionary.insert("can")
    dictionary.preOrderTraverse()
    println()
    dictionary.postOrderTraverse()
}

fun testTrieRemove() {
    val dictionary = CharTrieByHashtable()
    dictionary.insert("care")
    dictionary.remove("care")
}

//This solution is not optimized because by creating each Node we allocate an array with 26 size.
// so it is not space optimized
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
        word.forEach { char ->
            val index = char.lowercaseChar() - 'a' + 1
            if (current.children[index] == null) {
                current.children[index] = Node(char)
            }
            current = current.children[index]!!
        }
    }
}

class CharTrieByHashtable {
    private val root = Node(' ')

    private class Node(val value: Char) {
        val children = HashMap<Char, Node>()
        var isEndOfTheWord = false

        override fun toString(): String {
            return "value $value"
        }
    }

    fun insert(word: String) {
        var current = root
        word.lowercase(Locale.getDefault()).forEach { char ->
            if (current.children[char] == null) {
                current.children[char] = Node(char)
            }
            current = current.children[char]!!
        }
        current.isEndOfTheWord = true
    }

    fun contains(word: String?): Boolean {
        var current = root
        word?.lowercase(Locale.getDefault())?.forEach { char ->
            if (current.children.containsKey(char)) {
                current = current.children[char]!!
            } else {
                return false
            }
        }
        return current.isEndOfTheWord
    }

    //Visit the current Node.. recursively visit the left and right child
    //print all the word
    fun preOrderTraverse() {
        preOrderTraverse(root)
    }

    private fun preOrderTraverse(root: Node) {
        println(root.value)
        root.children.values.forEach {
            preOrderTraverse(it)
        }
    }

    //Recursively visit the left and right child, only visit the current node after the left and right
    //child have been visited
    //delete a word at trie post
    fun postOrderTraverse() {
        postOrderTraverse(root)
    }

    private fun postOrderTraverse(root: Node): Node {
        root.children.values.forEach {
            postOrderTraverse(it)
        }
        println(root.value)
        return root
    }

    fun remove(word: String) {
        remove(root, word.lowercase(Locale.getDefault()), 0)
    }

    private fun remove(root: Node, word: String, index: Int) {
        if (index == word.length){
            println(root.value)
            return
        }
        val char = word[index]
        val child = root.children[char] ?: return
        remove(child, word, index + 1)
        println(root.value)
    }
}

