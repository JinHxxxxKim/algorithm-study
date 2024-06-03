import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1. N명의 사람이 인출을 위해 줄을 서있는데, 각각 인출에 필요한 시간이 주어진다.
 * 2. 따라서 줄을 서는 순서에 따라 사람들이 기다려야 하는 시간의 합이 달라진다.
 * 3. 사람들이 최소한으로 기다리는 순서의 시간 합을 구한다.
 */
public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int NUM_OF_PEOPLE;
    static int[] requiredTimes;

    static int solution() {
        // 가장 적은 시간만큼 기다리려면, 앞에서 최대한 빨리 끝내주면 된다.
        // 따라서, 인출하는데 가장 시간이 적게 걸리는 사람을 앞에 세운다.
        Arrays.sort(requiredTimes);

        // 사람들이 기다려야 하는 시간의 총 합
        int totalTimeAcc = 0;
        // 앞에서부터의 시간 누적합
        int timeAcc = 0;
        for (int time : requiredTimes) {
            timeAcc += time;
            totalTimeAcc += timeAcc;
        }

        return totalTimeAcc;
    }

    public static void main(String[] args) throws Exception {
        // 줄을 선 사람의 수
        NUM_OF_PEOPLE = Integer.parseInt(in.readLine());
        // 줄을 선 각 사람들이 인출하는데 필요로 하는 시간
        requiredTimes = new int[NUM_OF_PEOPLE];
        st = new StringTokenizer(in.readLine());
        for (int person = 0; person < NUM_OF_PEOPLE; person++) {
            requiredTimes[person] = Integer.parseInt(st.nextToken());
        }

        int result = solution();
        System.out.println(result);
    }
}
