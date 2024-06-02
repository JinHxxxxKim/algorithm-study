package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 1. 입력
 * 	1-1. 노드의 번호 nodeNum => 최대 50000개이므로 인접행렬 방식의 그래프 표현은 불가
 * 	1-2. 트리 상에서 연결된 두 정점 => 인접리스트 방식으로 그래프 표현
 * 	1-3. 두 노드의 쌍 개수 tc
 * 	1-4. 두 노드의 쌍
 * 
 * 2. 최소 공통 조상 구하기
 * 	2-1. dfs를 이용해서 모든 노드의 깊이 구하기
 * 	2-2. 깊이가 다르다면, 깊이의 차이만큼 깊이가 더 깊은 노드의 부모를 찾는다.
 * 	2-3. 깊이가 같다면, 두 노드 동시에 부모노드로 올라가면서 최소 공통 조상을 찾는다.
 * 
 * 
 * 
 * 
 */

public class BOJ11437 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int nodeNum, tc;
	static ArrayList<ArrayList<Integer>> tree;
	static int[] depth;
	static int[] parent; 
	
	//dfs방식으로 각 노드의 깊이와 부모 구하기
	static void getDepth(int from, int cnt) {
		depth[from] = cnt++;
		
		for(Integer next: tree.get(from)) {
			if(depth[next]==0) {
				getDepth(next, cnt);
				parent[next]=from;
			}
		}
	}
	
	static void inputTestcase() throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		nodeNum = Integer.parseInt(br.readLine().trim());
		tree = new ArrayList<ArrayList<Integer>>();
		for(int idx=0; idx<=nodeNum; idx++) {
			tree.add(new ArrayList<Integer>());
		}
		
		//인접리스트 방식으로 트리 표현
		for(int idx=0; idx<nodeNum-1; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			tree.get(a).add(b);
			tree.get(b).add(a);
		}
		
		//각 노드들의 깊이를 저장할 테이블
		depth = new int[nodeNum+1];
		//각 노드들의 부모를 저장할 테이블
		parent = new int[nodeNum+1];
		
		//각 노드들의 깊이와 부모 구하기
		getDepth(1, 1);
	}
	
	static int find_lca(int nodeA, int nodeB) {
		//깊이
		int depthA = depth[nodeA];
		int depthB = depth[nodeB];
		
		//두 노드의 깊이가 다르다면
		if(depthA>depthB) {
			while(depthA!=depthB) {
				depthA-=1;
				nodeA=parent[nodeA];
			}
		}
		else if(depthA<depthB) {
			while(depthA!=depthB) {
				depthB-=1;
				nodeB=parent[nodeB];
			}
		}
		
		//두 노드의 깊이가 같다면, 두 노드 동시에 부모노드로 올라가면서 최소 공통 조상을 찾는다.
		while(nodeA!=nodeB) {
			nodeA = parent[nodeA];
			nodeB = parent[nodeB];
		}
		
		
		return nodeA;
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력
		inputTestcase();
		
		tc = Integer.parseInt(br.readLine().trim());
		for(int idx=0; idx<tc; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			//두 노드의 최소 공통 조상을 찾는다.
			int lca = find_lca(a, b);
			System.out.println(lca);
		}
		
	}
}
