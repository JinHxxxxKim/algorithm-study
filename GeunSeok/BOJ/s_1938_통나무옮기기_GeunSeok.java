import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * BOJ_1938_통나무옮기기
 * @author parkrootseok
 *
 * - 통나무
 *   - 상, 하, 좌, 우, 회전 가능
 *   - 단, 회전은 통나무를 둘러싸는 구역에 나무가 없어야함
 *
 * - 시작 지점에 있는 통나무를 끝으로 옮길 수 있는 최소 이동 횟수를 구해라
 *
 * 1. 맵의 크기를 받는다.
 * 2. 맵의 정보를 받고 시작 지점과 끝지점을 기록한다.
 * 3. BFS
 * 4. 통나무가 수직(ㅣ), 수평(ㅡ) 방향인지 확인 후 큐에 삽입
 * 5. 통나무 이동 시작
 *  5-1. 통나무가 끝에 도달했는지 확인 후 종료
 *  5-2. 4방향으로 이동(상, 하, 좌, 우)
 *  5-3. 방향 회전
 **/
public class s_1938_통나무옮기기_GeunSeok {

	static class Log implements Comparable<Log> {

		int count;
		Position mid;
		int direction;

		public Log(int count, Position position, int direction) {
			this.count = count;
			this.mid = position;
			this.direction = direction;
		}

		@Override
		public int compareTo(Log o) {
			return Integer.compare(o.count, this.count);
		}

	}

	static class Position {

		int row;
		int col;

		public Position(int row, int col) {
			this.row = row;
			this.col = col;
		}

	}

	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	static final char TREE = '1';
	static final char END = 'E';

	static final int LENGTH = 3;
	static final int HORIZONTAL = 0;
	static final int VERTICAL = 1;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static char[] inputs;

	static int size;
	static char[][] map;

	static int totalCount;
	static Position[] start;
	static Position[] end;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 맵의 크기를 받는다.
		size = Integer.parseInt(br.readLine().trim());

		// 2. 맵의 정보를 받고 시작 지점과 끝지점을 기록한다.
		map = new char[size][size];
		start = new Position[LENGTH];
		end = new Position[LENGTH];

		int sIdx = 0;
		int eIdx = 0;
		for (int row = 0; row < size; row++) {
			inputs = br.readLine().toCharArray();
			for (int col = 0; col < size; col++) {
				map[row][col] = inputs[col];

				if (map[row][col] == 'B') {
					start[sIdx++] = new Position(row, col);
				}

				if (map[row][col] == 'E') {
					end[eIdx++] = new Position(row, col);
				}
			}

		}

		// 3. BFS
		totalCount = 0;
		bfs();

		sb.append(totalCount);
		bw.write(sb.toString());
		bw.close();

	}

	public static boolean isValid(int row, int col) {

		if (0 <= row && row < size && 0 <= col && col < size) {
			return true;
		}

		return false;

	}

	public static boolean isMovable(int direction, int row, int col) {

		switch (direction) {

			case VERTICAL:

				if(row - 1 < 0 || row + 1 >= size) {
					return false;
				}

				if (map[row][col] == TREE || map[row - 1][col] == TREE || map[row + 1][col] == TREE) {
					return false;
				}

				break;

			case HORIZONTAL:

				if(col - 1 < 0 || col + 1 >= size) {
					return false;
				}

				if (map[row][col] == TREE || map[row][col - 1] == TREE || map[row][col + 1] == TREE) {
					return false;
				}

				break;

		}

		return true;

	}

	static public boolean isRotatable(int row, int col) {

		for (int curRow = row - 1; curRow <= row + 1; curRow++) {

			for (int curCol = col - 1; curCol <= col + 1; curCol++) {

				if (!isValid(curRow, curCol)) {
					return false;
				}

				if (map[curRow][curCol] == TREE) {
					return false;
				}

			}

		}

		return true;

	}

	public static void bfs() {

		boolean[][][] isVisited = new boolean[2][size][size];
		Queue<Log> logQ  = new ArrayDeque<>();

		// 4. 통나무가 수직(ㅣ), 수평(ㅡ) 방향인지 확인 후 큐에 삽입
		if (start[1].row + 1 == start[2].row) {
			logQ.add(new Log(0, start[1], VERTICAL));
		} else {
			logQ.add(new Log(0, start[1], HORIZONTAL));
		}

		// 5. 통나무 이동 시작
		while (!logQ.isEmpty()) {

			Log curLog = logQ.poll();
			int curDir = curLog.direction;
			int curRow =  curLog.mid.row;
			int curCol = curLog.mid.col;

			// 5-1. 통나무가 끝에 도달했는지 확인 후 종료
			if (end[1].row == curRow && end[1].col == curCol) {

				if (curDir == VERTICAL && map[curRow - 1][curCol] == END && map[curRow + 1][curCol] == END) {
					totalCount = curLog.count;
					return;
				}

				if (curDir == HORIZONTAL && map[curRow][curCol - 1] == END && map[curRow][curCol + 1] == END) {
					totalCount = curLog.count;
					return;
				}

			}

			// 5-2. 4방향으로 이동(상, 하, 좌, 우)
			for (int dir = 0; dir < dr.length; dir++) {

				int nRow = curRow + dr[dir];
				int nCol = curCol + dc[dir];

				// 유효한 인덱스인지 확인
				if (!isValid(nRow, nCol)) {
					continue;
				}

				// 방문한 곳인지 확인
				if (isVisited[curDir][nRow][nCol]) {
					continue;
				}

				// 다음 위치로 이동할 수 있는지 확인
				if (!isMovable(curDir, nRow, nCol)) {
					continue;
				}

				isVisited[curDir][nRow][nCol] = true;
				logQ.add(new Log(curLog.count + 1, new Position(nRow, nCol), curDir));

			}

			// 5-3. 방향 회전
			if (!isRotatable(curRow, curCol)) {
				continue;
			}

			if (curDir == VERTICAL && !isVisited[HORIZONTAL][curRow][curCol]) {
				isVisited[HORIZONTAL][curRow][curCol] = true;
				logQ.add(new Log(curLog.count + 1, new Position(curRow, curCol), HORIZONTAL));
			}

			if (curDir == HORIZONTAL && !isVisited[VERTICAL][curRow][curCol]) {
				isVisited[VERTICAL][curRow][curCol] = true;
				logQ.add(new Log(curLog.count + 1, new Position(curRow, curCol), VERTICAL));
			}

		}

	}

}