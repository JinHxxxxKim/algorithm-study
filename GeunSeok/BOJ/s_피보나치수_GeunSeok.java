import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_2742_피보나치수
 * @author parkrootseok
 *
 * 피보나치 수열에 대한 N번째 자리 숫자 구하기
 *
 * 1. N 입력
 * 2. 피보나치 수열에서 N번째 자리 숫자 찾기
 */
public class s_피보나치수_GeunSeok {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;

	public static int N;
	public static int[] memoization;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. N 입력
		N = Integer.parseInt(br.readLine().trim());

		// 2. 피보나치 수열에서 N번째 자리 숫자 찾기
		memoization = new int[N + 1];
		sb.append(fibonacci(N));

		bw.write(sb.toString());
		bw.close();

	}

	public static int fibonacci(int n) {

		if (memoization[n] > 0) {
			return memoization[n];
		}

		if (n == 1 || n == 2) {
			return 1;
		}

		return memoization[n] = fibonacci(n - 1) + fibonacci(n-2);

	}

}
