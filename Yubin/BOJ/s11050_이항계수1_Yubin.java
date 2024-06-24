import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder out = new StringBuilder();
    static StringTokenizer st;

    static int solution(final int N, final int K) {
        if (K == 0) {
            return 1;
        }

        // dp[n][k]는 nCk를 의미함
        int[][] dp = new int[N + 1][K + 1];
        // 즉, nC0은 항상 1이며 nC1은 n이다.
        for (int n = 1; n <= N; n++) {
            dp[n][0] = 1;
            dp[n][1] = n;
        }
        // kCk는 항상 k이다.
        for (int k = 0; k <= K; k++) {
            dp[k][k] = 1;
        }

        // 파스칼의 삼각형, (n-1, k-1) + (n-1, k) = (n, k) 구현
        for (int n = 2; n <= N; n++) {
            for (int k = 2; k <= K; k++) {
                dp[n][k] = dp[n - 1][k - 1] + dp[n - 1][k];
            }
        }

        return dp[N][K];
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int result = solution(n, k);
        System.out.println(result);
    }
}