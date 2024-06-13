import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_1947_선물전달
 * @author parkrootseok
 *
 * N명에게 N개의 선물을 전달하는 경우의 수를 구해라 (단, 동일한 번호를 가지는 선물은 불가)
 *
 * 1. N을 입력
 * 2. 완전 순열 점화식을 통해 경우의 수를 구하자
 *  2-1. 완전 순열 점화식을 통해 N에 대한 경우의 수를 계산
 *  2-2. 1,000,000,000으로 나눈 나머지값을 저장
 */
public class s_선물전달_GeunSeok {

	public static int MOD = 1_000_000_000;

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;;

	public static int N;
	public static long[] derangementSequence;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. N을 입력
		N = Integer.parseInt(br.readLine().trim());

		// 2. 완전 순열 점화식을 통해 경우의 수를 구하자
		derangementSequence = new long[Math.max(3, N + 1)];

		derangementSequence[1] = 0;
		derangementSequence[2] = 1;

		for (int n = 3 ; n <= N ; n++) {

			// 2-1. 완전 순열 점화식을 통해 N에 대한 경우의 수를 계산
			derangementSequence[n] = (n - 1) * (derangementSequence[n - 2] + derangementSequence[n - 1]);

			// 2-2. 1,000,000,000으로 나눈 나머지값을 저장
			derangementSequence[n] %= MOD;

		}

		sb.append((derangementSequence[N]));
		bw.write(sb.toString());
		bw.close();

	}

}
