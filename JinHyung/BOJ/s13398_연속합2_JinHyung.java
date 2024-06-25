package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [연속합 2] - BOJ
 * DP
 * => 2행짜리 DP배열 사용
 *   [0]행: 지금까지 원소를 제외한적 없음
 *   [1]행: 지금까지 1번 원소를 제외했거나, 이번 원소를 제외한다.
 *
 * 원소는 단 한번만 제외할 수 있다. -> 한번 1행으로 내려가면 올라올 수 없다.
 * 1. 수열 정보를 입력받는다.
 * 2. 수열의 0번 인덱스부터 순차적으로 탐색한다.
 *   CASE 1: 해당 원소를 포함하는 경우
 *     (1) 이전 DP의 값 + 현재 원소
 *     (2) 현재 원소(= 현재 원소부터 연속합을 시작한다.)
 *     (1), (2) 중 큰 값 선택
 *   CASE 2: 해당 원소를 제외하는 경우
 *     (1) 이전 DP의 값 (= 현재 원소 제외)
 *     (2) 이전 DP의 값 + 현재 원소 (= 이전에 한번 제외한 적이 있음)
 *     (1), (2) 중 큰 값 선택: 이번 원소를 빼거나, 이전에 뺸 경우의 수 중 큰 값
 *   매 DP배열의 값을 구할 때 마자 ans를 갱신한다.
 */
public class s13398_연속합2_JinHyung {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] numbers; // 수열
    static int[][] dp; // dp 배열
    static int ans;
    
    public static void main(String[] args) throws Exception {
        // 변수 입력 및 초기화
        N = Integer.parseInt(br.readLine().trim());
        numbers = new int[N];
        dp = new int[2][N];
        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < N; ++idx) {
            numbers[idx] = Integer.parseInt(st.nextToken());
        }

        // 로직 시작
        Arrays.fill(dp[0], Integer.MIN_VALUE);
        Arrays.fill(dp[1], Integer.MIN_VALUE);
        dp[0][0] = numbers[0];
        dp[1][0] = 0;
        ans = numbers[0];

        for (int idx = 1; idx < N; ++idx) {
            int currNum = numbers[idx];
            
            // CASE 1. 현재까지 원소를 제외하지 않은 경우
            // (1). 이번 원소를 제외한다.
            dp[1][idx] = Math.max(dp[1][idx], dp[0][idx - 1]);
            // (2). 이번 원소를 제외하지 않는다. (기존 연속합에 더하기 VS 다시 시작하기)
            dp[0][idx] = Math.max(dp[0][idx - 1] + currNum, currNum);

            // CASE 2. 현재까지 원소를 제외한 경우
            // (1). 이번 원소를 포함한다(제외할 수 없음)
            dp[1][idx] = Math.max(dp[1][idx], dp[1][idx - 1] + currNum);

            // 최대 연속합 값 갱신
            ans = Math.max(dp[0][idx], ans);
            ans = Math.max(ans, dp[1][idx]);
        }
        System.out.println(ans);
    }
}
