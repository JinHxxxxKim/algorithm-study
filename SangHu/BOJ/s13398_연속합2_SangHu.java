package algorithm_study.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ init ]
 * 1. 정수의 개수 입력
 * 2. 수열 입력
 * 3. dp 테이블 초기화
 *  3-1. [idx][0] 는 수를 제거하지 않은 경우, [idx][1] 는 수를 제거한 경우의 수를 저장
 *
 * [ solution ]
 * 4. idx 번째 수를 제거하지 않은 경우, 제거한 경우로 나누어 dp 갱신
 *  4-1. idx 를 제거하지 않은 경우
 *      4-1-1. 누적한 경우와 현재 값부터 새롭게 연속합을 시작한 경우 비교
 *  4-2. idx 를 제거한 경우
 *      4-2-1. idx 를 제거한 경우인 dp[idx-1][0](idx를 제외했으므로 이전의 연속합)과 이전에 이미 지워진 수가 있는 경우 중 최댓값
 *      4-2-2. 현재 수를 지우지 않으므로 이전 지운 경우의 최대 연속합과 현재값을 더한 값으로 계산
 */
public class s13398_연속합2_SangHu {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int[] numList;
    static int[][] dp;
    static int result;

    public static void solution() {
        result = numList[0]; // 최대 연속합을 저장할 result, n 이 1 이면 최댓값은 그때의 정수이므로 numList[0] 으로 초기화

        // 4. idx 번째 수를 제거하지 않은 경우, 제거한 경우로 나누어 dp 갱신
        for (int idx = 1; idx < N; idx++) {
            // 4-1. idx 를 제거하지 않은 경우
            // 4-1-1. 누적한 경우와 현재 값부터 새롭게 연속합을 시작한 경우 비교
            dp[idx][0] = Math.max(dp[idx - 1][0] + numList[idx], numList[idx]);

            // 4-2. idx 를 제거한 경우
            // 4-2-1. idx 를 제거한 경우인 dp[idx-1][0](idx를 제외했으므로 이전의 연속합)과 이전에 이미 지워진 수가 있는 경우 중 최댓값
            // 4-2-2. 현재 수를 지우지 않으므로 이전 지운 경우의 최대 연속합과 현재값을 더한 값으로 계산
            dp[idx][1] = Math.max(dp[idx-1][0], dp[idx-1][1] + numList[idx]);

            // 현재 최대 연속합 갱신
            result = Math.max(result, Math.max(dp[idx][0], dp[idx][1]));
        }

        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        init();

        solution();
    }

    public static void init() throws IOException {
        // 1. 정수의 개수 입력
        N = Integer.parseInt(br.readLine().trim());

        // 2. 수열 입력
        numList = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < N; idx++) {
            numList[idx] = Integer.parseInt(st.nextToken());
        }

        // 3. dp 테이블 초기화
        dp = new int[N][2];
        // 3-1. [idx][0] 는 수를 제거하지 않은 경우, [idx][1] 는 수를 제거한 경우의 수를 저장
        dp[0][0] = numList[0];
        dp[0][1] = 0;
    }
}