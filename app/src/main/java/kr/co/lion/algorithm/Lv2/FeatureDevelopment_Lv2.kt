package kr.co.lion.algorithm.Lv2

import kotlin.math.ceil

// 문제 설명
// 프로그래머스 팀에서는 기능 개선 작업을 수행 중입니다.
// 각 기능은 진도가 100%일 때 서비스에 반영할 수 있습니다.
// 또, 각 기능의 개발속도는 모두 다르기 때문에 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고,
// 이때 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포됩니다.
// 먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses와
// 각 작업의 개발 속도가 적힌 정수 배열 speeds가 주어질 때 각 배포마다 몇 개의 기능이 배포되는지를
// return 하도록 solution 함수를 완성하세요.

fun solution(progresses: IntArray, speeds: IntArray): IntArray {
    val days = progresses.mapIndexed { index, progress -> // mapIndexed() 함수 -> 배열을 순회하면서 각 요소의 Index와 값을 가져와서 변환할 수 있는 함수
        ceil((100.0 - progress) / speeds[index]).toInt()
    }

    val result = mutableListOf<Int>()
    var currentMaxDay = days[0]
    var count = 0

    // days 배열을 처음부터 순서대로 확인!
    // 만약 day가 현재 배포할 수 있는 최대 날짜보다 크다면 새로운 배포 그룹이 필요하므로 지금까지 센 개수를(count) result에 저장한다.
    // count를 1로 초기화 (새로운 배포 시작), currentMaxDay를 현재 기능의 완료 날짜로 업데이트 한다.
    for (day in days) {
        if (day > currentMaxDay) {
            result.add(count)
            count = 1
            currentMaxDay = day
        } else {
            // 현재 기능이 앞에 있는 기능과 같은 날 배포될 수 있다면 count++를 해서 배포될 기능 개수를 증가시킨다.
            count++
        }
    }
    result.add(count) // 마지막 배포 그룹은 for문이 끝나도 추가되지 않으므로 마지막으로 한 번 더 추가한다.

    return result.toIntArray() // result는 MutableList<Int> 이므로, 이를 IntArray로 변환해야 문제의 요구사항에 만족한다.
}

// mapIndexed() + ceil() 을 활용하여 각 기능이 완료되는 날짜를 계산
// for문을 활용하여 배포될 기능을 묶는다.
// mutableListOf()를 사용하여 결과를 저장 후 toIntArray()로 변환
