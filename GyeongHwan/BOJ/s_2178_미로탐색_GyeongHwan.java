package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * @author hkh12
 * 
 * bfs를 이용해서 최단거리 구하기
 * 
 */

public class BOJ2178 {
	static class position{
		int row;
		int col;
		public position(int row, int col) {
			this.row=row;
			this.col=col;
		}
	}
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int n, m;
	static int[][] map;
	static boolean[][] visited;
	static String input;
	static int cost;
	static Deque<position> queue;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1}; 
	
	static int nx, ny;
	
	static void inputTestcase() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for(int row=0; row<n; row++) {
			input = br.readLine().trim();
			for(int col=0; col<m; col++) {
				map[row][col]=input.charAt(col)-'0';
			}
		}
		
		visited = new boolean[n][m];
		
	}
	
	static void bfs() {
		queue = new ArrayDeque<>();
		position now = new position(0,0);
		visited[0][0]=true;
		queue.addLast(now);
		while(!queue.isEmpty()) {
			now = queue.removeFirst();
			if(now.row==n && now.col==m) {
				break;
			}
			//상하좌우
			for(int idx=0; idx<4; idx++) {
				nx = now.row + dx[idx];
				ny = now.col + dy[idx];
				//범위를 벗어난다면
				if(nx<0 || nx>=n || ny<0 || ny>=m) {
					continue;
				}
				//이동할 수 없는 칸이라면
				if(map[nx][ny]==0) {
					continue;
				}
				//이미 방문한 칸이라면
				if(visited[nx][ny]==true) {
					continue;
				}
				//맵의 범위 안이고, 이동할 수 있는 칸이라면
				map[nx][ny]=map[now.row][now.col]+1;
				visited[nx][ny]=true;
				queue.addLast(new position(nx,ny));
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		//입력
		inputTestcase();
		//bfs
		bfs();
		//출력
		System.out.println(map[n-1][m-1]);
	}
}
