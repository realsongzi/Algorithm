package kr.co.lion.algorithm

import kr.co.lion.algorithm.Lv2.solution
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertArrayEquals

class FeatureDevelopmentTest {

    private val solution: (IntArray, IntArray) -> IntArray = ::solution

    @Test
    fun testCase1(){
        val progresses = intArrayOf(93, 30, 55)
        val speeds = intArrayOf(1, 30, 5)
        val expected = intArrayOf(2, 1)

        val result = solution(progresses, speeds)

        assertArrayEquals(expected, result)
    }
}