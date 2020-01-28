package strings

import org.junit.Test
import kotlin.test.assertEquals

class TestTrie {

    @Test fun testTrie() {
        val trieKeys = listOf("she", "sells", "seashells", "by", "the", "seashore", "the", "shells", "she",
            "sells", "are", "surely", "seashells", "at", "the", "sea", "shore")
        val trie = Trie<Int>()
        trieKeys.forEach {
            if (trie.contains(it)) trie.put(it, trie.get(it)!! + 1)
            else trie.put(it, 1)
        }

        // Test keys
        assertEquals(trieKeys.toSet().toList().sorted(), trie.keys())

        // Test keys with prefix
        assertEquals(listOf("sea", "seashells", "seashore"), trie.keysWithPrefix("sea"))
        assertEquals(listOf("she", "shells", "shore"), trie.keysWithPrefix("sh"))

        // Test longest prefix
        assertEquals("she", trie.longestPrefixOf("shell"))
        assertEquals("she", trie.longestPrefixOf("she"))
        assertEquals("shells", trie.longestPrefixOf("shellsort"))
        assertEquals("she", trie.longestPrefixOf("shelters"))
    }
}