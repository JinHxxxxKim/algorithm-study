import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * BOJ_11660_구간합구하기5
 * @author parkrootseok
 *
 * 1. 표의 크기와 구해야하는 구간 합의 개수를 받는다.
 * 2. 표에 대한 정보를 받으면서 동일한 행에 대해 누적합을 구한다.
 * 3. 동일한 열에 대한 누적합을 구한다.
 * 4. 구간에 대한 정보를 받는다.
 * 5. 구간합을 구한다.
 **/
public class s_11660_구간합구하기5_GeunSeok {

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int[][] map;
	static int size;
	static int number;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 표의 크기와 구해야하는 구간 합의 개수를 받는다.
		inputs = br.readLine().trim().split(" ");
		size = Integer.parseInt(inputs[0]);
		number = Integer.parseInt(inputs[1]);

		// 2. 표에 대한 정보를 받으면서 동일한 행에 대해 누적합을 구한다.
		map = new int[size + 1][size + 1];
		for (int row = 1; row <= size; row++) {

			inputs = br.readLine().trim().split(" ");
			for (int col = 1; col <= size; col++) {
				map[row][col] = Integer.parseInt(inputs[col - 1]) + map[row][col - 1];
			}

		}

		// 3. 동일한 열에 대한 누적합을 구한다.
		for (int col = 1; col <= size; col++) {
			for (int row = 1; row <= size; row++) {
				map[row][col] += map[row - 1][col];
			}
		}


		for (int curNumber = 0; curNumber < number; curNumber++) {

			// 4. 구간에 대한 정보를 받는다.
			inputs = br.readLine().trim().split(" ");
			int x1 = Integer.parseInt(inputs[0]);
			int y1 = Integer.parseInt(inputs[1]);
			int x2 = Integer.parseInt(inputs[2]);
			int y2 = Integer.parseInt(inputs[3]);

			// 5. 구간합을 구한다.
			sb.append(map[x2][y2] - map[x2][y1 - 1] - map[x1 -1][y2] + map[x1 - 1][y1 - 1]).append("\n");

		}

		bw.write(sb.toString());
		bw.close();
		return;

	}

}