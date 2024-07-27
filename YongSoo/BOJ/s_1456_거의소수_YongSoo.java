package BOJ;

/**
 * 소수 판별 문제
 * 로직 자체는 쉬우나, overflow 관리가 어렵다
 * (질문 게시판을 참고한 후 해결)
   처음에는 overflow를 잡아내는 방법을 연산 결과가 음수가 되었을 때로 생각하였으나,
   overflow가 난 뒤에도 결과값이 양수가 될 수 있어서 알맞은 방법이 아니다.

 * 1. 주어진 변수 초기화
 * 2. 10^7 이하의 소수들을 배열에 저장
 * 3. 각 소수들에 대해, 거의 소수를 구하기
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class s_1456_거의소수_YongSoo {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static long A, B;
    static boolean[] isPrime;


    public static void main(String[] args) throws IOException {

        // 1. 주어진 변수 초기화
        setInitVariable();

        // 2. 10^7 이하의 소수들을 배열에 저장
        isPrime = new boolean[10000001];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int num=2; num< isPrime.length; num++) {
            if (isPrime[num]) {
                int notPrime = 2 * num;
                while (notPrime < isPrime.length) {
                    isPrime[notPrime] = false;
                    notPrime += num;
                }
            }
        }

        // 3. 각 소수들에 대해, 거의 소수를 구하기
        int answer = 0;
        for (int curNum = 2; curNum < isPrime.length; curNum++) {
            if (isPrime[curNum]) {
                long almostPrime = curNum;

                // almostPrime > B를 비교하는 것은 overflow 발생 가능성이 존재
                // almostPrime <= B / curNum을 비교한 후, 안쪽에서 almostPrime *= curNum을 수행
                while (almostPrime <= B / curNum) {
                    almostPrime *= curNum;
                    answer += almostPrime >= A? 1: 0;
                }
            }
        }

        System.out.println(answer);
    }

    private static void setInitVariable() throws IOException{
        st = new StringTokenizer(br.readLine().trim());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
    }

}