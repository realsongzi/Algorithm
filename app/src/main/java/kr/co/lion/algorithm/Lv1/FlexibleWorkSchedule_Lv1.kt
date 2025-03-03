package kr.co.lion.algorithm.Lv1

//문제 설명
// 프로그래머스 사이트를 운영하는 그렙에서는 재택근무와 함께 출근 희망 시각을 자유롭게 정하는 유연근무제를 시행하고 있습니다.
// 제도 정착을 위해 오늘부터 일주일 동안 각자 설정한 출근 희망 시각에 늦지 않고 출근한 직원들에게 상품을 주는 이벤트를 진행하려고 합니다.
// 직원들은 일주일동안 자신이 설정한 출근 희망 시각 + 10분까지 어플로 출근해야 합니다.
// 예를 들어 출근 희망 시각이 9시 58분인 직원은 10시 8분까지 출근해야 합니다.
// 단, 토요일, 일요일의 출근 시각은 이벤트에 영향을 끼치지 않습니다.
// 직원들은 매일 한 번씩만 어플로 출근하고, 모든 시각은 시에 100을 곱하고 분을 더한 정수로 표현됩니다.
// 예를 들어 10시 13분은 1013이 되고 9시 58분은 958이 됩니다.
// 당신은 직원들이 설정한 출근 희망 시각과 실제로 출근한 기록을 바탕으로 상품을 받을 직원이 몇 명인지 알고 싶습니다.
// 직원 n명이 설정한 출근 희망 시각을 담은 1차원 정수 배열 schedules, 직원들이 일주일 동안 출근한 시각을 담은 2차원 정수 배열 timelogs,
// 이벤트를 시작한 요일을 의미하는 정수 startday가 매개변수로 주어집니다.
// 이때 상품을 받을 직원의 수를 return 하도록 solution 함수를 완성해주세요.

// 풀이 함수: 조건에 맞는 직원 수를 반환합니다.
fun solution(schedules: IntArray, timelogs: Array<IntArray>, startday: Int): Int {
    // 1. 이벤트 기간 중 평일(월~금)에 해당하는 날짜의 인덱스를 미리 계산
    val workingDays = mutableListOf<Int>()
    for (j in 0 until 7) {
        val dayOfWeek = (startday - 1 + j) % 7 + 1
        if (dayOfWeek in 1..5) { // 월~금이면
            workingDays.add(j)
        }
    }

    var count = 0
    // 2. 각 직원에 대해
    for (i in schedules.indices) {
        // 출근 희망 시각을 분 단위로 변환 후 10분 추가
        val scheduleHour = schedules[i] / 100
        val scheduleMinute = schedules[i] % 100
        val allowedMinutes = scheduleHour * 60 + scheduleMinute + 10

        var qualifies = true
        // 3. 미리 계산한 평일 인덱스만 순회하며 검사
        for (j in workingDays) {
            val log = timelogs[i][j]
            val logHour = log / 100
            val logMinute = log % 100
            val logMinutes = logHour * 60 + logMinute

            // 허용 시간보다 늦으면 불합격
            if (logMinutes > allowedMinutes) {
                qualifies = false
                break
            }
        }
        if (qualifies) count++
    }
    return count
}

// --- 테스트 코드 예시 ---
fun main() {
    // Test 1: startday = 1 (월요일 시작)
    // 두 직원 모두 평일 모든 날에 정해진 시간 내에 출근한 경우.
    val schedules1 = intArrayOf(958, 1012)
    val timelogs1 = arrayOf(
        intArrayOf(958, 958, 958, 958, 958, 1000, 1000),
        intArrayOf(1012, 1020, 1012, 1012, 1012, 1012, 1012)
    )
    val result1 = solution(schedules1, timelogs1, 1)
    println("Test 1 - 예상 결과: 2, 실제 결과: $result1")

    // Test 2: startday = 1 (월요일 시작)
    // 두 직원 모두 평일 중 하루라도 정해진 허용 시간보다 늦게 출근한 경우 -> 상품 대상 아님.
    val schedules2 = intArrayOf(958, 1012)
    val timelogs2 = arrayOf(
        intArrayOf(958, 958, 959, 1009, 958, 1000, 1000),  // 9:59 또는 10:09가 허용 시간(10:08)을 초과할 수 있음.
        intArrayOf(1012, 1020, 1012, 1030, 1012, 1012, 1012) // 10:30 > 10:22 (1012+10분)
    )
    val result2 = solution(schedules2, timelogs2, 1)
    println("Test 2 - 예상 결과: 0, 실제 결과: $result2")

    // Test 3: startday = 4 (목요일 시작)
    // 주의: 이번 주의 평일은 인덱스 0(목), 1(금), 4(월), 5(화), 6(수)
    val schedules3 = intArrayOf(930)
    val timelogs3 = arrayOf(
        intArrayOf(930, 935, 945, 930, 930, 930, 930)
    )
    val result3 = solution(schedules3, timelogs3, 4)
    println("Test 3 - 예상 결과: 1, 실제 결과: $result3")
}