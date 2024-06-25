import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_1463_1로만들기
 * @author parkrootseok
 *
 * - 3가지 연산을 사용하여 1로 만들 수 있을 때, 최소 연산 횟수를 구하라
 *  - 나누기 3, 나누기 2, -1
 *
 * 1. 숫자를 입력
 * 2. 연산 진행
 *  2-1. 1을 빼는 경우
 *  2-2. 2으로 나누어 떨어지는 경우
 *  2-3. 3으로 나누어 떨어지는 경우
 */
public class s_1로만들기_GeunSeok {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String inputs[];

	public static int number;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 숫자를 입력
		number = Integer.parseInt(br.readLine().trim());

		// 2. 연산 진행
		int[] dp = new int[number + 1];
		for (int curNumber = 2; curNumber <= number; curNumber++) {

			// 2-1. 1을 빼는 경우
			dp[curNumber] = dp[curNumber - 1] + 1;

			// 2-2. 2으로 나누어 떨어지는 경우
			if (curNumber % 2 == 0) {
				dp[curNumber] = Math.min(dp[curNumber], dp[curNumber / 2] + 1);
			}

			// 2-3. 3으로 나누어 떨어지는 경우
			if (curNumber % 3 == 0) {
				dp[curNumber] = Math.min(dp[curNumber], dp[curNumber / 3] + 1);
			}

		}

		sb.append(dp[number]);
		bw.write(sb.toString());
		bw.close();

	}


}