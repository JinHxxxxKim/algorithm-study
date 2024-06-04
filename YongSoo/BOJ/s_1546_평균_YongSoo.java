package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 변수 초기화
 * 2. 배열을 순회하며, 배열의 최댓값 M과 총합 S를 구한다
 * 3. 새로운 평균을 구한다.(평균 / M * 100)
 */

public class s_1546_평균_YongSoo {

    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static int[] scores;
    public static void main(String[] args) throws IOException {
        // 1. 변수 초기화
        setInitVariable();

        // 2. 배열을 순회하며, 배열의 최댓값 M과 총합 S를 구한다
        int M = Integer.MIN_VALUE;
        int S = 0;
        for (int score: scores) {
            S += score;
            M = Math.max(M, score);
        }

        // 3. 새로운 평균을 구한다.(평균 / M * 100)
        double avg = (double)S / scores.length;
        double newAvg = avg/M * 100;

        System.out.println(newAvg);
    }


    private static void setInitVariable() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());

        scores = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx=0; idx<N; idx++) {
            scores[idx] = Integer.parseInt(st.nextToken());
        }
    }
}
