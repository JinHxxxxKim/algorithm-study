package algorithm_study.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [ init ]
 * 1. - 기준으로 입력을 나눠 저장
 * 2. 요소를 돌며 + 기준으로 나눠 누적 후 리스트에 저장
 *
 * [ main ]
 * 3. 리스트의 첫번째 값부터 다음 요소를 뺀 값을 계산 후 출력
 */
public class s1541_잃어버린괄호_SangHu {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static List<Integer> numList;
    static int result;

    public static void main(String[] args) throws IOException {
        init();

        // 3. 리스트의 첫번째 값부터 다음 요소를 뺀 값을 계산 후 출력
        result = numList.get(0);
        for (int idx = 1; idx < numList.size(); idx++) {
            result -= numList.get(idx);
        }

        System.out.println(result);
    }

    public static void init() throws IOException {
        // 1. - 기준으로 입력을 나눠 저장
        st = new StringTokenizer(br.readLine().trim(), "-");

        numList = new ArrayList<>();
        while(st.hasMoreTokens()) {
            int prefixNum = 0;

            // 2. 요소를 돌며 + 기준으로 나눠 누적 후 리스트에 저장
            StringTokenizer pt = new StringTokenizer(st.nextToken(), "+");

            while(pt.hasMoreTokens()) {
                prefixNum += Integer.parseInt(pt.nextToken());
            }

            numList.add(prefixNum);
        }
    }
}