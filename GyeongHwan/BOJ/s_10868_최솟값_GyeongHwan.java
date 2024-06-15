package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author hkh12
 * 
 * 1. 단순하게 생각하면 길이가 n인 수열에서 최솟값을 찾는 작업을 m번 반복 O(nm)이므로 시간초과가 발생
 * 2. m번의 작업마다 최솟값을 찾는 데 걸리는 시간복잡도가 logN 이하로 걸려야 한다.
 * 3. 어떤 자료구조를 사용해야 할까?
 * 	3-1. 힙 => 전체에서 최솟값을 구할 수는 있지만 특정 범위의 최솟값을 구할 수는 없다.
 * 	3-2. 세그먼트트리 => 특정 구간 내 데이터에 대한 연산을 수행해야 하므로.
 * 4. 세그먼트트리를 구현
 * 
 * 
 */

public class BOJ10868 {
	static class Scope{
		int start;
		int end;
		public Scope(int start, int end) {
			this.start=start;
			this.end=end;
		}
	}
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int n, m, result;
	static int start, end;
	static int[] arr;
	static Scope[] scopeArr;
	
	//세그먼트트리 인스턴스 생성
	static SegmentTree segmentTree;

	
	private static void inputTestcase() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		for(int idx=0; idx<n; idx++) {
			arr[idx]=Integer.parseInt(br.readLine().trim());
		}
		
		scopeArr = new Scope[m];
		for(int idx=0; idx<m; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			//arr배열의 인덱스를 입력
			start=Integer.parseInt(st.nextToken())-1;
			end=Integer.parseInt(st.nextToken())-1;
			scopeArr[idx]= new Scope(start, end);
		}
		
	}
	
	static class SegmentTree{
		//세그먼트 트리를 구현할 배열
		int[] tree;
		
		//생성자에서 세그먼트 트리의 전체노드 수(즉, 배열길이) 계산
		public SegmentTree(int arrSize) {
			//트리의 높이 계산
			int treeHeight = (int)Math.ceil(Math.log(arrSize)/Math.log(2));
			//트리의 노드 수 계산
			int treeNodeCnt = (int)Math.pow(2, treeHeight+1)-1;
			//트리를 구현할 배열 길이 설정
			tree = new int[treeNodeCnt];
			//세그먼트 트리 초기화
			init(arr, 1, 0, n-1);
		}
		
		//세그먼트 트리의 노드 값 초기화
		int init(int[] arr, int nodeNum, int start, int end) {
			//리프노드인 경우
			if(start==end) {
				//리프노드에 기존배열값 저장
				return tree[nodeNum]=arr[start];
			}
			//이너노드인 경우
			else {
				//왼쪽자식과 오른쪽자식의 값을 중 더 작은 값을 저장
				return tree[nodeNum]=Math.min(init(arr, nodeNum*2, start, (start+end)/2), init(arr, nodeNum*2+1, (start+end)/2+1, end));
			}
		}
		
		//세그먼트 트리의 특정 구간 최솟값을 구하는 메소드
		int query(int nodeNum, int start, int end, int targetLeft, int targetRight) {
			//노드가 표현하는 값의 구간(start~end)이 target구간(targetLeft~targetRight)에 속하지 않는 경우
			if(targetRight<start || end<targetLeft) {
				return Integer.MAX_VALUE;
			}
			//노드가 표현하는 값의 구간이 target구간에 속하는 경우
			else if(targetLeft<=start && end<=targetRight) {
				return tree[nodeNum];
			}
			//그 외의 경우는 자식노드를 재귀적으로 탐색
			//1. 노드가 표현하는 값의 구간이 일부만 target구간에 속하는 경우
			//2. 노드가 표현하는 값의 구간이 target구간을 모두 포함하는 경우
			else {
				return Math.min(query(nodeNum*2, start, (start+end)/2, targetLeft, targetRight), query(nodeNum*2+1,(start+end)/2+1, end, targetLeft, targetRight));
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		//입력
		inputTestcase();
		//세그먼트 트리 구현
		segmentTree = new SegmentTree(n);
		//m개의 쌍마다 답을 출력
		for(int idx=0; idx<m; idx++) {
			result = segmentTree.query(1, 0, n-1, scopeArr[idx].start, scopeArr[idx].end);
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}
}
