import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * 그리디
 * 시간이 적게 걸리는 사람부터 돈을 인출하면 된다.
 */

public class s_11399_ATM_YongSoo {
    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static int[] times;


    public static void main(String[] args) throws IOException {
        // 1. 변수 초기화
        setInitVariable();

        // 2. times 배열을 오름차순 정렬
        Arrays.sort(times);

        int sum = 0;

        // 정렬하였을 때, {idx}번째 사람의 시간은, (N-idx)번 더해지게 된다.
        for (int idx=0; idx<N; idx++) {
            sum += (N-idx) * times[idx];
        }

        System.out.println(sum);
    }

    private static void setInitVariable() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());

        times = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx=0; idx<N; idx++) {
            times[idx] = Integer.parseInt(st.nextToken());
        }
    }
}
