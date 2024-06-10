package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author hkh12
 * 
 * 1. 피보나치 수 구하기
 * 	1-1. dp테이블 생성
 * 	1-2. dp[n] 찾기
 * 
 */

public class BOJ2747 {
	static BufferedReader br;
	static StringBuilder sb;
	static int n;
	static int[] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		dp = new int[46];
		
		dp[0]=0;
		dp[1]=1;
		
		for(int idx=2; idx<=n; idx++) {
			dp[idx]=dp[idx-1]+dp[idx-2];
		}
		
		System.out.println(dp[n]);
	}
}
