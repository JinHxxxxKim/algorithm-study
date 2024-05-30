import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * BOJ_1300_K번째수
 * @author parkrootseok
 *
 * 이차원 배열의 크기인 N이 주어지고 이를 일차원 배열로 만든 후 오름차순 정렬했을 때 k번째 수를 구해라
 *
 * 1. N을 받는다.
 * 2. K를 받는다.
 * 3. 이진 탐색을 통해 B[K]보다 작거나 같은 숫자가 K개일 때를 탐색
 *  3-1. 현재 mid보다 작거나 같은수를 카운팅
 *  3-2. K보다 작거나 같은수가 많거나 동일하다면 오른쪽 범위를 축소
 *  3-3. K보다 작거나 같은수가 적다면 왼쪽 범위를 축소
 **/
public class s_K번째수_GeunSeok {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;

	public static int N;
	public static int K;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. N을 받는다.
		N = Integer.parseInt(br.readLine());

		// 2. K를 받는다.
		K = Integer.parseInt(br.readLine());

		// 3. 이진 탐색을 통해 B[K]보다 작거나 같은 숫자가 K개일 때를 탐색
		int number = binarySearch();

		sb.append(number);
		bw.write(sb.toString());
		bw.close();

	}

	public static int binarySearch() {

		int left = 1;
		int right = K;

		while (left < right) {

			int mid = (left + right) / 2;

			// 3-1. 현재 mid보다 작거나 같은수를 카운팅
			int count = 0;
			for (int row = 1; row <= N; row++) {
				// mid를 현재 행의 인덱스로 나누어 현재 행에서 mid보다 작은 값을 가지는 수의 개수를 구하여 누적합
				// - 2차원 배열은 i * j의 값으로 초기화가 이루어졌다. 즉, 1행은 1단을 의미한다.
				// - 즉, mid와 같거나 작은 단수에 존재하는 mid와 같거나 작은 숫자의 총 갯수를 의미한다.
				// - 예를 들어, mid를 3, row는 1이라 가정하면 3 / 1 = 3으로 1단(or 1행)에 3보다 작거나 같은수가 3개임을 의미한다.
				count += Math.min(N, mid / row);
			}

			// 3-2. K보다 작거나 같은수가 많거나 동일하다면 오른쪽 범위를 축소
			if (K <= count)  {
				right = mid;
			}

			// 3-3. K보다 작거나 같은수가 적다면 왼쪽 범위를 축소
			else {
				left = mid + 1;
			}

		}

		return left;

	}


}