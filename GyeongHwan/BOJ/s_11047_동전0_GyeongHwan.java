package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author hkh12
 * 
 * 그리디 연습문제
 * 1. 가급적 큰 단위의 동전들로 구성하면 최소의 동전개수로 k를 얻을 수 있다.
 * => 큰 단위가 작은 단위의 배수이기 때문에 큰단위로 표현할 수 있는 돈은 작은 단위로도 표현할 수 있다.
 * 
 */

public class BOJ11047 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int n, k, ans;
	static int[] coinVal;
	
	static int coinCnt;
	
	private static void inputTestcase() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		coinVal = new int[n];
		for(int idx=0; idx<n; idx++) {
			coinVal[idx]=Integer.parseInt(br.readLine().trim());
		}
	}
	
	public static void main(String[] args) throws IOException {
		//입력
		inputTestcase();
		//동전개수의 최솟값 구하기
		for(int idx=n-1; idx>=0; idx--) {
			if(k==0) {break;}
			coinCnt=k/coinVal[idx];
			if(coinCnt>=1) {
				k=k%coinVal[idx];
				ans+=coinCnt;
			}
		}
		System.out.println(ans);
	}
}
