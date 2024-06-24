import java.util.*;
import java.io.*;

public class Main {
    static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder out = new StringBuilder();
    static StringTokenizer st;

    static int N;
    static int[] nums;

    static void solution() {
        // 절댓값이 1,000,000을 넘지 않는 음수까지 포함되므로, 인덱스로 사용하기 위해
        // 전체 값을 1,000,000만큼 더하여 존재 유무를 확인해야 함
        final int OFFSET = 1_000_000;
        
        // 수가 중복되지 않으므로 boolean을 써도 됨, [-1_000_000, 1_000_000] 구간
        boolean[] isInArray = new boolean[OFFSET * 2 + 1];
        for (int idx = 0; idx < N; idx++) {
            isInArray[nums[idx] + OFFSET] = true;
        }

        for (int num = 0; num < isInArray.length; num++) {
            if (isInArray[num]) {
                out.append(num - OFFSET).append('\n');
            }
        }
    }

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(in.readLine());
        nums = new int[N];
        for (int idx = 0; idx < N; idx++) {
            nums[idx] = Integer.parseInt(in.readLine());
        }

        solution();
        System.out.println(out);
    }
}