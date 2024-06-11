package BOJ;

/**
 * DFS(혹은 BFS) 탐색을 한 번 수행할 경우, 모든 연결 요소들을 방문하게 된다.
 * 방문 배열로 한 번 방문한 노드들은 방문하지 않도록 하며, DFS 탐색으로 연결 요소의 개수를 구할 수 있다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class s_11724_연결요소의개수_YongSoo {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static ArrayList<Integer>[] edgeLists;
    static boolean[] visited;
    static int answer;

    public static void main(String[] args) throws IOException {
        // 1. 변수 초기화
        setInitVariable();

        // 2. DFS 탐색
        visited = new boolean[N+1];
        for (int id=1; id<=N; id++) {
            if (!visited[id]) {
                answer++;
                dfs(id);
            }
        }

        // 3. 정답 출력
        System.out.println(answer);

    }

    private static void dfs(int id) {
        visited[id] = true;
        for (int nextId: edgeLists[id]) {
            if (!visited[nextId]) {
                dfs(nextId);
            }
        }
    }

    private static void setInitVariable() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edgeLists = new ArrayList[N+1];
        for (int id=1; id<=N; id++) {
            edgeLists[id] = new ArrayList<>();
        }

        for (int cnt=0; cnt<M; cnt++) {
            st = new StringTokenizer(br.readLine().trim());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            edgeLists[u].add(v);
            edgeLists[v].add(u);
        }

        answer = 0;
    }
}
