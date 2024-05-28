package com.sanam.yvp.datastructureplayground

//super fast look up
//spell checkers
//building dictionaries
//compiler
//in java we call hashMap
//map.containsKey() o(1)
//map.containsValue() o(n)

// set like hashmap doest allow duplicate keys but despite hashmap it
//doest allow duplicate value
// set is order kind of hashmap

class HashTable<K, V> {
    //We’ll start with creating a fixed-size array with 16 slots
// (same as the default Java HashMap).
//collisions : What happened? Well we’ve got 16 slots in our array and we tried to add 26
// items so we’ve overwritten 10 of them!
// These are called collisions and there are many strategies to mitigate them but here are two basic ones:
//Store multiple items in each array slot
//Grow the array to make more space
    private val arraySize = 16
    private val items: Array<Entry<K, V>?> = arrayOfNulls(arraySize)
    private val collisionLinkedList: Array<LinkedList<Entry<K, V>>> =
        Array(arraySize) { LinkedList<Entry<K, V>>() }

    //it is important to save both keys and value to handle duplicates and override value,
    //so save both key and value to hashTable
    //to create this we use array of LinkedList
    //When we call put(key, value) on the hash map,
    // the class needs to decide where in the array to store the value based on the key.
    // To do this the map uses the hash code of the key.
    fun put(key: K, value: V) {
        val index = hash(key)
        items[index] = Entry(key, value)
    }

    //We can put items in each array slot by appending them to the end of the LinkedList
    // (or replacing an existing entry with the same key).
    fun putCollision(key: K, value: V) {
        val index = hash(key)
        val item = LinkedList<Entry<K, V>>()
            item.addLast(Entry(key, value))
            collisionLinkedList[index] = item
    }

    fun get(key: K): V? {
        return items[hash(key)]?.value
    }

    //We can get items for key K by walking through the LinkedList to find the Entry with that key
    // (hence why we store both key and value)
    fun getCollison(key: K): V? {
        return items[hash(key)]?.value
    }

    fun remove(key: K) {
        val index = hash(key)
        items[index] = null
    }

    //the hashTable should should map its key to and index value, the index in which
    // we should store the value, so this is the job of hash function
    //in context of data structure, a hash functions maps a key value to index value.
    private fun hash(key: K): Int {
        return key.hashCode() % arraySize
    }


    fun findNunRepeatedLetters(value: String): String {
        val number = 1
        val mapOfLetters = HashMap<Char, Int>()
        value.toCharArray().forEach {
            if (mapOfLetters.containsKey(it)) {
                mapOfLetters[it] = mapOfLetters[it]!!.plus(1)
            } else {
                mapOfLetters[it] = number
            }
        }

        value.toCharArray().forEach {
            if (mapOfLetters.containsKey(it)) {
                if (mapOfLetters[it]!! > 1) {
                    mapOfLetters.remove(it)
                }
            }
        }
        return mapOfLetters.toString();
    }

    fun findFirstRepeatedCharacter(value: String): Char {
        val mapOfLetters = HashMap<Char, Int>()
        value.toCharArray().forEach {
            if (mapOfLetters.containsKey(it))
                return it
            mapOfLetters[it] = 0

        }
        return Char.MIN_VALUE
    }

}

data class Entry<K, V>(val key: K, val value: V)