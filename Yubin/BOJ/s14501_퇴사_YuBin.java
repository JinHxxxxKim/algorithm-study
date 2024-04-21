import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. N+1일째 되는 날 퇴사하며, 남은 N일 동안 최대한 많은 상담을 하려고 한다.
 * 2. 상담 일정표는 일별 상담 일정을 표현하며, 각각 완료하는데 걸리는 기간과 금액이 정해져있다.
 * 3. 일별 상담 일정은 하나씩만 있다.
 * 4. 상담 일정을 적절히 골라서, 얻을 수 있는 최대 수익을 구한다.
 */
public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int EXIT_DAY;
    static int[] periods;
    static int[] rewards;

    static int solution() {
        // maxReward[d]는, d일에 얻을 수 있는 최대 보수를 의미한다.
        // 따라서 maxRewards[EXIT_DAY+1]은, 퇴사 후 얻을 수 있는 최대 보수를 의미한다.
        int[] maxRewards = new int[EXIT_DAY + 2];

        // 첫 날부터 마지막 날까지 뒤로 가며 얻을 수 있는 최대 누적 이익을 계산함
        for (int day = 1; day <= EXIT_DAY; day++) {
            // day까지는 어쨌든 시간이 흐른 것이므로, 이날 상담을 하든 안하든, 할 수 있든 못하든 day-1까지의
            // 최대 이득은 이미 가져가게 되는 것
            maxRewards[day] = Math.max(maxRewards[day - 1], maxRewards[day]);
            // 만약 오늘자의 상담이 가능하다면
            if (day + periods[day] <= EXIT_DAY + 1) {
                // 해당 상담을 진행한 후에 얻을 수 있었던 원래의 누적 이득과
                // 지금 날짜에 상담해서 얻을 수 있는 누적 합을 비교하여 더 큰 쪽으로 갱신해준다.
                maxRewards[day + periods[day]] = Math.max(maxRewards[day + periods[day]], maxRewards[day] + rewards[day]);
            }
        }

        // 꼭 마지막 날 딱 맞게 상담이 끝나야 최대 이득을 볼 수 있는 것은 아니므로, 전수 조사
        int maxReward = 0;
        for (int day = EXIT_DAY + 1; day >= 1; day--) {
            maxReward = Math.max(maxReward, maxRewards[day]);
        }
        return maxReward;
    }

    public static void main(String[] args) throws Exception {
        // 퇴사일
        EXIT_DAY = Integer.parseInt(in.readLine());
        // 상담 일정표 상의 상담 완료 기간과 금액
        // 즉, d일에 상담을 시작하면 periods[d]일을 소모하여 rewards[d]원을 받을 수 있음을 의미한다.
        periods = new int[EXIT_DAY + 1];
        rewards = new int[EXIT_DAY + 1];

        // 상담 일정표
        for (int day = 1; day <= EXIT_DAY; day++) {
            st = new StringTokenizer(in.readLine());
            periods[day] = Integer.parseInt(st.nextToken());
            rewards[day] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution());
    }
}
