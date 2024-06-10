package com.sanam.yvp.datastructureplayground

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

// this implementation has a problem in object oriented design
class CharTrieByHashtable {

    private val root = Node(' ')

    class Node(val value: Char) {
        val children = HashMap<Int, Node>()
        var isEndOfTheWord = false
        fun setEndOfTheWord(index: Int, word: String) {
            if (index == word.length - 1) {
                isEndOfTheWord = true
            }
        }
    }


    fun insert(word: String) {
        var current = root
        word.forEachIndexed { i, value ->
            val index = value.lowercaseChar() - 'a' + 1
            if (current.children[index] == null) {
                current.children[index] = Node(value)
            }
            current = current.children[index]!!
            current.setEndOfTheWord(i, word)
        }
    }

    fun contains(word: String?): Boolean {
        var exist = false
        var current = root
        word?.forEachIndexed { i, value ->
            val index = value.lowercaseChar() - 'a' + 1
            if (current.children.containsKey(index)) {
                current = current.children[index]!!
                if (i == word.length - 1) {
                    exist = current.isEndOfTheWord
                }
            } else {
                return false
            }
        }
        return exist
    }

    //Visit the current Node.. recursively visit the left and right child
    //print all the word
    fun preOrderTraverse() {
        preOrderTraverse(root)
    }

    //Recursively visit the left and right child, only visit the current node after the left and right
    //child have been visited
    //delete a word at trie post
    fun postOrderTraverse() {
        postOrderTraverse(root)
    }

    private fun preOrderTraverse(root: Node) {
        println(root.value)
        root.children.values.forEach {
            preOrderTraverse(it)
        }
    }

    private fun postOrderTraverse(root: Node) {
        root.children.values.forEach {
            postOrderTraverse(it)
        }
        println(root.value)
    }

    fun delete(word: String) {
        if (!contains(word)) {
            println("Word does not exist")
        } else {

        }
    }
}

