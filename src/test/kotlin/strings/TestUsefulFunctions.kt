package strings

import org.junit.Test
import kotlin.test.assertEquals

class TestUsefulFunctions {

    @Test fun testComputeHash() {
        assertEquals(614254, computeHash("test"))
        assertEquals(952913702, computeHash("ahdhaweqweqdadawdw"))
    }
}
