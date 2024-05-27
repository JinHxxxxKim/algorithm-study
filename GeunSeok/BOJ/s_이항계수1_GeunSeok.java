import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * BOJ_11050_이항계수1
 * @author parkrootseok
 *
 * N과 K가 주어지고 이항 계수를 구해라
 *
 * 1. N과 K를 받는다.
 * 2. 메모이제이션과 재귀를 이용하여 이항 계수를 구한다.
 **/
public class s_이항계수1_GeunSeok {

	public static final int MAX = 1_000_000;
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	public static String[] inputs;
	public static int[][] memoization;
	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. N과 K를 받는다.
		inputs = br.readLine().trim().split(" ");
		int N = Integer.parseInt(inputs[0]);
		int K = Integer.parseInt(inputs[1]);
		memoization = new int[N + 1][K + 1];

		// 2. 메모이제이션과 재귀를 이용하여 이항 계수를 구한다.
		sb.append(binomialCoefficient(N, K));
		bw.write(sb.toString());
		bw.close();

	}

	public static int binomialCoefficient(int N, int K) {

		// 이미 계산된 값이라면 바로 결과를 반환
		if (memoization[N][K] > 0) {
			return memoization[N][K];
		}

		// N과 K가 동일하거나 K가 0이라면 1을 반환
		if (N == K || K == 0) {
			return  1;
		}

		// 재귀함수 호출 후 결과를 메모이제이션 배열에 저장
		return memoization[N][K] = binomialCoefficient(N - 1, K - 1) + binomialCoefficient(N - 1, K);

	}

}
