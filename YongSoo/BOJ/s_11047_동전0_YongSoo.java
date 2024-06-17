package BOJ;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

/**
 * 그리디 알고리즘
 * 더 큰 가치의 동전이 항상 더 작은 가치의 배수이므로, 항상 더 큰 가치의 동전을 사용하는 것이 유리
 */

public class s_11047_동전0_YongSoo {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, K;
    static int[] values;

    public static void main(String[] args) throws IOException {

        // 1. 변수 초기화
        setInitVariable();

        // 2. 더 큰 가치의 동전부터 낸다
        int minCnt = 0;
        for (int idx=N-1; idx>=0; idx--) {

            // K원을 모두 지급한 경우: 종료
            if (K == 0) break;

            // values[idx] 가치의 동전을, 최대한 많이 낸다
            minCnt += K / values[idx];
            // K를 동전을 내고 남은 액수로 업데이트
            K %= values[idx];
        }

        // 3. 정답 출력
        System.out.println(minCnt);
    }



    private static void setInitVariable() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        values = new int[N];

        for (int idx=0; idx<N; idx++) {
            values[idx] = Integer.parseInt(br.readLine().trim());
        }
    }


}
