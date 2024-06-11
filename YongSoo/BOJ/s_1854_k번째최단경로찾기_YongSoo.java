package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 다익스트라 응용 문제
 * 기본 다익스트라 알고리즘을 그대로 따라가나, 탐색 조건이 약간 다르다.
 *
 * 밑에가 잘 이해되지 않을 경우, 함께 첨부한 SimpleDijkstra.java 파일과 비교하면 편합니다!
 * (템플릿을 동일하게 해 차이점이 잘 보이게 하였음)
 *
 * 차이점
 * - 여기서 사용하는 adjList와 heap은 일반 다익스트라 알고리즘에서 사용하는 것과 동일
 * - 여기서의 routeInfos가, 일반 다익스트라의 minDists와 대응된다고 생각하면 편함
 *   (여기서는 k개의 최단경로를 저장해야 하기 때문에, PriorityQueue 배열로 선언)
 *
 * 1. heap에서 경로를 하나 꺼낼 때, 해당 경로를 탐색할 필요가 있는지 확인
 *    - 일반 다익스트라의 경우, 해당 경로의 거리와 minDists를 비교. 해당 경로의 거리가 minDists[dest]보다 클 경우, 탐색하지 않았음
 *    - 이 경우, 해당 경로의 시간과 routeInfos를 비교.
 *      routeInfos[dest]가 이미 k개가 찼고, 해당 경로의 시간이 k번째 최단경로보다 오래 걸릴 경우, 탐색 불필요
 * 2. heap에서 꺼낸 경로로부터 새롭게 경로를 경신할 수 있는지 확인
 *    - 일반 다익스트라의 경우, 새롭게 경신된 경로의 거리와 minDists를 비교.
 *      새롭게 경신된 경로의 거리가 minDists[newDest]보다 작은 경우만, heap에 추가
 *    - 이 경우, 새롭게 경신된 경로의 거리와 routeInfos를 비교
 *      routeInfos[newDest]가 아직 k개가 차지 않은 경우 - 무조건 추가
 *      k개가 찬 경우 - k번째 최단경로의 시간과 해당 경로의 시간을 비교.
 *                   해당 경로의 시간이 더 짧은 경우에만, 기존 k번째 최단 경로 제거 & heap에 추가
 */

public class s_1854_k번째최단경로찾기_YongSoo {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int n, m, k;
    // 인접 리스트
    static ArrayList<Road>[] adjLists;

    // 일반 다익스트라 알고리즘에서의 minDists 배열 역할
    // routeInfos[dest]는, 1번 도시에서 {dest}번 도시로 가는 (최대) k개의 최단경로를 담고 있다.
    static PriorityQueue<Integer>[] routeInfos;

    static PriorityQueue<Road> heap;

    // 도로 정보를 나타내는 클래스
    // 목적지(dest)와 소요 시간(time)을 변수로 가짐
    static class Road implements Comparable<Road> {
        int dest;
        int time;

        public Road(int dest, int time) {
            this.dest = dest;
            this.time = time;
        }

        @Override
        public int compareTo(Road o) {
            return -Integer.compare(this.time, o.time);
        }
    }

    public static void main(String[] args) throws IOException {

        // 1. 변수 초기화
        setInitVariable();

        // 2. 다익스트라 초기값 세팅
        // 1번 도시에서 1번 도시로 가는 최단경로는 0이다.
        routeInfos[1].add(0);
        heap.offer(new Road(1, 0));

        // 3. 다익스트라 탐색
        while (!heap.isEmpty()) {
            Road road = heap.poll();

            int dest = road.dest;
            int time = road.time;

            PriorityQueue<Integer> routeInfo = routeInfos[dest];

            // 현재 경로가 k번째 최단경로보다 소요시간이 더 클 경우 - 더 이상의 탐색 불필요
            if (routeInfo.size() == k && routeInfo.peek() < time) continue;

            // 해당 dest로부터 다른 목적지로 이동하는 경우를 탐색
            ArrayList<Road> adjList = adjLists[dest];

            for (Road nextRoad: adjList) {
                int newDest = nextRoad.dest;
                int newTime = nextRoad.time;

                // nextRoad를 이용하면 1번 ~ newDest까지 time + newTime만큼의 시간으로 갈 수 있다.
                // 이 경로가 k번째 최단경로 안에 속하는지 탐색
                PriorityQueue<Integer> newRouteInfo = routeInfos[newDest];

                // 탐색이 불필요한 경우 : 이미 newDest로 가는 k개의 최단경로가 존재하고, 이 경로가 최단 경로를 갱신할 수 없는 경우
                if (newRouteInfo.size() == k && newRouteInfo.peek() <= time + newTime) continue;

                // k개의 최단경로가 존재하는 경우
                // 가장 긴 경로는 더 이상 속하지 않으므로, 제거해 주면 된다
                if (newRouteInfo.size() == k) {
                    newRouteInfo.poll();
                }
                // 새로운 최단경로 삽입
                newRouteInfo.offer(time+newTime);
                // heap에 추가
                heap.offer(new Road(newDest, time+newTime));
            }
        }

        for (int id=1; id<=n; id++) {
            PriorityQueue<Integer> routeInfo = routeInfos[id];
            int kthMinDist = routeInfo.size()==k? routeInfo.poll(): -1;
            sb.append(kthMinDist).append("\n");
        }

        System.out.println(sb);

    }


    // 문제에서 주어지는 변수 초기화
    private static void setInitVariable() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine().trim());
        sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // 인접리스트(adjLists) 초기화
        adjLists = new ArrayList[n+1];
        for (int id=1; id<=n; id++) {
            adjLists[id] = new ArrayList<>();
        }

        // 인접리스트 값 세팅
        for (int idx=0; idx<m; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int origin = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            adjLists[origin].add(new Road(dest, time));
        }

        routeInfos = new PriorityQueue[n+1];

        // routeInfos는, 시간이 오래 걸리는 순으로 정렬됨
        for (int id=1; id<=n; id++) {
            routeInfos[id] = new PriorityQueue<>(Collections.reverseOrder());
        }

        // road의 time을 기준으로 heap을 정렬
        heap = new PriorityQueue<>(Comparator.comparingInt(r -> r.time));
    }
}