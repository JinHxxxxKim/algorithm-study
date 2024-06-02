import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * nCk를 구하는 문제
 * 
 */

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int n, k;
	static int ans=0;
	
	public static void combination(int selectIdx, int elementIdx) {
		//기저조건
		//모두 선택한 경우
		if(selectIdx==k) {
			ans++;
            return;
		}
		
		//모든 원소를 살펴본 경우
		if(elementIdx==n) {
			return;
		}
		

		//재귀호출
		combination(selectIdx+1, elementIdx+1);

		//재귀호출
		combination(selectIdx, elementIdx+1);
		
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		//조합 구하기
		combination(0,0);
		
		//출력
		System.out.println(ans);
	}
}