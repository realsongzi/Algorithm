package kr.co.lion.algorithm.Lv1


// Greedy O(n)
class Oblong_Lv1 {
    fun solution(sizes: Array<IntArray>): Int {
        var maxWidth = 0
        var maxHeight = 0

        for (size in sizes) {
            val (w, h) = size.sortedDescending() // 큰 값을 항상 가로로 설정
            maxWidth = maxOf(maxWidth, w) // 가로 최댓값 갱신
            maxHeight = maxOf(maxHeight, h) // 세로 최댓값 갱신
        }

        return maxWidth * maxHeight // 최소 지갑 크기
    }
}

// 완전탐색 O(2^N)
//fun Solution(sizes: Array<IntArray>): Int {
//    var minArea = Int.MAX_VALUE
//
//    for (i in 0 until (1 shl sizes.size)) { // 모든 회전 조합을 탐색 (2^N)
//        var maxWidth = 0
//        var maxHeight = 0
//
//        for (j in sizes.indices) {
//            val (w, h) = sizes[j]
//            val (cw, ch) = if ((i and (1 shl j)) != 0) intArrayOf(h, w) else intArrayOf(w, h)
//
//            maxWidth = maxOf(maxWidth, cw)
//            maxHeight = maxOf(maxHeight, ch)
//        }
//
//        minArea = minOf(minArea, maxWidth * maxHeight)
//    }
//
//    return minArea
//}


// 정렬관련
// sort() : 원본 리스트 정렬 (오름차순)
// sorted() : 새로운 정렬된 리스트 반환(Immutable)
// sortedDescending() : 내림차순
// sortedBy() : 특정 조건으로 정렬