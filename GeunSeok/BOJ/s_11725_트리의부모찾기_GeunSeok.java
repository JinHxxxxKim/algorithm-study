import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * BOJ_11725_트리의부모찾기
 * @author parkrootseok
 *
 * 1. 정점의 개수를 받기
 * 2. 정점들의 연결 정보를 받기
 * 3. dfs를 활용하여 각 정점들의 부모 노드를 탐색
 * 4. 정점들의 부모 노드를 출력
 **/
public class s_11725_트리의부모찾기_GeunSeok {

	static class Vertex {

		int name;
		List<Integer> adjacentVertices;

		public Vertex(int name, List<Integer> adjacentVertices) {
			this.name = name;
			this.adjacentVertices = adjacentVertices;
		}

	}

	static final int ROOT_VERTEX = 1;

	static BufferedReader br;
	static BufferedWriter bw;
	static StringBuilder sb;
	static String[] inputs;

	static int vertexNumber;
	static Vertex[] vertices;
	static int[] parents;
	static boolean isVisited[];

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 정점의 개수를 받기
		vertexNumber = Integer.parseInt(br.readLine().trim());

		parents = new int[vertexNumber + 1];
		vertices = new Vertex[vertexNumber + 1];
		for (int vertex = 0; vertex <= vertexNumber; vertex++) {
			vertices[vertex] = new Vertex(vertex, new ArrayList<>());
		}

		// 2. 정점들의 연결 정보를 받기
		for (int index = 1; index < vertexNumber; index++) {

			inputs = br.readLine().trim().split(" ");

			int from = Integer.parseInt(inputs[0]);
			int to = Integer.parseInt(inputs[1]);

			vertices[from].adjacentVertices.add(to);
			vertices[to].adjacentVertices.add(from);
		}

		// 3. dfs를 활용하여 각 정점들의 부모 노드를 탐색
		isVisited = new boolean[vertexNumber + 1];
		dfs(ROOT_VERTEX);

		// 4. 정점들의 부모 노드를 출력
		for (int vertex = 2; vertex <= vertexNumber; vertex++) {
			sb.append(parents[vertex]).append("\n");
		}

		bw.write(sb.toString());
		bw.close();

	}

	public static void dfs(int curVertex) {

		// 전처리 : 방문 체크
		isVisited[curVertex] = true;

		for (int adjacentVertex : vertices[curVertex].adjacentVertices) {

			// 이미 방문한 정점이라면 스킵
			if (isVisited[adjacentVertex]) {
				continue;
			}

			// 전처리 : 방문할 정점의 부모를 현재 방문한 노드로 설정
			parents[adjacentVertex] = curVertex;

			// 재귀 호출
			dfs(adjacentVertex);

		}

	}

}