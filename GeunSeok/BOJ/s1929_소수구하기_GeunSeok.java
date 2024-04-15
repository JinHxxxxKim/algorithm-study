import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * BOJ_1929_소수구하기
 * @author parkrootseok
 *
 *  1. 소수를 판단할 숫자의 범위 입력 받는다.
 *  2. 소수 체크 배열을 만든다.
 *  3. 소수만 출력
 **/
public class s1929_소수구하기_GeunSeok {

    static final int LIMIT = 1_000_000;

    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    static String[] inputs;

    static boolean[] isPrime;
    static int start;
    static int end;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        // 1. 소수를 판단할 숫자의 범위 입력 받는다.
        inputs = br.readLine().trim().split(" ");
        start = Integer.parseInt(inputs[0]);
        end = Integer.parseInt(inputs[1]);

        // 2. 소수 체크 배열을 만든다.
        isPrime = new boolean[LIMIT + 1];
        Arrays.fill(isPrime, true);

        isPrime[0] = isPrime[1] = false;
        for (int number = 2; number <= Math.sqrt(LIMIT); number++) {

            // 2-1. 현재 수가 소수일 때 현재 수의 곱으로 만들 수 있는 수라면 소수가 아님
            if (isPrime[number]) {

                for (int checkNumber = number * number; checkNumber <= LIMIT; checkNumber += number) {
                    isPrime[checkNumber] = false;
                }

            }

        }

        // 3. 소수만 출력
        for (int number = start; number <= end; number++) {
            if (isPrime[number]) {
                sb.append(number).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        return;

    }

}