import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * 누적합 배열을 이용
 * cumSum[row][col] : (1,1)에서 (row,col)까지의 합
 *    - 이 때, (x1, y1)부터 (x2, y2)까지의 합은
 *      cumSum[x2][y2] - cumSum[x2][y1-1] - cumSum[x1-1][y2] + cumSum[x1-1][y1-1]이다.
 */

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int N, M;
    static int[][] table;
    static int[][] cumSum;


    public static void main(String[] args) throws IOException {
        // 1. 변수 초기화
        setInitVariable();

        // 2. 누적합 배열 초기화
        setCumSum();

        for (int cnt=0; cnt<M; cnt++) {
            st = new StringTokenizer(br.readLine().trim());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            sb.append(cumSum[x2][y2] - cumSum[x1-1][y2] - cumSum[x2][y1-1] + cumSum[x1-1][y1-1]).append("\n");
        }

        System.out.println(sb);

    }

    private static void setCumSum() {
        cumSum = new int[N+1][N+1];

        // (x1, y1)부터 (x2, y2)까지의 합은
        // cumSum[x2][y2] - cumSum[x2][y1-1] - cumSum[x1-1][y2] + cumSum[x1-1][y1-1]이다.
        // 위 공식을 (row, col)부터 (row, col)까지의 합에 적용시키면 아래와 같은 식을 얻는다.
        for (int row=1; row<=N; row++) {
            for (int col=1; col<=N; col++) {
                cumSum[row][col] = table[row][col] + cumSum[row][col-1] + cumSum[row-1][col] - cumSum[row-1][col-1];
            }
        }
    }


    private static void setInitVariable() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        table = new int[N+1][N+1];
        for (int row=1; row<=N; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col=1; col<=N; col++) {
                table[row][col] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
