import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * BOJ_11399_ATM
 * @author parkrootseok
 *
 * - 모든 사람이 돈을 인출하는데 필요한 시간의 최솟값을 구해라
 *
 * 1. 사람의 수를 받는다.
 * 2. 각 사람마다 필요한 시간의 정보를 받는다.
 * 3. 시간을 오름차순 정렬
 * 4. 필요한 시간을 구한다.
 **/
public class s_11399_ATM_GeunSeok {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int personNumber;
	static int[] time;
	static int dp[];
	static int totalTime;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 사람의 수를 받는다.
		personNumber = Integer.parseInt(br.readLine().trim());

		// 2. 각 사람마다 필요한 시간의 정보를 받는다.
		time = new int[personNumber];
		inputs = br.readLine().trim().split(" ");
		for (int index = 0; index < personNumber; index++) {
			time[index] = Integer.parseInt(inputs[index]);
		}

		// 3. 시간을 오름차순 정렬
		Arrays.sort(time);

		// 4. 필요한 시간을 구한다.
		dp = new int[personNumber];
		dp[0] = time[0];
		totalTime = dp[0];
		for (int index = 1; index < personNumber; index++) {
			dp[index] = (dp[index - 1] + time[index]);
			totalTime += dp[index];
		}

		sb.append(totalTime);
		bw.write(sb.toString());
		bw.close();

	}

}