package algorithm_study.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [ init ]
 * 1. 피보나치 수를 구할 n 입력
 * 2. memo 배열 초기화
 *
 * [ fiboRecur(int num) ]
 * 3. num 이 1 또는 2 일 경우 1 을 반환하고 종료
 * 4. 3 이상의 수는 func(num-1) + func(num-2) 을 반환
 *  4-1. 메모이제이션 활용
 *
 * [ main ]
 * 5. n 번째 피보나치 수 출력
 */
public class s2747_피보나치수_SangHu {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int fibonacciNum;
    static int[] memo;

    public static int fiboRecur(int num) {
        // 3. num 이 1 또는 2 일 경우 1 을 반환하고 종료
        if (num == 1 || num == 2)
            return 1;

        if (memo[num] != 0)
            return memo[num];

        // 4. 3 이상의 수는 func(num-1) + func(num-2) 을 반환
        return memo[num] = fiboRecur(num - 1) + fiboRecur(num - 2);
    }

    public static void main(String[] args) throws IOException {
        init();

        // 5. n 번째 피보나치 수 출력
        fibonacciNum = fiboRecur(n);
        System.out.println(fibonacciNum);
    }

    public static void init() throws IOException {
        // 1. 피보나치 수를 구할 n 입력
        n = Integer.parseInt(br.readLine().trim());

        // 2. memo 배열 초기화
        memo = new int[n + 1];
    }
}