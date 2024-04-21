import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * goal: 상담했을 때, 백준이 얻을 수 있는 최대 수익 구하기
 * 
 * 1. 입력
 * 	1-1. 남은 날짜 수 n
 * 	1-2. 기간 time, 금액 pay => 각각 int[]로 저장
 * 
 * 2. dp
 * 	2-0. 메모이제이션: int[] memo: i일~마지막n일까지 받을 수 있는 최대금액
 * 	2-1. 점화식: max(pay[now]+memo[now+time[now]], memo[now+1])
 * 	2-2. 뒤에서부터 memo 채우기
 * 
 */

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int n;
	static int[] memo;
	static int[] time;
	static int[] pay;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine().trim());
		memo = new int[n+1];
		time = new int[n+1];
		pay = new int[n+1];
		for(int idx=1; idx<=n; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int t = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			time[idx]=t;
			pay[idx]=p;
		}
		
		//마지막 날짜 memo값 구하기
		if(time[n]!=1) {memo[n]=0;}
		else {memo[n]=pay[n];}
		
		//뒤에서부터 앞으로 memo배열 채우기
		for(int now=n-1; now>=1; now--) {
			//
			if(now+time[now]-1==n) {
				memo[now]=Math.max(pay[now], memo[now+1]);
			}
			else if(now+time[now]-1>n) {
				memo[now]=memo[now+1];
			}else {
				memo[now]=Math.max(pay[now]+memo[now+time[now]],memo[now+1]);
			}
		}
		
		System.out.println(memo[1]);
		
	}
}