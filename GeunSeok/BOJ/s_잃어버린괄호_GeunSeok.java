import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_1541_잃어버린괄호
 * @author parkrootseok
 *
 * 괄호를 활용해 주어진 식의 값을 최소로 만들어라
 *
 * 1. 식을 입력받고 '-'를 기준으로 분리
 * 2. '-'를 기준으로 분리한 문자열 배열을 탐색
 *  2-1 '+'를 기준으로 분리한 후 모두 더하여
 *  2-2. 뺄셈 수행
 */
public class s_잃어버린괄호_GeunSeok {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 식을 입력받고 '-'를 기준으로 분리
		inputs = br.readLine().trim().split("-");

		// 2. '-'를 기준으로 분리한 문자열 배열을 탐색
		boolean isFirst = true;
		int result = 0;
		for (int index = 0; index < inputs.length; index++) {

			// 2-1 '+'를 기준으로 분리한 후 모두 더하여
			int sum = 0;
			String[] numbers = inputs[index].split("\\+");
			for (String number : numbers) {
				sum += Integer.parseInt(number);
			}

			// 2-2. 뺄셈 수행
			if (isFirst) {
				result = sum;
				isFirst = false;
			}

			else {
				result -= sum;
			}

		}

		sb.append(result);
		bw.write(sb.toString());
		bw.close();

	}


}
