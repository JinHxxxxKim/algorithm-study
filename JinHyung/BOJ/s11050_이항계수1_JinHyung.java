package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * DP, Memoization
 *
 * 1. n, k를 입력받는다.(n <= 10)
 * 2. (n+1) x (n+1) memoization 배열을 생성한다.
 * 3. init: 0행 0열 = 1, 모든 행의 0열 = 1
 * 4. logic: 모든 행을 순회하며, memo[row][col] = memo[row-1][col-1] + memo[row-1][col]
 * 5. memo[n][k]를 출력한다.
 */
public class s11050_이항계수1_JinHyung {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, k;
    static int[][] memo;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        memo = new int[n + 1][n + 1];
        for (int row = 0; row <= n; ++row) {
            memo[row][0] = 1;
        }
        for (int row = 1; row <= n; ++row) {
            for (int col = 1; col <= row; ++col) {
                memo[row][col] = (memo[row - 1][col - 1] + memo[row - 1][col]) % 10007;
            }
        }
        System.out.println(memo[n][k]);
    }
}
