package BOJ;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 조합을 이용하는 문제
 * N개의 'a'와 M개의 'z'가 있을 때, 가능한 총 조합의 수는 {(N+M) combination N}개
 * 이 중 a로 시작하는 조합의 수는 {(N+M-1) combination N-1}개이므로
 * 1 ~ {(N+M-1) combination N-1} 번째 수는 a로 시작하고, 그 이후는 z로 시작한다.
 * 이처럼, 첫 인덱스부터 시작하여 현재 인덱스가 a인지, z인지 판단하는 방법으로 정답을 구할 수 있다.

 * 조합의 경우 dp를 이용하여 계산 시간을 줄일 수 있다.

 * 1. 변수 초기화
 *    dp : combination 값을 계산하는 배열
 *         N = 100, M = 100일 때 {(N+M) combination N}은 매우 큰 수이다.
 *         로직상 (K+1)을 넘는 수는 필요하지 않으므로, 이를 넘으면 일괄적으로 K+1로 초기화
 *  2. updateString 함수를 이용해, 각 인덱스별로 현재 인덱스가 a인지, z인지를 판단
 *     2-1. 현재 인덱스가 a로 시작하는 조합의 수를 구한다
 *     2-2. prefix와 2-1에서 구한 값을 더했을 때, K가 넘는지를 판단
 *          2-2-1. 넘는다면, 현재 인덱스는 a여야 한다.
 *          2-2-2. 넘지 않는다면, 현재 인덱스는 z여야 한다.
 *  3. 완료 후 StringBuilder에 저장된 문자열을 출력한다.
 */

public class s_1256_사전_YongSoo {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int K;

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        // 1. 변수 초기화
        setInitVariable();

        // 엣지 케이스 판단 : 사전에 수록되어 있는 문자열의 총 개수가 K보다 작은 경우
        if (findCombination(N+M, N) < K) {
            System.out.println(-1);
        }
        else {
            // 2. updateString 함수를 이용해, 각 인덱스별로 현재 인덱스가 a인지, z인지를 판단
            updateString(0, 0,  1);

            // 3. 완료 후 StringBuilder 에 저장된 문자열 출력
            System.out.println(sb);
        }
    }

    /**
     *
     * @param curIdx : 현재 보고 있는 인덱스(문자열 기준)
     * @param useA : 이전까지 사용한 'a' 문자열의 개수
     * @param prefix : 현재 조합으로 가능한 문자열의 최소 순번
     */
    private static void updateString(int curIdx, int useA, int prefix) {
        // 문자열의 끝에 도달한 경우
        if (curIdx == (N+M)) return;

        // 2-1. 현재 인덱스가 a로 두었을 때 가능한 조합의 수를 구한다
        int comb = findCombination(N+M-curIdx-1, N-useA-1);

        // K번째 문자열의 현재 인덱스가 'z'가 되려면, prefix + comb <= K여야 함
        if (prefix + comb <= K) {
            sb.append("z");
            updateString(curIdx+1, useA, prefix + comb);
        }
        else {
            sb.append("a");
            updateString(curIdx+1, useA+1, prefix);
        }
    }

    // {n combination c}의 값을 반환
    // 해당 값이 K+1보다 큰 경우, K+1을 반환
    // {0 combination 0} = 1로 두어야 함에 유의
    private static int findCombination(int n, int c) {
        if (c < 0 || c > n) return 0;
        if (c == 0 || c == n) return dp[n][c] = 1;
        if (dp[n][c] != 0) return dp[n][c];

        return dp[n][c] = Math.min((findCombination(n-1, c) + findCombination(n-1, c-1)), K+1);
    }

    private static void setInitVariable() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N+M+1][N+1];
    }
}
