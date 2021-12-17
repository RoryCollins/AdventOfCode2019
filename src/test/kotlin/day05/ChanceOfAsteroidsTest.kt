package day05

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import shared.Solution

internal class ChanceOfAsteroidsTest {

    @Test
    fun `Solve first example`() {
        val input = "3,0,4,0,99"
        assertEquals(Solution(100, 0), solve(input))
    }

    @Test
    fun `Support parameter nodes`(){
        val input = "1002,4,3,4,33"
        assertEquals(Solution(100, 0), solve(input))
    }
}