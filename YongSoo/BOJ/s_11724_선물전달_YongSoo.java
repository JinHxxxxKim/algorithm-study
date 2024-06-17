package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 점화식
 * f(n) = (n-1) * (f(n-1) + f(n-2))
 */
public class s_11724_선물전달_YongSoo {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final long DIV = 1_000_000_000;
    static int N;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        // 1. 변수 초기화
        setInitVariable();
        
        dp[1] = 0;
        if (N > 1) dp[2] = 1;
        
        for (int idx=3; idx<=N; idx++) {
            dp[idx] = (idx-1) * (dp[idx-1] + dp[idx-2]) % DIV;
        }

        System.out.println(dp[N]);
        
    }

    private static void setInitVariable() throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        dp = new long[N+1];
    }
}
