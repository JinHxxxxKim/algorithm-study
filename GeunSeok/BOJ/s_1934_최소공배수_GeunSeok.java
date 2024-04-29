import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_1934_최소공배수
 * @author parkrootseok
 *
 * - A * B = GCD * LCM (두 수의 곱은 최대 공약수와 최소 공배수 곱과 같음)을 이용하여 풀
 *
 * 1. 두 수에 대한 테스트 케이스 횟수를 입력
 * 2. 두 수를 입력
 * 3. 두 수에 대한 최대 공약수를 구하기
 * 4. A * B / GCD = LCM를 활용하여 최소 공배수를 구하기
 **/
public class s_1934_최소공배수_GeunSeok {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int testCaseNumber;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 두 수에 대한 테스트 케이스 횟수를 입력
		testCaseNumber = Integer.parseInt(br.readLine().trim());

		for (int curTestCaseNumber = 0 ; curTestCaseNumber < testCaseNumber; curTestCaseNumber++) {

			// 2. 두 수를 입력
			inputs = br.readLine().trim().split(" ");

			int A = Integer.parseInt(inputs[0]);
			int B = Integer.parseInt(inputs[1]);

			// 3. 두 수에 대한 최대 공약수 구하기
			int gcd = gcd(B, A);

			// 4. A * B / GCD = LCM를 활용하여 최소 공배수 구하기
			sb.append( (A * B) / gcd).append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

	public static int gcd(int A, int B) {

		if (B == 0) {
			return A;
		}

		return gcd(B, A % B);

	}

}