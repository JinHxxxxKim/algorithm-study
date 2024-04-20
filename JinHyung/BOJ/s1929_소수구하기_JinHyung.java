package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author JinHyung
 * 
 * 소수구하기
 * 
 * input의 크기 = 100만 
 * 에라토스테네스의 체
 *
 * 1. 입력값 N, M을 입력받는다.
 * 2. 전처리: 1을 지운다.
 * 3. M의 제곱근 + 1까지 수를 순회한다
 *   - 소수가 아니라면, 해당 수를 데외한 모든 배수를 체크한다.
 * 4. 에라토스테네스의 체에 걸러지지 않은 수를 출력한다. 
 */
public class s1929_소수구하기_JinHyung {
	static final StringBuilder sb = new StringBuilder();
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, M;
	static boolean[] isPrimeNumber;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		isPrimeNumber = new boolean[M + 1];
		Arrays.fill(isPrimeNumber, true);
		// 1은 소수가 아니므로 PASS
		isPrimeNumber[1] = false;
		for (int number = 2; number <= M; ++number) {
			// 탐색 중인 수가 소수인 경우
			if (isPrimeNumber[number]) {
				// 해당 수를 제외한 모든 배수 = 합성수
				int mulNum = 2;
				// 소수의 배수에 대해 모두 false 처리
				while(number * mulNum <= M) {
					isPrimeNumber[number * mulNum] = false;
					++mulNum;
				}
			}
		}
		// 에라토스테네스의 체에 걸러지지 않은 수를 출력
		for(int num = N; num<=M;++num) {
			if(isPrimeNumber[num]) {
				sb.append(num).append("\n");
			}
		}
		System.out.println(sb);
	}
}
