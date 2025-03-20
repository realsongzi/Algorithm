package kr.co.lion.algorithm.Lv3
import java.util.*

//문제 설명
//주어진 항공권을 모두 이용하여 여행경로를 짜려고 합니다. 항상 "ICN" 공항에서 출발합니다.
//
//항공권 정보가 담긴 2차원 배열 tickets가 매개변수로 주어질 때, 방문하는 공항 경로를 배열에 담아 return 하도록 solution 함수를 작성해주세요.
//
//제한사항
//모든 공항은 알파벳 대문자 3글자로 이루어집니다.
//주어진 공항 수는 3개 이상 10,000개 이하입니다.
//tickets의 각 행 [a, b]는 a 공항에서 b 공항으로 가는 항공권이 있다는 의미입니다.
//주어진 항공권은 모두 사용해야 합니다.
//만일 가능한 경로가 2개 이상일 경우 알파벳 순서가 앞서는 경로를 return 합니다.
//모든 도시를 방문할 수 없는 경우는 주어지지 않습니다.
//입출력 예
//tickets	return
//[["ICN", "JFK"], ["HND", "IAD"], ["JFK", "HND"]]	["ICN", "JFK", "HND", "IAD"]
//[["ICN", "SFO"], ["ICN", "ATL"], ["SFO", "ATL"], ["ATL", "ICN"], ["ATL","SFO"]]	["ICN", "ATL", "ICN", "SFO", "ATL", "SFO"]
//입출력 예 설명
//예제 #1
//
//["ICN", "JFK", "HND", "IAD"] 순으로 방문할 수 있습니다.
//
//예제 #2
//
//["ICN", "SFO", "ATL", "ICN", "ATL", "SFO"] 순으로 방문할 수도 있지만 ["ICN", "ATL", "ICN", "SFO", "ATL", "SFO"] 가 알파벳 순으로 앞섭니다.

// DFS + 백트래킹 , 유레리안 경로 (Eulerian Path)
class AirRoute_Lv3 {
    fun solution(tickets: Array<Array<String>>): Array<String>{
        val graph = mutableMapOf<String, PriorityQueue<String>>() // 그래프데이터의 구조를 선언 Key = 출발 공항, Value = 도착 공항 리스트 / 여러 개의 경로가 있을 때 알파벳순으로 먼저 탐색하도록 PriorityQueue를 사용

        // 그래프 초기화 (항공권 정보 저장)
        for ((from, to) in tickets) {
            // from 공항이 graph에 없으면, 새로운 PriorityQueue를 생성
            if (!graph.containsKey(from)) {
                graph[from] = PriorityQueue()
            }
            graph[from]!!.add((to)) }

            val route = mutableListOf<String>()

            fun dfs(airport: String){
                val destinations = graph[airport]
                while (destinations != null && destinations.isNotEmpty()) {
                    dfs(destinations.poll())
                }
                route.add(airport)
            }
        dfs("ICN")
        return route.reversed().toTypedArray() // 후위 순회를 사용했기 때문에 경로가 역순이라 reversed()를 사용해 정방향으로 변경, 타입 매칭
    }
}


// 후위 순회를 사용한 이유 : 모든 항공권을 사용한 후 경로를 저장해야 하므로 단순히 경로를 저장하는 방식으로는 해결할 수 없기에 DFS를 이용해 가능한 곳을 모두 방문한 후, 더 이상 이동할 경로가 없을 때 경로를 기록해야 한다.
