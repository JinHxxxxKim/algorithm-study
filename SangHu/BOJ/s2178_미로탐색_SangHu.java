package algorithm_study.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [ init ]
 * 1. 행, 열 입력
 * 2. 맵의 정보 입력
 *
 * [ mazeBFS ]
 * 3. PQ 를 이용한 BFS
 *  3-1. (0, 0) 출발
 */
public class s2178_미로탐색_SangHu {
    static class Pos implements Comparable<Pos> {
        int row;
        int col;
        int count;

        public Pos(int row, int col, int count) {
            this.row = row;
            this.col = col;
            this.count = count;
        }

        @Override
        public int compareTo(Pos o) {
            return Integer.compare(this.count, o.count);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int rowSize, colSize;
    static int[][] map;

    static PriorityQueue<Pos> pq;

    static final int[] deltaRow = {0, 1, 0, -1};
    static final int[] deltaCol = {1, 0, -1, 0};

    public static void mazeBFS() {
        pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[rowSize][colSize];

        pq.add(new Pos(0, 0, 1));
        visited[0][0] = true;

        while (!pq.isEmpty()) {
            Pos curPos = pq.poll();

            if (curPos.row == rowSize - 1 && curPos.col == colSize - 1) {
                System.out.println(curPos.count);
                return;
            }

            for (int deltaIdx = 0; deltaIdx < 4; deltaIdx++) {
                int nextRow = curPos.row + deltaRow[deltaIdx];
                int nextCol = curPos.col + deltaCol[deltaIdx];

                if (isArrange(nextRow, nextCol) || visited[nextRow][nextCol] || map[nextRow][nextCol] == 0)
                    continue;

                pq.add(new Pos(nextRow, nextCol, curPos.count + 1));
                visited[nextRow][nextCol] = true;
            }
        }
    }

    public static boolean isArrange(int row, int col) {
        return row < 0 || row >= rowSize || col < 0 || col >= colSize;
    }

    public static void main(String[] args) throws IOException {
        init();

        mazeBFS();
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        map = new int[rowSize][colSize];
        for (int row = 0; row < rowSize; row++) {
            String input = br.readLine().trim();
            for (int col = 0; col < colSize; col++) {
                map[row][col] = input.charAt(col) - '0';
            }
        }
    }
}