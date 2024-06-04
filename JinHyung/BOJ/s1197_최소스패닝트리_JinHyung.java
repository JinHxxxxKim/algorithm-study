package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/**
 * @author JinHyungKim
 * 
 * MST
 * 
 * 1. V, E를 입력 받는다.
 * 2. 모든 간선을 입력받아 간선 우선순위 큐에 저장한다.
 *   - 이 때, 우선순위 큐는 간선의 가중치가 낮은 간선이 우선순위가 높다
 * 3. 우선순위 큐가 빌 때까지 반복한다.
 *   3-1. 간선의 두 정점 모두 방문한 적이 있다면 PASS
 *   3-2. 방문하지 않은 정점이 있다면 해당 정점을 방문처리 한 뒤, 가중치 합에 더한다.
 * 4. 최종적으로 가중치의 합을 출력한다.
 *
 */
public class s1197_최소스패닝트리_JinHyung {
	static final StringBuilder sb = new StringBuilder();
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int V, E;
	static int[] rootInfo;
	
	public static void main(String[] args) throws Exception {
		// 입력
		st = new StringTokenizer(br.readLine().trim());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		rootInfo = new int[V + 1];
		
		// 간선 입력 받고, 저장(정렬)
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int cnt = 0; cnt < E; ++cnt) {
			st = new StringTokenizer(br.readLine().trim());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			Edge e = new Edge(node1, node2, cost);
			pq.offer(e);
		}

		// 각 노드 루트 정보 초기화
		for (int nodeNum = 1; nodeNum < V + 1; ++nodeNum) {
			rootInfo[nodeNum] = nodeNum;
		}

		// MST
		int costSum = 0;
		while (!pq.isEmpty()) {
			Edge currEdge = pq.poll();
			int node1 = currEdge.node1;
			int node2 = currEdge.node2;
			int cost = currEdge.cost;
			// 두 노드의 root가 다르다 => 두 노드가 다른 집합에 속해있다.
			if (find(node1) != find(node2)) {
				// 두 노드의 집합을 합친 뒤, 비용 합
				union(node1, node2);
				costSum += cost;
			}
		}
		System.out.println(costSum);
	}
	
	
	// 두 node의 집합을 union
	static void union(int node1, int node2) {
		int root1 = find(node1);
		int root2 = find(node2);
		
		rootInfo[root1] = root2;
	}
	
	// node의 root를 찾는 메소드
	static int find(int node) {
		if (node == rootInfo[node])
			return node;
		return rootInfo[node] = find(rootInfo[node]);
	}
	
	// 간선 클래스
	static class Edge implements Comparable<Edge>{
		int node1, node2;
		int cost;
		
		public Edge(int node1, int node2, int cost) {
			super();
			this.node1 = node1;
			this.node2 = node2;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge edge) {
			return Integer.compare(cost, edge.cost);
		}
	}
}
