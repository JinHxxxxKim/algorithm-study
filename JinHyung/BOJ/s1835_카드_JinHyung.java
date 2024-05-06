package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author JinHyung
 * 
 * 포인터
 * 
 * 1. 카드를 옮기는 것이 아닌 포인터를 옮긴다고 생각
 * 2. 포인터 == 카드 뭉치의 제일 앞 부분
 * 3. 카드를 앞에서부터 순회하며, 이동 횟수를 채우면 해당 카드를 뽑고(제외하고) 순서를 세긴다.
 * 4. 이미 뽑은 카드는 넘어간다.
 *
 */
public class s1835_카드_JinHyung {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	
	static int N;
	static int[] array; // 순서를 저장할 배열
	static boolean[] isOut; // 이미 선택된 카드인지 여부
	
	public static void main(String[] args) throws Exception{
		N = Integer.parseInt(br.readLine().trim());
		array = new int[N];
		isOut = new boolean[N];
		int num = 1; // 순서
		int idx = 0; // 현재 확인하고 있는 배열의 인덱스
		
		// N개의 카드를 뽑는다.
		for (int cnt = 1; cnt <= N; ++cnt) {
			// cnt만큼 이동한 뒤, 카드를 뽑아야한다.
			int moveCnt = 0;
			while (moveCnt < cnt) {
				// cnt만큼 카드를 뒤로 보내고자하는 반복문
				if(isOut[idx]) {
					// 이미 제거된 카드라면 PASS, 인덱스만 증가
					idx = (idx + 1) % N;
				}else {
					// 아직 제거 안된 카드라면 뒤로 보낸다
					idx = (idx + 1) % N;
					++moveCnt;
				}
			}
			
			// 아직 선택되지 않은 카드를 탐색
			while(isOut[idx]) {
				idx = (idx + 1) % N;
			}
			// 해당 카드의 번호를 설정
			isOut[idx] = true;
			array[idx] = num++;
		}
		for(int seq:array) {
			sb.append(seq).append(" ");
		}
		System.out.println(sb.toString().trim());
	}
}
