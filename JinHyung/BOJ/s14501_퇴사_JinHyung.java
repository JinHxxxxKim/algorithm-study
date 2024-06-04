package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author JinHyung
 * 
 * 0-1 Knapsack
 * 
 * 상담에 대해 선택할 수 있는 경우의 수 2가지
 *   - 해당 날짜의 상담을 수주한다.
 *   - 해당 날짜의 상담을 수주하지 않는다.
 *   
 * 주어진 상담에 대해 역순으로 상담을 결정한다.
 * 
 * 해당 상담을 수주하거나 수주하지 않을 때의 최대 가치를 해당 날짜에 저장한다.
 * 
 * 1일차까지 도달하면 최대가치를 출력한다.
 * 
 */
public class s14501_퇴사_JinHyung {
	static final StringBuilder sb = new StringBuilder();
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N;
	static Task[] tasks; // 상담 정보 저장
	static int[] maxValue; // 해당 날짜의 최대 가치 저장

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine().trim());
		tasks = new Task[N];
		maxValue = new int[N + 1];
		for (int taskCnt = 0; taskCnt < N; ++taskCnt) {
			st = new StringTokenizer(br.readLine().trim());
			int reqDate = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			tasks[taskCnt] = new Task(value, reqDate);
		}
		
		// 역순으로 조회
		for(int day = N - 1; day >= 0; --day) {
			Task currTask = tasks[day]; // 당일의 상담 정보
			// 소요 날짜 + 현재 날짜 > N -> 수주 불가능
			if (currTask.reqDate + day > N) {
				// 수주 불가능
				// 이전 날짜의 최대값 가져오기
				maxValue[day] = maxValue[day + 1];
				continue;
			} else {
				// 수주 가능
				// 수주 했을 때 vs 수주 하지 않았을 때의 최대값 저장
				maxValue[day] = Math.max(maxValue[day + currTask.reqDate] + currTask.value, 
										maxValue[day + 1]);
			}
		}
		System.out.println(maxValue[0]);
	}

	// 상담 클래스
	static class Task {
		int value, reqDate;

		public Task(int value, int reqDate) {
			super();
			this.value = value;
			this.reqDate = reqDate;
		}

	}
}
