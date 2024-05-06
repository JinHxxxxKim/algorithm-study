package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author JinHyung
 * 
 * 누적합
 * 
 * 1. 인출 시간을 모두 입력받는다.
 * 2. 순서가 빠를 수록 더해지는 횟수가 많다 => 앞 순위로 배치한다.
 *   - 증 오름차순으로 정렬한다.
 * 3. 각 소요시가느이 누적합을 구한다.
 * 4. 모든 누적합을 더한다.
 *
 */
public class s11399_ATM_JinHyung {
	static final StringBuilder sb = new StringBuilder();
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N;
	static int[] reqTime;
	static int[] cuSum;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine().trim());
		reqTime = new int[N];
		cuSum = new int[N + 1];
		st = new StringTokenizer(br.readLine().trim());
		// 소요시간 입력
		for (int idx = 0; idx < N; ++idx) 
			reqTime[idx] = Integer.parseInt(st.nextToken());
		
		int ans = 0;
		// 소요시간 오름차순 정렬
		Arrays.sort(reqTime);
		// 소요시간 누적합
		for (int idx = 1; idx < N + 1; ++idx) {
			cuSum[idx] = cuSum[idx - 1] + reqTime[idx - 1];
			// 소요시간 합
			ans += cuSum[idx];
		}
		System.out.println(ans);
	}
}
