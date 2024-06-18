package BOJ;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1차원 DP 2개를 이용
 *
 * start[idx] : idx에서 시작하는 연속된 몇 개의 수의 합 중 가장 큰 값
 * end[idx] : idx에서 끝나는 연속된 몇 개의 수의 합 중 가장 큰 값
 *
 * 수를 제거하지 않았을 때의 최댓값 : start[idx] + end[idx] - seq[idx]
 * {idx+1}번째 수를 제거하였을 때 최댓값 : start[idx] + end[idx+1] - seq[idx+1]
 *
 * 이를 모든 idx에 대해 반복한다.
 */

public class s_13398_연속합2_YongSoo {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int[] seq;
    static int[] start;
    static int[] end;
    static int answer;

    public static void main(String[] args) throws IOException {

        // 1. 변수 초기화
        setInitVariable();

        // 2. start와 end 배열 값 초기화
        start = new int[n];
        end = new int[n];

        // 2-1. start[idx]의 경우, seq[idx] 하나만 선택하는 경우와
        // idx를 포함해 연속된 2개 이상의 수를 선택하는 경우 중 최댓값이 된다.
        start[n-1] = seq[n-1];
        for (int idx=n-2; idx>=0; idx--) {
            start[idx] = Math.max(seq[idx], seq[idx] + start[idx+1]);
        }

        // 2-1. end[idx]의 경우, seq[idx] 하나만 선택하는 경우와
        // idx를 포함해 연속된 2개 이상의 수를 선택하는 경우 중 최댓값이 된다.
        end[0] = seq[0];
        for (int idx=1; idx<n; idx++) {
            end[idx] = Math.max(seq[idx], seq[idx] + end[idx-1]);
        }

        // 3. start와 end를 이용해 최대 연속합을 구한다
        for (int idx=0; idx<n; idx++) {

            // 3-1. 수를 제거하지 않는 경우
            answer = Math.max(answer, end[idx] + start[idx] - seq[idx]);

            // 3-2. 수를 제거하는 경우
            if (idx+1 < n) {
                answer = Math.max(answer, end[idx] + start[idx+1] - seq[idx+1]);
            }
        }

        // 4. 정답 출력
        System.out.println(answer);

    }



    private static void setInitVariable() throws IOException {
        n = Integer.parseInt(br.readLine().trim());

        seq = new int[n];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx=0; idx<n; idx++) {
            seq[idx] = Integer.parseInt(st.nextToken());
        }

        answer = Integer.MIN_VALUE;
    }


}
