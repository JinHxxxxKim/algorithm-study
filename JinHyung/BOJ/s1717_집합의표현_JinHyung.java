package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author JinHxxxxKim
 *
 * UNION-FIND
 * 
 * 1. n, m을 입력받는다.
 * 2. CASE1. 연산 0(UNION)
 *   - node1, node2를 입력받는다.
 *   - node1, node2의 rootInfo를 갱신한다.
 *   
 * 3. CASE1. 연산 1(FIND)
 *   - node1, node2를 입력받는다.
 *   - node1, node2의 rootInfo를 탐색한다.
 *   - 같다면 YES, 다르다면 NO 출력
 */
public class s1717_집합의표현_JinHyung {
	static final int UNION = 0;
	static final int FIND = 1;
	
	static final String YES = "YES";
	static final String NO = "NO";
	
	static int N, M;
	static int[] rootInfo; // 동일 집합인지 확인하는 배열
	
	static final StringBuilder sb = new StringBuilder();
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception{
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		rootInfo = new int[N + 1];
		
		// 전처리 (루트 초기화)
		for(int node = 0; node <= N; ++node) {
			rootInfo[node] = node;
		}
		
		int opCnt = 0; // 연산 횟수 카운팅
		while(opCnt < M) {
			st = new StringTokenizer(br.readLine().trim());
			int currOp = Integer.parseInt(st.nextToken());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			if(currOp == UNION) {
				union(node1, node2);
			}else if(currOp == FIND) {
				if(find(node1)==find(node2)) {
					sb.append(YES).append("\n");
				}else {
					sb.append(NO).append("\n");
				}
			}else {
				System.out.println("ERROR");
			}
			++opCnt;
		}
		
		System.out.println(sb);
	}

	// rootInfo(속한 집합)을 탐색하는 메소드
	private static int find(int node) {
		if (node == rootInfo[node]) return node;
		else return rootInfo[node] = find(rootInfo[node]);
	}

	// 두 노드의 집합을 합치는 메소드
	private static void union(int node1, int node2) {
		int node1Root = find(node1);
		int node2Root = find(node2);
		if(node1Root != node2Root) {
			rootInfo[node1Root] = node2Root;
		}
	}
}

