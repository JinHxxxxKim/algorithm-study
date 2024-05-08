package algorithm_study.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ inputTestCase() ]
 * 1. 맵의 크기, 합을 구하는 횟수를 입력받는다.
 * 2. 맵의 정보를 입력받는다.
 *
 * [ makePrefixSum() ]
 * 3. 맵을 돌며 행의 누적합을 저장하는 배열을 만든다.
 *
 * [ main() ]
 * 4. 계산할 범위의 시작점과 끝점을 입력받는다.
 *  4-1. 시작점의 row 부터 끝점의 row 를 반복한다.
 *      4-1-1. (row, 끝점 col) - (row, 시작점 col-1) 을 누적한다.
 *  4-2. 구간합 출력
 */
public class s11660_구간합구하기5_SangHu {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int mapSize, calSumCount;
    static int[][] map;
    static int[][] prefixSumMap;

    static int areaPrefixSum;

    public static void makePrefixSum() {
        prefixSumMap = new int[mapSize+1][mapSize+1];

        // 3. 맵을 돌며 행의 누적합을 저장하는 배열을 만든다.
        for (int row = 1; row < mapSize+1; row++) {
            for (int col = 1; col < mapSize+1; col++) {
                prefixSumMap[row][col] = prefixSumMap[row][col-1] + map[row][col];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputTestCase();

        makePrefixSum();

        for (int idx = 0; idx < calSumCount; idx++) {
            areaPrefixSum = 0; // 구간합 저장

            // 4. 계산할 범위의 시작점과 끝점을 입력받는다.
            st = new StringTokenizer(br.readLine().trim());
            int startRow = Integer.parseInt(st.nextToken());
            int startCol = Integer.parseInt(st.nextToken());
            int endRow = Integer.parseInt(st.nextToken());
            int endCol = Integer.parseInt(st.nextToken());

            // 4-1. 시작점의 row 부터 끝점의 row 를 반복한다.
            for (int row = startRow; row <= endRow; row++) {
                // 4-1-1. (row, 끝점 col) - (row, 시작점 col-1) 을 누적한다.
                areaPrefixSum += prefixSumMap[row][endCol] - prefixSumMap[row][startCol-1];
            }

            // 4-2. 구간합 출력
            sb.append(areaPrefixSum).append("\n");
        }

        System.out.println(sb);
    }

    public static void inputTestCase() throws IOException {
        // 1. 맵의 크기, 합을 구하는 횟수를 입력받는다.
        st = new StringTokenizer(br.readLine().trim());
        mapSize = Integer.parseInt(st.nextToken());
        calSumCount = Integer.parseInt(st.nextToken());

        // 2. 맵의 정보를 입력받는다.
        map = new int[mapSize+1][mapSize+1];
        for (int row = 1; row < mapSize+1; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 1; col < mapSize + 1; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }
    }
}