import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1. NxN 크기 격자는 0과 1로 채워져 있으며, 1은 아직 잘려지지 않은 나무, 0은 빈 공간이다.
 * 2. 격자 위의 연속된 가로/세로 세 칸으로, 출발지의 통나무 위치 BBB와 목적지 EEE가 있다.
 * 3. BBB를 옮기는 방법은 아래 5가지 방법이 있다.
 *  3-1. U : 통나무를 위로 한 칸 옮긴다.
 *  3-2. D : 통나무를 아래로 한 칸 옮긴다.
 *  3-3. L : 통나무를 왼쪽으로 한 칸 옮긴다.
 *  3-4. R : 통나무를 오른쪽으로 한 칸 옮긴다.
 *  3-5. T : 통나무를 가운데를 기준으로 90도 회전 시킨다.
 * 4. 통나무를 이동시키려면, 그 경로에 나무가 없어야 한다.
 * 5. 3번의 기본 동작만으로 목적지에 도달하는 최소 횟수를 구하고, 불가능한 경우 0을 반환한다.
 */
public class Main {
    static final char EMPTY = '0';
    static final char TREE = '1';
    static final char SOURCE = 'B';
    static final char DESTINATION = 'E';
    // 상, 하, 좌, 우
    static final int[][] DELTA = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static int MAP_SIZE;
    static char[][] map;

    static boolean inRange(int row, int col) {
        return 0 <= row && row < MAP_SIZE && 0 <= col && col < MAP_SIZE;
    }

    static int solution(Plan sourcePlan, Plan destPlan) {
        // 해당 위치에 해당 방향으로 접근한 적 있는지 확인하기 위한 비트 마스크 배열
        int[][] isVisited = new int[MAP_SIZE][MAP_SIZE];
        // 해당 위치에 수평 방향으로 접근한 적 있음을 표시
        final int HORIZONTAL_MASK = 1;
        final int VERTICAL_MASK = 2;

        // BFS로 가장 짧은 경로를 찾기 위한 큐
        Queue<Plan> plans = new LinkedList<>();

        // 시작점부터 더 돌 수 있는 위치가 없을 때까지 순회 시작
        plans.add(sourcePlan);
        while (!plans.isEmpty()) {
            // 지금 움직이려는 방향으로
            Plan curplan = plans.remove();

            // 만약 도착지에 왔다면 바로 반환
            if (curplan.equals(destPlan)) {
                return curplan.moveCount;
            }

            // 도착지는 아니지만 방문한 적이 없는 경우
            if (curplan.isHorizontal && (isVisited[curplan.centerRow][curplan.centerCol] & HORIZONTAL_MASK) == HORIZONTAL_MASK) {
                continue;
            } else if (!curplan.isHorizontal && (isVisited[curplan.centerRow][curplan.centerCol] & VERTICAL_MASK) == VERTICAL_MASK) {
                continue;
            }

            int centerRow = curplan.centerRow;
            int centerCol = curplan.centerCol;

            // 방문 처리 해주고
            if (curplan.isHorizontal) {
                isVisited[centerRow][centerCol] |= HORIZONTAL_MASK;
            } else {
                isVisited[centerRow][centerCol] |= VERTICAL_MASK;
            }

            // 이동 가능한 위치가 나온다면 다음 탐색 대상으로 넣어줌
            // 수평 방향인 경우
            if (curplan.isHorizontal) {
                // 네 방향으로
                for (int[] delta : DELTA) {
                    boolean canMove = true;
                    // 이동 가능한지부터 확인
                    for (int colDelta = -1; colDelta <= 1 && canMove; colDelta++) {
                        // 격자를 벗어나거나 나무가 있으면 이동 불가
                        if (!inRange(centerRow + delta[0], centerCol + colDelta + delta[1])
                                || map[centerRow + delta[0]][centerCol + colDelta + delta[1]] == TREE) {
                            canMove = false;
                            break;
                        }
                    }
                    // 이동 가능하며, 해당 방향으로 움직여본 적이 없으면 다음 후보로 등록
                    if (canMove && (isVisited[centerRow + delta[0]][centerCol + delta[1]] & HORIZONTAL_MASK) != HORIZONTAL_MASK) {
                        plans.add(new Plan(centerRow + delta[0], centerCol + delta[1], curplan.moveCount + 1, true));
                    }
                }

                // 회전 가능 여부 확인
                // 나무의 상 하가 격자를 벗어나지 않고
                boolean canRotate = inRange(centerRow - 1, centerCol) && inRange(centerRow + 1, centerCol);
                // 수평 방향으로 놓여 있었으므로,
                for (int colDelta = -1; colDelta <= 1 && canRotate; colDelta++) {
                    // 해당 위치에 나무가 없으며
                    if (map[centerRow - 1][centerCol + colDelta] == TREE || map[centerRow + 1][centerCol + colDelta] == TREE) {
                        canRotate = false;
                        break;
                    }
                }
                // 수직 방향으로 놓였던 적이 없었으면 다음 후보로 추가해줌
                if (canRotate && (isVisited[centerRow][centerCol] & VERTICAL_MASK) != VERTICAL_MASK) {
                    plans.add(new Plan(centerRow, centerCol, curplan.moveCount + 1, false));
                }
            }
            // 수직 방향인 경우
            else {
                // 네 방향으로
                for (int[] delta : DELTA) {
                    boolean canMove = true;
                    // 이동 가능한지부터 확인
                    for (int rowDelta = -1; rowDelta <= 1 && canMove; rowDelta++) {
                        // 격자를 벗어나거나 나무가 있으면 이동 불가
                        if (!inRange(centerRow + rowDelta + delta[0], centerCol + delta[1])
                                || map[centerRow + rowDelta + delta[0]][centerCol + delta[1]] == TREE) {
                            canMove = false;
                            break;
                        }
                    }
                    // 이동 가능하며, 해당 방향으로 움직여본 적이 없으면 다음 후보로 등록
                    if (canMove && (isVisited[centerRow + delta[0]][centerCol + delta[1]] & VERTICAL_MASK) != VERTICAL_MASK) {
                        plans.add(new Plan(centerRow + delta[0], centerCol + delta[1], curplan.moveCount + 1, false));
                    }
                }

                // 회전 가능 여부 확인
                // 나무의 좌 우가 격자를 벗어나지 않고
                boolean canRotate = inRange(centerRow, centerCol - 1) && inRange(centerRow, centerCol + 1);
                // 수직 방향으로 놓여 있었으므로,
                for (int rowDelta = -1; rowDelta <= 1 && canRotate; rowDelta++) {
                    // 해당 위치에 나무가 없으며
                    if (map[centerRow + rowDelta][centerCol - 1] == TREE || map[centerRow + rowDelta][centerCol + 1] == TREE) {
                        canRotate = false;
                        break;
                    }
                }
                // 수평 방향으로 놓였던 적이 없었으면 다음 후보로 추가해줌
                if (canRotate && (isVisited[centerRow][centerCol] & HORIZONTAL_MASK) != HORIZONTAL_MASK) {
                    plans.add(new Plan(centerRow, centerCol, curplan.moveCount + 1, true));
                }
            }
        }

        // 모든 경우의 수를 다 찾아봐도 도착지에 갈 수 없으면 0 반환
        return 0;
    }

