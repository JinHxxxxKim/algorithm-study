package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 1. 입력
 * 	1-1. n
 * 	1-2. map
 * 
 * 2. bfs
 * 	2-0. Position객체를 큐에 넣어준다.
 * 		2-0-1. 좌표, 방향, 이동횟수
 * 	2-1. 상, 하, 좌, 우, 회전
 * 	2-2. 방문처리: 3차원배열 
 * 		2-2-1. visited[0][][]: 가로
 * 		2-2-2. visited[1][][]: 세로
 * 
 * 3. 회전할 수 있는지 확인하는 메소드 canRotate
 * 	3-1. 맵 범위를 넘어가는 경우 
 * 	3-2. 나무가 있는 경우 
 * 4. 옮길 수 있는지 확인하는 메소드 canMove
 * 	4-1. 맵 범위를 넘어가는 경우
 * 	4-2. 나누가 있는 경우
 * 
 * 
 * 
 */

public class BOJ1938 {
	static class Position{
		int row;
		int col;
		int dir; //0->가로, 1->세로
		int count; //이동횟수
		public Position(int row, int col, int dir, int count) {
			this.row = row;
			this.col = col;
			this.dir = dir;
			this.count = count;
		}
	}
	
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int n;
	static char[][] map;
	static int ans;
	
	static boolean[][][] visited;
	
	//통나무와 타겟
	static Position tree;
	static Position target;
	
	//통나무와 타겟의 구체적인 좌표
	static Point[] treePoint = new Point[3];
	static Point[] targetPoint = new Point[3];
	
	//큐로 사용할 deque 생성
	static Deque<Position> deque;
	
	//상하좌우
	static int[] dx= {-1, 1, 0, 0};
	static int[] dy= {0, 0, -1, 1};
	
	
	static void inputTestcase() throws NumberFormatException, IOException{
		n = Integer.parseInt(br.readLine().trim());
		map = new char[n][n];
		int treeIdx=0;
		int targetIdx=0;
		for(int row=0; row<n; row++) {
			String str = br.readLine().trim();
			for(int col=0; col<n; col++) {
				map[row][col]=str.charAt(col);
				if(map[row][col]=='B') {
					treePoint[treeIdx]=new Point(row,col);
					treeIdx++;
				}
				if(map[row][col]=='E') {
					targetPoint[targetIdx]=new Point(row,col);
					targetIdx++;
				}
			}
		}
		
		//통나무 위치정보 객체 생성
		int treedir=0;
		if(treePoint[0].y+1==treePoint[1].y) {treedir=0;}
		else {treedir=1;}
		tree = new Position(treePoint[1].x, treePoint[1].y, treedir, 0);
		
		//타겟 위치정보 객체 생성
		int targetdir=0;
		if(targetPoint[0].y+1==targetPoint[1].y) {targetdir=0;}
		else {targetdir=1;}
		target = new Position(targetPoint[1].x, targetPoint[1].y, targetdir, 0);
		
	}
	
	static boolean canMove(int dir, int nx, int ny, int idx) {
		//가로 방향
		if(dir==0) {
			//상하이동
			if(idx<2) {
				//범위를 벗어나는 경우
				if(nx<0||nx>=n) {return false;}
				//나무가 있는 경우
				if(map[nx][ny]=='1' || map[nx][ny-1]=='1' || map[nx][ny+1]=='1') {return false;}
			}
			//좌우이동
			else {
				//범위를 벗어나는 경우
				if(ny-1<0||ny+1>=n) {return false;}
				//나무가 있는 경우
				if(map[nx][ny]=='1' || map[nx][ny-1]=='1' || map[nx][ny+1]=='1') {return false;}
			}
		}
		//세로 방향
		else {
			//상하이동
			if(idx<2) {
				//범위를 벗어나는 경우
				if(nx-1<0||nx+1>=n) {return false;}
				//나무가 있는 경우
				if(map[nx][ny]=='1' || map[nx-1][ny]=='1' || map[nx+1][ny]=='1') {return false;}
			}
			//좌우이동
			else {
				//범위를 벗어나는 경우
				if(ny<0||ny>=n) {return false;}
				//나무가 있는 경우
				if(map[nx][ny]=='1' || map[nx-1][ny]=='1' || map[nx+1][ny]=='1') {return false;}
			}
		}
		return true;
	}
	
	static boolean canRotate(int x, int y) {
		for(int row=x-1; row<=x+1; row++) {
			for(int col=y-1; col<=y+1; col++) {
				//맵 범위를 벗어난다면
				if(row<0 || row>=n || col<0 || col>=n) {return false;}
				//나무가 있다면
				if(map[row][col]=='1') {return false;}
			}
		}
		return true;
	}
	
	static void bfs() {
		deque = new ArrayDeque<>();
		deque.addLast(tree);
		visited[tree.dir][tree.row][tree.col]=true;
		
		while(!deque.isEmpty()) {
			Position now = deque.removeFirst();
			
			//타겟과 일치한다면
			if(now.row==target.row && now.col==target.col && now.dir==target.dir) {
				ans = now.count;
				return;
			}
			
			//상하좌우
			for(int idx=0; idx<4; idx++) {
				int nx = now.row + dx[idx];
				int ny = now.col + dy[idx];
				
				//통나무가 이동할 수 있는지 확인
				if(!canMove(now.dir, nx, ny, idx)) {continue;}
				
				//방문여부 확인
				if(visited[now.dir][nx][ny]) {continue;}
				
				visited[now.dir][nx][ny]=true;
				deque.addLast(new Position(nx,ny,now.dir,now.count+1));
			}
			
			//회전
			if(canRotate(now.row, now.col)) {
				if(now.dir==0 && !visited[1][now.row][now.col]) { //현재 가로방향이면, 회전 시 세로가 되는데 세로인 경우 미방문상태여야 탐색가치가 있다.
					visited[1][now.row][now.col]=true;
					deque.addLast(new Position(now.row, now.col, 1, now.count+1));
				}
				else if(now.dir==1 && !visited[0][now.row][now.col]) { //현재 세로방향이면, 회전 시 가로가 되는데 가로인 경우 미방문상태여야 탐색가치가 있다.
					visited[0][now.row][now.col]=true;
					deque.addLast(new Position(now.row, now.col, 0, now.count+1));
				}
			}
		}
		
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//입력
		inputTestcase();
		
		//방문체크용 3차원 배열 생성
		visited = new boolean[2][n][n];
		
		//큐 생성
		deque = new ArrayDeque<>();
		
		//bfs수행
		bfs();
		
		//출력
		System.out.println(ans);
	}
}
