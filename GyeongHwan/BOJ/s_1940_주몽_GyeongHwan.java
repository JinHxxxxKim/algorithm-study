import java.util.*;
import java.io.*;

public class BOJ1940 {
	static BufferedReader br;
	static StringTokenizer st;
	static int n, m;
	static int[] items;
	static int ans=0;
	static int[] selectList = new int[2];


	static void inputTestcase() throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		m = Integer.parseInt(br.readLine().trim());
		items = new int[n];
		st = new StringTokenizer(br.readLine().trim());
		for(int idx=0; idx<n; idx++){
			items[idx]=Integer.parseInt(st.nextToken());
		}
	}

	static void combination(int selectIdx, int elementIdx){
		//기저조건
		if(selectIdx == 2){
			if(selectList[0]==0 || selectList[1]==0){return;}
			if(selectList[0]+selectList[1]==m){
				ans++;
			}
			return;
		}
		if(elementIdx==n){
			return;
		}

		//아직 다 선택하지 않았다면
		selectList[selectIdx]=items[elementIdx];
		combination(selectIdx+1, elementIdx+1);
		selectList[selectIdx]=0;
		combination(selectIdx, elementIdx+1);
	}

	public static void main(String[] args) throws IOException {
		//입력
		inputTestcase();
		//조합
		combination(0,0);
		//출력
		System.out.println(ans);
	}
}
