package algorithm_study.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * [ init() ]
 * 1. 맵의 크기를 입력받는다.
 * 2. 맵의 정보를 입력받는다.
 *  2-1. B 이면서 findStartStatus 가 false 면
 *      2-1-1. 방향을 검사해 startTrain 생성
 *      2-1-2. findStartStatus true 로 전환
 *  2-2. E 이면서 findDefinitionStatus 가 false 면
 *      2-2-1. 방향을 검사해 definitionTrain 생성
 *      2-2-2. findDefinitionStatus true 로 전환
 * [ moveTree() ]
 * 3. BFS 를 이용한 통나무 옮기기
 *  3-1. 두 방향 별 3차원 방문 처리 [2][mapSize][mapSize]
 *  3-2. 현재 Tree 의 isDefinition 이 true 면
 *      3-2-1. operCount 출력 후 종료
 *  3-3. 5 가지 동작 중 가능한 경우
 *      3-3-1. 중심 좌표 이동, operCount+1, turn 인 경우 방향 바꾸어 큐에 삽입, 방문 처리
 *
 * [ class Tree ]
 * - row, col, direction, operCount
 * - canUP()
 *  >> 가로일 때, row - 1 이 맵을 벗어나지 않고 (row-1, col-1/col/col+1) 이 빈칸인 경우만 true
 *  >> 세로일 때, row - 2 가 맵을 벗어나지 않고 (row-2, col) 이 빈칸인 경우만 true
 * - canDOWN()
 *  >> 가로일 때, row + 1 이 맵을 벗어나지 않고 (row+1, col-1/col/col+1) 이 빈칸인 경우만 true
 *  >> 세로일 때, row + 2 가 맵을 벗어나지 않고 (row+2, col) 이 빈칸인 경우만 true
 * - canLEFT()
 *  >> 가로일 때, col - 2 가 맵을 벗어나지 않고 (row, col-2) 가 빈칸인 경우만 true
 *  >> 세로일 때, col - 1 이 맵을 벗어나지 않고 (row-1/row/row+1, col-1)  빈칸인 경우만 true
 * - canRIGHT()
 *  >> 가로일 때, col + 2 가 맵을 벗어나지 않고 (row, col+2) 가 빈칸인 경우만 true
 *  >> 세로일 때, col + 1 이 맵을 벗어나지 않고 (row-1/row/row+1, col+1)  빈칸인 경우만 true
 * - canTURN()
 *  >> 좌표를 중심으로 3x3 구역이 1 이 아닌 경우만 true
 * - isDefinition()
 *  >> definitionTree 의 row, col 과 같고 방향이 같다면 true
 */
public class s1938_통나무옮기기_SangHu {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static final int HORIZONTAL = 0, VERTICAL = 1;

    static int mapSize;
    static char[][] map;
    static Tree startTree;
    static Tree definitionTree;

    static Deque<Tree> treeQ;
    static int resultOperCount;

