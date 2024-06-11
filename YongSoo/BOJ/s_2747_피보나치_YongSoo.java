package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1. 변수 초기화
 * 2. 배열을 순회하며, 배열의 최댓값 M과 총합 S를 구한다
 * 3. 새로운 평균을 구한다.(평균 / M * 100)
 */

public class s_2747_피보나치_YongSoo {
    static BufferedReader br;
    static int n;
    static long[] fibonacciList;
    public static void main(String[] args) throws IOException {
        // 1. 변수 초기화
        setInitVariable();

        // 2. 점화식을 이용해서, 피보나치 수 구하기
        fibonacciList[1] = 1;
        for (int idx=2; idx<=n; idx++) {
            fibonacciList[idx] = fibonacciList[idx-1] + fibonacciList[idx-2];
        }

        // 3. 정답 출력
        System.out.println(fibonacciList[n]);
    }


    private static void setInitVariable() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());

        fibonacciList = new long[n+1];
    }
}
