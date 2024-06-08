package com.sanam.yvp.datastructureplayground

fun dictionary() {
    val dictionary = CharTrieByHashtable()
    dictionary.insert("cat")
    dictionary.insert("can")
    dictionary.insert("cow")
    dictionary.insert("Bowl")
    println("Done!")
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
        word.forEach {
            val index = it.lowercaseChar() - 'a' + 1
            if (current.children[index] == null) {
                current.children[index] = Node(it)
            }
            current = current.children[index]!!
        }
    }
}

// this implementation has a problem in object oriented design
class CharTrieByHashtable {
    private val root = Node(' ')

    private class Node(val value: Char) {
        val children = HashTable<Int, Node>()
    }

    fun insert(word: String) {
        var current = root
        word.forEach {
            val index = it.lowercaseChar() - 'a' + 1
            if (current.children.get(index) == null) {
                current.children.put(index ,Node(it) )
            }
            current = current.children.get(index)!!
        }
    }
}