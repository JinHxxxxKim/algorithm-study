package algo;

import java.util.*;
import java.io.*;

/**
 * @author hkh12
 * 
 * a가 b를 신뢰한다. = b를 해킹 시, a도 해킹 가능
 * 
 * 그래프의 표현 => 자기자신을 신뢰하는 노드들의 리스트 생성
 * 각 노드에서 bfs탐색 시, 방문하는 노드의 개수 구하기
 * 
 * 
 * 
 */

public class BOJ1325 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	static int n, m;
	static int a, b;
	static ArrayList<Integer>[] graph;
	static int[] damage;
	static Deque<Integer> deque;
	
	static void inputTestcase() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new ArrayList[n+1];
		for(int idx=1; idx<n+1; idx++) {
			graph[idx] = new ArrayList<>();
		}
		for(int idx=0; idx<m; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			graph[b].add(a);
		}
	}
	
	static void bfs(int start) {
		//방문체크용 배열
		boolean[] visited = new boolean[n+1];
		visited[start]=true;
		//큐 생성
		deque = new ArrayDeque<>();
		deque.addLast(start);
		//방문할 수 있는 노드 세기
		int cnt=1;

		while(!deque.isEmpty()) {
			int now = deque.removeFirst();
			for(int idx=0; idx<graph[now].size(); idx++) {
				if(!visited[graph[now].get(idx)]) {
					visited[graph[now].get(idx)]=true;
					deque.addLast(graph[now].get(idx));
					cnt++;
				}
			}
		}
		damage[start]=cnt;
	}
	
	public static void main(String[] args) throws IOException {
		//입력
		inputTestcase();
		
		//특정 노드가 해킹될 때, 해킹할 수 있는 모든 노드 개수를 정리하는 배열
		damage = new int[n+1];
		
		//bfs로 damage배열 완성
		for(int idx=1; idx<n+1; idx++) {
			bfs(idx);
		}
		
		//damage배열의 최댓값 구하기
		int maxDamage = 0;
		for(int idx=1; idx<=n; idx++) {
			if(maxDamage<damage[idx]) {
				maxDamage=damage[idx];
			}
		}
		//해당 데미지를 가지는 노드의 번호를 출력
		for(int idx=1; idx<=n; idx++) {
			if(maxDamage==damage[idx]) {
				sb.append(idx).append(" ");
			}
		}
		System.out.println(sb);
	}
}
