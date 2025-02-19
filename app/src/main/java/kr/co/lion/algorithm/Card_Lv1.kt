package kr.co.lion.algorithm

// 문제 설명
// 코니는 영어 단어가 적힌 카드 뭉치 두 개를 선물로 받았습니다.
// 코니는 다음과 같은 규칙으로 카드에 적힌 단어들을 사용해 원하는 순서의 단어 배열을 만들 수 있는지 알고 싶습니다.
//
// 원하는 카드 뭉치에서 카드를 순서대로 한 장씩 사용합니다.
// 한 번 사용한 카드는 다시 사용할 수 없습니다.
// 카드를 사용하지 않고 다음 카드로 넘어갈 수 없습니다.
// 기존에 주어진 카드 뭉치의 단어 순서는 바꿀 수 없습니다.
// 예를 들어 첫 번째 카드 뭉치에 순서대로 ["i", "drink", "water"],
// 두 번째 카드 뭉치에 순서대로 ["want", "to"]가 적혀있을 때
// ["i", "want", "to", "drink", "water"] 순서의 단어 배열을 만들려고 한다면
// 첫 번째 카드 뭉치에서 "i"를 사용한 후 두 번째 카드 뭉치에서 "want"와 "to"를 사용하고
// 첫 번째 카드뭉치에 "drink"와 "water"를 차례대로 사용하면 원하는 순서의 단어 배열을 만들 수 있습니다.
// 문자열로 이루어진 배열 cards1, cards2와 원하는 단어 배열 goal이 매개변수로 주어질 때,
// cards1과 cards2에 적힌 단어들로 goal를 만들 있다면 "Yes"를, 만들 수 없다면 "No"를 return하는 solution 함수를 완성해주세요.



fun solution(cards1: Array<String>, cards2: Array<String>, goal: Array<String>): String {
    var idx1 = 0
    var idx2 = 0

    for (word in goal) {
        // cards1에서 단어 확인
        if (idx1 < cards1.size && cards1[idx1] == word) {
            idx1++
        }
        // cards1이 아니라면 cards2에서 단어 확인
        else if (idx2 < cards2.size && cards2[idx2] == word) {
            idx2++
        } else {
            // 두 카드 뭉치 어디에도 현재 단어가 없다면
            return "No"
        }
    }
    // goal을 끝까지 성공적으로 만들었다면
    return "Yes"
}

fun main() {
    // 예시 1
    val cards1_1 = arrayOf("i", "drink", "water")
    val cards2_1 = arrayOf("want", "to")
    val goal1 = arrayOf("i", "want", "to", "drink", "water")
    println(solution(cards1_1, cards2_1, goal1)) // "Yes"

    // 예시 2
    val cards1_2 = arrayOf("i", "water", "drink")
    val cards2_2 = arrayOf("want", "to")
    val goal2 = arrayOf("i", "want", "to", "drink", "water")
    println(solution(cards1_2, cards2_2, goal2)) // "No"
}

// forEach 확장함수를 이용해서 적용할 수 있음
//fun solution(cards1: Array<String>, cards2: Array<String>, goal: Array<String>): String {
//    var idx1 = 0
//    var idx2 = 0
//
//    goal.forEach { word ->
//        // cards1에서 단어 확인
//        if (idx1 < cards1.size && cards1[idx1] == word) {
//            idx1++
//        }
//        // cards1이 아니라면 cards2에서 단어 확인
//        else if (idx2 < cards2.size && cards2[idx2] == word) {
//            idx2++
//        } else {
//            // 두 카드 뭉치 어디에도 현재 단어가 없다면 곧바로 함수 종료
//            return "No"
//        }
//    }
//
//    // goal을 끝까지 성공적으로 만들었다면
//    return "Yes"
//}
