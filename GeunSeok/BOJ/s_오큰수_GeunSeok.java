import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;

/**
 * BOJ_17298_오큰수
 * @author parkrootseok
 *
 * 현재 자신을 초과하는 가장 첫 숫자를 구하라
 *
 * 1. 숫자의 갯수를 입력
 * 2. 숫자 정보를 입력받으면서 스택을 사용하여 오큰수에 대한 정보를 저장
 */
public class s_오큰수_GeunSeok {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;;

	public static int size;
	public static int[] numbers;
	public static int[] positions;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 숫자의 갯수를 입력
		size = Integer.parseInt(br.readLine().trim());

		// 2. 숫자 정보를 입력받으면서 스택을 사용하여 오큰수에 대한 정보를 저장
		numbers = new int[size];
		positions = new int[size];
		Arrays.fill(positions, -1);

		Stack<Integer> numberStack = new Stack<>();
		inputs = br.readLine().trim().split(" ");
		for (int curIndex = 0; curIndex < size; curIndex++) {

			numbers[curIndex] = Integer.parseInt(inputs[curIndex]);

			while (!numberStack.isEmpty() && true) {

				int index = numberStack.peek();
				int number = numbers[index];

				// 우선순위가 가장 높은 값보다 크다면 오큰수 기록 후 스택에서 제거
				if (number < numbers[curIndex]) {
					positions[index] = numbers[curIndex];
					numberStack.pop();
				}

				// 더 작은 값이라면 탈출
				else {
					break;
				}

			}

			numberStack.add(curIndex);

		}

		for (int number : positions) {
			sb.append(number).append(" ");
		}

		bw.write(sb.toString());
		bw.close();

	}

}
