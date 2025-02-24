package kr.co.lion.algorithm

//문제 설명
//문자열 s가 입력되었을 때 다음 규칙을 따라서 이 문자열을 여러 문자열로 분해하려고 합니다.
//
//먼저 첫 글자를 읽습니다. 이 글자를 x라고 합시다.
//이제 이 문자열을 왼쪽에서 오른쪽으로 읽어나가면서, x와 x가 아닌 다른 글자들이 나온 횟수를 각각 셉니다.
// 처음으로 두 횟수가 같아지는 순간 멈추고, 지금까지 읽은 문자열을 분리합니다.
//s에서 분리한 문자열을 빼고 남은 부분에 대해서 이 과정을 반복합니다. 남은 부분이 없다면 종료합니다.
//만약 두 횟수가 다른 상태에서 더 이상 읽을 글자가 없다면, 역시 지금까지 읽은 문자열을 분리하고, 종료합니다.
//문자열 s가 매개변수로 주어질 때, 위 과정과 같이 문자열들로 분해하고, 분해한 문자열의 개수를 return 하는 함수 solution을 완성하세요.
//
//제한사항
//1 ≤ s의 길이 ≤ 10,000
//s는 영어 소문자로만 이루어져 있습니다.

fun solution1(s: String): Int {
    var answer = 0       // 분리한 문자열 개수를 저장
    var index = 0        // 현재 문자열의 인덱스

    while (index < s.length) {
        // 현재 구간의 첫 글자를 x로 설정
        val x = s[index]
        var countX = 0       // x와 같은 문자 개수
        var countOther = 0   // x가 아닌 문자 개수

        // 구간을 끝까지 순회
        while (index < s.length) {
            if (s[index] == x) {
                countX++
            } else {
                countOther++
            }
            index++

            // x와 다른 문자의 개수가 같아지면 구간 분리
            if (countX == countOther) {
                answer++
                break
            }
        }
        // 만약 문자열이 끝났는데 균형을 이루지 못한 경우에도 구간으로 카운트
        if (index == s.length && countX != countOther) {
            answer++
        }
    }
    return answer
}

// 테스트 함수
fun main() {
    // 다양한 테스트 케이스로 검증
    val testCases = listOf(
        "banana",           // 예상 결과: 3 -> "ba", "na", "na"
        "abracadabra",      // 예상 결과: 6
        "aaabbaccccabba"    // 예상 결과: 3
    )

    for (test in testCases) {
        println("Input: \"$test\" -> Output: ${solution1(test)}")
    }
}