import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_11047_동전0
 * @author parkrootseok
 *
 * 가지고 있는 동전으로 K를 만들 수 있을 때 사용할 수 있는 최소 갯수
 *
 * 1. 동전 개수와 목표 합계를 입력
 * 2. 동전 종류 입력
 * 3. 가장 큰 금액부터 필요한 개수를 구하여 누적합
 *  3-1. 현재 동전이 몇개 사용될 수 있는지 구하기
 *  3-2. 사용한 개수를 더하고 사용한 금액을 제외
 */
public class s_동전0_GeunSeok {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;;

	public static int coinNumber;
	public static int[] coins;
	public static int target;


	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 동전 개수와 목표 합계를 입력
		inputs = br.readLine().trim().split(" ");
		coinNumber = Integer.parseInt(inputs[0]);
		target = Integer.parseInt(inputs[1]);

		// 2. 동전 종류 입력
		coins = new int[coinNumber];
		for (int index = 0 ; index < coinNumber; index++) {
			coins[index] = Integer.parseInt(br.readLine().trim());
		}

		// 3. 가장 큰 금액부터 필요한 개수를 구하여 누적합
		int totalCount = 0;
		for (int index = coinNumber - 1; 0 <= index; index--) {

			// 3-1. 현재 동전이 몇개 사용될 수 있는지 구하기
			int count = target / coins[index];

			if (count == 0) {
				continue;
			}

			// 3-2. 사용한 개수를 더하고 사용한 금액을 제외
			target -= count * coins[index];
			totalCount += count;

		}

		sb.append(totalCount);
		bw.write(sb.toString());
		bw.close();

	}

}
