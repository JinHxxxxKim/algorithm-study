import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 1. 입력
 * 	1-1. 표의 크기 n, 합을 구해야 하는 횟수 m
 * 	1-2. 표 정보 map
 * 	1-3. x1, y1, x2, y2
 * 
 * 2. (0,0) ~ (x,y)까지의 범위의 합을 map[x][y]에 넣기
 * 
 * 3. 점화식: map[row][col]=map[row][col]+map[row][col-1]+map[row-1][col]-map[row-1][col-1];
 * 
 * 4. map을 만들 때, row=0인 행과 col=0인 열은 0으로 채우고, map[1][1]~map[n][n]에 입력받기
 * 
 */

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int n, m;
	static int[][] map;
	static int x1,y1,x2,y2;
	
	static int ans;
	
	static void inputTestcase() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n+1][n+1];
		for(int row=1; row<=n; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int col=1; col<=n; col++) {
				map[row][col]=Integer.parseInt(st.nextToken());
			}
		}
		
	}
	
	static void getSum() {
		//구간 합 구하기
		for(int row=1; row<=n; row++) {
			for(int col=1; col<=n; col++) {
				map[row][col]=map[row][col]+map[row][col-1]+map[row-1][col]-map[row-1][col-1];
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//입력
		inputTestcase();
		//구간합 구하기
		getSum();
		
		for(int testcase=1; testcase<=m; testcase++) {
			//각 테스트케이스 입력
			st = new StringTokenizer(br.readLine().trim());
			x1=Integer.parseInt(st.nextToken());
			y1=Integer.parseInt(st.nextToken());
			x2=Integer.parseInt(st.nextToken());
			y2=Integer.parseInt(st.nextToken());
			
			ans = map[x2][y2] - map[x2][y1-1] - map[x1-1][y2] + map[x1-1][y1-1];
			
			//출력
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}
}
