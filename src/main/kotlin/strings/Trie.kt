package strings

class Trie<Value>(val R: Int = 26, private val minChar: Char = 'a') {
    private var root: Node? = null
    inner class Node(var value: Value? = null) {
        val next = MutableList<Node?>(R) { null }
    }

    fun get(key: String): Value? {
        val x = get(root, key, 0)
        return x?.value
    }

    fun contains(key: String): Boolean {
        return get(key) != null
    }

    private fun get(x: Node?, key: String, d: Int): Node? {
        if (x == null) return null
        if (d == key.length) return x
        val c = key[d] - minChar
        return get(x.next[c], key, d + 1)
    }

    fun put(key: String, value: Value) {
        root = put(root, key, value, 0)
    }

    private fun put(xx: Node?, key: String, value: Value?, d: Int): Node? {
        // Change value associated with key if in subtrie rooted at x
        val x = xx ?: Node()
        if (d == key.length) {
            x.value = value
            return x
        }
        val c = key[d] - minChar
        x.next[c] = put(x.next[c], key, value, d + 1)
        return x
    }

    fun keys(): Iterable<String> {
        return keysWithPrefix("")
    }

    fun keysWithPrefix(pre: String): Iterable<String> {
        val q = mutableListOf<String>()
        collect(get(root, pre, 0), pre, q)
        return q
    }

    private fun collect(x: Node?, pre: String, q: MutableList<String>) {
        if (x == null) return
        if (x.value != null) q.add(pre)
        for (c in 0 until R) {
            collect(x.next[c], pre + (minChar + c), q)
        }
    }

    fun longestPrefixOf(s: String): String {
        val length = search(root, s, 0, 0)
        return s.substring(0, length)
    }

    private fun search(x: Node?, s: String, d: Int, len: Int): Int {
        var length = len
        if (x == null) return length
        if (x.value != null) length = d
        if (d == s.length) return length
        val c = s[d] - minChar
        return search(x.next[c], s, d + 1, length)
    }
}