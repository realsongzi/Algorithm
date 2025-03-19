package kr.co.lion.algorithm.Lv2

// 완전탐색
class FindPrimeNumber_Lv2 {
    fun solution(numbers: String): Int {
        val numSet = (1..numbers.length) // 1부터 numbers 길이까지
            .flatMap { numbers.toList().permutations(it) } // 각 길이의 순열 생성
            .map { it.joinToString("").toInt() } // 숫자로 변환
            .toSet() // 중복 제거

        return numSet.count { isPrime(it) }
    }

    // 최적화된 소수 판별 함수
    private fun isPrime(n: Int): Boolean {
        if (n < 2) return false
        if (n == 2 || n == 3) return true
        if (n % 2 == 0 || n % 3 == 0) return false
        return (5..Math.sqrt(n.toDouble()).toInt() step 6).none { n % it == 0 || n % (it + 2) == 0 }
    }

    // 순열 생성 확장 함수
    private fun <T> List<T>.permutations(length: Int): Set<List<T>> =
        if (length == 1) this.map { listOf(it) }.toSet()
        else this.flatMap { elem -> (this - elem).permutations(length - 1).map { listOf(elem) + it } }.toSet()
}

// flatMap {} -> 리스트의 각 요소를 변환 후, 여러 리스트를 하나의 리스트로 평탄화 하는 함수
// 중첩된 리스트를 단일 리스트로 변환
// permutations() -> 리스트의 모든 가능한 순열(순서가 다른 조합)을 생성하는 함수
// joinToString("") -> 리스트의 모든 요소를 하나의 문자열로 합쳐주는 함수
// toSet() -> 리스트에서 중복된 요소를 제거하고 집합으로 변환하는 함수
// sqrt() -> n의 제곱근(√n)을 구하는 함수