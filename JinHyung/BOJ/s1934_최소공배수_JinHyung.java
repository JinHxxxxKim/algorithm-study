package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author JinHyung
 * 
 * LCM, GCD, 유클리드 호제법
 * 
 * 최소 공배수 = 두 수의 곱 / 최대공약수
 * 
 * 1. 두 수의 최대 공약수를 먼저 구한다.
 *   1-1. 두 수 중 더 작은 수로 큰 수를 나눈 나머지를구한다.
 *   1-2. 나머지가 0이라면, 작은 수가 최대공약수가 된다.
 *   1-3. 나머지가 0이아니라면, 원래의 작은수를 큰수로 설정, 나머지응 작은수로 설정하여 반복한다.
 * 2. 두 수를 곱한 뒤, 최대 공약수로 나눈다.
 *
 */
public class s1934_최소공배수_JinHyung {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	
	public static void main(String[] args) throws Exception{
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= TC; ++testCase) {
			st = new StringTokenizer(br.readLine().trim());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			
			// 1. 최대 공약수 먼저 구하기
			int bigNum = num1 >= num2 ? num1 : num2; // 큰 수 설정
			int smallNum = num1 < num2 ? num1 : num2; // 작은 수 설정
			int gcd = 1; // 초기 최대공약수
			int lcm = num1 * num2;
			while(true) { 
				if(bigNum % smallNum == 0) {
					// 나누어 떨어질때까지 반복
					gcd = smallNum;
					break;
				}
				// 젯수, 피젯수 수정
				int temp = bigNum % smallNum;
				bigNum = smallNum;
				smallNum = temp;
			}
			sb.append(lcm/gcd).append("\n");
		}
		System.out.println(sb);
	}
}
