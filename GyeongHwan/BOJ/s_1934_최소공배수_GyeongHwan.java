import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int GCD(int a, int b) {
		if(a%b==0) {
			return b;
		}
		return GCD(b, a%b);
	}
	
	static int LCM(int a, int b) {
		return (a*b)/GCD(a,b);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine().trim());
		for(int testcase=1; testcase<=t; testcase++) {
			st = new StringTokenizer(br.readLine().trim());
			int e1 = Integer.parseInt(st.nextToken());
			int e2 = Integer.parseInt(st.nextToken());
			sb.append(LCM(e1,e2)).append("\n");
		}
		System.out.println(sb);
	}
}
