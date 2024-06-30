import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * BOJ_2178_미로탐색
 * @author parkrootseok
 *
 * 시작 지점에서 종료 지점까지 도착하기 위해서 필요한 최소 칸 수를 구해라
 *
 * 1. 미로에 대한 정보 입력
 * 2. BFS 탐색을 통해 최소 칸 수를 계산
 */
public class s_미로탐색_GeunSeok {

	public static class Node {

		int row;
		int col;
		int count;

		public Node(int row, int col, int count) {
			this.row = row;
			this.col = col;
			this.count = count;
		}

	}

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;

	public static int[] dr = {1, -1, 0, 0};
	public static int[] dc = {0, 0, 1, -1};

	public static int[][] maze;
	public static int rowSize;
	public static int colSize;
	public static boolean[][] isVisited;
	public static int answer;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 미로에 대한 정보 입력
		inputs = br.readLine().trim().split(" ");
		rowSize = Integer.parseInt(inputs[0]);
		colSize = Integer.parseInt(inputs[1]);

		maze = new int[rowSize][colSize];
		isVisited = new boolean[rowSize][colSize];

		for (int row = 0; row < rowSize; row++) {
			inputs = br.readLine().trim().split("");
			for (int col = 0; col < colSize; col++) {
				maze[row][col] = Integer.parseInt(inputs[col]);
			}
		}

		// 2. BFS 탐색을 통해 최소 칸 수를 계산
		bfs();

		sb.append(answer);
		bw.write(sb.toString());
		bw.close();

	}

	public static boolean isValid(int row, int col) {
		return 0 <= row && row < rowSize && 0 <= col && col < colSize;
	}


	public static void bfs() {

		Queue<Node> nodeQ = new ArrayDeque<>();
		nodeQ.add(new Node(0, 0,  1));

		while(!nodeQ.isEmpty()) {

			Node curNode = nodeQ.poll();

			if (curNode.row == rowSize - 1 && curNode.col == colSize - 1) {
				answer = curNode.count;
				return;
			}

			for (int dir = 0; dir < dr.length; dir++) {

				int nextRow = curNode.row + dr[dir];
				int nextCol = curNode.col + dc[dir];

				if (!isValid(nextRow, nextCol)) {
					continue;
				}

				if (maze[nextRow][nextCol] == 0) {
					continue;
				}

				if (isVisited[nextRow][nextCol]) {
					continue;
				}

				isVisited[nextRow][nextCol] = true;
				nodeQ.add(new Node(nextRow, nextCol, curNode.count + 1));

			}

		}

	}

}
