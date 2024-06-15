package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [제곱ㄴㄴ수] - BOJ
 * 에레토스테네스의 체
 *
 * - max, min 간의 차이는 최대 100만: 배열 선언 가능
 * - min 의 최대값 = 1조: 가능한 제곱수의 최대값은 100만 근사
 *
 * 1. 에레토스테네스의 체를 구한다.
 *   1-1. 2~100만까지 제곱수를 구한다.
 *   1-2. 해당 제곱수의 배수 중에서 min 이상의 값을 구한다.
 *   1-3. 구한 값을 시작으로 에레토스테네스의 체를 완성시킨다.
 * 2. 해당 수에서 제곱ㄴㄴ수의 개수를 구한다.
 */
public class s1016_제곱ㄴㄴ수_JinHyung {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static long min, max;
    static boolean[] flag; // 해당 수가 제곱수 인지 확인하는 flag

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        min = Long.parseLong(st.nextToken());
        max = Long.parseLong(st.nextToken());
        int size = (int) (max - min) + 1;
        flag = new boolean[size];

        // 최대 100만 순회: 2 ~ sqrt(max)만큼의 반복
        // max 는 대략 1조 => 100만 근사
        for (int num = 2; Math.pow(num, 2) <= max; ++num) {
            long powNum = (long) Math.pow(num, 2); // 이번 탐색에서 확인하고자 하는 제곱수
            // min에 근사한 수를 찾기 위한 과정

            // min을 제곱수로 나는 몫
            long startNum = (min / powNum);
            // 나머지가 있다면 +1 (min 이상의 제곱수 배수를 찾기 위함)
            if(min % powNum != 0) startNum++;
            // 확인하고자 하는 숫자의 시작점
            long chkNum = powNum * startNum;

            // powNum이 2, size가 100만일 경우 최대 50만번 순회
            while (chkNum <= max) {
                // min 을 prefix로 하여 인덱스로 사용
                int chkIdx = (int) (chkNum - min);
                // 해당 수는 제곱 ㄴㄴ 수가 아님
                flag[chkIdx] = true;
                
                // 곱하는 수, 확인하려는 수 갱신
                ++startNum;
                chkNum += powNum;
            }
        }

        int cnt = 0;
        for (boolean isNotAns : flag) {
            if (!isNotAns) {
                ++cnt;
            }
        }
        System.out.println(cnt);

    }
}
