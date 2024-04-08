package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 다익스트라 알고리즘
 * 
 * 1. dist[] 배열을 INF로 모두 초기화한다.
 * 2. 시작 정점의 dist값을 0으로 갱신하고, 우선순위 큐에 넣는다
 * 3. 우선순위 큐에서 꺼내서 인접 정점까지의 가중치를 확인하고 
 *    현재 자신의 dist값에 더하고 인접 정점의 dist를 갱신하고 우선순위 큐에 넣는다.
 * 4. 인접 정점 확인 시, 이미 방문한 정점은 PASS
 * 5. 모든 정점을 확인하였다면 break
 */
public class Sol_1753 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	private static final int INF = Integer.MAX_VALUE;

	static int V, E; // 정점, 간선 수
	static Node[] adList; // 인접 리스트
	static boolean[] isVisited; // 방문 여부 배열
	static int[] dist; // 각 노드까지의 최단 거리
	static int startNode; // 시작 정점

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine().trim());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		startNode = Integer.parseInt(br.readLine().trim());

		// 정점 번호는 1번부터 시작
		adList = new Node[V + 1];
		isVisited = new boolean[V + 1];
		dist = new int[V + 1];
		Arrays.fill(dist, INF);
		dist[startNode] = 0;

		// 인접 리스트 생성
		for (int edgeCnt = 0; edgeCnt < E; ++edgeCnt) {
			st = new StringTokenizer(br.readLine().trim());
			int srcNode = Integer.parseInt(st.nextToken());
			int destNode = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adList[srcNode] = new Node(adList[srcNode], destNode, weight);
		}

		// 다익스트라 알고리즘 시작
		PriorityQueue<Node> pq = new PriorityQueue<>();
		// 시작정점과, 해당 노드로 도달하는데의 최소비용
		pq.add(new Node(startNode, 0));
		int visitCnt = 0; // 방문 정점개수 카운팅
		while (!pq.isEmpty()) {
			Node currNode = pq.poll();
			int currNodeNum = currNode.nodeNum;
			int currCost = currNode.weight;

			// 방문한 적이 있다면 PASS
			if (isVisited[currNodeNum])
				continue;

			visitCnt++;
			// 방문한 정점의 수가 V개라면 모든 정점을 방문했다 판단
			if (visitCnt == V)
				break;
			isVisited[currNodeNum] = true;

			// 인접 노드 탐색
			Node adNode = adList[currNodeNum];
			while (adNode != null) {
				// 방문한 노드는 PASS, 도달하는 비용이 업데이트 되지 않으면 PASS
				if (!isVisited[adNode.nodeNum] && currCost + adNode.weight < dist[adNode.nodeNum]) {
					dist[adNode.nodeNum] = currCost + adNode.weight;
					pq.offer(new Node(adNode.nodeNum, dist[adNode.nodeNum]));
				}
				adNode = adNode.next;
			}
		}
		// 출력
		for (int idx = 1; idx <= V; ++idx) {
			if (dist[idx] == Integer.MAX_VALUE)
				sb.append("INF").append("\n");
			else
				sb.append(dist[idx]).append("\n");
		}
		System.out.println(sb);
	}

	// 정점 클래스
	static class Node implements Comparable<Node> {
		Node next;
		int nodeNum, weight;

		public Node(Node next, int nodeNum, int weight) {
			super();
			this.next = next;
			this.nodeNum = nodeNum;
			this.weight = weight;
		}

		public Node(int nodeNum, int weight) {
			this.nodeNum = nodeNum;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
}