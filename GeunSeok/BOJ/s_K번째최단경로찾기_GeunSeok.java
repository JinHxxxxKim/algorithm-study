import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * BOJ_1854_K번째최단경로찾기
 * @author parkrootseok
 *
 * 정점들의 관계와 K에 대한 정보가 주어지고 1번 도시에서 i번 도시로 가는 K번째 최단 경로를 구해라.
 * 만약, 존재하지 않는 경우 -1을 출력
 *
 * 1. 정점의 갯수, 간선의 갯수, 목표 순번을 입력
 * 2. 그래프에 대한 정보를 초기화
 * 3. dijkstra 알고리즘을 통해 최단 경로를 조사
 *  3-1. dijkstra 알고리즘을 사용하기 위한 준비
 * 4. 인접 정점 탐색 시작
 *  4-1. 비용을 관리하는 우선순위 큐의 사이즈가 목표 순번보다 작거나 다른 최적해라면
 *    4-1-1. 비용을 관리하는 우선순위 큐에 추가
 *    4-1-2. 만약 추가한 후에 우선운위 큐의 사이즈가 목표 순번보다 크다면 삭제
 *    4-1-3. 다음 탐색을 위해 정점을 추가
 **/
public class s_K번째최단경로찾기_GeunSeok {

	public static class Vertex {

		int name;
		ArrayList<AdjVertex> adjacentVertices;
		PriorityQueue<Integer> cost;

		public Vertex(int name) {
			this.name = name;
			this.adjacentVertices = new ArrayList<>();
			this.cost = new PriorityQueue<>(Comparator.reverseOrder());
		}

	}

	public static class AdjVertex {

		int name;
		int cost;

		public AdjVertex(int name, int cost) {
			this.name = name;
			this.cost = cost;
		}

	}

	public static class Graph {

		Vertex[] vertices;

		public Graph(Vertex[] vertices) {
			this.vertices = vertices;
		}

	}

	public static class Node implements Comparable<Node> {

		int name;
		int cost;

		public Node(int name, int cost) {
			this.name = name;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}

	}


	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;

	public static int vertexNumber;
	public static int edgeNumber;
	public static int targetOrder;
	public static Graph graph;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 정점의 갯수, 간선의 갯수, 목표 순번을 입력
		inputs = br.readLine().trim().split(" ");
		vertexNumber = Integer.parseInt(inputs[0]);
		edgeNumber = Integer.parseInt(inputs[1]);
		targetOrder = Integer.parseInt(inputs[2]);

		// 2. 그래프에 대한 정보를 초기화
		graph = new Graph(new Vertex[vertexNumber + 1]);
		for (int vName = 1; vName <= vertexNumber; vName++) {
			graph.vertices[vName] = new Vertex(vName);
		}

		for (int index = 0; index < edgeNumber; index++) {

			inputs = br.readLine().trim().split(" ");
			int from = Integer.parseInt(inputs[0]);
			int to = Integer.parseInt(inputs[1]);
			int cost = Integer.parseInt(inputs[2]);

			graph.vertices[from].adjacentVertices.add(new AdjVertex(to, cost));

		}

		// 3. dijkstra 알고리즘을 통해 최단 경로를 조사
		dijkstra();

		for (int vertex = 1; vertex <= vertexNumber; vertex++) {

			if (graph.vertices[vertex].cost.size() < targetOrder) {
				sb.append("-1").append("\n");
			}

			else {
				sb.append(graph.vertices[vertex].cost.peek()).append("\n");
			}

		}

		bw.write(sb.toString());
		bw.close();

	}

	public static void dijkstra() {

		// 3-1. dijkstra 알고리즘을 사용하기 위한 준비
		PriorityQueue<Node> pq = new PriorityQueue<>();
		graph.vertices[1].cost.add(0);
		pq.add(new Node(1, graph.vertices[1].cost.peek()));

		// 4. 인접 정점 탐색 시작
		while (!pq.isEmpty()) {

			Node curNode = pq.poll();
			Vertex from = graph.vertices[curNode.name];

			for (AdjVertex adjVertex : from.adjacentVertices) {

				Vertex to = graph.vertices[adjVertex.name];

				// 4-1. 비용을 관리하는 우선순위 큐의 사이즈가 목표 순번보다 작거나 다른 최적해라면
				if (to.cost.size() < targetOrder || curNode.cost + adjVertex.cost < to.cost.peek()) {

					// 4-1-1. 비용을 관리하는 우선순위 큐에 추가
					to.cost.add(curNode.cost + adjVertex.cost);

					// 4-1-2. 만약 추가한 후에 우선운위 큐의 사이즈가 목표 순번보다 크다면 삭제
					if (targetOrder < to.cost.size()) {
						to.cost.poll();
					}

					// 4-1-3. 다음 탐색을 위해 정점을 추가
					pq.add(new Node(adjVertex.name, curNode.cost + adjVertex.cost));

				}

			}

		}

	}

}
