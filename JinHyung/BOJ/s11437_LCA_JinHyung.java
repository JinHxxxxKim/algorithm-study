package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. 모든 노드에 대해 연결 리스트를 만든다.
 * 2. 루트 노드부터 모든 노드로 BFS를 진행하여 각 노드의 depth를 설정한다.
 * 3. LCA를 찾고자 하는 두 노드에 대해 입력을 받는다.
 * 4. 두 노드의 depth를 동일하게 맞춘다.
 * 5. depth가 동일한 두 노드로부터 부모 노드로 이동해가며 동일한지 확인한다.
 * 6. 동일하다면, 해당 노드가 LCA이므로 출력한다.
 */
public class s11437_LCA_JinHyung {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static int[] depth;
    static int[] parent;
    static ArrayList<Integer>[] adList;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        adList = new ArrayList[N + 1];
        // 인접 리스트 초기화
        for (int idx = 1; idx <= N; ++idx) {
            adList[idx] = new ArrayList<>();
        }
        // 인접 리스트 입력받기
        for (int edgeCnt = 0; edgeCnt < N - 1; ++edgeCnt) {
            st = new StringTokenizer(br.readLine().trim());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            // 부모 노드가 먼저 입력으로 주어진다는 말이 없으므로, 양방향 리스트로 생성
            adList[node2].add(node1);
            adList[node1].add(node2);
        }
        depth = new int[N + 1];
        parent = new int[N + 1];
        bfs();

        M = Integer.parseInt(br.readLine().trim());
        for (int testCase = 0; testCase < M; ++testCase) {
            st = new StringTokenizer(br.readLine().trim());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            sb.append(solution(node1, node2)).append("\n");
        }
        System.out.println(sb);
    }

    // root로부터 모든 노드까지의 parent, depth를 구하는 메소드
    private static void bfs() {
        boolean[] isVisited = new boolean[N + 1];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        isVisited[1] = true;
        parent[1] = -1;
        depth[1] = 0;
        while (!q.isEmpty()) {
            int currNode = q.poll();
            for (Integer nextNode : adList[currNode]) {
                if (isVisited[nextNode]) {
                    continue;
                }
                // parent, depth info 설정
                parent[nextNode] = currNode;
                depth[nextNode] = depth[currNode] + 1;
                q.offer(nextNode);
                isVisited[nextNode] = true;
            }
        }
    }

    // node1, node2의 LCA를 구하는 메소드
    private static int solution(int node1, int node2) {
        // 두 node의 depth 중 더 낮은(숫자가 큰) node의 depth를 다른 node와 맞춘다.
        int node1Depth = depth[node1];
        int node2Depth = depth[node2];
        // 두 노드 depth 맞추기
        if (node1Depth > node2Depth) {
            while (node1Depth > node2Depth) {
                node1 = parent[node1];
                node1Depth = depth[node1];
            }
        } else if (node1Depth < node2Depth) {
            while (node1Depth < node2Depth) {
                node2 = parent[node2];
                node2Depth = depth[node2];
            }
        }
        // ==> node1, node2 는 같은 depth의 높이인 node임
        // node1, node2 중 공통조상이 있다면 해당 노드 return
        if (node1 == node2) {
            return node1;
        }
        while (node1 != node2) {
            if (parent[node1] == parent[node2]) {
                return parent[node1];
            }
            node1 = parent[node1];
            node2 = parent[node2];
        }
        return -1;
    }
}
