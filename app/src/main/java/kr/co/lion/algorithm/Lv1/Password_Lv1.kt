package kr.co.lion.algorithm.Lv1


// 문제 설명
// 두 문자열 s와 skip, 그리고 자연수 index가 주어질 때, 다음 규칙에 따라 문자열을 만들려 합니다. 암호의 규칙은 다음과 같습니다.
//
// 문자열 s의 각 알파벳을 index만큼 뒤의 알파벳으로 바꿔줍니다.
// index만큼의 뒤의 알파벳이 z를 넘어갈 경우 다시 a로 돌아갑니다.
// skip에 있는 알파벳은 제외하고 건너뜁니다.
// 예를 들어 s = "aukks", skip = "wbqd", index = 5일 때,
// a에서 5만큼 뒤에 있는 알파벳은 f지만 [b, c, d, e, f]에서 'b'와 'd'는 skip에 포함되므로 세지 않습니다.
// 따라서 'b', 'd'를 제외하고 'a'에서 5만큼 뒤에 있는 알파벳은 [c, e, f, g, h] 순서에 의해 'h'가 됩니다.
// 나머지 "ukks" 또한 위 규칙대로 바꾸면 "appy"가 되며 결과는 "happy"가 됩니다.
// 두 문자열 s와 skip, 그리고 자연수 index가 매개변수로 주어질 때 위 규칙대로 s를 변환한 결과를 return하도록 solution 함수를 완성해주세요.
//
//제한사항
//5 ≤ s의 길이 ≤ 50
//1 ≤ skip의 길이 ≤ 10
//s와 skip은 알파벳 소문자로만 이루어져 있습니다.
//skip에 포함되는 알파벳은 s에 포함되지 않습니다.
//1 ≤ index ≤ 20

fun solution(s: String, skip: String, index: Int): String {
    // skip에 포함된 문자들을 집합으로 생성 (검색 시간 O(1))
    val skipSet = skip.toSet()

    // 'a'부터 'z'까지 반복하면서 skip에 포함되지 않은 문자만 allowedList에 저장
    val allowedList = ('a'..'z').filter { it !in skipSet }
    val allowedSize = allowedList.size

    // allowedList의 각 문자에 대해 인덱스 매핑 (예: 'a' -> 0, 'c' -> 1, ...)
    val charToIndex = allowedList.withIndex().associate { (i, c) -> c to i }

    // s의 각 문자를 규칙에 따라 변환하여 결과 문자열 생성
    return s.map { c ->
        val currentIndex = charToIndex[c] ?: 0  // s의 문자는 항상 allowedList에 존재
        // 현재 인덱스에서 index만큼 이동 후, 리스트 크기로 모듈러 연산하여 순환 처리
        val newIndex = (currentIndex + index) % allowedSize
        allowedList[newIndex]
    }.joinToString("")
}

fun main() {
    // 테스트 케이스: 문제에서 주어진 예시
    val s = "aukks"
    val skip = "wbqd"
    val index = 5
    val result = solution(s, skip, index)

    println("입력: s = \"$s\", skip = \"$skip\", index = $index")
    println("출력: $result")  // 예상 결과: "happy"
}