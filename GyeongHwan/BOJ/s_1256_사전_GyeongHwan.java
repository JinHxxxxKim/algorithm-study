import java.util.*;
import java.io.*;

/**
 * @hkh1284
 *
 * 문제풀이 방법
 * 1. 조합
 * : 200!/(100!100!)이므로 시간초과
 *
 * 2. 다이나믹 프로그래밍
 * 	2-0. dp테이블 생성: dp[n+1][m+1] => a의 사용개수, z의 사용개수
 * 	2-1. a로 시작하는 경우, dp[aNum-1][zNum]
 * 	2-2. z로 시작하는 경우, dp[aNum][zNum-1]
 * 	2-3. 즉, dp[aNum][zNum]=dp[aNum-1][zNum]+dp[aNum][zNum-1]
 * 	2-4. dp[n][m]은 a를 n개 z를 m개 사용하는 경우의 문자열 개수
 * 	2-5. dp[n][m]<k이면 -1반환
 *
 * 3. k번째 문자열 구하기 =>
 * 	3-0. 항상 a가 z보다 먼저 온다.
 * 	3-1. 첫번째 문자가 a인 문자열의 수(dp[a-1][z])<k라면,
 * 		dp[a-1][z]>k
 * 	3-2. 첫번째 문자가 a인 문자열의 수(dp[a-1][z])>=k라면,
 *		dp[][]
 *
 * 주의!
 * n=100 m=100인 경우, dp[n][m]의 값은 대략 9*10^58이 된다.
 * 따라서, dp의 자료형은 double[][]이 되어야 한다.
 *
 */

public class BOJ1256 {
	static BufferedReader br;
	static StringTokenizer st;
	static int n, m, k;
	static String ans="";
	static double[][] dp;

	static void inputTestcase() throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		//dp테이블 생성 및 초기화
		dp = new double[n+1][m+1];
		//z의 개수가 0인 경우
		for(int idx=1; idx<=n; idx++){
			dp[idx][0]=1;
		}
		//a의 개수가 0인 경우
		for(int idx=1; idx<=m; idx++){
			dp[0][idx]=1;
		}
	}

	static void makes(int aNum, int zNum, double k){ //aNum는 남은 a개수, zNum는 남은 z개수
		//만약 a문자만 남은 경우
		if(zNum==0){
			for(int idx=0; idx<aNum; idx++){
				ans+="a";
			}
			return;
		}
		//만약 z문자만 남은 경우
		if(aNum==0){
			for(int idx=0; idx<zNum; idx++){
				ans+="z";
			}
			return;
		}
		//첫번째 문자가 a인 문자열의 수
		double startA = dp[aNum-1][zNum];
		//첫번째 문자가 a인 문자열의 수가 k보다 작은 경우(=k번째 문자열이 z로 시작하는 경우)
		if(startA<k){
			ans+="z";
			zNum--;
			makes(aNum, zNum, k-startA);
		}
		//첫번째 문자가 a인 문자열의 수가 k보다 크거나 같은 경우(=k번째 문자열이 a로 시작하는 경우)
		else{
			ans+="a";
			aNum--;
			makes(aNum, zNum, k);
		}
	}

	public static void main(String[] args) throws IOException{
		//입력
		inputTestcase();
		//다이나믹 프로그래밍
		for(int aNum=1; aNum<=n; aNum++){
			for(int zNum=1; zNum<=m; zNum++){
				dp[aNum][zNum]=dp[aNum-1][zNum]+dp[aNum][zNum-1];
			}
		}
		//수록된 문자열의 개수가 k보다 작으면 -1 출력
		if(dp[n][m]<k){
			System.out.println(-1);
			return;
		}
		//문자열 만들기
		makes(n, m, k);
		//출력
		System.out.println(ans);
		System.out.println(dp[n][m]);
	}
}
