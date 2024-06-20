import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * BOJ_11724_연결요소의개수
 * @author parkrootseok
 *
 * 무방향 그래프에 대한 연결 요소 개수를 구하라
 *
 * 1. 무방향 그래프에 대한 정보를 받아 초기화
 * 2. Merge Sort를 이용해 Swap 횟수를 카운팅
 */
public class s_연결요소의개수_GeunSeok {

	public static class Vertex {

		int name;
		List<Integer> adjacentVertex;

		public Vertex(int name) {
			this.name = name;
			this.adjacentVertex = new ArrayList<>();
		}
	}

	public static class Graph {

		Vertex[] vertices;

		public Graph(Vertex[] vertices) {
			this.vertices = vertices;
		}

	}

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;

	public static Graph graph;
	public static int vertexNumber;
	public static int edgeNumber;
	public static boolean[] isVisited;

	public static void initGraph() throws IOException {

		inputs = br.readLine().trim().split(" ");
		vertexNumber = Integer.parseInt(inputs[0]);
		edgeNumber = Integer.parseInt(inputs[1]);

		graph = new Graph(new Vertex[vertexNumber + 1]);
		for (int vertexName = 1; vertexName <= vertexNumber; vertexName++) {
			graph.vertices[vertexName] = new Vertex(vertexName);
		}

		for (int curEdgeNumber = 0; curEdgeNumber < edgeNumber; curEdgeNumber++) {

			inputs = br.readLine().trim().split(" ");
			int from = Integer.parseInt(inputs[0]);
			int to = Integer.parseInt(inputs[1]);

			graph.vertices[from].adjacentVertex.add(to);
			graph.vertices[to].adjacentVertex.add(from);

		}

	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 무방향 그래프에 대한 정보를 받아 초기화
		initGraph();

		// 2. DFS를 사용하여 연결 요소의 개수를 카운팅
		isVisited = new boolean[vertexNumber + 1];
		int componentNumber = 0;
		for (int vertexName = 1; vertexName <= vertexNumber; vertexName++) {

			if (isVisited[vertexName]) {
				continue;
			}

			componentNumber++;
			dfs(vertexName);

		}

		sb.append(componentNumber);
		bw.write(sb.toString());
		bw.close();

	}

	public static void dfs(int curVertex) {

		isVisited[curVertex] = true;

		for (int adjacentVertex : graph.vertices[curVertex].adjacentVertex) {

			if (isVisited[adjacentVertex]) {
				continue;
			}

			dfs(adjacentVertex);

		}

	}

}
