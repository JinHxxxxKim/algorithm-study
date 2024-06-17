package BOJ;

/**
 * 에라토스테네스의 체 알고리즘을 응용
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class s_1016_제곱ㄴㄴ수_YongSoo {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static long min, max;
    static boolean[] isNotSquareNumber;
    static int answer;

    public static void main(String[] args) throws IOException {

        // 1. 변수 초기화
        setInitVariable();

        // 2. 에라토스테네스의 채를 이용해, primeList에 소수 넣기
        // 넣는 동시에, isNotSquareNumber 업데이트
        setPrimeList();

        // isNotSquareNumber 배열을 탐색하며 answer 업데이트
        for (boolean b: isNotSquareNumber) {
            if (b) answer++;
        }

        // 4. 정답 출력
        System.out.println(answer);
    }

    private static void setPrimeList() {
        boolean[] isPrime = new boolean[1000001];
        Arrays.fill(isPrime, true);

        isPrime[0] = isPrime[1] = false;
        for (int idx=0; idx< isPrime.length; idx++) {
            if (isPrime[idx]) {

                // 에라토스테네스 알고리즘
                for (int nextIdx=2*idx; nextIdx< isPrime.length; nextIdx += idx) {
                    isPrime[nextIdx] = false;
                }


                // 위와 유사한 방식으로, min ~ max 사이의 제곱수의 배수들을 찾아서 isNotSquareNumber 배열 업데이트
                // int와 long 사이 타입 캐스팅에 유의(배열의 index를 int로 바꿔줘야 함)
                long squareNum = (long) idx * idx;
                long startIdx = (min-1) / (squareNum) + 1;
                long endIdx = max / (squareNum);
                for (long subIdx = startIdx; subIdx<=endIdx; subIdx++) {
                    isNotSquareNumber[(int)(squareNum * subIdx - min)] = false;
                }
            }
        }
    }

    private static void setInitVariable() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        min = Long.parseLong(st.nextToken());
        max = Long.parseLong(st.nextToken());

        answer = 0;
        isNotSquareNumber = new boolean[(int)(max - min + 1)];
        Arrays.fill(isNotSquareNumber, true);
    }


}
