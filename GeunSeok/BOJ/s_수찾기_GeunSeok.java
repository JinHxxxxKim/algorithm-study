import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * BOJ_1920_수찾기
 * @author parkrootseok
 *
 * 주어지는 숫자에 대해 배열 A에 존재 여부를 판단
 *
 * 1. 배열에 대한 정보 입력
 * 2. 존재 여부 판단
 */
public class s_수찾기_GeunSeok {

	public static final String EXIST = "1\n";
	public static final String NOT_EXIST = "0\n";

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;

	public static int N;
	public static int M;
	public static boolean[] numbers;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 배열에 대한 정보 입력
		N = Integer.parseInt(br.readLine().trim());
		Set<Integer> numbers = new HashSet<>();

		inputs = br.readLine().trim().split(" ");
		for (int index = 0; index < inputs.length; index++) {
			numbers.add(Integer.parseInt(inputs[index]));
		}

		// 2. 존재 여부 판단
		M = Integer.parseInt(br.readLine().trim());
		inputs = br.readLine().trim().split(" ");
		for (int index = 0; index < inputs.length; index++) {

			if (numbers.contains(Integer.parseInt(inputs[index]))) {
				sb.append(EXIST);
			}

			else {
				sb.append(NOT_EXIST);
			}

		}

		bw.write(sb.toString());
		bw.close();

	}


}
