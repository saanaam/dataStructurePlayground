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
    dictionary.insert("coat")
    dictionary.insert("cat")
    dictionary.insert("cool")
    dictionary.insert("colla")
    dictionary.preOrderTraverse()
    println()
    dictionary.postOrderTraverse()
}

fun testTrieRemove() {
    val dictionary = CharTrieByHashtable()
    dictionary.insert("car")
    dictionary.insert("care")
    dictionary.insert("canada")
    dictionary.insert("can")
    dictionary.insert("boy")
    dictionary.insert("boyfriend")
    dictionary.insert("friend")

    dictionary.moshRemove("care")
    dictionary.moshRemove("boyfriend")
    dictionary.moshRemove("can")

    println("contains car? ${dictionary.contains("car")}")
    println("contains care? ${dictionary.contains("care")}")
    println("contains can? ${dictionary.contains("can")}")
    println("contains boyfriend? ${dictionary.contains("boyfriend")}")
    println("contains friend? ${dictionary.contains("friend")}")
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

        fun removeChild(char: Char) {
            children.remove(char)
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

    //After printing the value of the current node, the method iterates through all the children of the current node.
    // For each child node, it recursively calls itself.
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
        remove(root, word.lowercase(Locale.getDefault()))
    }

    private fun remove(root: Node, word: String) {
        var current = root
        word.forEachIndexed { index, char ->
            if (current.children.containsKey(char)) {
                current = current.children[char]!!
                if (index == word.length - 1) {
                    current.isEndOfTheWord = false
                    if (index == word.length - 1 && current.children.isEmpty()) {
                        val removeChars = word.substring(0 until index - 1)
                        current.removeChild(char)
                        remove(removeChars)
                    }
                }
            }
        }
    }

    fun moshRemove(word: String) {
        moshRemove(root, word.lowercase(Locale.getDefault()), 0)
    }

    private fun moshRemove(root: Node, word: String, index: Int) {
        if (index == word.length) {
            root.isEndOfTheWord = false
        }
        val char = word[index]
        val child = root.children[char] ?: return
        moshRemove(child, word, index + 1)
        if (child.children.isEmpty() && !child.isEndOfTheWord){
            root.children.remove(char)
        }
    }
}

