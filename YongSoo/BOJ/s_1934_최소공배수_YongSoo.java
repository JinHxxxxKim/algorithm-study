import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * A와 B의 최대공약수를 G, 최소공배수를 L이라고 했을 때, A*B = G*L이 성립
 * 유클리드 호제법을 이용해 G를 구한 후, 위 공식을 사용해 L을 구한다
 */

public class s_1934_최소공배수_YongSoo {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;
    static int T;
    static int A;
    static int B;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        T = Integer.parseInt(br.readLine().trim());

        for (int testCase=1; testCase<=T; testCase++) {
            // 1. 변수 초기화
            setInitVariable();

            // 2. 유클리드 호제법으로 최대공약수 구하기
            int GCM = getGCM(A, B);

            // 3. 최소공배수 구하기
            int LCM = A * B / GCM;

            sb.append(LCM).append("\n");
        }

        // 4. 정답 출력
        System.out.println(sb);

    }

    private static int getGCM(int A, int B) {
        int maxNum = Math.max(A, B);
        int minNum = Math.min(A, B);

        while (true) {
            int res = maxNum % minNum;

            if (res == 0) {
                return minNum;
            }

            maxNum = minNum;
            minNum = res;
        }
    }

    // 문제에서 주어진 변수를 초기화
    private static void setInitVariable() throws IOException{
        st = new StringTokenizer(br.readLine().trim());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
    }
}
