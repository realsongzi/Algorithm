package kr.co.lion.algorithm.Lv2


//문제 설명
//사전에 알파벳 모음 'A', 'E', 'I', 'O', 'U'만을 사용하여 만들 수 있는, 길이 5 이하의 모든 단어가 수록되어 있습니다. 사전에서 첫 번째 단어는 "A"이고, 그다음은 "AA"이며, 마지막 단어는 "UUUUU"입니다.
//
//단어 하나 word가 매개변수로 주어질 때, 이 단어가 사전에서 몇 번째 단어인지 return 하도록 solution 함수를 완성해주세요.
//
//제한사항
//word의 길이는 1 이상 5 이하입니다.
//word는 알파벳 대문자 'A', 'E', 'I', 'O', 'U'로만 이루어져 있습니다.
//입출력 예
//word	result
//"AAAAE"	6
//"AAAE"	10
//"I"	1563
//"EIO"	1189
//입출력 예 설명
//입출력 예 #1
//
//사전에서 첫 번째 단어는 "A"이고, 그다음은 "AA", "AAA", "AAAA", "AAAAA", "AAAAE", ... 와 같습니다. "AAAAE"는 사전에서 6번째 단어입니다.
//
//입출력 예 #2
//
//"AAAE"는 "A", "AA", "AAA", "AAAA", "AAAAA", "AAAAE", "AAAAI", "AAAAO", "AAAAU"의 다음인 10번째 단어입니다.
//
//입출력 예 #3
//
//"I"는 1563번째 단어입니다.
//
//입출력 예 #4
//
//"EIO"는 1189번째 단어입니다.

// 수학적 계산을 이용
class DictionaryTest_Lv2 {

    fun solution(word: String): Int {
        val chars = listOf('A', 'E', 'I', 'O', 'U')
        val weights = listOf(781, 156, 31, 6, 1) // 각 자리의 가중치를 부여 (5^4 + 5^3 + 5^2 + 5^1 + 5^0)

        var index = 0
        for (i in word.indices) {
            val charIndex = chars.indexOf(word[i]) // 현재 문자의 인덱스 찾기
            index += charIndex * weights[i] + 1 // 현재 문자(word[i])가 chars 리스트에서 몇 번째인지 (charIndex)를 찾고, 해당 자리 (i)에서 가질 수 있는 단어 개수 (weights[i])를 곱하여 몇 개를 건너 뛰었는지 계산
                                                // 단어의 순서는 1부터 시작하기 때문에 + 1
        }
        return index
    }
}

// 완전 탐색을 이용
//class DictionaryBruteForceTest {
//
//    val vowels = listOf('A', 'E', 'I', 'O', 'U')
//    val wordList = mutableListOf<String>()
//
//    fun generateWords(current: String) {
//        if (current.length > 5) return
//        wordList.add(current)
//        for (char in vowels) {
//            generateWords(current + char)
//        }
//    }
//
//    fun solution(word: String): Int {
//        wordList.clear()
//        generateWords("")
//        wordList.sort()  // 정렬
//        return wordList.indexOf(word) + 1  // 인덱스 + 1 반환
//    }
//}