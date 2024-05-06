package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * BFS, DP
 * 
 * 1. N을 입력받는다.
 * 
 * 기차의 중심점을 기준으로 방문처리를 진행
 * 즉, 3차원의 방문 배열을 사용(N x N x 2{기차의 상태: 수직, 수평})
 *   -> 해당 위치에서 해당 방향으로 놓인 적이 있다면, ture
 * 
 * 현재 기차 위치에서 5가지 기능에 대한 수행 가능성을 판단한다.
 * 수행 가능하다면, 수행 한 뒤의 기차 정보를 방문 처리 후, Queue에 넣는다.
 * Queue가 비지 않을 때까지, 또는 목적지에 도달할 때까지 반복 수행
 *
 */
public class s1938_통나무옮기기_JinHyung {
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int TREE = 1;
	static final int HORIZONTAL = 0;
	static final int VERTICAL = 1;
	static final int[] dx = { -1, 1, 0, 0 };
	static final int[] dy = { 0, 0, -1, 1 };

	static int N;
	static char[][] map;
	static boolean[][][] isVisited;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine().trim());
		map = new char[N][N];
		for (int row = 0; row < N; ++row) {
			String rowInfo = br.readLine().trim();
			for (int col = 0; col < N; ++col) {
				map[row][col] = rowInfo.charAt(col);
			}
		}
		Train initTrain = null;
		boolean findTrain = false;
		
		// 초기 기차 정보 저장
		for (int row = 0; row < N; ++row) {
			for (int col = 0; col < N; ++col) {
				if (map[row][col] == 'B') {
					// 오른쪽, 아래를 확인
					if (col + 1 < N && map[row][col + 1] == 'B') {
						initTrain = new Train(row, col + 1, 0, HORIZONTAL);
					} else {
						initTrain = new Train(row + 1, col, 0, VERTICAL);
					}
					findTrain = true;
				}
				if (findTrain)
					break;
			}
			if (findTrain)
				break;
		}
		
		
		// == 로직 == //
		int ans = 0;
		isVisited = new boolean[N][N][2];
		Queue<Train> q = new ArrayDeque<>();
		q.offer(initTrain);
		isVisited[initTrain.row][initTrain.col][initTrain.status] = true;
		while(!q.isEmpty()) {
			Train currTrain = q.poll();
			// 도착지 검사
			if(currTrain.isArrive()) {
				ans = currTrain.opCnt;
				break;
			}

			// U 연산 가능 여부 확인
			if (currTrain.canMoveUp() && !isVisited[currTrain.row - 1][currTrain.col][currTrain.status]) {
				isVisited[currTrain.row - 1][currTrain.col][currTrain.status] = true;
				q.offer(new Train(currTrain.row - 1, currTrain.col, currTrain.opCnt + 1, currTrain.status));
			}
			// D 연산 가능 여부 확인
			if (currTrain.canMoveDown() && !isVisited[currTrain.row + 1][currTrain.col][currTrain.status]) {
				isVisited[currTrain.row + 1][currTrain.col][currTrain.status] = true;
				q.offer(new Train(currTrain.row + 1, currTrain.col, currTrain.opCnt + 1, currTrain.status));
			}
			// L 연산 가능 여부 확인
			if (currTrain.canMoveLeft() && !isVisited[currTrain.row][currTrain.col - 1][currTrain.status]) {
				isVisited[currTrain.row][currTrain.col - 1][currTrain.status] = true;
				q.offer(new Train(currTrain.row, currTrain.col - 1, currTrain.opCnt + 1, currTrain.status));
			}
			// R 연산 가능 여부 확인
			if (currTrain.canMoveRight() && !isVisited[currTrain.row][currTrain.col + 1][currTrain.status]) {
				isVisited[currTrain.row][currTrain.col + 1][currTrain.status] = true;
				q.offer(new Train(currTrain.row, currTrain.col + 1, currTrain.opCnt + 1, currTrain.status));
			}
			// T 연산 가능 여부 확인
			if (currTrain.canTurn() && !isVisited[currTrain.row][currTrain.col][(currTrain.status + 1)%2]) {
				isVisited[currTrain.row][currTrain.col][(currTrain.status + 1)%2] = true;
				q.offer(new Train(currTrain.row, currTrain.col, currTrain.opCnt + 1, (currTrain.status + 1)%2));
			}			
		}
		System.out.println(ans);
	}

	static class Train {
		int row, col, opCnt, status;

		public Train(int row, int col, int opCnt, int status) {
			super();
			this.row = row;
			this.col = col;
			this.opCnt = opCnt;
			this.status = status;
		}

		public boolean canTurn() {
			boolean ret = true;
			// 범위 체크
			if (row - 1 < 0 || row + 1 >= N || col - 1 < 0 || col + 1 >= N) {
				ret = false;
			}
			// 유효성(나무) 검사 (9 구역)
			if (ret) {
				for (int r = row - 1; r < row + 2; ++r) {
					for (int c = col - 1; c < col + 2; ++c) {
						
						if(map[r][c] == '1') {
							ret = false;
						}
							
					}
				}
			}
			return ret;
		}

		public boolean canMoveRight() {
			boolean ret = true;
			// 범위 체크 및 유효성(나무) 검사
			if (status == HORIZONTAL) {
				if(col + 2 >= N || map[row][col + 2] == '1')
					ret = false;
			}
			// 범위 체크 및 유효성(나무) 검사
			if (status == VERTICAL) {
				if (col + 1 >= N || map[row][col + 1] == '1' 
						 		 || map[row + 1][col + 1] == '1'
						 		 || map[row - 1][col + 1] == '1')
					ret = false;
			}
			return ret;
		}

		public boolean canMoveLeft() {
			boolean ret = true;
			// 범위 체크 및 유효성(나무) 검사
			if (status == HORIZONTAL) {
				if(col - 2 < 0 || map[row][col - 2] == '1')
					ret = false;
			}
			// 범위 체크 및 유효성(나무) 검사
			if (status == VERTICAL) {
				if (col - 1 < 0 || map[row][col - 1] == '1' 
						 		|| map[row + 1][col - 1] == '1'
						 		|| map[row - 1][col - 1] == '1')
					ret = false;
			}
			return ret;
		}

		public boolean canMoveDown() {
			boolean ret = true;
			// 범위 체크 및 유효성(나무) 검사
			if (status == HORIZONTAL) {
				if (row + 1 >= N || map[row + 1][col] == '1' 
								 || map[row + 1][col - 1] == '1'
								 || map[row + 1][col + 1] == '1')
					ret = false;
			}
			// 범위 체크 및 유효성(나무) 검사
			if (status == VERTICAL) {
				if (row + 2 >= N || map[row + 2][col] == '1')
					ret = false;
			}
			return ret;
		}

		public boolean canMoveUp() {
			boolean ret = true;
			if (status == HORIZONTAL) {
				// 범위 체크 및 유효성(나무) 검사
				if (row - 1 < 0 || map[row - 1][col] == '1' 
								|| map[row - 1][col - 1] == '1'
								|| map[row - 1][col + 1] == '1')
					ret = false;
			}
			if (status == VERTICAL) {
				// 범위 체크 및 유효성(나무) 검사
				if (row - 2 < 0 || map[row - 2][col] == '1')
					ret = false;
			}
			return ret;
		}

		// 도착지 검사
		public boolean isArrive() {
			boolean ret = false;
			if (map[row][col] == 'E') {
				if (status == HORIZONTAL) {
					if (map[row][col - 1] == 'E' && map[row][col + 1] == 'E')
						ret = true;
				} else {
					if (map[row - 1][col] == 'E' && map[row + 1][col] == 'E')
						ret = true;
				}
			}
			return ret;
		}
		

		@Override
		public String toString() {
			return "Train [row=" + row + ", col=" + col + ", opCnt=" + opCnt + ", status=" + status + "]";
		}
		
	}
}