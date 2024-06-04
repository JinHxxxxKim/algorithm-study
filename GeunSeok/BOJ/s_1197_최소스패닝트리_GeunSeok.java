import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

/**
 * BOJ_1197_최소스패닝트리
 * @author parkrootseok
 *
 * 1. 정점의 개수와 간선의 개수를 받는다.
 * 2. 간선에 대한 정보를 받는다
 * 3. 크루스칼 알고리즘에서 연결성을 확인하기 위해 Union&find 관련 배열 초기화 진행
 * 4. 크루스칼 알고리즘을 통해 MST를 완성
 **/
public class s_1197_최소스패닝트리_GeunSeok {

	static class Edge implements Comparable<Edge> {

		int from;
		int to;
		int cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}

	}

	static class Graph {

		PriorityQueue<Edge> edges;

		public Graph(Edge[] edges) {
			this.edges = new PriorityQueue<>();
		}

	}

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static Graph graph;

	static int vertexNumber;
	static int edgeNumber;

	static int[] unf;
	static int[] rank;
	static int totalCost;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 정점의 개수와 간선의 개수를 받는다.
		inputs = br.readLine().trim().split( " ");
		vertexNumber = Integer.parseInt(inputs[0]);
		edgeNumber = Integer.parseInt(inputs[1]);

		// 2. 간선에 대한 정보를 받는다.
		graph = new Graph(new Edge[edgeNumber]);
		for (int index = 0; index < edgeNumber; index++) {

			inputs = br.readLine().trim().split( " ");

			int from = Integer.parseInt(inputs[0]);
			int to = Integer.parseInt(inputs[1]);
			int cost = Integer.parseInt(inputs[2]);

			graph.edges.add(new Edge(from, to, cost));

		}

		// 3. 크루스칼 알고리즘에서 연결성을 확인하기 위해 Union&find 관련 배열 초기화 진행
		init();

 		// 4. 크루스칼 알고리즘을 통해 MST 완성
		totalCost = 0;
		kruskal();

		sb.append(totalCost);
		bw.write(sb.toString());
		bw.close();
		return;

	}

	public static void init() {

		unf = new int[vertexNumber + 1];
		rank = new int[vertexNumber + 1];
		for (int curVertex = 1; curVertex <= vertexNumber; curVertex++) {
			unf[curVertex] = curVertex;
		}

	}

	public static int find(int vertex) {

		if (vertex == unf[vertex]) {
			return vertex;
		}

		return unf[vertex] = find(unf[vertex]);

	}

	public static void union(int from, int to) {

		int fromParent = find(from);
		int toParent = find(to);

		if (fromParent == toParent) {
			return;
		}

		if (rank[fromParent] > rank[toParent]) {
			unf[toParent] = fromParent;
			return;
		}

		unf[fromParent] = toParent;
		if (rank[fromParent] == rank[toParent]) {
			rank[toParent]++;
		}

	}

	public static void kruskal() {

		int connectedCount = 0;
		while (connectedCount < vertexNumber - 1) {

			Edge edge = graph.edges.poll();

			if (find(edge.from) == find(edge.to)) {
				continue;
			}

			union(edge.from, edge.to);
			totalCost += edge.cost;
			connectedCount++;

		}

	}

}