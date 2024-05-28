package com.sanam.yvp.datastructureplayground

import java.lang.StringBuilder
import java.util.Stack


class StackQuestions {
    //note:
    // for going back ro reverse sth stack is the best friend
    private val openExpression = listOf('(', '<', '{', '[') as List<Char>
    private val closeExpression = listOf(')', '>', '}', ']') as List<Char>

    fun reverseString(string: String): String {
        //abcd
        var stack = Stack<Char>()
        var reverse = StringBuilder()
        var reversed = StringBuilder()

        string.forEachIndexed { index, c ->
            stack.push(string[index])
        }

        while (!stack.empty()) {
            reverse.append(stack.pop())
        }
        return reverse.toString()
    }

    fun containsBalancedExpression(string: String): Int {
        val stack = Stack<Char>()
        var number = 0
        string.toCharArray().forEach { ch ->
            if (stack.empty()) {
                stack.push(ch)
            } else {
                if (ch.toString() == "(") {
                    stack.push(ch)
                }
                if (ch.toString() == ")") {
                    if (!stack.empty() && stack.any { it.toString() == "(" }) {
                        stack.pop()
                        number++
                    }
                }
            }
        }
        return number
    }

    fun areExpressionBalance(string: String): Boolean {
        val stack = Stack<Char>()
        string.toCharArray().forEach { it ->
            if (isOpeningExpression(it)) {
                stack.push(it)
            }

            if (isClosingExpression(it)) {
                if (stack.isEmpty()) return false
                if (!isBracketMatches(stack.pop(), it)) {
                    return false
                }
            }
        }
        return stack.empty()
    }

    private fun isOpeningExpression(it: Char) = openExpression.contains(it)
    private fun isClosingExpression(it: Char) = closeExpression.contains(it)
    private fun isBracketMatches(openChar: Char, closeChar: Char) =
        openExpression.indexOf(openChar) == closeExpression.indexOf(closeChar)

}

