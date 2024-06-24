package BOJ;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 기본적인 BFS 연습 문제
 * minDistances 배열을 두어, 각 칸에 도달할 수 있는 최소 칸의 개수를 저장한다.
 * (일종의 방문 배열 역할)
 */

public class s_2178_미로탐색_YongSoo {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static final int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int N, M;
    static int[][] maze;
    static int[][] minDistances;
    static ArrayDeque<Info> queue;
    static int answer;
    static class Info {
        int row;
        int col;
        int cnt;

        public Info(int row, int col, int cnt) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "row=" + row +
                    ", col=" + col +
                    ", cnt=" + cnt +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {

        // 1. 변수 초기화
        setInitVariable();

        // 2. BFS 알고리즘을 이용한 answer 업데이트
        solve();

        System.out.println(answer);

    }

    // BFS 알고리즘을 이용한 answer 업데이트
    private static void solve() {

        // (N, M) = (1, 1)일 경우, 탐색이 불필요
        if (N==1 && M==1) {
            answer = 1;
            return;
        }


        minDistances[0][0] = 1;
        queue.add(new Info(0, 0, 1));

        // BFS 탐색 시작
        while (!queue.isEmpty()) {
            Info info = queue.poll();
            int row = info.row;
            int col = info.col;
            int cnt = info.cnt;

            // (row, col)에서 인접한 칸들을 탐색
            for (int[] delta: deltas) {
                int newRow = row + delta[0];
                int newCol = col + delta[1];

                // 최소 칸의 개수를 갱신할 수 있는 경우
                if (isValid(newRow, newCol) && maze[newRow][newCol] == 1 && minDistances[newRow][newCol] > cnt+1) {
                    // 이동한 점이 (N, M)의 위치인 경우
                    if (newRow == N-1 && newCol == M-1) {
                        answer = cnt+1;
                        return;
                    }
                    // minDistances 값 갱신
                    minDistances[newRow][newCol] = cnt+1;
                    queue.add(new Info(newRow, newCol, cnt+1));
                }
            }
        }
    }

    // (row, col)이 유효한 좌표인지를 반환
    private static boolean isValid(int row, int col) {
        return row>=0 && row<N && col>=0 && col<M;
    }


    private static void setInitVariable() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maze = new int[N][M];
        for (int row=0; row<N; row++) {
            String s = br.readLine().trim();
            for (int col=0; col<M; col++) {
                maze[row][col] = s.charAt(col) - '0';
            }
        }

        minDistances = new int[N][M];
        for (int row=0; row<N; row++) {
            Arrays.fill(minDistances[row], Integer.MAX_VALUE);
        }

        queue = new ArrayDeque<>();
        answer = 0;
    }


}
