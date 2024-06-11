package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 * BFS 탐색
 * - 처음 기차의 위치에서 시작하여, BFS 탐색을 하며 목적지까지 도달할 수 있는지를 체크한다.
 * - 방문 배열의 경우, (중심점의 행, 중심점의 열, 기차의 가로/세로 여부)로 기차의 위치가 정해지므로, N*N*2 크기의 3차원 배열로 관리 가능하다.
 */

public class s_1938_통나무옮기기_YongSoo {
    static BufferedReader br;

    // 각각 위쪽, 오른쪽, 아래쪽, 왼쪽 방향을 나타냄
    static final int[][] dTrains = {{-1,0}, {0,1}, {1,0}, {0,-1}};

    // dNeighbors[0] : 통나무가 가로로 있을 때, 각 통나무들의 중심으로부터 떨어진 좌표를 나타냄
    // dNeighbors[0] : 통나무가 세로로 있을 때, 각 통나무들의 중심으로부터 떨어진 좌표를 나타냄
    static final int[][][] dNeighbors = {{{0,1}, {0,0}, {0,-1}}, {{1,0}, {0,0}, {-1,0}}};
    static int N;
    static char[][] board;

    // 기차의 위치를 저장하고 있는 변수
    static PosInfo train;
    // 목적지의 위치를 저장하고 있는 변수
    static PosInfo dest;
    static ArrayDeque<PosInfo> queue;
    // 방문 배열
    static boolean[][][] visited;

    static class PosInfo {
        int row;
        int col;
        int cnt;
        // 가로로 위치하면 1, 아니면 0
        int direction;

        public PosInfo(int row, int col, int direction, int cnt) {
            this.row = row;
            this.col = col;
            this.direction = direction;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "PosInfo{" +
                    "row=" + row +
                    ", col=" + col +
                    ", cnt=" + cnt +
                    ", direction=" + direction +
                    '}';
        }
    }


    public static void main(String[] args) throws IOException {
        setInitVariable();
        setTrain();
        setDest();

        int answer = getAnswer();
        System.out.println(answer);

    }


    private static int getAnswer() {
        //BFS 탐색
        queue = new ArrayDeque<>();

        queue.add(train);
        visited[train.row][train.col][train.direction] = true;

        while (!queue.isEmpty()) {
            PosInfo posInfo = queue.poll();

            // 상하좌우 4방향으로, 기차가 이동할 수 있는지를 탐색
            for (int dirIdx=0; dirIdx<4; dirIdx++) {

                // 기차의 (이동하는 row 거리, 이동하는 col 거리)를 나타낸다
                int[] dTrain = dTrains[dirIdx];

                // 해당 방향으로 이동할 수 있는 경우
                if (validateFor(posInfo, dTrain)) {
                    int newRow = posInfo.row + dTrain[0];
                    int newCol = posInfo.col + dTrain[1];
                    int newDirection = posInfo.direction;
                    int newCnt = posInfo.cnt+1;

                    // 목적지에 도달한 경우 : 탐색 종료
                    if (arrivedToDest(newRow, newCol, newDirection)) {
                        return newCnt;
                    }

                    // 방문 처리 & 큐에 추가
                    visited[newRow][newCol][newDirection] = true;
                    queue.add(new PosInfo(newRow, newCol, newDirection, newCnt));
                }
            }

            // 회전할 수 있는 경우
            if (validateForRotate(posInfo)) {
                int newRow = posInfo.row;
                int newCol = posInfo.col;
                int newDirection = 1 - posInfo.direction;
                int newCnt = posInfo.cnt+1;

                // 목적지에 도달한 경우 : 탐색 종료
                if (arrivedToDest(newRow, newCol, newDirection)) {
                    return newCnt;
                }

                // 방문 처리 & 큐에 추가
                visited[newRow][newCol][newDirection] = true;
                queue.add(new PosInfo(newRow, newCol, newDirection, newCnt));
            }
        }

        // 이동이 불가능한 경우
        return 0;
    }

    // 목적지에 도달했는지 여부를 반환
    private static boolean arrivedToDest(int row, int col, int direction) {
        return dest.row == row && dest.col == col && dest.direction == direction;
    }

    // 3*3 크기의 공간에 '1'이 있는지를 체크
    private static boolean validateForRotate(PosInfo posInfo) {
        int row = posInfo.row;
        int col = posInfo.col;
        int direction = posInfo.direction;

        if (row==0 || row==N-1 || col==0 || col==N-1) return false;

        if (visited[row][col][1 - direction]) return false;

        for (int dRow=-1; dRow<=1; dRow++) {
            for (int dCol=-1; dCol<=1; dCol++) {
                if (board[row+dRow][col+dCol] == '1') {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean validateFor(PosInfo posInfo, int[] dTrain) {
        int row = posInfo.row;
        int col = posInfo.col;
        int direction = posInfo.direction;

        int[][] dCoordinate = dNeighbors[direction];

        // 현재 통나무는 3개가 존재함
        // 3개를 같은 방향으로 각각 이동시켰을 때, 좌표를 벗어나거나 1로 표시되어 있지는 않은지를 탐색
        for (int neighbor=0; neighbor<3; neighbor++) {
            int nextRow = row + dCoordinate[neighbor][0] + dTrain[0];
            int nextCol = col + dCoordinate[neighbor][1] + dTrain[1];

            if (!isValidateCoordinate(nextRow, nextCol) || board[nextRow][nextCol] == '1') {
                return false;
            }
        }

        // 방문처리 되어있지 않아야 가는 의미가 있음
        return !visited[row + dTrain[0]][col + dTrain[1]][direction];
    }

    // (row, col)이 적절한 좌표인지 여부를 반환
    private static boolean isValidateCoordinate(int row, int col) {
        return row>=0 && row<N && col>=0 && col<N;
    }

    private static void setTrain() {
        for (int row=0; row<N; row++) {
            for (int col=0; col<N; col++) {
                if (board[row][col] == 'B') {
                    // 가로로 위치한지 확인
                    if (col<N-1 && board[row][col+1] == 'B') {
                        train = new PosInfo(row, col+1, 0, 0);
                    }
                    else {
                        train = new PosInfo(row+1, col, 1, 0);
                    }

                    return;
                }
            }
        }
    }

    private static void setDest() {
        for (int row=0; row<N; row++) {
            for (int col=0; col<N; col++) {
                if (board[row][col] == 'E') {
                    // 가로로 위치한지 확인
                    if (col<N-1 && board[row][col+1] == 'E') {
                        dest = new PosInfo(row, col+1, 0, 0);
                    }
                    else {
                        dest = new PosInfo(row+1, col, 1, 0);
                    }

                    return;
                }
            }
        }
    }


    private static void setInitVariable() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine().trim());

        board = new char[N][N];

        for (int row=0; row<N; row++) {
            String s = br.readLine().trim();

            for (int col=0; col<N; col++) {
                board[row][col] = s.charAt(col);
            }
        }

        visited = new boolean[N][N][2];
    }
}
