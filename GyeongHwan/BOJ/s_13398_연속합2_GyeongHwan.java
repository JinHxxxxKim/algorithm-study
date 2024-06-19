package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author hkh12
 * 
 * 1. n이 최대 10^5이므로 O(nlogn)이하의 시간복잡도를 가져야 한다.
 * 2. 다이나믹 프로그래밍
 * 
 * left[idx]: idx에서 끝나는 수열 중에서 연속합이 가장 큰 값
 * => left[idx] = Math.max(left[idx-1]+seq[idx], seq[idx])
 * 
 * right[idx]: idx에서 시작하는 수열 중에서 연속합이 가장 큰 값
 * => right[idx] = Math.max(right[idx+1]+seq[idx], seq[idx])
 * 
 * idx=0 ~ n-1
 * 1) 수를 제거하지 않는 경우
 * dp[idx] = left[idx]+right[idx]-seq[idx]
 * 
 * 2) 수(seq[idx])를 하나 제거한 경우
 * dp2[idx] = left[idx-1]+right[idx+1]
 * 
 * 
 */

public class BOJ13398 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int n, ans, result;
	static int[] seq;
	static int[] left, right;
	static int[] dp, dp2;
	
	static void inputTestcase() throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		n = Integer.parseInt(br.readLine().trim());
		seq = new int[n];
		st = new StringTokenizer(br.readLine().trim());
		for(int idx=0; idx<n; idx++) {
			seq[idx]=Integer.parseInt(st.nextToken());
		}
		
		if(n==1) {
			return;
		}
		
		left = new int[n];
		left[0]=seq[0];
		for(int idx=1; idx<n; idx++) {
			left[idx]=Math.max(left[idx-1]+seq[idx], seq[idx]);
		}
		right = new int[n];
		right[n-1]=seq[n-1];
		for(int idx=n-2; idx>=0; idx--) {
			right[idx]=Math.max(right[idx+1]+seq[idx], seq[idx]);
		}
		
		dp = new int[n];
		dp[0]=left[0]+right[0]-seq[0];
		dp[n-1]=left[n-1]+right[n-1]-seq[n-1];
		
		dp2 = new int[n];
		dp2[0]=right[1];
		dp2[n-1]=left[n-2];
		
		int e1 = Math.max(dp[0], dp[n-1]);
		int e2 = Math.max(dp2[0], dp2[n-1]);
		ans = Math.max(e1, e2);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력
		inputTestcase();
		
		if(n!=1) {
			//dp테이블 채우기
			for(int idx=1; idx<n-1; idx++) {
				//수를 제거하지 않는 경우
				dp[idx]=left[idx]+right[idx]-seq[idx];
				
				//수를 제거하는 경우
				dp2[idx]=left[idx-1]+right[idx+1];
				
				//ans갱신
				result = Math.max(dp[idx], dp2[idx]);
				ans = Math.max(ans, result);
				
				
			}
			//출력
			System.out.println(ans);
		}else {
			System.out.println(seq[0]);
		}
		
		
	}
}
