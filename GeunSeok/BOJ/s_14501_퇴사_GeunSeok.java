import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * BOJ_14501_퇴사
 * @author parkrootseok
 *
 *  1. 퇴사 일자 입력
 *  2. 퇴사 전까지 일자별 상담 기간과 금액 입력
 *  3. DP
 *   3-1. 현재 날짜에서 완료가 가능한 경우 최적해인지 판단 후 초기화
 *   3-2. 전날이 최적해라면 초기화
 **/
public class s_14501_퇴사_GeunSeok {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int leaveDay;
	static int days[];
	static int cost[];
	static int dp[];

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 퇴사 일자 입력
		leaveDay = Integer.parseInt(br.readLine().trim());

		// 2. 퇴사 전까지 일자별 상담 기간과 금액 입력
		days = new int[leaveDay];
		cost = new int[leaveDay];
		dp = new int[leaveDay + 1];
		for(int curDay = 0; curDay < leaveDay; curDay++) {
			inputs = br.readLine().trim().split(" ");
			days[curDay] = Integer.parseInt(inputs[0]);
			cost[curDay] = Integer.parseInt(inputs[1]);
		}

		// 3. DP
		for(int curDay = 0; curDay < leaveDay; curDay++) {

			// 3-1. 현재 날짜에서 완료가 가능한 상담일 때 DP 배열을 최대값으로 초기화
			if (curDay + days[curDay] <= leaveDay) {
				dp[curDay + days[curDay]] = Math.max(dp[curDay + days[curDay]], dp[curDay] + cost[curDay]);
			}

			// 3-2. 전날에 더 많은 돈을 벌 수 있다면 이전 날 비용으로 초기화
			dp[curDay + 1] = Math.max(dp[curDay + 1], dp[curDay]);

		}

		sb.append(dp[leaveDay]);
		bw.write(sb.toString());
		bw.close();
		return;

	}

}