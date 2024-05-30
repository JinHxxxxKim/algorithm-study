import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * BOJ_11437_LCA
 * @author parkrootseok
 *
 * 트리에서 두 노드의 가장 가까운 공통 조상을 찾아 출력해라
 *
 * 1. 노드의 개수를 입력
 * 2. 트리 초기화
 * 3. 공통 조상을 찾을 쌍의 개수 입력
 * 4. DFS 탐색을 통해 부모 노드 기록 배열과 깊이 배열을 생성
 * 5. 공통 조상을 찾을 쌍 입력
 * 6. 공통 조상 찾기
 *  6-1. 두 노드의 높이를 동일하게 설정
 *  6-2. 두 노드가 동일할 때 까지 부모 노드로 이동
 **/
public class s_LCA_GeunSeok {

	public static class Node {

		int name;
		ArrayList<AdjNode> adjacentNodes;

		public Node(int name) {
			this.name = name;
			this.adjacentNodes = new ArrayList<>();
		}

	}

	public static class AdjNode {

		int name;

		public AdjNode(int name) {
			this.name = name;
		}

	}

	public static BufferedReader br;
	public static BufferedWriter bw;
	public static StringBuilder sb;
	public static String[] inputs;

	public static int nodeNumber;
	public static int searchNumber;
	public static Node[] nodes;

	public static boolean[] isVisited;
	public static int[] depths;
	public static int[] parent;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 1. 노드의 개수를 입력
		nodeNumber = Integer.parseInt(br.readLine().trim());

		// 2. 트리 초기화
		nodes = new Node[nodeNumber + 1];
		for (int name = 1; name <= nodeNumber; name++) {
			nodes[name] = new Node(name);
		}

		for (int index = 0; index < nodeNumber - 1; index++) {

			inputs = br.readLine().trim().split(" ");
			int from = Integer.parseInt(inputs[0]);
			int to = Integer.parseInt(inputs[1]);

			nodes[from].adjacentNodes.add(new AdjNode(to));
			nodes[to].adjacentNodes.add(new AdjNode(from));

		}

		// 3. 공통 조상을 찾을 쌍의 개수 입력
		searchNumber = Integer.parseInt(br.readLine().trim());

		// 4. DFS 탐색을 통해 부모 노드 기록 배열과 깊이 배열을 생성
		isVisited = new boolean[nodeNumber + 1];
		depths = new int[nodeNumber + 1];
		parent = new int[nodeNumber + 1];
		dfs(1, 0);

		// 5. 공통 조상을 찾을 쌍 입력
		for (int curSearchNumber = 0; curSearchNumber < searchNumber; curSearchNumber++) {

			inputs = br.readLine().trim().split(" ");
			int aNode = Integer.parseInt(inputs[0]);
			int bNode = Integer.parseInt(inputs[1]);

			// 6. 공통 조상 찾기
			int commonAncestorNode = LCA(aNode, bNode);

			sb.append(commonAncestorNode).append("\n");

		}

		bw.write(sb.toString());
		bw.close();

	}

	public static void dfs(int curNode, int depth) {

		// 현재 노드의 깊이 기록
		depths[curNode] = depth;

		// 현재 노드을 방문 처리
		isVisited[curNode] = true;

		// 인접 노드을 탐색
		for (AdjNode adjNode : nodes[curNode].adjacentNodes) {

			if (isVisited[adjNode.name]) {
				continue;
			}

			// 방문할 노드의 부모를 기록
			parent[adjNode.name] = curNode;

			// 다음 노드 탐색
			dfs(adjNode.name, depth + 1);

		}

	}

	public static int LCA(int A, int B) {

		// 6-1. 두 노드의 높이를 동일하게 설정
		while (depths[A] < depths[B])  {
			B = parent[B];
		}

		while (depths[B] < depths[A])  {
			A = parent[A];
		}

		// 6-2. 두 노드가 동일할 때 까지 부모 노드로 이동
		while (A != B) {
			A = parent[A];
			B = parent[B];
		}

		return A;

	}

}