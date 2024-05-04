import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 1. 입력
 * 	1-1. 사람의 수 n
 * 	1-2. 각 사람이 돈을 인출하는데 걸리는 시간 withdraw
 * 2. 돈을 인출하는데 걸리는 시간을 기준으로 오름차순 정렬
 * 3. 1번시간*n + 2번시간*(n-1) + ... + n번시간*1
 * 
 */

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int n;
	static int[] withdraw;
	static int ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		n = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine().trim());
		withdraw = new int[n];
		for(int idx=0; idx<n; idx++) {
			withdraw[idx]=Integer.parseInt(st.nextToken());
		}
		
		//정렬
		Arrays.sort(withdraw);
		
		//필요한 시간 합의 최솟값 구하기
		for(int idx=0; idx<n; idx++) {
			ans += withdraw[idx]*(n-idx);
		}
		
		
		//출력
		System.out.println(ans);
	}
}
