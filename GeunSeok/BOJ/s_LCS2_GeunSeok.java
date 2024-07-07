import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_9252_LCS2
 * @author parkrootseok
 *
 * - 두 수열의 최장 공통 부분 수열을 찾아라.
 *
 * 1. 두 문자열을 입력
 * 2. 두 문자열을 비교하여 최장 부분 수열의 길이를 누적합
 * 	2-1. 두 문자열이 같다면 현재 최장 부분 수열의 길이 + 1
 * 	2-2. 두 문자열이 다르다면 현재 최장 부분 수열의 길이 유지
 * 3. 최장 부분 수열 길이 누적합 결과를 역추적하여 최장 부분 수열이 되는 문자열을 생성
 *  3-1. 두 문자열이 같다면 추가
 *  3-2. 두 문자열이 다르면 최장 부분 수열의 누적 기록을 가진 인덱스로 이동
 */
public class s_LCS2_GeunSeok {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String inputs[];

	public static String a;
	public static String b;
	public static int[][] dp;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 두 문자열을 입력
		a = br.readLine().trim();
		b = br.readLine().trim();

		// 2. 두 문자열을 비교하여 최장 부분 수열의 길이를 누적합
		dp = new int[a.length() + 1][b.length() + 1];
		for (int aIndex = 1; aIndex <= a.length(); aIndex++) {

			for (int bIndex = 1; bIndex <= b.length(); bIndex++) {

				// 2-1. 두 문자열이 같다면 현재 최장 부분 수열의 길이 + 1
				if (a.charAt(aIndex - 1) == b.charAt(bIndex - 1)) {
					dp[aIndex][bIndex] = dp[aIndex - 1][bIndex - 1] + 1;
				}

				// 2-2. 두 문자열이 다르다면 현재 최장 부분 수열의 길이 유지
				else {
					dp[aIndex][bIndex] = Math.max(dp[aIndex - 1][bIndex], dp[aIndex][bIndex - 1]);
				}

			}

		}

		// 3. 최장 부분 수열 길이 누적합 결과를 역추적하여 최장 부분 수열이 되는 문자열을 생성
		int aIndex = a.length();
		int bIndex = b.length();
		while (aIndex > 0 && bIndex > 0) {

			char aChar = a.charAt(aIndex - 1);
			char bChar = b.charAt(bIndex - 1);

			// 3-1. 두 문자열이 같다면 추가
			if (aChar == bChar) {
				sb.insert(0, aChar);
				aIndex--;
				bIndex--;
			}

			// 3-2. 두 문자열이 다르면 최장 부분 수열의 누적 기록을 가진 인덱스로 이동
			else if (dp[aIndex][bIndex] == dp[aIndex - 1][bIndex]) {
				aIndex--;
			} else {
				bIndex--;
			}

		}

		sb.insert(0, dp[a.length()][b.length()] + "\n");
		bw.write(sb.toString());
		bw.close();

	}

}