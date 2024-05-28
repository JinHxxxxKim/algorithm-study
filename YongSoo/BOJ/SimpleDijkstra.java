import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SimpleDijkstra {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int n, m, k;
    // 인접 리스트
    static ArrayList<Road>[] adjLists;

    // 일반 다익스트라 알고리즘 : minDists 배열 이용
    static int[] minDists;

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
        minDists[1] = 0;
        heap.offer(new Road(1, 0));

        // 3. 다익스트라 탐색
        while (!heap.isEmpty()) {
            Road road = heap.poll();

            int dest = road.dest;
            int time = road.time;

            int minDist = minDists[dest];

            // 현재 경로가 최단경로보다 소요시간이 더 클 경우 - 더 이상의 탐색 불필요
            if (time > minDist) continue;

            // 해당 dest로부터 다른 목적지로 이동하는 경우를 탐색
            ArrayList<Road> adjList = adjLists[dest];

            for (Road nextRoad: adjList) {
                int newDest = nextRoad.dest;
                int newTime = nextRoad.time;

                // nextRoad를 이용하면 1번 ~ newDest까지 time + newTime만큼의 시간으로 갈 수 있다.
                // 이 경로가 최단경로인지 탐색
                int newMinDist = minDists[newDest];

                // 탐색이 불필요한 경우 : 이 경로가 최단 경로를 갱신할 수 없는 경우
                if (time+newTime >= newMinDist) continue;

                // minDists 업데이트
                minDists[newDest] = time+newTime;

                // heap에 추가
                heap.offer(new Road(newDest, time+newTime));
            }
        }

        for (int id=1; id<=n; id++) {
            int minDist = minDists[id] == Integer.MAX_VALUE? -1: minDists[id];
            sb.append(minDist).append("\n");
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

        minDists = new int[n+1];

        // minDists를 Integer.MAX_VALUE로 초기화
        for (int id=1; id<=n; id++) {
            minDists[id] = Integer.MAX_VALUE;
        }

        // road의 time을 기준으로 heap을 정렬
        heap = new PriorityQueue<>(Comparator.comparingInt(r -> r.time));
    }
}