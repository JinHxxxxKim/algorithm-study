package algorithm_study.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 테스트 케이스 개수
 * 2. 두 수 입력
 * 3. 유클리드 호제법을 이용한 최대공약수 찾기
 *  3-1. 두 수를 전달받아 재귀를 통해 second 가 0 일 때의 first 반환
 * 4. 두 수 곱한 값에 최대공약수 나눈 값 출력
 */
public class s1934_최소공배수_SangHu {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int firstNum, secondNum;
    static int gcdResult;

    public static int GCD(int first, int second) {
        // 3-1. 두 수를 전달받아 재귀를 통해 second 가 0 일 때의 first 반환
        if (second == 0)
            return first;

        return GCD(second, first % second);
    }

    public static void main(String[] args) throws IOException {
        // 1. 테스트 케이스 개수
        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            init();

            gcdResult = GCD(firstNum, secondNum);

            // 4. 두 수 곱한 값에 최대공약수 나눈 값 출력
            sb.append(firstNum * secondNum / gcdResult).append("\n");
        }

        System.out.println(sb);
    }

    public static void init() throws IOException {
        // 2. 두 수 입력
        st = new StringTokenizer(br.readLine().trim());
        firstNum = Integer.parseInt(st.nextToken());
        secondNum = Integer.parseInt(st.nextToken());

        if(firstNum > secondNum) {
            int temp = firstNum;
            firstNum = secondNum;
            secondNum = temp;
        }
    }
}
