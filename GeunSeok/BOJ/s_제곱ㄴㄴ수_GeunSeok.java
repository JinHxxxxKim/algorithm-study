import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_1016_제곱ㄴㄴ수
 * @author parkrootseok
 *
 * 어떤 정수가 주이지고 1보다 큰 제곱수로 나누어 떨어지지 않는 수를 제곱ㄴㄴ수라 한다.
 * 범위가 주어지고 포함되는 제곱 ㄴㄴ수의 수를 구해라
 *
 * 1. 범위 입력
 * 2. 에라토스테네스의 체를 활용하여 제곱수로 나누어 떨어지는 경우를 확인
 *  2-1. 현재 제곱수를 구하고
 *  2-2. 현재 제곱수로 나눌 수 있는 가장 첫 시작점을 계산
 *  2-3. 시작점부터 인덱스를 증가하여 제곱수로 나누어 떨어지는 수를 체크
 * isPow 배열을 확인해 제곱ㄴㄴ수 갯수를 계산
 */
public class s_제곱ㄴㄴ수_GeunSeok {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;;

	public static long min;
	public static long max;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 범위 입력
		inputs = br.readLine().trim().split(" ");
		min = Long.parseLong(inputs[0]);
		max = Long.parseLong(inputs[1]);

		// 2. 에라토스테네스의 체를 활용하여 제곱수로 나누어 떨어지는 경우를 확인
		boolean[] isPow = new boolean[(int) (max - min + 1)];
		for (long number = 2; number <= Math.sqrt(max); number++) {

			// 2-1. 현재 제곱수를 구하고
			long pow = (long) Math.pow(number, 2);

			// 2-2. 현재 제곱수로 나눌 수 있는 가장 첫 시작점을 계산
			long start = min / pow;

			// 단, 나머지가 0인 아닌 경우 버려진 소수점 아래 자리수를 보정
			if (min % pow != 0) {
				start++;
			}

			// 2-3. 시작 지점부터 인덱스를 증가하여 제곱수로 나누어 떨어지는 수를 체크
			for (long quotient = start; quotient * pow <= max; quotient++) {
				if (!isPow[(int) (quotient * pow - min)]) {
					isPow[(int) (quotient * pow - min)] = true;
				}
			}

		}

		// 3. isPow 배열을 확인해 제곱ㄴㄴ수 갯수를 계산
		int totalCount = 0;
		for (long index = min; index <= max; index++) {

			if (!isPow[(int) (index - min)]) {
				totalCount++;
			}

		}

		sb.append(totalCount++);
		bw.write(sb.toString());
		bw.close();

	}

}
