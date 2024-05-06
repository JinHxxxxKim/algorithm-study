package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author JinHyung
 *	
 * 트리, BFS
 * 
 * 1. N을 입력받는다
 * 2. 모든 간선의 정보를 입력받아 인접리스트를 만든다.
 * 3. 1번 노드부터 탐색을 시작한다.
 * 4. 인접 노트 중 탐색하지 않은 노드(=자식노드)에 대해 부모 정보를 업데이트 한다
 */
public class s11725_트리의부모찾기_JinHyung {
	static final StringBuilder sb = new StringBuilder();
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N;
	static ArrayList<Integer>[] adList;
	static int[] parentInfo;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine().trim());
		adList = new ArrayList[N +1]; // 1번부터 시작하므로 크기 + 1
		
		// 인접리스트 생성
		for (int idx = 1; idx < N + 1; ++idx) {
			adList[idx] = new ArrayList<>();
		}
		
		// 간선 추가
		for (int idx = 1; idx < N; ++idx) {
			st = new StringTokenizer(br.readLine().trim());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			adList[node1].add(node2);
			adList[node2].add(node1);
		}

		parentInfo = new int[N + 1];

		// 탐색 시작: 1번 노트부터 BFS
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] isVisited = new boolean[N + 1];
		q.offer(1);
		isVisited[1] = true;
		while (!q.isEmpty()) {
			int currNode = q.poll();
			// 방문하지 않은(부모노드가 아닌) 노드에 대해 부모 정보 저장 및 queue에 삽입
			for (int adNode : adList[currNode]) {
				if (isVisited[adNode])
					continue;
				isVisited[adNode] = true;
				parentInfo[adNode] = currNode;
				q.offer(adNode);
			}
		}

		// 출력
		for (int idx = 2; idx < N + 1; ++idx) {
			sb.append(parentInfo[idx]).append("\n");
		}
		System.out.println(sb);
	}
}
