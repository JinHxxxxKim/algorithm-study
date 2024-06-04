package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Dijkstra
 * 1. 1번 정점(시작 노드)에서 Dijkstra 알고리즘을 실행한다.
 *   1-1. 각 정점을 방문할 때마다 해당 점점의 최소 거리 리스트에 cost를 추가한다.
 *   1-2. 만일 이미 k번째 최단 경로를 모두 탐색했다면 PASS
 *   1-3. 현재 노드의 인접 노드를 탐색한다.
 *     1-3-1. 인접 노드가 k번째 최단 경로를 모두 탐색했다면 PASS
 *     1-3-2. 아니라면 pq에 삽입
 */
public class s1854_k번째최단경로_JinHyung {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static ArrayList<Edge>[] adList;
    static ArrayList<Integer>[] distList;
    static int n, m, k;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // 인접 리스트, cost(dist)리스트 생성 및 초기화
        adList = new ArrayList[n + 1];
        distList = new ArrayList[n + 1];
        for (int nodeNum = 1; nodeNum < n + 1; ++nodeNum) {
            adList[nodeNum] = new ArrayList<>();
            distList[nodeNum] = new ArrayList<>();
        }
        for (int edgeNum = 0; edgeNum < m; ++edgeNum) {
            st = new StringTokenizer(br.readLine().trim());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adList[node1].add(new Edge(cost, node2));
        }

        // Dijkstra Algorithm
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(0, 1)); // 시작점 삽입
        while (!pq.isEmpty()) {
            Edge currNode = pq.poll();
            // 만일 현재 Node가 k번째 최단 경로를 구했다면 PASS
            if (distList[currNode.nodeNum].size() >= k) {
                continue;
            }
            // 현재 Node가 k번째 최단 경로를 구하지 못했다면, 경로 비용 추가
            distList[currNode.nodeNum].add(currNode.cost);

            // 인접 노드 탐색
            for (Edge nextNode : adList[currNode.nodeNum]) {
                // 최적화 1: 인접 노드가 k번째 최단 경로를 모두 찾았다면 PASS
                // => 해당 노드를 거쳐가는 경로는 반드시 k번째 이상이다 (양의 가중치)
                if (distList[nextNode.nodeNum].size() >= k) {
                    continue;
                }
                // 비용 갱신 후, qp에 삽입
                int cost = currNode.cost + nextNode.cost;
                pq.offer(new Edge(cost, nextNode.nodeNum));
            }
        }

        // 출력
        for (int nodeNum = 1; nodeNum < n + 1; ++nodeNum) {
            // k번째 최단 경로가 존재하지 않는다.
            if (distList[nodeNum].size() < k) {
                sb.append(-1).append("\n");
            } else {
                sb.append(distList[nodeNum].get(k - 1)).append("\n");
            }
        }
        System.out.println(sb);
    }

    static class Edge implements Comparable<Edge>{
        int cost;
        int nodeNum;

        public Edge(int cost, int nodeNum) {
            this.cost = cost;
            this.nodeNum = nodeNum;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
