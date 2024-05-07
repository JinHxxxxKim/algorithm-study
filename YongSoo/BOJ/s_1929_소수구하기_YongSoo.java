import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * 에라토스테네스의 체 알고리즘 사용
 */

public class s_1929_소수구하기_YongSoo {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int N, M;

    // isPrime으로 변수를 설정할 경우 처음에 값을 모두 true로 바꿔줘야 함
    static boolean[] isNotPrime;


    public static void main(String[] args) throws IOException {
        setInitVariable();

        isNotPrime[0] = isNotPrime[1] = true;

        for (int num=2; num<=N; num++) {
            // num이 소수일 경우
            if (!isNotPrime[num]) {

                // M 이상일 경우, 정답에 추가
                if (num >= M) {
                    sb.append(num).append("\n");
                }

                // 2*num, 3*num, ...는 모두 합성수이다(소수가 아니다)
                for (int compositionNum = 2*num; compositionNum<=N; compositionNum+=num) {
                    isNotPrime[compositionNum] = true;
                }
            }
        }

        System.out.println(sb);

    }

    private static void setInitVariable() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        sb = new StringBuilder();

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        isNotPrime = new boolean[N+1];
    }

}