import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_13398_연속합2
 * @author parkrootseok
 *
 * - N개의 수열이 주어지고 연속된 수의 합이 최대인 경우를 구해라
 *  - 단, 수를 하나 제거 가능(제거하지 않는것도 가능)
 *
 * 1. 수열 입력
 * 2. dp를 사용하여 최대 합을 가지는 구간을 계산
 *  2-1. 제거하지 않은 경우
 *  2-2. 제거한 경우(특정수를 제거한 결과가 제거하지 않은 경우보다 크다면 저장)
 *  2-3. 현재 연속합을 비교하여 최대 연속합을 갱신
 */
public class s_연속합2_GeunSeok {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;

	public static int length;
	public static int[] sequence;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 수열 입력
		length = Integer.parseInt(br.readLine().trim());
		inputs = br.readLine().trim().split(" ");
		sequence = new int[length];
		for (int index = 0; index < length; index++) {
			sequence[index] = Integer.parseInt(inputs[index]);
		}

		// 2. dp를 사용하여 최대 합을 가지는 구간을 계산
		int[][] dp = new int[length][2];
		dp[0][0] = sequence[0];
		dp[0][1] = sequence[0];

		int maxSum = sequence[0];
		for (int index = 1; index < length; index++) {

			// 2-1. 제거하지 않은 경우
			dp[index][0] = Math.max(dp[index - 1][0] + sequence[index], sequence[index]);

			// 2-2. 제거한 경우(특정수를 제거한 결과가 제거하지 않은 경우보다 크다면 저장)
			dp[index][1] = Math.max(dp[index - 1][0], dp[index - 1][1] + sequence[index]);

			// 2-3. 현재 연속합을 비교하여 최대 연속합을 갱신
			maxSum = Math.max(maxSum, Math.max(dp[index][0], dp[index][1]));

		}

		sb.append(maxSum);
		bw.write(sb.toString());
		bw.close();

	}

}