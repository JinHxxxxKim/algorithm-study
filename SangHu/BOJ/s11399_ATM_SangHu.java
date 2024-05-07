package algorithm_study.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [ inputTestCase() ]
 * 1. 사람의 수를 입력받는다.
 * 2. 각 사람의 인출 필요 시간을 입력받는다.
 *
 * [ atm() ]
 * 3. 인출 시간을 기준으로 오름차순 정렬을 진행한다.
 * 4. 첫 번째 요소부터 누적합을 계산한다.
 */
public class s11399_ATM_SangHu {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int humanCount;
    static int[] timeList;

    static int prefixSum;

    public static void main(String[] args) throws IOException {
        inputTestCase();

        // 3. 인출 시간을 기준으로 오름차순 정렬을 진행한다.
        Arrays.sort(timeList);

        // 4. 첫 번째 요소부터 누적합을 계산한다.
        int curSum = timeList[0];
        prefixSum = timeList[0];
        for (int idx = 1; idx < humanCount; idx++) {
            curSum = curSum + timeList[idx];
            prefixSum += curSum;
        }

        System.out.println(prefixSum);
    }

    public static void inputTestCase() throws IOException {
        // 1. 사람의 수를 입력받는다.
        humanCount = Integer.parseInt(br.readLine().trim());

        // 2. 각 사람의 인출 필요 시간을 입력받는다.
        st = new StringTokenizer(br.readLine().trim());
        timeList = new int[humanCount];
        for (int idx = 0; idx < humanCount; idx++) {
            timeList[idx] = Integer.parseInt(st.nextToken());
        }
    }
}