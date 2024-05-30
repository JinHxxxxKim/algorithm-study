import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

/**
 * CT_루돌프의반란
 * @author parkrootseok
 *
 * 루돌프
 * - 가장 가까운 산타를 향해 1칸 돌진
 *  - 만약, 2개 이상이 존재하면 r좌표가 큰 산타로 돌진하고 동일하면 c좌표가 큰 산타를 향해 돌진
 *  - 8방향으로 움직일 수 있음
 *  - 8방향 중 가장 가까워 지는 방향으로 한 칸 돌진
 *
 * 산타
 *  - 1번부터 순차적으로 이동 시작
 *  - 루돌프와 가까운 방향으로 1칸 이동
 *  - 만약, 루돌프와 가까워 질 수 없다면 이동하지 않음
 *  - 4방향으로 움직일 수 있음
 *  - 가능한 방향이 여러 개라면 상 -> 우 -> 하 -> 좌 우선순위에 맞게 이동
 *
 * 충돌
 *  - 루돌프가 움직여서 발생한 경우 -> 산타 C 점수 획득 -> 산타는 루돌프가 이동한 방향으로 C만큼 이동
 *  - 산타가 움직여서 발생한 경우 -> 산타는 D 만큼 획득 -> 산타는 자신이 이동한 '반대' 방향으로 D만큼 이동
 *  - 밀려난 칸에 다른 산타가 있는 경우 상호작용이 발생
 *
 * 상호작용
 *  - 기존 칸에 있던 산타는 들어온 산타와 동일한 방향으로 1칸 밀려남
 *  - 기존 산타가 밀려난 곳에 또 산타가 있다면 연쇄적으로 작용함
 *
 * 기절
 *  - 산타는 루돌프와 충돌 후 기절
 *  - 기절은 K + 1(다음 턴)까지 K + 2(다다음 턴)부터는 다시 정상
 *  - 움직일 수 없지만 상호작용에는 반응
 *  - 루돌프는 기절한 산타에게도 돌진 가능
 *
 * 1. 입력
 *  1-1. 크기, 턴 수, 산타수, 루돌프 힘, 산타 힘 입력
 *  1-2. 루돌프 초기 위치 입력 후 루돌프 객체 생성
 *  1-3. 산타 번호, 초기 위치를 받고 산타 객체를 생성
 *  2. 게임 진행
 *   2-1. 루돌프 이동 시작
 *     2-1-1. 가장 가까운 위치에 존재하는 산타 찾기
 *     2-1-2. 찾은 산타를 향해 갈 방향을 결정(단, 산타와 가장 가까운 곳으로 이동)
 *     2-1-3. 이동 방향이 정해졌다면 이동
 *     2-1-4. 이동이 끝났다면 충돌이 발생한 산타가 있는지 확인
 *     2-1-5. 충돌이 발생한 하면서 산타가 탈락하지 않았다면 상호 작용 수행
 *   2-2. 산타 이동 시작
 *    2-2-1. 루돌프와 가장 거리가 가까운 방향 찾기
 *    2-2-2. 이동할 수 있는 방향이 존재할 경우 이동
 *    2-2-3. 충돌이 발생한 경우에 대한 후처리 진행
 *   2-3. 턴 종료에 따른 후처리 작업 진행
 *    2-3-1. 살아있는 산타들에게 점수를 부여하고 게임 종료 여부를 확인
 *    2-3-2. 기절 상태 산타들의 상태를 확인하고 정상상태로 초기화
 **/
public class s_루돌프의반란_GeunSeok {

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;

	public static int size;
	public static int totalTurnNumber;
	public static int santaNumber;
	public static int santaPower;
	public static int ludolphPower;

	public static Santa[] santas;
	public static Ludolph ludolph;

	public static void input() throws IOException {

		//  1-1. 크기, 턴 수, 산타수, 루돌프 힘, 산타 힘 입력
		inputs = br.readLine().trim().split(" ");

		size = Integer.parseInt(inputs[0]);
		totalTurnNumber = Integer.parseInt(inputs[1]);
		santaNumber = Integer.parseInt(inputs[2]);
		ludolphPower = Integer.parseInt(inputs[3]);
		santaPower = Integer.parseInt(inputs[4]);

		//  1-2. 루돌프 초기 위치 입력 후 루돌프 객체 생성
		inputs = br.readLine().trim().split(" ");
		ludolph = new Ludolph(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));

		//  1-3. 산타 번호, 초기 위치를 받고 산타 객체를 생성
		santas = new Santa[santaNumber];
		for (int index = 0; index < santaNumber; index++) {
			inputs = br.readLine().trim().split(" ");
			int number = Integer.parseInt(inputs[0]);
			int row = Integer.parseInt(inputs[1]);
			int col = Integer.parseInt(inputs[2]);

			santas[number - 1] = new Santa(number, row, col);
		}

	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 입력
		input();

