import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 구현 문제
 * 주요 변수들
 *   - santas: 산타들의 정보를 가지고 있는 배열
 *   - board: (row, col)에 산타가 있는지를 확인하기 위한 2차원 배열
 *            산타가 없다면 board[row][col]은 null이다.
 *   - Santa 클래스의 stun 변수의 경우, boolean이 아닌 int형이다.
 *     기절한 산타는 다음 턴까지 기절해 있어야 하는데 boolean으로는 이를 구현하기 어려워 int를 사용
 */

public class Main {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    // 상하좌우 방향을 나타내는 배열
    static int[][] deltas = {{-1,0}, {0,1}, {1,0}, {0,-1}};
    static int N, M, P, C, D;
    // 루돌프의 위치
    static Pos rudolph;

    // 산타들의 정보를 저장하고 있는 배열
    static Santa[] santas;
    // (row, col)에 산타가 있는지를 확인하기 위한 2차원 배열

    static Santa[][] board;
    // 탈락한 산타의 수
    static int deadNum;

    static class Pos {
        int row;
        int col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }

    static class Santa {
        int id;
        int row;
        int col;
        int stun;
        boolean survive;
        int score;

        public Santa(int id, int row, int col) {
            this.id = id;
            this.row = row;
            this.col = col;
            this.survive = true;
            this.stun = -1;
        }

        @Override
        public String toString() {
            return "Santa{" +
                    "id=" + id +
                    ", row=" + row +
                    ", col=" + col +
                    ", stun=" + stun +
                    ", survive=" + survive +
                    '}';
        }
    }


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        setInitVariable();

        int time = 1;

        outerLoop:
        while (time <= M) {
            // 1. 루돌프의 움직임
            // 1-1. 가장 가까운 산타 설정
            Santa targetSanta = getTargetSanta();

//            System.out.println("targetSanta = " + targetSanta);

            // 1-2. 루돌프가 targetSanta에게 가장 가까워지는 방향으로 1칸 돌진
            int dRow = Integer.compare(targetSanta.row, rudolph.row);
            int dCol = Integer.compare(targetSanta.col, rudolph.col);
            rudolph.row += dRow;
            rudolph.col += dCol;

            // 루돌프가 산타와 충돌한 경우
            if (rudolph.row == targetSanta.row && rudolph.col == targetSanta.col) {
                // 충돌 발생 : 산타의 위치 연쇄적으로 업데이트
                chaining(targetSanta, dRow, dCol, C, time);
                // 모든 산타가 탈락하였을 경우, 반복문 종료
                if (deadNum == P) break;
            }

            // 2. 산타의 움직임
            for (Santa santa: santas) {
                // 기절했거나 이미 탈락한 산타는 움직일 수 없다
                if (!santa.survive || santa.stun>=0) continue;

                // 2-1. 산타의 이동방향 구하기
                // 움직이지 않을 경우, -1 return
                int dirIdx = getDir(santa);

                if (dirIdx >= 0) {
                    board[santa.row][santa.col] = null;
                    santa.row += deltas[dirIdx][0];
                    santa.col += deltas[dirIdx][1];

                    board[santa.row][santa.col] = santa;
                }

                // 충돌하였을 경우
                if (rudolph.row == santa.row && rudolph.col == santa.col) {

                    // 연쇄작용 발생
                    chaining(santa, -deltas[dirIdx][0], -deltas[dirIdx][1], D, time);
                    if (deadNum == P) break outerLoop;
                }

            }

            // 매 턴 이후 아직 탈락하지 않은 산타들에게 1점씩을 추가로 부여
            for (Santa santa: santas) {
                if (santa.survive) {
                    santa.score++;

                    //
                    if (santa.stun == time) {
                        santa.stun = -1;
                    }
                }
            }

            time++;

//            System.out.println("rudolph = " + rudolph);
//            System.out.println("Arrays.toString(santas) = " + Arrays.toString(santas));
//            System.out.println("Arrays.toString(scores) = " + Arrays.toString(scores));
        }

        for (Santa santa: santas) {
            sb.append(santa.score).append(" ");
        }

