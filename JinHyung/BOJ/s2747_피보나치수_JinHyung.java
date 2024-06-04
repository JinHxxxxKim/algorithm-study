package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * [피보나치 ] - BOJ
 *
 * Memoization
 *
 * 1. n을 입력받는다.
 * 2. fibo(n) = fibo(n-1) + fibo(n-2): 재귀 호출
 * 3. n == 0이라면, 0 == 1 or 2이라면 1을 반환한다.
 * 4. fibo(n)이 이미 구해진적이 있다면 해당 수 반환.
 */
public class s2747_피보나치수_JinHyung {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[] memo; // fibo 수를 저장할 배열
    static int n;

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine().trim());
        // memo 배열 초기화
        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        System.out.println(fibo(n));
    }

    private static int fibo(int n) {
        // n == 0이라면, 0 == 1 or 2이라면 1을 반환한다.
        if (n == 0) {
            return 0;
        } else if (n == 1 || n == 2) {
            return 1;
        }

        if (memo[n] != -1) {
            // 이미 구해진적이 있는지 확인
            return memo[n];
        }else{
            // 아니라면 구해야지
            return memo[n] = fibo(n - 1) + fibo(n - 2);
        }
    }
}
