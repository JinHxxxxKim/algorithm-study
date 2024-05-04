import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;
 
/**
 * goal: 가장 높이 탑을 쌓는 경우를 찾고, 높이와 위에서부터의 벽돌번호를 출력해라.
 * 
 * 1. 입력
 * 	1-1. 벽돌의 수 brick
 * 	1-2. area, height, weight
 * => 벽돌 클래스를 만들 때, area, height, weight 뿐만 아니라 id 필드까지 추가해 벽돌의 번호를 함께 저장
 * 
 * 2. 정렬
 * 	2-1. 밑면의 넓이를 기준으로 오름차순 정렬
 * 
 * 3. dp테이블 생성
 * 	3-1. dp[idx]는 idx번째 벽돌을 맨 아래에 놓았을 때 꼭대기까지의 최대 높이를 의미
 * 초기idea) dp[idx]를 밑면넓이 기준 0~idx번째 벽돌까지 고려했을 때 최대높이라고 생각했음
 * 
 * 4. 점화식
 * 	4-1. dp[idx]를 구하려면?
 * 	4-2. prevIdx번째 벽돌이 idx번째 벽돌보다 무게가 가볍고 밑면넓이가 작다고 가정
 * 	4-3. 0~idx-1까지 각 벽돌이 idx번째 벽돌보다 무게가 가볍고 밑면넓이가 작다면
 * 		4-3-1. dp[idx]= Max(dp[idx], idx번째 벽돌의 높이 + dp[prevIdx]) 수행
 * 
 * 5. 가장 높은 탑의 경우 사용된 벽돌 번호 구하기
 * 	5-1. 스택생성
 * 	5-2. dp[]에서 뒤에서부터 ans와 같은 값을 찾기
 *  5-3. 해당 벽돌번호를 스택에 넣기
 *  5-4. targetHeight에서 해당벽돌높이만큼을 뺀 값을 다시 dp[]에서 찾기
 *  
 */

public class Main {
	static class brick implements Comparable<brick>{
		int id;
		int area;
		int weight;
		int height;
		public brick(int id, int area, int weight, int height) {
			this.id = id;
			this.area = area;
			this.weight = weight;
			this.height = height;
		}
		//area 기준으로 정렬
		public int compareTo(brick o) {
			return Integer.compare(this.area, o.area);
		}
	}
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int targetHeight=0;
	
	static int brickCnt;
	static brick[] bricks;
	
	static int[] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		//입력
		brickCnt = Integer.parseInt(br.readLine().trim());
		bricks = new brick[brickCnt];
		for(int idx=0; idx<brickCnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int a = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			bricks[idx] = new brick(idx+1,a,w,h);
		}
		
		//밑면의 넓이를 기준으로 오름차순 정렬
		Arrays.sort(bricks);
		
		//dp테이블 생성
		dp = new int[brickCnt];
		
		//dp테이블 채우기
		for(int now=0; now<brickCnt; now++) {
			dp[now]=bricks[now].height;
			for(int prev=0; prev<now; prev++) {
				if(bricks[prev].weight<bricks[now].weight) {
					dp[now]= Math.max(dp[now], bricks[now].height + dp[prev]);
				}
			}
		}
		
		//가장 높은 탑의 높이 찾기
		for(int idx=0; idx<brickCnt; idx++) {
			if(dp[idx]>targetHeight) {
				targetHeight=dp[idx];
			}
		}
		
		//가장 높은 탑의 경우 사용된 벽돌 번호 구하기
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		for(int cur=brickCnt-1; cur>=0; cur--) {
			if(targetHeight==dp[cur]) {
				deque.addLast(bricks[cur].id);
				targetHeight-=bricks[cur].height;
			}
		}
		
		//출력
		sb.append(deque.size()).append("\n");
		while(!deque.isEmpty()) {
			sb.append(deque.removeLast()).append("\n");
		}
		
		System.out.println(sb);
		
	}
}
