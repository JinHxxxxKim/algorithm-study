import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 주어진 자연수 범위 안의 모든 소수를 출력한다.
 */
public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder out = new StringBuilder();
    static StringTokenizer st;

    static int from;
    static int to;

    static boolean[] isPrime;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(in.readLine());
        // 소수를 출력할 범위
        from = Integer.parseInt(st.nextToken());
        to = Integer.parseInt(st.nextToken());
        // 에라토스테네스의 체 수행
        solution();
        // 결과 출력
        System.out.println(out);
    }

    static void solution() {
        // 에라토스테네스의 체 구현
        isPrime = new boolean[to + 1];
        // 순회를 시작하기 전에는 모든 수가 소수인 것으로 처리
        Arrays.fill(isPrime, true);
        // 1은 소수가 될 수 없으므로, 2부터 to까지 알고리즘 적용
        isPrime[1] = false;
        for (int baseNum = 2; baseNum <= to; baseNum++) {
            // 만약 이전 스텝에서 소수가 아닌 것으로 판명되었으면 스킵
            if (!isPrime[baseNum]) {
                continue;
            }
            // 소수인 것으로 판명났으면, 그 수의 배수들을 모두 소수가 아닌 것으로 처리
            for (int notPrimeNum = baseNum * 2; notPrimeNum <= to; notPrimeNum += baseNum) {
                isPrime[notPrimeNum] = false;
            }
        }
        // from부터 to까지, 소수인 것으로 판명된 숫자들만 출력
        for (int num = from; num <= to; num++) {
            if (isPrime[num]) {
                out.append(num).append('\n');
            }
        }
    }
}
