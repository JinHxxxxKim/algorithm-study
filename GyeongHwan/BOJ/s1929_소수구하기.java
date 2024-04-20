import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int m, n;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		boolean[] notPrimeNum = new boolean[n+1];
		notPrimeNum[0]=true;
		notPrimeNum[1]=true;
		
		for(int i=2; i<=n; i++){
			//소수가 아니라고 판명된 수는 넘어가기
			if(notPrimeNum[i]){continue;}
			//소수인 수의 배수들을 찾아서 notPrimeNum표시
			for(int j=i+i; j<=n; j+=i){
				notPrimeNum[j]=true;
			}
		}
		
		
		for(int idx=m; idx<=n; idx++) {
			if(!notPrimeNum[idx]) {
				System.out.println(idx);
			}
		}
		
	}
}