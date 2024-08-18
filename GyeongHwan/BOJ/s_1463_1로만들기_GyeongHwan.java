import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * 1.입력
 * 	1-1. 정수N
 * 2.크기가 n인 dp테이블 생성 
 * 3.각 숫자의 연산 사용 횟수 최솟값을 저장
 * 	3-1. dp[n]=min(dp[n/2]+1, dp[n/3]+1, dp[n-1]+1)
 * 
 */

public class Main {
	static BufferedReader br;
	static int n;
	static int[] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		dp = new int[n+1];
		dp[1]=0;
		if(n>=2) {dp[2]=1;}
		if(n>=3) {dp[3]=1;}
		for(int idx=4; idx<=n; idx++) {
			if(idx%2==0 && idx%3==0) {
				dp[idx]=Math.min(dp[idx/2]+1, dp[idx/3]+1);
				dp[idx]=Math.min(dp[idx], dp[idx-1]+1);
			}
			else if(idx%2==0) {
				dp[idx]=Math.min(dp[idx/2]+1, dp[idx-1]+1);
			}
			else if(idx%3==0) {
				dp[idx]=Math.min(dp[idx/3]+1, dp[idx-1]+1);
			}
			else {
				dp[idx]=dp[idx-1]+1;
			}
			
		}
		System.out.println(dp[n]);
	}
}