package BOJ;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 등차수열의 합 공식을 이용해서 풀이
 * 투 포인터로도 풀이가 가능하던데, 그 부분이 조금 더 깔끔하긴 한 듯
 *
 * 등차수열이 있을 때, 처음 수와 마지막 수의 합을 A, 수의 개수를 B라고 하자.
 * 이 때, A * B = 2 * N이다.
 *
 * 1) B가 짝수인 경우
 *    A는 무조건 홀수여야 한다. 또한, 처음 수 (A-1)/2 - (B/2) + 1이 1 이상이어야 한다.
 * 2) B가 홀수인 경우
 *    A는 반드시 짝수일 수밖에 없다.(고려가 필요 없다) 또한, 처음 수 (A/2) - (B-1)/2가 1 이상이어야 한다.
 * 2*N의 약수에 대해 1), 2) 조건을 만족하는지 세 주면 정답을 얻을 수 있다
 */

public class s_2018_수들의합5_YongSoo {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int answer;

    public static void main(String[] args) throws IOException {

        // 1. 변수 초기화
        setInitVariable();

        // 2. 2*N의 약수에 대해, 조건을 만족하는지 세 준다.
        for (int div = 1; div<=N; div++) {
            if (2 * N % div == 0) {
                // 2번 조건을 만족하는지 확인
                if (div % 2 == 1) {
                    int quotient = N / div;
                    if (quotient - (div / 2) > 0) answer++;
                }
                // 1)번 조건을 만족하는지 확인
                else {
                    int quotient = 2 * N / div;
                    if (quotient % 2 == 1 && (quotient / 2) - (div / 2) >= 0) answer++;
                }
            }
        }

        System.out.println(answer);

    }



    private static void setInitVariable() throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        answer = 0;
    }


}
