package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * @author hkh12
 * 
 * 연결요소 = 나누어진 각각의 그래프
 * 
 * 1. 입력
 * 	1-1. 인접리스트 방식으로 그래프 표현
 * 2. bfs를 이용해 아직 방문체크가 되지 않은 노드와 연결된 모든 노드 탐색
 * 3. 연결 요소의 개수+1
 * 4. 모든 노드가 방문체크될 때까지 2와 3을 반복
 * 
 */

public class BOJ11724 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int n, m;
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	static int u, v;
	static int connectedComponent=0;
	
	static Deque<Integer> queue;
	
	static void inputTestcase() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[n+1];
		for(int idx=0; idx<=n; idx++) {
			graph[idx]=new ArrayList<>();
		}
		for(int idx=0; idx<m; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}
		
		visited = new boolean[n+1];
		
	}
	
	static void bfs(int start) {
		queue = new ArrayDeque<>();
		queue.addLast(start);
		while(!queue.isEmpty()) {
			int now = queue.removeFirst();
			for(int idx=0; idx<graph[now].size(); idx++) {
				if(visited[graph[now].get(idx)]==false) {
					queue.addLast(graph[now].get(idx));
					visited[graph[now].get(idx)]=true;
				}
				
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		//입력
		inputTestcase();
		//bfs수행
		for(int idx=1; idx<=n; idx++) {
			if(visited[idx]==false) {
				bfs(idx);
				connectedComponent++;
			}
		}
		//출력
		System.out.println(connectedComponent);
	}
}
