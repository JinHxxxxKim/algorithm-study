package algorithm_study.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 완전순열
 *  - 1을 2부터 n까지 총 (n - 1) 개의 자연수 중 하나로 대응해야 하는 경우
 *  - 이때 1이 k에 대응된다 하면 이후의 대응을 두 가지 경우로 나눌 수 있음
 *      -- k가 1에 대응되는 경우, 1과 k는 서로 짝을 이루었고 나머지 (n-2)개를 교란하여 경우의 수가 dp[n-2]
 *      -- k가 1에 대응되지 않는 경우, 1과 k를 같은 것으로 취급해 (n-1)개를 교란하여 경우의 수가 dp[n-1]
 *  - k로 가능한 수는 1을 제외한 (n-1)개가 있어 점화식 dp[n] = (n-1)(dp[n-1] + dp[n-2]) 을 만듦
 *
 * [ init ]
 * 1. 학생의 수 입력
 * 2. dp 테이블 초기화
 *  2-1. 인덱스는 그때의 학생 수로 선물을 교환할 수 있는 경우의 수
 *  2-2. 최대 학생 수는 1_000_000
 *
 * [ solution ]
 * 3. 0 또는 1명일 때, 경우의 수는 0
 *  3-1. 2명은 1 (A <-> B)
 * 4. 완전순열 점화식을 통해 dp 테이블 채우기
 */
public class s1947_선물전달_SangHu {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static final int MOD = 1_000_000_000;

    static int humanCount;
    static long[] dp;

    public static void solution() {
        // 3. 0 또는 1명일 때, 경우의 수는 0
        dp[0] = 0;
        dp[1] = 0;
        // 3-1. 2명은 1 (A <-> B)
        dp[2] = 1;

        // 4. 완전순열 점화식을 통해 dp 테이블 채우기
        for (int idx = 3; idx <= humanCount; idx++) {
            dp[idx] = (idx - 1) * (dp[idx-1] + dp[idx-2]) % MOD;
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        solution();

        System.out.println(dp[humanCount]);
    }

    public static void init() throws IOException {
        // 1. 학생의 수 입력
        humanCount = Integer.parseInt(br.readLine().trim());

        // 2. dp 테이블 초기화
        dp = new long[1_000_001];
    }
}