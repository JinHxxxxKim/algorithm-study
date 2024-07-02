package BOJ;


import java.io.*;
import java.util.*;

/**
 * 다이나믹 프로그래밍을 이용

 * 첫 문자열을 s1, 두 번째 문자열을 s2라 했을 때,
 * dp[i][j] : s1[0:i]와 s2[0:j]로 만들 수 있는 LCS의 길이라고 할 때,
 * dp[i][j] = dp[i-1][j-1] + 1 (s1[i-1] == s2[j-1]일 때),
 *            Max(dp[i][j-1], dp[i-1][j]) (아닐 때) 가 성립한다.

 * 이를 이용하여 dp를 설정하고 LCS의 길이를 구할 수 있다.
 * LCS의 길이를 구한 뒤, 실제 문자열을 찾는 과정은 dp를 역순으로 탐색하며 문자열을 구해낸다.
 */

public class s_9252_LCS2_YongSoo {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static String s1;
    static String s2;
    static int[][] dp;


    public static void main(String[] args) throws IOException {

        // 1. 변수 초기화
        setInitVariable();

        // 2. dp를 이용해 LCS의 길이 구하기
        for (int row=1; row<dp.length; row++) {
            for (int col=1; col<dp[0].length; col++) {

                // 현재 보고 있는 위치의 두 문자열이 동일할 경우
                if (s1.charAt(row-1) == s2.charAt(col-1)) {
                    dp[row][col] = dp[row-1][col-1] + 1;
                }

                // 아닐 경우
                else {
                    dp[row][col] = Math.max(dp[row-1][col], dp[row][col-1]);
                }
            }
        }

        // dp[s1.length()][s2.length()]가 LCS의 길이가 된다.
        sb.append(dp[s1.length()][s2.length()]).append("\n");

        // 3. dp를 역순으로 탐색하며, 실제 LCS를 구해낸다.
        int row = s1.length();
        int col = s2.length();

        // LCS에 포함되는 문자열들을 임시로 저장해 둘 stack
        ArrayDeque<String> stack = new ArrayDeque<>();

        while (row > 0 && col > 0) {
            if (s1.charAt(row-1) == s2.charAt(col-1)) {
                stack.addLast(String.valueOf(s1.charAt(row-1)));
                row -= 1;
                col -= 1;
            }
            else if (dp[row][col] == dp[row-1][col]) {
                row -= 1;
            }
            else {
                col -= 1;
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.removeLast());
        }

        System.out.println(sb);
    }

    private static void setInitVariable() throws IOException {
        s1 = br.readLine().trim();
        s2 = br.readLine().trim();

        dp = new int[s1.length()+1][s2.length()+1];
    }
}