    public static void main(String[] args) throws Exception {
        // 지도 크기
        MAP_SIZE = Integer.parseInt(in.readLine());
        // 격자 상태
        map = new char[MAP_SIZE][MAP_SIZE];
        // 시작점, 종료점의 위치와 방향을 쉽게 구하기 위한 임시 변수
        // 통나무 길이는 3개이며, 2차원 좌표로 표현된다.
        int[][] sourcePoints = new int[3][2];
        int numOfSources = 0;
        int[][] destPoints = new int[3][2];
        int numOfDests = 0;

        for (int row = 0; row < MAP_SIZE; row++) {
            String line = in.readLine();
            for (int col = 0; col < MAP_SIZE; col++) {
                map[row][col] = line.charAt(col);
                if (map[row][col] == SOURCE) {
                    sourcePoints[numOfSources][0] = row;
                    sourcePoints[numOfSources][1] = col;
                    numOfSources++;
                } else if (map[row][col] == DESTINATION) {
                    destPoints[numOfDests][0] = row;
                    destPoints[numOfDests][1] = col;
                    numOfDests++;
                }
            }
        }

        // 격자를 돌며 찾은 시작점과 종료점의 목록을 각각 정렬하여 방향을 확인한다.
        TwoDimensionalComparator comparator = new TwoDimensionalComparator();
        Arrays.sort(sourcePoints, comparator);
        Arrays.sort(destPoints, comparator);

        // 만약 row가 같다면 수평 방향이고, 다르다면 수직 방향
        boolean isSourceHorizontal = sourcePoints[0][0] == sourcePoints[1][0];
        boolean isDestHorizontal = destPoints[0][0] == destPoints[1][0];

        Plan sourcePlan = new Plan(sourcePoints[1][0], sourcePoints[1][1], 0, isSourceHorizontal);
        Plan destPlan = new Plan(destPoints[1][0], destPoints[1][1], 0, isDestHorizontal);

        int result = solution(sourcePlan, destPlan);
        System.out.println(result);
    }

    static class TwoDimensionalComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            } else {
                return o1[0] - o2[0];
            }
        }
    }

    static class Plan {
        int centerRow, centerCol, moveCount;
        boolean isHorizontal;

        public Plan(int centerRow, int centerCol, int moveCount, boolean isHorizontal) {
            this.centerRow = centerRow;
            this.centerCol = centerCol;
            this.moveCount = moveCount;
            this.isHorizontal = isHorizontal;
        }

        public boolean equals(Plan foe) {
            return this.centerRow == foe.centerRow && this.centerCol == foe.centerCol && this.isHorizontal == foe.isHorizontal;
        }
    }
}
