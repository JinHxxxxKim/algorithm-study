import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 정의를 이용해 계산한다.
 * nCk = nC(n-k)임을 이용
 */

public class s_11050_이항계수1_YongSoo {
    static BufferedReader br;
    static StringTokenizer st;
    static int N, K;

    public static void main(String[] args) throws IOException {

        // 1. 변수 초기화
        setInitVariable();

        // 이항 계수 계산
        System.out.println(coefficient(N, Math.min(K, N-K)));
    }

    private static int coefficient(int n, int k) {
        int coef = 1;
        for (int cnt=0; cnt<k; cnt++) {
            coef *= (n-cnt);
        }

        for (int div=k; div>0; div--) {
            coef /= div;
        }

        return coef;
    }


    // 문제에서 주어지는 변수 초기화
    private static void setInitVariable() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }
}