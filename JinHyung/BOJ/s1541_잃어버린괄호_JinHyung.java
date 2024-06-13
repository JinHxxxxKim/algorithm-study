package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [잃어버린 괄호] - BOJ
 * 
 * '-'기호 뒤에 오는 수를 최대로 만들어야함
 *
 * 1. 주어진 문자열에 대해 '-'를 token으로 split한다.
 * 2. split된 문자열들에 대해 '+'를 token으로 split한다.
 * 3. 해당 문자열의 모든 수를 더한다.
 * 4. 구해진 연산 결과를 앞(0번째 Split String)에서 뺀다. {가장 처음과 마지막 문자는 숫자}
 */
public class s1541_잃어버린괄호_JinHyung {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st, tempSt;

    public static void main(String[] args) throws Exception {
        String expression = br.readLine().trim();
        // '-'를 기준으로 split
        st = new StringTokenizer(expression, "-");

        // Src Number(뺼 기준이 될 수)를 구한다.
        int srcNum = 0;
        tempSt = new StringTokenizer(st.nextToken(), "+");
        while (tempSt.hasMoreTokens()) {
            srcNum += Integer.parseInt(tempSt.nextToken());
        }

        // 빼고자 하는 수를 구한다.
        while (st.hasMoreTokens()) {
            String localExpression = st.nextToken();
            tempSt = new StringTokenizer(localExpression, "+");
            int localSum = 0;
            while (tempSt.hasMoreTokens()) {
                localSum += Integer.parseInt(tempSt.nextToken());
            }
            // 해당 수를 뺀다.
            srcNum -= localSum;
        }

        System.out.println(srcNum);
    }
}
