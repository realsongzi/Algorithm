package kr.co.lion.algorithm

fun solution(n: Int, w: Int, num: Int): Int {
    // 1) num이 속한 층 구하기
    val floorNum = (num - 1) / w + 1
    val totalFloors = (n - 1) / w + 1

    // 2) 그 층에서의 offset(0-based)
    val offset = num - (floorNum - 1)*w - 1

    // 마지막 층에 남은 박스 수
    val leftover = n - (totalFloors - 1)*w

    // 3) num이 있는 층의 실제 박스 수
    val widthFloorNum = if (floorNum < totalFloors) w else leftover

    // 4) num의 열(왼->오에서 몇 번째) 구하기
    val colNum = if (floorNum % 2 == 1) {
        // 홀수층: 순방향
        offset + 1
    } else {
        // 짝수층: 역방향
        widthFloorNum - offset
    }

    // 5) 위층들 순회하며, 해당 colNum에 박스가 있는지 확인
    var countAbove = 0
    for (aboveFloor in (floorNum+1)..totalFloors) {
        // 이 위층의 실제 박스 수
        val widthAbove = if (aboveFloor < totalFloors) w else leftover

        // 왼->오로 보았을 때 colNum 위치에 박스가 존재해야 함
        if (colNum <= widthAbove) {
            // 존재
            countAbove++
        }
    }

    // 6) 위 박스 + 자기 자신
    return countAbove + 1
}

// 간단 테스트
fun main() {
    println(solution(22, 6, 8))   // 예시: 8번 꺼낼 때?
    println(solution(22, 6, 17))  // 17번 꺼낼 때?
    println(solution(22, 6, 20))  // 20번 꺼낼 때?
}