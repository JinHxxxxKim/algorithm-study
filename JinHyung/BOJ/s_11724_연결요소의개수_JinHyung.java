package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [연결 요소의 개수] - BOJ
 * BFS
 *
 * 1. 연결리스트를 통해 그래프를 입력받는다.
 * 2. 1번 노드부터 BFS 탐색을 시작한다.
 * 3. BFS 탐색이 종료되면 연결 개수 카운팅을 증가시킨다.
 *
 */
public class s_11724_연결요소의개수_JinHyung {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static ArrayList<Integer>[] adList;
    static boolean[] isVisited;
    static int ans;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // node의 번호는 1번부터 시작
        adList = new ArrayList[N + 1];
        for (int idx = 1; idx <= N; idx++) {
            adList[idx] = new ArrayList<Integer>();
        }

        // 연결리스트 초기화
        for (int idx = 0; idx < M; ++idx) {
            st = new StringTokenizer(br.readLine().trim());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            
            adList[node1].add(node2);
            adList[node2].add(node1);
        }
        
        // 1번 node부터 탐색 시작
        ans = 0;
        isVisited = new boolean[N + 1];
        for(int node = 1; node <= N; node++) {
            if (isVisited[node]) {
                continue;
            }
            bfs(node);
            ++ans;
        }
        System.out.println(ans);
    }

    private static void bfs(int node) {
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(node);
        isVisited[node] = true;
        while (!q.isEmpty()) {
            int currNode = q.poll();
            for (Integer nextNode : adList[currNode]) {
                if (isVisited[nextNode]) {
                    continue;
                }
                isVisited[nextNode] = true;
                q.offer(nextNode);
            }
        }
    }
}