		// 2. 받은 턴 수 만큼 게임 진행
		for (int curTurnNumber = 1; curTurnNumber <= totalTurnNumber; curTurnNumber++) {

			// 2-1. 루돌프 이동 시작
			ludolph.move();

			// 2-2. 산타 이동 시작
			for (Santa santa : santas) {

				if (!santa.isMovable || !santa.isAlive) {
					continue;
				}

				santa.move();

			}

			// 2-3. 턴 종료에 따른 후처리 작업 진행
			boolean isFinished = true;
			for (Santa santa : santas) {

				// 2-3-1. 살아있는 산타들에게 점수를 부여하고 게임 종료 여부를 확인
				if (santa.isAlive) {
					santa.score++;
					isFinished = false;
				}

				// 2-3-2. 기절 상태 산타들의 상태를 확인하고 정상상태로 초기화
				if (!santa.isMovable) {

					// 증가한 값이 2라면 상태 초기화
					if (++santa.delay == 2) {
						santa.isMovable = true;
						santa.delay = 0;
					}

				}

			}

			if (isFinished) {
				break;
			}

		}

		for (Santa santa : santas) {
			sb.append(santa.score).append(" ");
		}

		bw.write(sb.toString());
		bw.close();

	}

	public static class Position {

		int row;
		int col;

		public Position(int row, int col) {
			this.row = row;
			this.col = col;
		}

		public boolean isValid() {
			return 1 <= row && row <= size && 1 <= col && col <= size;
		}

		public boolean isEmpty() {

			for (Santa santa : santas) {

				if (santa.isAlive && this.row == santa.pos.row && this.col == santa.pos.col) {
					return false;
				}

			}

			return true;

		}

		public int getDistance(Position pos) {
			return (int) (Math.pow((this.row - pos.row), 2) + Math.pow((this.col - pos.col), 2));
		}

	}

	public static class Ludolph {

		int[] dr = {1, -1, 0, 0, 1, 1, -1, -1};
		int[] dc = {0, 0, 1, -1, 1, -1, 1, -1};

		Position pos;

		public Ludolph(int row, int col) {
			this.pos = new Position(row, col);
		}

		public void move() {

			// 2-1-1. 가장 가까운 위치에 존재하는 산타 찾기
			PriorityQueue<Santa> santaPQ = new PriorityQueue<>();
			int minDistance = Integer.MAX_VALUE;

			for (Santa santa : santas) {

				// 이미 탈락한 산타는 스킵
				if (!santa.isAlive) {
					continue;
				}

				// 현재 산타와 거리를 구한 후
				int distance = santa.pos.getDistance(this.pos);

				// 최소값인지 확인
				if (minDistance > distance) {
					// 최소값과 PQ를 갱신한 후 추가
					minDistance = distance;
					santaPQ.clear();
					santaPQ.add(santa);
				}

				// 동일한 거리에 있는 산타라면
				else if (minDistance == distance) {
					// PQ에 추가
					santaPQ.add(santa);
				}

			}

			// 2-1-2. 찾은 산타를 향해 갈 방향을 결정(단, 산타와 가장 가까운 곳으로 이동)
			Santa findSanta = santaPQ.peek();
			minDistance = Integer.MAX_VALUE;

			int moveDirection = -1;
			for (int dir = 0; dir < this.dr.length; dir++) {

				// 다음 이동할 위치
				Position nextPosition = new Position(this.pos.row + this.dr[dir],  this.pos.col + this.dc[dir]);

				// 다음 이동할 위치가 인덱스를 벗어나는 경우 이동 불가
				if (!nextPosition.isValid()) {
					continue;
				}

				// 다음 이동할 위치와 가장 가까운 산타와의 거리 차이
				int distance = nextPosition.getDistance(findSanta.pos);

				// 최소값읹지 확인 후 방향을 기록
				if (minDistance > distance) {
					minDistance = distance;
					moveDirection = dir;
				}

			}

			// 2-1-3. 이동 방향이 정해졌다면 이동
			this.pos.row += this.dr[moveDirection];
			this.pos.col += this.dc[moveDirection];

			// 4. 이동이 끝났다면 충돌이 발생한 산타가 있는지 확인
			Santa conflictSanta = null;
			for (Santa santa : santas) {

				// 충돌한 산타는
				if (santa.isAlive && this.pos.row == santa.pos.row && this.pos.col == santa.pos.col) {
					conflictSanta = santa;

					// 루돌프의 힘만큼 점수를 획득하고
					conflictSanta.score += ludolphPower;

					// 루돌프와 같은 방향으로 C칸 만큼 밀리고
					conflictSanta.pos.row += (this.dr[moveDirection] * ludolphPower);
					conflictSanta.pos.col += (this.dc[moveDirection] * ludolphPower);

					// 기절 상태
					conflictSanta.isMovable = false;
					conflictSanta.delay = 0;

					break;
				}

			}

			// 2-1-5. 충돌이 발생한 하면서 산타가 탈락하지 않았다면 상호 작용 수행
			if (conflictSanta != null) {

				while (true) {

					conflictSanta.checkFinish();
					boolean isConflict = false;

					// 충돌이 발생한 산타와 동일한 위치에 존재하는 산타가 있는지 확인
					for (Santa santa : santas) {

						if (conflictSanta.number == santa.number)  {
							continue;
						}

						// 충돌이 발생한 산타가 있다면
						if (conflictSanta.pos.row == santa.pos.row && conflictSanta.pos.col == santa.pos.col) {
							// 루돌프가 이동한 방향으로 1칸 이동
							santa.pos.row += this.dr[moveDirection];
							santa.pos.col += this.dc[moveDirection];

							// 충돌이 발생한 산타를 교체
							conflictSanta = santa;

							// 충돌 발생 여부 기록
							isConflict = true;
							break;
						}

					}

					// 충돌이 발생하지 않은 경우 종료
					if (!isConflict) {
						return;
					}

				}

			}

		}

	}

	public static class Santa implements Comparable<Santa> {

		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};

		int number;
		Position pos;
		int score;
		boolean isMovable;
		boolean isAlive;
		int delay;

		public Santa(int number, int row, int col) {
			this.number = number;
			this.pos = new Position(row, col);
			this.score = 0;
			this.isMovable = true;
			this.isAlive = true;
			this.delay = 0;
		}

		public void checkFinish() {
			if (this.pos.row < 1 || size < this.pos.row || this.pos.col < 1 || size < this.pos.col) {
				isAlive = false;
			}
		}

		public void move() {

			// 2-2-1. 루돌프와 가장 거리가 가까운 방향 찾기
			int curDistance = this.pos.getDistance(ludolph.pos);
			int minDistance = Integer.MAX_VALUE;
			int moveDirection = -1;
			for (int dir = 0; dir < this.dr.length; dir++) {

				// 다음 이동할 곳
				Position nextPosition = new Position(this.pos.row + this.dr[dir], this.pos.col + this.dc[dir]);

				// 다음 이동할 위치가 인덱스를 벗어나는 경우 이동 불가
				if (!nextPosition.isValid()) {
					continue;
				}

				// 다음 이동할 위치에 산타가 이미 있는 경우 이동 불가
				if (!nextPosition.isEmpty()) {
					continue;
				}

				int distance = nextPosition.getDistance(ludolph.pos);

				// 거리가 좁아지지 않는 경우도 이동 불가
				if (curDistance < distance) {
					continue;
				}

				if (minDistance > distance) {
					minDistance = distance;
					moveDirection = dir;
				}

			}

			// 2-2-2. 이동할 수 있는 방향이 존재할 경우 이동
			if (moveDirection != -1) {
				this.pos.row += this.dr[moveDirection];
				this.pos.col += this.dc[moveDirection];
			}

			// 2-2-3. 충돌이 발생한 경우에 대한 후처리 진행
			if (this.pos.row == ludolph.pos.row && this.pos.col == ludolph.pos.col) {

				// 산타의 힘만큼 점수를 획득하고
				this.score += santaPower;

				// 반대 방향으로 산타의 힘 만큼 이동
				this.pos.row -= (this.dr[moveDirection] * santaPower);
				this.pos.col -= (this.dc[moveDirection] * santaPower);

				// 기절 상태
				this.isMovable = false;
				this.delay = 0;

				// 상호작용 수행
				Santa conflictSanta = this;
				while (true) {

					// 밀린 자리가 유효한지 확인
					conflictSanta.checkFinish();
					boolean isConflict = false;

					// 충돌이 발생한 산타와 동일한 위치에 존재하는 산타가 있는지 확인
					for (Santa santa : santas) {

						if (!santa.isAlive || conflictSanta.number == santa.number)  {
							continue;
						}

						// 충돌이 발생한 산타가 있다면
						if (conflictSanta.pos.row == santa.pos.row && conflictSanta.pos.col == santa.pos.col) {

							// 산타가 밀려나온 방향으로 1칸 이동
							santa.pos.row -= conflictSanta.dr[moveDirection];
							santa.pos.col -= conflictSanta.dc[moveDirection];

							// 밀려난 자리에 또 다른 산타가 있는지 확인하기 위해 밀려난 산타로 변경
							conflictSanta = santa;

							// 충돌 발생 여부 기록
							isConflict = true;
							break;
						}

					}

					// 충돌이 발생하지 않은 경우 종료
					if (!isConflict) {
						return;
					}

				}

			}

		}

		@Override
		public int compareTo(Santa o) {

			// r좌표가 동일할 때 c좌표 내림차순
			if (this.pos.row == o.pos.row) {
				return Integer.compare(o.pos.col, this.pos.col);
			}

			// 기본적으로는 r좌표 내림차순
			return Integer.compare(o.pos.row, this.pos.row);

		}

	}

}