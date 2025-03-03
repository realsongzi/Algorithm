package kr.co.lion.algorithm.Lv1

fun solution(s: String): List<Int> {
    val answer = mutableListOf<Int>()
    // 알파벳 a~z의 마지막 등장 인덱스를 저장할 배열을 -1로 초기화
    val lastIndex = IntArray(26) { -1 }

    for (i in s.indices) {
        // 현재 문자를 0~25 범위의 인덱스로 변환 ('a'는 0, 'b'는 1, ...)
        val charIdx = s[i] - 'a'
        // 만약 이전에 해당 문자가 등장하지 않았다면 -1 추가
        if (lastIndex[charIdx] == -1) {
            answer.add(-1)
        } else {
            // 이전 등장 인덱스와의 거리를 계산하여 추가
            answer.add(i - lastIndex[charIdx])
        }
        // 현재 인덱스로 업데이트하여 앞으로의 계산에 활용
        lastIndex[charIdx] = i
    }

    return answer
}

fun main() {
    // 테스트 케이스 리스트
    val testCases = listOf(
        "banana",       // 예상 결과: [-1, -1, -1, 2, 2, 2]
        "abcabcbb",     // 예상 결과: [-1, -1, -1, 3, 3, 3, 2, 1]
        "aaa",          // 예상 결과: [-1, 1, 1]
        "abcdef"        // 예상 결과: [-1, -1, -1, -1, -1, -1]
    )

    // 각 테스트 케이스에 대해 solution 함수 실행 및 결과 출력
    for (test in testCases) {
        println("Input: $test")
        println("Output: ${solution(test)}")
        println()
    }
}