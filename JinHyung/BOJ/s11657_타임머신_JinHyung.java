package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [타임머신] - BOJ
 * 벨만-포드
 * 1번 노드에서 특정 노드까지의 최단 경로는, 최대 N-1개의 정점을 지날 수 있다.
 *
 * 1. 간선 정보 저장
 * 2. 거리 초기화: 출발지 = 0, 나머지 = MAX_VAL
 * 3. 총 N번(노드의 수)의 반복 -> N회차에 dist가 갱신될 경우, 음의 사이클이 존재
 *   3-1. 만일 현재 방문할 수 없는 정점(= nodeCnt개의 edge로는 방문할 수 었는 정점)
 *   3-2. 만일 현재 방문할 수 있는 정점(= nodeCnt개의 edge이내로 방문할 수 었는 정점)
 *     > 해당 정점 최단거리 갱신
 * 4. 출력
 */
public class s11657_타임머신_JinHyung {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static Edge[] edgeList; // 간선 리스트
    static long[] dist; // 1번 도시부터의 최단거리 저장 배열
    // dist배열의 자료형이 long인 것에 대하여:
    // ex) N = 500, M = 6,000 모든 간선의 가중치가 -10,000일 경우 + 사이클의 길이=6,000
    //     1회 탐색 시: 가장 작은 값 = -60,000,000 > N회 탐색 시 대략 -30억이 되어 Integer의 경우 Underflow가 발생한다.

    static int N, M;
    static boolean isMinusCycle; // 음의 사이클 flag

    // 간선 클래스
    static class Edge {
        int srcNode;
        int destNode;
        int cost;

        public Edge(int srcNode, int destNode, int cost) {
            this.srcNode = srcNode;
            this.destNode = destNode;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 간선 정보 저장
        edgeList = new Edge[M];
        for (int idx = 0; idx < M; ++idx) {
            st = new StringTokenizer(br.readLine().trim());
            int srcNode = Integer.parseInt(st.nextToken());
            int destNode = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edgeList[idx] = new Edge(srcNode, destNode, cost);
        }

        // 거리 초기화: 출발지 = 0, 나머지 = MAX_VAL
        dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;

        // 1번 노드에서 특정 노드까지의 최단 경로는, 최대 N-1개의 정점을 지날 수 있다.
        // 총 N번(노드의 수)의 반복 -> N회차에 dist가 갱신될 경우, 음의 사이클이 존재
        for (int nodeCnt = 0; nodeCnt <= N; ++nodeCnt) {
            for (Edge edge : edgeList) {
                int srcNode = edge.srcNode;
                int destNode = edge.destNode;
                int cost = edge.cost;
                
                // 1. 만일 현재 방문할 수 없는 정점(= nodeCnt개의 edge로는 방문할 수 었는 정점)
                if (dist[srcNode] == Long.MAX_VALUE) {
                    continue;
                }

                // 2. 만일 현재 방문할 수 있는 정점(= nodeCnt개의 edge이내로 방문할 수 었는 정점)
                if (dist[srcNode] + cost < dist[destNode]) {
                    // 목적 정점의 최단 경로가 갱신될 수 있는지 확인
                    if (nodeCnt == N) {
                        // N-1 번의 탐색 이후에도 갱신될 경우(= 음의 사이클이 존재)
                        isMinusCycle = true;
                    } else {
                        // 갱신
                        dist[destNode] = dist[srcNode] + cost;
                    }
                }
            }
        }

        if (isMinusCycle) {
            // 음의 사이클이 존재할 경우
            System.out.println(-1);
            return; 
        }
        // 출력
        for (int node = 2; node <= N; ++node) {
            if (dist[node] == Long.MAX_VALUE) {
                sb.append(-1).append("\n");
            } else {
                sb.append(dist[node]).append("\n");
            }
        }
        System.out.println(sb);
    }
}
