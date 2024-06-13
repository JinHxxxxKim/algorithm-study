package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [평균] - BOJ
 *
 * 1. N을 입력 받고, 각 과목의 점수를 입력받는다.
 * 2. 해당 점수들 중 최고점을 탐색한다.
 * 3. 모든 점수에 대해 공식을 적용한다.
 * 4. 평균을 계산한다.
 */
public class s1546_평균_JinHyung {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static double[] srcScore;
    static double[] fixScore;

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine().trim());
        srcScore = new double[n];
        fixScore = new double[n];
        st = new StringTokenizer(br.readLine().trim());

        // 점수 입력 & 최고점 탐색
        double maxScore = -1;
        for (int idx = 0; idx < n; ++idx) {
            srcScore[idx] = Double.parseDouble(st.nextToken());
            maxScore = Math.max(maxScore, srcScore[idx]);
        }

        // 수정된 점수 계산
        for (int idx = 0; idx < n; ++idx) {
            fixScore[idx] = (srcScore[idx]/maxScore) * 100;
        }

        // 평균 계산
        double sum = 0.0;
        for (int idx = 0; idx < n; ++idx) {
            sum += fixScore[idx];
        }

        System.out.println(sum / n);
    }
}
