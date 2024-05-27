import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * BOJ_2751_수졍렬하기2
 * @author parkrootseok
 *
 * N개의 수가 주어지고, 이를 오름차순 정렬해라
 * 단, 수는 절댓값이 1,000,000보다 작거나 같은 정수이며 중복되지 않는다.
 *
 * 1. 숫자의 개수 N을 받는다.
 * 2. N개의 숫자를 입력받고 해당 숫자에 대해 카운팅
 *  2-1. 절대값이 1,000,000보다 작으므로 MAX를 더하여 인덱싱을 한다.
 * 3. 카운팅된 숫자만 출력한다.
 *  3-1. 기존에 인덱싱을 위해 MAX를 더했으므로 뺀 값을 출력
 **/
public class s_수정렬하기2_GeunSeok {

	public static final int MAX = 1_000_000;
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;

	public static String[] inputs;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 숫자의 개수 N을 받는다.
		int N = Integer.parseInt(br.readLine());

		// 2. N개의 숫자를 입력받고 해당 숫자에 대해 카운팅
		int[] count = new int[MAX + MAX + 1];
		for (int index = 0; index < N; index++) {
			// 2-1. 절대값이 1,000,000보다 작으므로 MAX를 더하여 인덱싱을 한다.
			count[Integer.parseInt(br.readLine()) + MAX]++;
		}

		// 3. 카운팅된 숫자만 출력한다.
		for (int index = 0; index < count.length ; index++) {
			// 3-1. 기존에 인덱싱을 위해 MAX를 더했으므로 뺀 값을 출력
			if (count[index] == 1) {
				sb.append(index - MAX).append("\n");
			}
		}

		bw.write(sb.toString());
		bw.close();

	}

}
