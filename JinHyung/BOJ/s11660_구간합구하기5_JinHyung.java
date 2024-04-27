package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author JinHyung
 * 
 * 누적합
 * 
 * 1. N x N 크기의 배열을 입력받는다.
 * 2. N+1 x N+1 크기의 padding을 추가한 누적합 배열을 만든다.
 *   2-1. 누적합 배열 공식
 *     - cuSum[i][j] = cuSum[i][j-1] + cuSum[i-1][j] - cuSum[i-1][j-1] + arr[i][j]
 * 3. 특정 구간의 누적합을 구한다.
 *   -  cuSum[i][j] - cuSum[i][j-열 차이] + cuSum[i-행 차이][j] + arr[i-행 차이][j-열 차이]
 *  
 */
public class s11660_구간합구하기5_JinHyung {
	static final StringBuilder sb = new StringBuilder();
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, M;
	static int[][] arr;
	static int[][] cuSum;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][N];
		cuSum = new int[N+1][N+1]; // add padding
		
		// 배열 입력 및 누적합 배열 계산 
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < N; ++col) {
				arr[row][col] = Integer.parseInt(st.nextToken());

				cuSum[row + 1][col + 1] = cuSum[row][col + 1] // 왼쪽
										+ cuSum[row + 1][col] // 위
										- cuSum[row][col]     // 대각
										+ arr[row][col];      // 자기 자신
			}
		}
		
		for (int cnt = 0; cnt < M; ++cnt) {
			st = new StringTokenizer(br.readLine().trim());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			// 우하단 누적합 - 우상단 누적합 - 좌하단 누적합 + 좌상단 누적합
			int result = cuSum[x2][y2];
			result -= cuSum[x2 - (x2 - x1 + 1)][y2]; // 우상단 누적합
			result -= cuSum[x2][y2 - (y2 - y1 + 1)]; // 좌하단 누적합
			result += cuSum[x2 - (x2 - x1 + 1)][y2 - (y2 - y1 + 1)]; // 좌상단 누적합
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}

}
