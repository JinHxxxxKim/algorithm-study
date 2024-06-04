import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

/**
 * BOJ_2655_가장높은탑쌓기
 * @author parkrootseok
 *
 * - 밑면이 정사각형인 직육면체 벽돌들을 사용하여 탑을 쌓는다. (단, 규칙이 존재)
 * - 규칙
 *  - 회전 불가
 *  - 밑면의 넓이와 무게가 같은 벽돌은 없다
 *  - 밑면이 넓거나 무게가 무거운 벽돌은 반드시 밑에
 *
 * 1. 벽돌의 개수를 받는다.
 * 2. 벽돌에 대한 정보를 받는다.
 **/
public class s_2655_가장높은탑쌓기_GeunSeok {

	static class Brick implements Comparable<Brick> {

		int index;
		int bottom;
		int height;
		int weight;

		public Brick(int index, int bottom, int height, int weight) {
			this.index = index;
			this.bottom = bottom;
			this.height = height;
			this.weight = weight;
		}

		@Override
		public int compareTo(Brick o) {
			return Integer.compare(this.bottom, o.bottom);
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int brickNumber;
	static Brick[] bricks;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 벽돌의 개수를 받는다.
		brickNumber = Integer.parseInt(br.readLine().trim());

		// 2. 벽돌에 대한 정보를 받는다.
		bricks = new Brick[brickNumber + 1];
		bricks[0] = new Brick(0,0, 0, 0);
		for (int curBrick = 1; curBrick <= brickNumber; curBrick++) {

			inputs = br.readLine().trim().split(" ");
			bricks[curBrick] = new Brick(curBrick, Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2]));

		}

		/**
		 * 3. 벽돌을 밑면의 넓이를 기준으로 오름차순 정렬
		 * - 오름차순 정렬을 하는 이유는 벽돌을 맨 밑이 아닌 위에서 부터 쌓기 위함
		 * - 즉, 현재 벽돌 인덱스보다 크다면 해당 벽돌은 위에 쌓을 수 없음
		 **/
		Arrays.sort(bricks);

		// 4. 특정 벽돌이 가장 아래에 위치할 때 최적해를 구한다.
		int max = 0;
		int[] dp = new int[brickNumber + 1];
		for (int bottomBrick = 1; bottomBrick <= brickNumber; bottomBrick++) {

			// 4-1. 현재 벽돌 위에 올 수 있는 벽돌들을 순차적으로 탐색
			for (int topBrick = 0; topBrick < bottomBrick; topBrick++) {

				// 4-1. 현재 벽돌보다 무게가 작다면 위에 올 수 있는 벽돌임
				if (bricks[bottomBrick].weight > bricks[topBrick].weight) {

					// 무게가 적은 벽돌이랴면 현재 벽돌이 맨밑에 위치할 때 최대 높이를 최적해로 갱신
					dp[bottomBrick] = Math.max(dp[bottomBrick], dp[topBrick] + bricks[bottomBrick].height);

				}

			}

			max = Math.max(max, dp[bottomBrick]);

		}

		// 5. 가장 아래 쌓인 벽돌부터 찾는다.
		Stack<Integer> stack = new Stack<>();
		for (int curBrick = brickNumber; curBrick >= 1; curBrick--) {

			if (max == dp[curBrick]) {
				max -= bricks[curBrick].height;
				stack.add(bricks[curBrick].index);
			}

		}

		sb.append(stack.size()).append("\n");
		while (!stack.isEmpty()) {
			sb.append(stack.pop()).append("\n");
		}

		bw.write(sb.toString());
		bw.close();

	}

}