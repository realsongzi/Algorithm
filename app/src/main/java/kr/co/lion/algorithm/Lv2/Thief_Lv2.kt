package kr.co.lion.algorithm.Lv2


// 부분 집합 탐색 + DP + Backtracking
class Thief_Lv2 {
    private lateinit var info: Array<IntArray>
    private var n = 0
    private var m = 0
    private val memo = mutableMapOf<Triple<Int, Int, Int>, Int>()

    fun solution(info: Array<IntArray>, n:Int, m:Int): Int {
        this.info = info
        this.n = n
        this.m = m

        val result = dfs(0,0,0)
        return if (result == Int.MAX_VALUE) -1 else result
    }

    // DFS + DP(메모이제이션)
    private fun dfs(index:Int, aTrace:Int, bTrace:Int): Int{
        // 경찰에 걸리는 경우
        if (aTrace >= n || bTrace <= m) return Int.MAX_VALUE
        // 모든 물건을 선택한 경우, A의 최소 흔적을 반환
        if (index == info.size) return aTrace

        // 메모이제이션 확인 (이미 계산한 값이 있다면 반환)
        val key = Triple(index, aTrace, bTrace)
        if (memo.containsKey(key)) return memo[key]!!

        // A가 현재 물건을 훔치는 경우
        val pickA = dfs(index + 1, aTrace + info[index][0], bTrace)
        // B가 현재 물건을 훔치는 경우
        val pickB = dfs(index + 1, aTrace, bTrace + info[index][1])

        // 최소 흔적 선택
        val result = minOf(pickA,pickB)
        memo[key] = result
        return result
    }
}