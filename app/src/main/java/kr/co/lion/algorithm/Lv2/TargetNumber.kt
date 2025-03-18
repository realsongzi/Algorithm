package kr.co.lion.algorithm.Lv2


//n개의 음이 아닌 정수들이 있습니다.
// 이 정수들을 순서를 바꾸지 않고 적절히 더하거나 빼서 타겟 넘버를 만들려고 합니다.
// 예를 들어 [1, 1, 1, 1, 1]로 숫자 3을 만들려면 다음 다섯 방법을 쓸 수 있습니다.
//
//-1+1+1+1+1 = 3
//+1-1+1+1+1 = 3
//+1+1-1+1+1 = 3
//+1+1+1-1+1 = 3
//+1+1+1+1-1 = 3
//사용할 수 있는 숫자가 담긴 배열 numbers,
// 타겟 넘버 target이 매개변수로 주어질 때 숫자를 적절히 더하고 빼서 타겟 넘버를 만드는 방법의 수를 return 하도록 solution 함수를 작성해주세요.
//
//제한사항
//주어지는 숫자의 개수는 2개 이상 20개 이하입니다.
//각 숫자는 1 이상 50 이하인 자연수입니다.
//타겟 넘버는 1 이상 1000 이하인 자연수입니다.
//입출력 예
//numbers	target	return
//[1, 1, 1, 1, 1]	3	5
//[4, 1, 2, 1]	4	2
//입출력 예 설명
//입출력 예 #1
//
//문제 예시와 같습니다.
//
//입출력 예 #2
//
//+4+1-2+1 = 4
//+4-1+2-1 = 4
//총 2가지 방법이 있으므로, 2를 return 합니다.

fun solution(numbers: IntArray, target: Int): Int {
    // 재귀 함수를 통해 모든 경우를 탐색합니다.
    fun dfs(index: Int, sum: Int): Int {
        // 모든 숫자를 다 사용한 경우
        if (index == numbers.size) {
            return if (sum == target) 1 else 0
        }
        // 현재 숫자를 더하는 경우와 빼는 경우를 모두 탐색하여 합산합니다.
        return dfs(index + 1, sum + numbers[index]) + dfs(index + 1, sum - numbers[index])
    }
    return dfs(0, 0)
}

fun main() {
    // 테스트 케이스 1
    val numbers1 = intArrayOf(1, 1, 1, 1, 1)
    val target1 = 3
    val result1 = solution(numbers1, target1)
    println("Test case 1: Expected = 5, Result = $result1")

    // 테스트 케이스 2
    val numbers2 = intArrayOf(4, 1, 2, 1)
    val target2 = 4
    val result2 = solution(numbers2, target2)
    println("Test case 2: Expected = 2, Result = $result2")

    // 추가 테스트: 여러 케이스를 더 작성할 수 있습니다.
}

// 기존에 작성했던 코드가 시간복잡도 O(2^N)으로
// 더 효율적인 DP를 활용해서 중복 연산을 줄여보자
// DP 적용 후 O(N * S)
// 오류 (StackOverflowError) 발생
//class Solution {
//    fun solution(numbers: IntArray, target: Int): Int {
//        val memo = mutableMapOf<Pair<Int, Int>, Int>() // DP 캐시
//
//        fun dfs(index: Int, sum: Int): Int {
//            if (index == numbers.size) return if (sum == target) 1 else 0
//
//            // 메모이제이션 (이전 계산 값이 있으면 그대로 반환)
//            val key = Pair(index, sum)
//            if (memo.containsKey(key)) return memo[key]!!
//
//            // 현재 숫자를 더하거나 빼는 경우의 수 계산
//            val count = dfs(index + 1, sum + numbers[index]) + dfs(index + 1, sum - numbers[index])
//
//            // 결과 저장
//            memo[key] = count
//            return count
//        }
//        return dfs(0, 0)
//    }
//}


// 꼬리재귀 (Tail Recursion) 적용
// Kotlin의 teilrec 키워드를 사용하면 꼬리 재귀 최적화(TCE, Tail Call Optimization) 이 적용됨
// 꼬리 재귀를 적용하면, 재귀 호출을 반복문으로 변환하여 스택 오버플로우를 방지한다.
//class Solution {
//    fun solution(numbers: IntArray, target: Int): Int {
//        val memo = mutableMapOf<Pair<Int, Int>, Int>()
//
//        tailrec fun dfs(index: Int, sum: Int): Int {
//            if (index == numbers.size) return if (sum == target) 1 else 0
//
//            val key = Pair(index,sum)
//            if (memo.containsKey(key)) return memo[key]!!
//
//            val left = dfs(index + 1, sum + numbers[index])
//            val right = dfs(index + 1, sum - numbers[index])
//
//            memo[key] = left + right
//            return memo[key]!!
//        }
//        return dfs(0,0)
//    }
//}

// BFS (반복문) 을 사용하여 풀어보기
// WHY? DP(재귀)보다 BFS(큐) 방식이 더 직관적이고 안정적인 성능을 가지며
// DFS는 스택 오버플로우 위험이 있고, DP는 구현이 다소 어려울 수 있으나, BFS는 가장 간단하게 해결 가능하다.
// 모든 경우를 큐에 저장하고, 반복문으로 탐색한다.

//import java.util.*
//
//class Solution {
//    fun solution(numbers: IntArray, target: Int): Int {
//        var count = 0
//        val queue: Queue<Pair<Int, Int>> = LinkedList()
//        queue.add(Pair(0, 0)) // (현재 index, 현재 sum)
//
//        while (queue.isNotEmpty()) {
//            val (index, sum) = queue.poll()
//
//            if (index == numbers.size) {
//                if (sum == target) count++
//            } else {
//                queue.add(Pair(index + 1, sum + numbers[index]))
//                queue.add(Pair(index + 1, sum - numbers[index]))
//            }
//        }
//        return count
//    }
//}
