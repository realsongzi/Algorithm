package kr.co.lion.algorithm.Lv3

import java.util.*

fun solution(n: Long, bans: Array<String>): String {
    // 1. banned 단어들을 길이별로 그룹핑하고 정렬합니다.
    val bannedByLength = mutableMapOf<Int, MutableList<String>>()
    for (word in bans) {
        val len = word.length
        bannedByLength.computeIfAbsent(len) { mutableListOf() }.add(word)
    }
    // 각 길이별 banned 리스트를 사전순으로 정렬
    for (list in bannedByLength.values) {
        list.sort()
    }

    // 2. 0부터 11까지 26의 거듭제곱을 미리 계산합니다.
    // power26[i] = 26^i
    val power26 = LongArray(12)
    power26[0] = 1L
    for (i in 1..11) {
        power26[i] = power26[i - 1] * 26L
    }

    // 3. 길이가 1부터 11까지 순회하며, 각 길이에서 사용할 수 있는 유효 주문의 개수를 누적해
    // n번째 주문이 어느 길이에 속하는지 결정합니다.
    var remaining = n  // 남은 순서 번호
    var targetLength = 0
    for (L in 1..11) {
        val totalForL = power26[L]          // 길이가 L인 모든 문자열 개수 (26^L)
        val bannedCountForL = bannedByLength[L]?.size ?: 0  // 해당 길이에서 삭제된 주문 수
        val validForL = totalForL - bannedCountForL         // 실제 남은 주문의 수
        if (remaining <= validForL) {
            targetLength = L
            break
        } else {
            remaining -= validForL
        }
    }

    // 4. targetLength 길이의 주문 중, 남은 순서(remaining번째)에 해당하는 주문을 자리별로 결정합니다.
    var result = ""
    // result의 길이를 targetLength가 될 때까지 한 글자씩 결정
    for (pos in 0 until targetLength) {
        // 각 자리에서 'a'부터 'z'까지 후보 문자를 확인
        for (c in 'a'..'z') {
            val candidate = result + c
            // candidate를 접두어(prefix)로 가지는 문자열의 전체 개수 (나머지 자리에 올 수 있는 경우의 수)
            val completions = power26[targetLength - candidate.length]
            // candidate로 시작하는 banned 단어의 개수를 계산합니다.
            // banned 단어들은 targetLength 길이의 banned 리스트에서 찾습니다.
            val bannedList = bannedByLength[targetLength]
            val bannedCountCandidate = if (bannedList != null) countBanned(bannedList, candidate) else 0
            // candidate 접두어를 가진 주문 중 실제 유효한 주문의 수
            val validCountCandidate = completions - bannedCountCandidate

            if (remaining <= validCountCandidate) {
                // 현재 candidate의 블록에 n번째 주문이 포함됨:
                // result에 c를 추가하고 다음 자리로 진행합니다.
                result = candidate
                break
            } else {
                // 현재 candidate 블록의 유효 주문 수를 건너뛰고,
                // 남은 순서를 업데이트 합니다.
                remaining -= validCountCandidate
            }
        }
    }
    return result
}

// banned 단어 리스트 (이미 정렬된 상태)에서, 특정 prefix로 시작하는 단어의 개수를 이분 탐색으로 구하는 함수
fun countBanned(list: List<String>, prefix: String): Int {
    // lower bound: prefix가 들어갈 위치
    val lower = list.binarySearch(prefix).let { if (it < 0) -it - 1 else it }
    // upper bound: prefix 다음 문자인 '{'를 붙여서 계산 ('{'는 'z'보다 한 칸 큰 문자)
    val upper = list.binarySearch(prefix + '{').let { if (it < 0) -it - 1 else it }
    return upper - lower
}