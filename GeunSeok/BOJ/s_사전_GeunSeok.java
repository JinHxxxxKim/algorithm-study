import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_1256_사전
 * @author parkrootseok
 *
 * - 모든 문자열은 N개의 a와 M개의 z로 구성
 * - 사전은 알파벳 순서대로 정렬
 * - K번쨰에 있는 문자열을 구해라
 *
 * 1. a의 개수, z 개수, 찾으려고 하는 순번에 대한 정보 입력
 * 2. 주어진 문자열로 만들 수 있는 조합의 경우의 수를 구한 후 가능 여부를 체크
 * 	2-1. 목표 순번보다 경우의 수가 더 작다면 찾을수 없으므로 종료
 * 	2-2. 가능한 경우 목표 순번에 위치하는 문자열 구하기
 */
public class s_사전_GeunSeok {

	static final int MAX_NUMBER = 1_000_000_000;

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String inputs[];

	public static int aNumber;
	public static int bNumber;
	public static int targetOrder;
	public static int[][] memoization;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. a의 개수, z 개수, 찾으려고 하는 순번에 대한 정보 입력
		inputs = br.readLine().trim().split(" ");
		aNumber = Integer.parseInt(inputs[0]);
		bNumber = Integer.parseInt(inputs[1]);
		targetOrder = Integer.parseInt(inputs[2]);

		// 2. 주어진 문자열로 만들 수 있는 조합의 경우의 수를 구한 후 가능 여부를 체크
		memoization = new int[101][101];
		if (isPossible(aNumber, bNumber) < targetOrder) {
			// 2-1. 목표 순번보다 경우의 수가 더 작다면 찾을수 없으므로 종료
			sb.append("-1");
		}

		else {
			// 2-2. 가능한 경우 목표 순번에 위치하는 문자열 구하기
			findString(aNumber, bNumber, targetOrder);
		}

		bw.write(sb.toString());
		bw.close();

	}

	public static int isPossible(int N, int K) {

		if (N == 0 | K == 0) {
			// N 또는 K가 0이라도 N 또는 K로만 존재하는 경우가 존재하기 때문에 1을 기록
			memoization[N][K] = 1;
			return 1;
		}

		if (memoization[N][K] > 0) {
			return memoization[N][K];
		}

		return memoization[N][K] = Math.min(isPossible(N - 1, K) + isPossible(N, K-1), MAX_NUMBER + 1);

	}

	public static void findString(int N, int K, int targetOrder) {

		// 모든 a를 다 사용한 경우
		if (N == 0) {

			// 뒤에 사용할 수 있는 a를 모두 추가
			for (int index = 0; index < K; index++) {
				sb.append("z");
			}

			return;

		}

		// 모든 z를 사용한 경우
		if (K == 0) {

			// 뒤에 사용할 수 있는 z를 모두 추가
			for (int index = 0; index < N; index++) {
				sb.append("a");
			}

			return;

		}

		// a를 사용했을 때 목표 순번보다 작은 경우
		// - 현재 문자열 위치에 'a'를 사용했을 때 나올 수 있는 조합의 수가 목표하는 순번보다 작다는 것은 'a'가 아닌 'z'가 들어가야 함을 의미
		if (memoization[N - 1][K] < targetOrder) {
			// z를 사용해야함
			sb.append("z");

			// z를 사용했을 경우 a를 사용한 경우를 제거
			findString(N, K - 1, targetOrder - memoization[N - 1][K]);
		}

		// a를 사용했을 때 목표 순번보다 크거나 같은 경우의 수를 가지는 경우
		else  {
			// a를 사용해야함
			sb.append("a");
			findString(N - 1, K, targetOrder);
		}

	}

}