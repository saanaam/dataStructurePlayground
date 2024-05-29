package com.sanam.yvp.datastructureplayground

fun dictionary() {
    val dictionary = CharTrie()
    dictionary.insert("cat")
    dictionary.insert("can")
    println("Done!")
}

class CharTrie {
    private val dictionary = TreeNode(' ')

    var isEnd: Boolean = false

    private fun index(char: Char) = char.lowercaseChar() - 'a' + 1

    fun insert(word: String) {
        var current = dictionary
        word.forEach {
            if (dictionary.children.getOrNull(index(it)) == null) {
                current.add(TreeNode(it))
            }
            current = current.
        }
    }
}