        System.out.println(sb);
    }

    // 현재 targetSanta가 (dRow, dCol)만큼 밀려날 때, 연쇄작용을 처리
    private static void chaining(Santa targetSanta, int dRow, int dCol, int value, int time) {
        targetSanta.stun = time+1;
        // C만큼의 점수 획득
        targetSanta.score += value;

        // 밀려남
        board[targetSanta.row][targetSanta.col] = null;

        targetSanta.row += value * dRow;
        targetSanta.col += value * dCol;

        // 경기장 밖으로 밀려난 경우
        if (outOfCoordinate(targetSanta.row, targetSanta.col)) {
            targetSanta.survive = false;

            deadNum++;
        }
        // 해당 위치에 다른 산타가 있는 경우
        else {
            Santa otherSanta = board[targetSanta.row][targetSanta.col];
            board[targetSanta.row][targetSanta.col] = targetSanta;
            while (otherSanta != null) {
                otherSanta.row += dRow;
                otherSanta.col += dCol;

                if (outOfCoordinate(otherSanta.row, otherSanta.col)) {
                    otherSanta.survive = false;

                    deadNum++;
                    return;
                }

                Santa tempSanta = board[otherSanta.row][otherSanta.col];
                board[otherSanta.row][otherSanta.col] = otherSanta;
                otherSanta = tempSanta;
            }

        }
    }

    // 산타의 이동방향을 반환하는 함수
    private static int getDir(Santa santa) {

        int dir = -1;
        int minDist = getDist(rudolph.row, rudolph.col, santa.row, santa.col);

        for (int dirIdx=0; dirIdx< deltas.length; dirIdx++) {
            int[] delta = deltas[dirIdx];

            int newRow = santa.row + delta[0];
            int newCol = santa.col + delta[1];

            if (outOfCoordinate(newRow, newCol) || board[newRow][newCol] != null) continue;

            int curDist = getDist(rudolph.row, rudolph.col, newRow, newCol);
            if (curDist < minDist) {
                minDist = curDist;
                dir = dirIdx;
            }
        }
        return dir;
    }

    private static boolean outOfCoordinate(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= N;
    }

    private static Santa getTargetSanta() {
        Santa targetSanta = null;
        int minDist = Integer.MAX_VALUE;

        for (Santa santa: santas) {
            // 탈락하지 않은 산타 중 선택
            if (santa.survive) {
                if (targetSanta == null) {
                    targetSanta = santa;
                    minDist = getDist(rudolph.row, rudolph.col, santa.row, santa.col);
                }
                else {
                    int curDist = getDist(rudolph.row, rudolph.col, santa.row, santa.col);

                    // 더 가까운 산타를 찾았을 경우
                    if (curDist < minDist) {
                        minDist = curDist;
                        targetSanta = santa;
                    }
                    else if (curDist == minDist) {
                        if (compare(santa, targetSanta) < 0) {
                            targetSanta = santa;
                        }
                    }
                }
            }
        }

        return targetSanta;
    }

    // 두 산타의 우선순위를 비교하는 함수
    private static int compare(Santa santa, Santa targetSanta) {
        if (santa.row == targetSanta.row) {
            return -Integer.compare(santa.col, targetSanta.col);
        }
        return -Integer.compare(santa.row, targetSanta.row);
    }

    // (r1, c1)와 (r2, c2) 사이의 거리를 반환하는 함수
    private static int getDist(int r1, int c1, int r2, int c2) {
        int dRow = r1 - r2;
        int dCol = c1 - c2;
        return dRow * dRow + dCol * dCol;
    }

    // 문제에서 주어지는 변수 초기화
    private static void setInitVariable() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());
        int rowOfRudolph = Integer.parseInt(st.nextToken())-1;
        int colOfRudolph = Integer.parseInt(st.nextToken())-1;
        rudolph = new Pos(rowOfRudolph, colOfRudolph);

        board = new Santa[N][N];
        santas = new Santa[P];
        for (int idx=0; idx<P; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int id = Integer.parseInt(st.nextToken())-1;
            int row = Integer.parseInt(st.nextToken())-1;
            int col = Integer.parseInt(st.nextToken())-1;

            santas[id] = new Santa(id, row, col);

            board[row][col] = santas[id];
        }
        deadNum = 0;
    }
}