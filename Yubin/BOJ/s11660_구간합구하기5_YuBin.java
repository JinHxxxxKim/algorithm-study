import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. NxN 크기 격자에 NxN개의 수가 각각 들어가 있다.
 * 2. 이때, (x1, y1)부터 (x2, y2)까지의 합을 여러 번 구한다.
 */
public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder out = new StringBuilder();
    static StringTokenizer st;

    static int TABLE_SIZE;
    static int NUM_OF_OPERATIONS;
    static int[][] table;

    static int[][] from;
    static int[][] to;

    static void solution() {
        // accSumTable[x][y]는, (0, 0)부터 (x, y)까지의 폐구간 합을 의미한다.
        int[][] accSumTable = new int[TABLE_SIZE + 1][TABLE_SIZE + 1];

        // (row, col) 위치의 값과 (0, 0) ~ (row, col-1) 구간의 합과 (0, 0) ~ (row-1, col) 구간의 합을 더해주되
        // 두 구간의 합이 겹치는 위치인 (row-1, col-1) 부분을 빼주는게 포인트
        for (int row = 1; row <= TABLE_SIZE; row++) {
            for (int col = 1; col <= TABLE_SIZE; col++) {
                accSumTable[row][col] = table[row][col] + accSumTable[row][col - 1] + accSumTable[row - 1][col] - accSumTable[row - 1][col - 1];
            }
        }

        // 주어진 횟수만큼 구간합을 구하여 출력한다.
        for (int operation = 0; operation < NUM_OF_OPERATIONS; operation++) {
            int fromX = from[operation][0];
            int fromY = from[operation][1];
            int toX = to[operation][0];
            int toY = to[operation][1];
            out.append(accSumTable[toX][toY] - accSumTable[toX][fromY - 1] - accSumTable[fromX - 1][toY] + accSumTable[fromX - 1][fromY - 1]).append('\n');
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(in.readLine());
        // 격자의 크기
        TABLE_SIZE = Integer.parseInt(st.nextToken());
        // 합을 구해야 하는 횟수
        NUM_OF_OPERATIONS = Integer.parseInt(st.nextToken());

        // 격자를 구성하는 수 (1-based)
        table = new int[TABLE_SIZE + 1][TABLE_SIZE + 1];
        for (int row = 1; row <= TABLE_SIZE; row++) {
            st = new StringTokenizer(in.readLine());
            for (int col = 1; col <= TABLE_SIZE; col++) {
                table[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        // 구간합을 구해야 하는 구간의 양 끝점
        // 단, from이 to보다 항상 왼쪽 위에 있거나 같은 행/열에 있다. (x1 <= x2, y1 <= y2)
        from = new int[NUM_OF_OPERATIONS][2];
        to = new int[NUM_OF_OPERATIONS][2];
        for (int operation = 0; operation < NUM_OF_OPERATIONS; operation++) {
            st = new StringTokenizer(in.readLine());
            from[operation][0] = Integer.parseInt(st.nextToken());
            from[operation][1] = Integer.parseInt(st.nextToken());
            to[operation][0] = Integer.parseInt(st.nextToken());
            to[operation][1] = Integer.parseInt(st.nextToken());
        }

        solution();
        System.out.println(out);
    }
}
