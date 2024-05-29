package com.sanam.yvp.datastructureplayground


fun beveragesTries() {
    val tree = TreeNode("Beverages")
    val hot = TreeNode("hot")
    val cold = TreeNode("cold")
    val tea = TreeNode("tea")
    val coffee = TreeNode("coffee")
    val soda = TreeNode("soda")
    val milk = TreeNode("milk")
    tree.add(hot)
    tree.add(cold)
    hot.add(tea)
    hot.add(coffee)
    cold.add(soda)
    cold.add(milk)
    tree.forEachDepthFirst { println(it.value) }
}

class TreeNode<T>(val value: T) {

    val children: MutableList<TreeNode<T>> = mutableListOf()

    fun add(child: TreeNode<T>) = children.add(child)

    fun forEachDepthFirst(visit: Visitor<T>) {
        visit(this)
        children.forEach {
            it.forEachDepthFirst(visit)
        }
    }

    override fun toString(): String {
        return "value=$value)"
    }

}

typealias Visitor<T> = (TreeNode<T>) -> Unit