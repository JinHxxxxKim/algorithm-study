package algorithm_study.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ init() ]
 * 1. N, K 입력
 * 2. dp 초기화
 *  2-1. N+1, K+1 크기
 *  2-2. N 개 중 0 을 뽑거나 K 를 뽑는 경우 1 초기화
 *
 * [ comb() ]
 * 3. n 개 중 k 개를 선택하는 경우의 수
 *  3-1. 현재 값보다 작은 두 값의 합을 dp 에 저장
 *
 * [ main ]
 * 4. dp[N][K] 출력
 */
public class s11050_이항계수1_SangHu {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, K;
    static int[][] dp;

    public static void comb() {
        for (int element = 1; element < N+1; element++) {
            for (int select = 1; select < K+1; select++) {
                dp[element][select] = dp[element-1][select] + dp[element-1][select-1];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        comb();

        System.out.println(dp[N][K]);
    }

    public static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N+1][K+1];
        // 뽑지 않는 경우
        for (int element = 0; element < N+1; element++) {
            dp[element][0] = 1;
        }
        // n 과 k 가 같은 경우
        for (int element = 0; element < K+1; element++) {
            dp[element][element] = 1;
        }
    }

    /**
     * 팩토리얼로 연산
     * public static void comb(n, k) {
     *     if (k == 0 || n == k)
     *         return 1;
     *
     *     return comb(n-1, k) + comb(n-1, k-1);
     * }
     */
}