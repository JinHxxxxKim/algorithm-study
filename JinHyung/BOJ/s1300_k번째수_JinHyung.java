package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 n=5
 1	2	3	4	5
 2	4	6	8	10
 3	6	9	12	15
 4	8	12	16	20
 5	10	15	20	25

 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11,12,13,14,15,16, 17, 18, 19, 20, 21, 22, 23, 24, 25
 1, 2, 2, 3, 3, 4, 4, 4, 5, 5, 6, 6, 8, 8, 9, 10, 10, 12, 12, 15, 15, 16, 20, 20, 25

 11

 MID 보다 작거나 같은 수가 몇개 있는가?(이진 탐색): Lower Bound

 중간 값이 검색 값보다 작다면 left 값을 mid+1
 중간 값이 검색 값보다 크거나 같다면 right값을 mid
 
 (LEFT, RIGHT) MID { MID = (LEFT + RIGHT) / 2 }

 (1, 25) 13: 5 + 5 + 4 + 3 + 2 > 19 [13보다 작거나 같은 수가 19개 있다.]
 (1, 13) 7 : 5 + 3 + 2 + 1 + 1 > 12 [7보다 작거나 같은 수가 12개 있다.]
 (1, 7) 4 : 4 + 2 + 1 + 1 + 0 > 8   [4보다 작거나 같은 수가 8개 있다.]
 (5, 7) 6 : 5 + 3 + 2 + 1 + 1 > 12  [6보다 작거나 같은 수가 12개 있다.]
 (5, 6) 5 : 5 + 2 + 1 + 1 + 1 > 10  [5보다 작거나 같은 수가 10개 있다.]
 (6, 6) 6 : 5 + 3 + 2 + 1 + 1 > 12  [6보다 작거나 같은 수가 12개 있다.] => 6이 답


 */
public class s1300_k번째수_JinHyung {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, k;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine().trim());
        k = Integer.parseInt(br.readLine().trim());

        long left = 1;
        long right = Math.min(1_000_000_000, ((long) n * n));

        while (left < right) {
            long mid = (left + right) / 2;
            // 이분 탐색(LowerBound)
            long sum = 0;
            for (int idx = 1; idx <= n; ++idx) {
                sum += Math.min(mid / idx, n);
            }
            if (sum >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left);
    }
}