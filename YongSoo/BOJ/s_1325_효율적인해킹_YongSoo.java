package BOJ;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * BFS 연습문제
 *
 * 시간초과 날 확률 높음... 조심할 것
 * 신뢰하는 컴퓨터 간에 사이클이 존재할 수 있기 때문에, 모든 컴퓨터에 대해 한 번씩 BFS를 다 돌려 봐야 한다.
 *
 * 1. 변수 초기화
 * 2. 모든 컴퓨터에 대해 BFS를 수행하며, 해당 컴퓨터가 처음 감염되었을 때 감염되는 컴퓨터의 총 수(hackingNums[id])를 업데이트
 *    이를 이용해 hackingNums의 최댓값 구하기
 * 3. 한 번 더 hackingNums를 탐색하며, hackingNums[id] == answer인 id 모두 구하기
 */

public class s_1325_효율적인해킹_YongSoo {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static ArrayList<Integer>[] adjLists;
    static int[] hackingNums;
    static int answer;

    public static void main(String[] args) throws IOException {

        // 1. 변수 초기화
        setInitVariable();

        // 2. BFS를 탐색하며, 해당 컴퓨터가 처음 감염되었을 때 감염되는 컴퓨터의 총 수(hackingNums[id])를 업데이트
        for (int id=1; id<=N; id++) {
            bfs(id);
            // 가장 많이 당하는 해킹 수(answer) 업데이트
            answer = Math.max(answer, hackingNums[id]);
        }

        // 3. 한 번 더 hackingNums를 탐색하며, hackingNums[id] == answer인 id 모두 구하기
        for (int id=1; id<=N; id++) {
            if (hackingNums[id] == answer) {
                sb.append(id).append(" ");
            }
        }

        // 4. 정답 출력
        System.out.println(sb);
    }

    // {startId} 번째 컴퓨터가 감염되었을 때, 감염되는 컴퓨터의 총 수를 hackingNums[startId]에 저장
    // 세부 주석은 생략(일반 BFS 로직과 동일)
    private static void bfs(int startId) {
        // 방문 배열
        boolean[] visited = new boolean[N+1];
        visited[startId] = true;
        // 방문 queue
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(startId);

        int hackingNum = 1;

        while (!queue.isEmpty()) {
            int id = queue.poll();
            ArrayList<Integer> adjList = adjLists[id];
            for (int nextId: adjList) {
                if (!visited[nextId]) {
                    visited[nextId] = true;
                    queue.add(nextId);
                    hackingNum++;
                }
            }
        }

        hackingNums[startId] = hackingNum;
    }


    private static void setInitVariable() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjLists = new ArrayList[N+1];
        for (int id=1; id<=N; id++) {
            adjLists[id] = new ArrayList<>();
        }

        for (int cnt=0; cnt<M; cnt++) {
            st = new StringTokenizer(br.readLine().trim());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            adjLists[B].add(A);
        }

        hackingNums = new int[N+1];
        answer = 0;
    }


}
