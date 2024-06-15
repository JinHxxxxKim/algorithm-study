package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * [선물 전달] - BOJ
 * 교란 순열
 *
 * A1, A2, A3, A4, A5 ,,, An 의 사람들이 존재한다.
 * 각각의 선물은
 * a1, a2, a3, a4, a5 ,,, an 이라고 하자.
 *
 * A1이 받을 수 있는 선물의 개수는 n-1개다.
 * 그 중 a2를 받은 경우, A2의 경우의 수를 생각해보자.
 * CASE 1. a1을 A2에게 전달할 경우:
 *   - A1, A2가 서로 선물을 맞교환한 상태로, n-2명일 때 교란순열의 경우의 수와 동일하다.
 * CASE 2. a1가 A2에게 전달되지 않을 경우
 *   - a2는 A1에게 전달되었고, a1은 A2에게 전달될 수 없다
 *   > a1이 a2의 역할을 대신한다고 볼 수 있다.
 *   따라서 n-1명일 때 교란순열의 경우의 수와 동일하다.
 *
 * 점화식: Dn = (n-1)(Dn-1 + Dn-2)
 */
public class s1947_선물전달_JinHyung {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static long[] memo;

    static int divideNum = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        memo = new long[N + 1];
        memo[0] = 1;
        memo[1] = 0;

        for (int idx = 2; idx <= N; idx++) {
            memo[idx] = ((idx - 1) * (memo[idx - 2] + memo[idx - 1])) % divideNum;
        }
        System.out.println(memo[N]);
    }
}
