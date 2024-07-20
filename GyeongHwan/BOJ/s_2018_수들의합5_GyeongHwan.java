import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br;
	static int n, cnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		for(int idx=1; idx<=n/2; idx++) {
			int result=0; 
			int temp=0;
			while(result<n) {
				result=result+idx+temp;
				temp++;
				if(result==n) {
					cnt++;
					break;
				}
			}
		}
		cnt++; //n을 나타내는 방법 중 자기자신 하나로 나타내는 방법 추가
		System.out.println(cnt);
		
	}
}