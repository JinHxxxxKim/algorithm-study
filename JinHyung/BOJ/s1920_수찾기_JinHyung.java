package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 1. N을 입력받고, N개의 수를 Set에 저장한다.
 * 2. M개의 수를 입력받은 후, Set에 Contain한지 확인한다.
 */
public class s1920_수찾기_JinHyung {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static Set<Integer> set;

    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(br.readLine().trim());
        set = new HashSet<>();
        st = new StringTokenizer(br.readLine().trim());
        // set에 숫자 저장
        for (int idx = 0; idx < N; ++idx) {
            set.add(Integer.parseInt(st.nextToken()));
        }
        M = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());
        // 포함 여부 판별
        for (int idx = 0; idx < M; ++idx) {
            int num = Integer.parseInt(st.nextToken());
            if (set.contains(num)) {
                sb.append(1);
            }else{
                sb.append(0);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
