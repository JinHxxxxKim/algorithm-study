import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 
 * 1. 입력
 * 	1-1. 노드의 개수 n
 * 	1-2. 두 정점
 * 
 * 2. 부모를 저장할 부모테이블 parent 생성
 * 
 * 3. 1과 연결된 수들(a)의 부모는 1 -> a들과 연결된 수들의 부모는 a 
 * 
 * 4. 제공되는 연결된 두 정점들로 그래프 구성
 * 	4-1. 그래프 표현 방법: 인접행렬, 인접리스트, 간선리스트
 * 	=> 인접행렬: n의 최대가 10^5이므로 n*n의 행렬을 만들면 탐색할 때 시간초과 우려 
 *  => 인접리스트 or 간선리스트?: 특정 정점을 기준으로 연결된 자식노드들을 찾아야 하므로 인접리스트로 구현이 더 편리 
 * 5. 1을 시작으로 bfs 수행
 * 6. 
 * 
 * 
 */

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int n;
	static Deque<Integer> deque = new ArrayDeque<>();
	static int[] parent;
	static ArrayList<Integer>[] graph;
	
	static void inputTestcase() throws NumberFormatException, IOException {
		n = Integer.parseInt(br.readLine().trim());
		graph = new ArrayList[n+1];
		for(int idx=0; idx<=n; idx++) {
			graph[idx] = new ArrayList<>();
		}
		for(int idx=1; idx<n; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			graph[num1].add(num2);
			graph[num2].add(num1);
		}
		
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//입력
		inputTestcase();
		//부모테이블
		parent = new int[n+1];
		parent[1]=-1;
		
		
		//bfs
		deque.addLast(1);
		while(!deque.isEmpty()) {
			int now = deque.removeFirst();
			for(int idx=0; idx<graph[now].size(); idx++) {
				int child = graph[now].get(idx);
				if(parent[child]!=0) { //이미 부모노드가 정해졌다면
					continue;
				}
				parent[child]=now;
				deque.addLast(child);
			}
		}
		
		//출력
		for(int idx=2; idx<=n; idx++) {
			sb.append(parent[idx]).append("\n");
		}
		System.out.println(sb);
	}
}
