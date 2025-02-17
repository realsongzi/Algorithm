package kr.co.lion.algorithm

//1 ~ n의 번호가 있는 택배 상자가 창고에 있습니다. 당신은 택배 상자들을 다음과 같이 정리했습니다.
//
//왼쪽에서 오른쪽으로 가면서 1번 상자부터 번호 순서대로 택배 상자를 한 개씩 놓습니다.
// 가로로 택배 상자를 w개 놓았다면 이번에는 오른쪽에서 왼쪽으로 가면서 그 위층에 택배 상자를 한 개씩 놓습니다.
// 그 층에 상자를 w개 놓아 가장 왼쪽으로 돌아왔다면 또다시 왼쪽에서 오른쪽으로 가면서 그 위층에 상자를 놓습니다.
// 이러한 방식으로 n개의 택배 상자를 모두 놓을 때까지 한 층에 w개씩 상자를 쌓습니다.
//
//ex1-1.png
//
//위 그림은 w = 6일 때 택배 상자 22개를 쌓은 예시입니다.
//다음 날 손님은 자신의 택배를 찾으러 창고에 왔습니다.
// 당신은 손님이 자신의 택배 상자 번호를 말하면 해당 택배 상자를 꺼내줍니다.
// 택배 상자 A를 꺼내려면 먼저 A 위에 있는 다른 모든 상자를 꺼내야 A를 꺼낼 수 있습니다.
// 예를 들어, 위 그림에서 8번 상자를 꺼내려면 먼저 20번, 17번 상자를 꺼내야 합니다.
//
//당신은 꺼내려는 상자 번호가 주어졌을 때, 꺼내려는 상자를 포함해 총 몇 개의 택배 상자를 꺼내야 하는지 알고 싶습니다.
//창고에 있는 택배 상자의 개수를 나타내는 정수 n,
// 가로로 놓는 상자의 개수를 나타내는 정수 w와 꺼내려는 택배 상자의 번호를 나타내는 정수 num이 매개변수로 주어집니다.
// 이때, 꺼내야 하는 상자의 총개수를 return 하도록 solution 함수를 완성해 주세요.


fun solution(n: Int, w: Int, num: Int): Int {
    // 전체 층 수
    val totalFloors = (n - 1) / w + 1
    // num이 위치한 층
    val floorNum = (num - 1) / w + 1

    // 그 층의 시작 박스 번호 (가득 찬 층이면 w개, 마지막 층이면 leftover개)
    val floorStart = (floorNum - 1) * w + 1

    // num이 그 층에서 몇 번째(0-based)인지
    val idxInFloor = num - floorStart

    // num의 물리적 열(col) 구하기
    // 홀수층이면 왼->오, 짝수층이면 오른->왼
    val colOfNum = if (floorNum % 2 == 1) {
        // 왼->오
        idxInFloor + 1
    } else {
        // 오른->왼
        w - idxInFloor
    }

    var countAbove = 0

    // 위에 있는 층들을 순회
    for (f in floorNum+1..totalFloors) {
        val floorStartF = (f - 1) * w + 1    // 해당 층의 박스 시작 번호
        // 이 층에 실제로 몇 개의 박스가 있는가?
        // (마지막 층이면 leftover, 아니면 w개)
        val boxesInThisFloor = if (f < totalFloors) w else (n - (totalFloors - 1) * w)

        // 물리적으로 colOfNum에 해당하는 "박스 index(0-based)"를 역산
        val idx = if (f % 2 == 1) {
            // 홀수층(왼->오): col = idx + 1 -> idx = col - 1
            colOfNum - 1
        } else {
            // 짝수층(오른->왼): col = w - idx -> idx = w - col
            // 그러나 만약 마지막 층이 leftover < w일 수도 있으니
            // 한꺼번에 "항상 w칸"이라고 가정하고 col을 치환하면
            // idx = w - colOfNum
            w - colOfNum
        }

        // idx가 이 층에서 실제로 박스가 존재하는 범위(0 <= idx < boxesInThisFloor)인지 확인
        if (idx in 0 until boxesInThisFloor) {
            // 실제 박스 번호 j
            val boxAbove = floorStartF + idx
            // 유효 범위인지 (그래도 안전하게)
            if (boxAbove <= n) {
                countAbove++
            }
        }
    }

    // 위 박스 count + 자기 자신
    return countAbove + 1
}

// 간단 확인용 main
fun main() {
    println(solution(22, 6, 8))   // 예시 => 3 이어야 함
    println(solution(22, 6, 17))  // 그림에서 17 위에는 20만 있으므로 => 2
    println(solution(22, 6, 20))  // 맨 위층은 아무도 없으므로 => 1
}