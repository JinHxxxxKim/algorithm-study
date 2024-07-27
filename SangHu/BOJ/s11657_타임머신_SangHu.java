package algorithm_study.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [ init ]
 * 1. 정점의 개수, 간선의 개수 입력
 * 2. 간선 정보 입력
 * 3. 최소 가중치를 저장할 배열 distance 초기화
 *  3-1. 출발 정점 1을 제외하고 INF 로 초기화
 *  3-2. 음의 싸이클 반복 시 최대 -(500 * 6000 * 10000 = 300억) 이므로 long 형
 *      -- 6000 = 500 * 12(싸이클)
 *
 * [ BellmanFord ]
 * 4. 정점의 개수-1 만큼 반복하며 간선 업데이트
 *  4-1. 현재 간선의 출발 정점까지의 거리가 업데이트 되지 않았다면 갱신 x
 *  4-2. to 정점의 현재 가중치와 from 정점을 지나 to 정점으로 가는 가중치 비교
 *      4-2-1. 더 작다면 to 정점까지의 가중치 업데이트
 * 5. 간선의 정보를 한 번 더 검사
 *  5-1. 가중치 업데이트가 일어난다면 음의 싸이클이 존재
 * 6. 최종 업데이트된 가중치 출력
 *  6-1. 업데이트 된 적 없다면 -1 출력
 */
public class s11657_타임머신_SangHu {
    static class Edge {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int INF = Integer.MAX_VALUE;

    static int nodeCount, edgeCount;
    static long[] dist;

    static ArrayList<Edge> graph;

    public static void BellmanFord(int start) {
        // 4. 정점의 개수-1 만큼 반복하며 간선 업데이트
        for (int count = 0; count < nodeCount-1; count++) {
            for (Edge edge : graph) {
                // 4-1. 현재 간선의 출발 정점까지의 거리가 업데이트 되지 않았다면 갱신 x
                // 4-2. to 정점의 현재 가중치와 from 정점을 지나 to 정점으로 가는 가중치 비교
                if (dist[edge.from] != INF && dist[edge.to] > dist[edge.from] + edge.cost) {
                    // 4-2-1. 더 작다면 to 정점까지의 가중치 업데이트
                    dist[edge.to] = dist[edge.from] + edge.cost;
                }
            }
        }

        // 5. 간선의 정보를 한 번 더 검사
        for (Edge edge : graph) {
            // 5-1. 가중치 업데이트가 일어난다면 음의 싸이클이 존재
            if (dist[edge.from] != INF && dist[edge.to] > dist[edge.from] + edge.cost) {
                System.out.println(-1);
                return;
            }
        }

        // 6. 최종 업데이트된 가중치 출력
        for (int idx = start+1; idx <= nodeCount; idx++) {
            // 6-1. 업데이트 된 적 없다면 -1 출력
            if (dist[idx] == INF)
                sb.append(-1).append("\n");
            else
                sb.append(dist[idx]).append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        init();

        BellmanFord(1);
    }

    public static void init() throws IOException {
        // 1. 정점의 개수, 간선의 개수 입력
        st = new StringTokenizer(br.readLine().trim());
        nodeCount = Integer.parseInt(st.nextToken());
        edgeCount = Integer.parseInt(st.nextToken());

        // 2. 간선 정보 입력
        graph = new ArrayList<>();
        for (int edge = 0; edge < edgeCount; edge++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.add(new Edge(from, to, cost));
        }

        // 3. 최소 가중치를 저장할 배열 distance 초기화
        dist = new long[nodeCount+1];

        // 3-1. 출발 정점 1을 제외하고 INF 로 초기
        Arrays.fill(dist, INF);
        dist[1] = 0;
    }
}
