import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_2018_수들의합5
 * @author parkrootseok
 *
 * - 자연수 N을 만들 수 있는 수열의 집합 개수를 구해라
 *
 * 1. 숫자를 입력
 * 2. dfs를 통해 경우의 수를 조사
 */
public class s_수들의합5_GeunSeok {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String inputs[];

	public static int number;
	public static int count;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 숫자를 입력
		number = Integer.parseInt(br.readLine().trim());

		// 2. dfs를 통해 경우의 수를 조사
		count = 1;
		for (int curNumber = 1; curNumber <= number / 2; curNumber++) {
			dfs(curNumber, curNumber);
		}

		sb.append(count);
		bw.write(sb.toString());
		bw.close();

	}

	public static void dfs(int preNumber, int sum) {

		preNumber++;

		if (sum == number) {
			count++;
			return;
		}

		if (sum + preNumber > number) {
			return;
		}

		dfs(preNumber,sum + preNumber);

	}





}