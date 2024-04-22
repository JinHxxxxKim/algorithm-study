import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * DP로 풀이
 *
 * dp[i]를 i일부터 시작하여 얻을 수 있는 최대 수익이라고 할 때, dp[i]는 다음 두 값 중 큰 값이다.
 *
 * 1. i일에 상담을 하지 않는 경우 : 최대 수익은 dp[i+1]이다.
 * 2. i일에 상담을 하는 경우 : i일에 잡혀 있는 상담을 완료하는 데 걸리는 기간을 T_i,
 *                        받을 수 있는 금액을 P_i라 했을 때,
 *                        최대 수익은 P_i + dp[i + T_i]이다.
 */

public class s_14501_퇴사_YongSoo {

    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static Plan[] plans;
    static int[] dp;

    static class Plan {
        int time;
        int reward;

        public Plan(int time, int reward) {
            this.time = time;
            this.reward = reward;
        }
    }


    public static void main(String[] args) throws IOException {
        // 변수 초기화
        setInitVariable();

        for (int day=N; day>=1; day--) {
            Plan plan = plans[day];

            int time = plan.time;
            int reward = plan.reward;

            dp[day] = dp[day+1];

            // {day}일에 있는 상담을 완료하려면, day + time이 N+1 이하여야 함
            if (day + time <= N+1) {
                dp[day] = Math.max(dp[day], reward + dp[day+time]);
            }
        }

        // 정답 출력
        System.out.println(dp[1]);

    }


    // 문제에서 주어지는 변수 초기화
    private static void setInitVariable() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());

        plans = new Plan[N+1];

        for (int idx=1; idx<=N; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int time = Integer.parseInt(st.nextToken());
            int reward = Integer.parseInt(st.nextToken());

            plans[idx] = new Plan(time, reward);
        }

        dp = new int[N+2];
    }
}