    public static void moveTree() {
        treeQ = new ArrayDeque<>();
        boolean[][][] visited = new boolean[2][mapSize][mapSize];

        treeQ.add(startTree);
        visited[startTree.direction][startTree.row][startTree.col] = true;

        while (!treeQ.isEmpty()) {
            Tree curTree = treeQ.poll();

            // 도착 검사
            if (curTree.isDefinition()) {
                resultOperCount = curTree.operCount;
                return;
            }

            int row = curTree.row;
            int col = curTree.col;
            int direction = curTree.direction;
            int operCount = curTree.operCount;

            // 상
            if (curTree.canUP() && !visited[direction][row-1][col]) {
                treeQ.add(new Tree(row-1, col, direction, operCount+1));
                visited[direction][row-1][col] = true;
            }

            // 하
            if (curTree.canDOWN() && !visited[direction][row+1][col]) {
                treeQ.add(new Tree(row+1, col, direction, operCount+1));
                visited[direction][row+1][col] = true;
            }

            // 좌
            if (curTree.canLEFT() && !visited[direction][row][col-1]) {
                treeQ.add(new Tree(row, col - 1, direction, operCount + 1));
                visited[direction][row][col-1] = true;
            }

            // 우
            if (curTree.canRIGHT() && !visited[direction][row][col+1]) {
                treeQ.add(new Tree(row, col + 1, direction, operCount + 1));
                visited[direction][row][col+1] = true;
            }

            // 회전
            if (curTree.canTURN() && !visited[(direction+1) % 2][row][col]) {
                treeQ.add(new Tree(row, col, (direction + 1) % 2, operCount + 1));
                visited[(direction+1) % 2][row][col] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        resultOperCount = 0;
        moveTree();

        System.out.println(resultOperCount);
    }

    public static void init() throws IOException {
        mapSize = Integer.parseInt(br.readLine().trim());

        map = new char[mapSize][mapSize];
        for (int row = 0; row < mapSize; row++) {
            String input = br.readLine().trim();
            for (int col = 0; col < mapSize; col++) {
                map[row][col] = input.charAt(col);
            }
        }

        // 출발 나무 찾기
        boolean findStartStatus = false;
        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize; col++) {
                // 첫번째 시작 나무 발견 시
                if (!findStartStatus && map[row][col] == 'B') {
                    if (row + 1 < mapSize && map[row+1][col] == 'B') {
                        startTree = new Tree(row + 1, col, VERTICAL, 0);
                    }
                    else {
                        startTree = new Tree(row, col + 1, HORIZONTAL, 0);
                    }
                    findStartStatus = true;
                    break;
                }
            }

            if (findStartStatus)
                break;
        }

        // 도착 나무 찾기
        boolean findDefinitionStatus = false;
        for (int row = 0; row < mapSize; row++) {
            for (int col = 0; col < mapSize; col++) {
                // 첫번째 도착지 나무 발견 시
                if (!findDefinitionStatus && map[row][col] == 'E') {
                    if (row + 1 < mapSize && map[row+1][col] == 'E') {
                        definitionTree = new Tree(row + 1, col, VERTICAL, 0);
                    }
                    else {
                        definitionTree = new Tree(row, col + 1, HORIZONTAL, 0);
                    }
                    findDefinitionStatus = true;
                    break;
                }
            }

            if (findDefinitionStatus)
                break;
        }
    }

    static class Tree {
        int row;
        int col;
        int direction;
        int operCount;

        public Tree(int row, int col, int direction, int operCount) {
            this.row = row;
            this.col = col;
            this.direction = direction;
            this.operCount = operCount;
        }

        public boolean canUP() {
            if (direction == HORIZONTAL) {
                if (row - 1 < 0 || map[row-1][col-1] == '1' || map[row-1][col] == '1' || map[row-1][col+1] == '1')
                    return false;
            }
            else if(direction == VERTICAL) {
                if (row - 2 < 0 || map[row-2][col] == '1')
                    return false;
            }

            return true;
        }

        public boolean canDOWN() {
            if (direction == HORIZONTAL) {
                if (row + 1 >= mapSize || map[row+1][col-1] == '1' || map[row+1][col] == '1' || map[row+1][col+1] == '1')
                    return false;
            }
            else if (direction == VERTICAL) {
                if (row + 2 >= mapSize || map[row+2][col] == '1')
                    return false;
            }

            return true;
        }

        public boolean canLEFT() {
            if (direction == HORIZONTAL) {
                if (col - 2 < 0 || map[row][col-2] == '1')
                    return false;
            }
            else if(direction == VERTICAL) {
                if (col - 1 < 0 || map[row-1][col-1] == '1' || map[row][col-1] == '1' || map[row+1][col-1] == '1')
                    return false;
            }

            return true;
        }

        public boolean canRIGHT() {
            if (direction == HORIZONTAL) {
                if (col + 2 >= mapSize || map[row][col+2] == '1')
                    return false;
            }
            else if(direction == VERTICAL) {
                if (col + 1 >= mapSize || map[row-1][col+1] == '1' || map[row][col+1] == '1' || map[row+1][col+1] == '1')
                    return false;
            }

            return true;
        }

        public boolean canTURN() {
            if (row - 1 < 0 || col - 1 < 0 || row + 1 >= mapSize || col + 1 >= mapSize)
                return false;

            for (int rangeRow = row-1; rangeRow <= row+1; rangeRow++) {
                for (int rangeCol = col-1; rangeCol <= col+1; rangeCol++) {
                    if (map[rangeRow][rangeCol] == '1')
                        return false;
                }
            }

            return true;
        }

        public boolean isDefinition() {
            if (direction != definitionTree.direction || row != definitionTree.row || col != definitionTree.col)
                return false;

            return true;
        }
    }
}