package algorithm_study.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ init ]
 * 1. 과목의 개수 입력
 * 2. 각 과목을 배열에 저장
 *  2-1. 최댓값 갱신
 *
 * [ solution ]
 * 3. 배열을 돌며 새로운 점수의 누적을 계산
 * 4. 누적 / 과목의 개수 값 계산
 *
 * [ main ]
 * 5. 결과 출력
 */
public class s1546_평균_SangHu {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int subjectCount;
    static int[] scoreList;
    static int maxScore;

    static double result;

    public static void solution() {
        double scorePrefix = 0;

        // 3. 배열을 돌며 새로운 점수의 누적을 계산
        for (int idx = 0; idx < subjectCount; idx++) {
            double tmp = 1.0 * scoreList[idx] / maxScore * 100;

            scorePrefix += tmp;
        }

        // 4. 누적 / 과목의 개수 값 계산
        result = scorePrefix / subjectCount;
    }

    public static void main(String[] args) throws IOException {
        init();
        solution();

        System.out.println(result);
    }

    public static void init() throws IOException {
        // 1. 과목의 개수 입력
        subjectCount = Integer.parseInt(br.readLine().trim());
        scoreList = new int[subjectCount];
        maxScore = 0;

        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < subjectCount; idx++) {
            // 2. 각 과목을 배열에 저장
            scoreList[idx] = Integer.parseInt(st.nextToken());
            // 2-1. 최댓값 갱신
            maxScore = Math.max(maxScore, scoreList[idx]);
        }
    }
}