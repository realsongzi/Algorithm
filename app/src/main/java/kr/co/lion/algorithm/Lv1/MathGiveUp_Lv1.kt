package kr.co.lion.algorithm.Lv1

// 완전탐색

class MathGiveUp_Lv1 {
    fun solution(answers: IntArray): IntArray {
        val patterns = arrayOf(
            intArrayOf(1, 2, 3, 4, 5),
            intArrayOf(2, 1, 2, 3, 2, 4, 2, 5),
            intArrayOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5)
        )

        // 패턴과 정답을 비교하여 각 수포자의 점수를 계산
        val scores = patterns.map { pattern ->
            answers.indices.count { i -> answers[i] == pattern[i % pattern.size] }
        }

        // 최고 점수 계산
        val maxScore = scores.maxOrNull() ?: 0

        // 최고 점수를 받은 수포자 번호 필터링 후 반환
        return scores.mapIndexedNotNull { index, score -> if (score == maxScore) index + 1 else null }
            .toIntArray()
    }
}