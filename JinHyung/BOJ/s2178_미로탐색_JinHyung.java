package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [미로탐색] - BOJ
 * BFS
 * 
 * 설명 생략
 */
public class s2178_미로탐색_JinHyung {
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, 1, -1};

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[][] map;
    static int[][] dist;
    static boolean[][] isVisited;

    public static void main(String[] args) throws Exception {
        // 변수 초기화 및 입력
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dist = new int[N][M];
        isVisited = new boolean[N][M];
        for (int row = 0; row < N; ++row) {
            String rowInfo = br.readLine().trim();
            for (int col = 0; col < M; ++col) {
                map[row][col] = rowInfo.charAt(col) - '0';
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        isVisited[0][0] = true;
        dist[0][0] = 1;
        while (!q.isEmpty()) {
            int[] currNode = q.poll();
            int currRow = currNode[0];
            int currCol = currNode[1];

            if (currRow == N - 1 && currCol == M - 1) {
                break;
            }

            // 인접 노드 탐색
            for (int dir = 0; dir < 4; ++dir) {
                int tempRow = currRow + dx[dir];
                int tempCol = currCol + dy[dir];

                // 범위 검증
                if (tempRow < 0 || tempCol < 0 || tempRow >= N || tempCol >= M) {
                    continue;
                }
                // 벽 검증
                if (map[tempRow][tempCol] == 0) {
                    continue;
                }
                // 방문 검증
                if (isVisited[tempRow][tempCol]) {
                    continue;
                }

                q.offer(new int[]{tempRow, tempCol});
                isVisited[tempRow][tempCol] = true;
                dist[tempRow][tempCol] = dist[currRow][currCol] + 1;
            }
        }
        System.out.println(dist[N - 1][M - 1]);
    }

}
