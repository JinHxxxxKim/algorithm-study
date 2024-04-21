package algorithm_study.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ inputTestCase() ]
 * 1. 소수를 판별할 범위의 시작 숫자, 마지막 숫자를 입력받는다.
 * 2. primeList 배열을 true 로 초기화
 *
 * [ isPrime() ]
 * 3. 소수 중 가장 작은 2 부터 소수인 수의 배수들을 모두 false 로 전환
 *
 * [ main() ]
 * 4. 입력으로 받은 범위 내 소수 출력
 */
public class s1929_소수구하기_SangHu {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int startNum, endNum;
    static boolean[] primeList;

    public static void isPrime() {
        // 3. 소수 중 가장 작은 2 부터 소수인 수의 배수들을 모두 false 로 전환
        for (int num = 2; num <= Math.sqrt(endNum); num++) {
            // 소수라면 true
            if (primeList[num]) {
                // 해당 소수의 배수들 false 로 전환
                for (int excludeNum = num * num; excludeNum <= endNum; excludeNum += num) {
                    primeList[excludeNum] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputTestCase();

        isPrime();

        // 4. 입력으로 받은 범위 내 소수 출력
        for (int num = startNum; num <= endNum; num++) {
            if (primeList[num])
                sb.append(num).append("\n");
        }

        System.out.println(sb);
    }

    public static void inputTestCase() throws IOException {
        // 1. 소수를 판별할 범위의 시작 숫자, 마지막 숫자를 입력받는다.
        st = new StringTokenizer(br.readLine().trim());
        startNum = Integer.parseInt(st.nextToken());
        endNum = Integer.parseInt(st.nextToken());

        // 2. primeList 배열을 true 로 초기화
        primeList = new boolean[endNum+1];
        for (int idx = 0; idx < endNum+1; idx++) {
            primeList[idx] = true;
        }

        // 소수 중 가장 작은 수는 2 이므로 0, 1 은 false 초기화
        primeList[0] = false;
        primeList[1] = false;
    }
}
