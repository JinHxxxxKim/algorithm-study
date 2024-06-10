package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author hkh12
 * 
 * 1. 버블소트 구현하기
 * 2. swap할때마다 카운팅
 * =>버블소트의 시간복잡도는 O(n^2)이다.
 * =>시간초과 
 * 
 * 3. 버블소트를 직접 수행하지 않고 swap만 셀 수 있나?
 * ex)
 * 초기상태: 624513
 * 6의 swap횟수 = 5
 * 5의 swap횟수 = 2
 * 4의 swap횟수 = 2
 * 3의 swap횟수 = 0
 * 2의 swap횟수 = 1
 * 1의 swap횟수 = 0
 * => 각 원소들은 자신보다 오른쪽에 있는 원소들 중 자신보다 크기가 작은 원소의 개수만큼 swap한다.
 * 
 * 4. n개의 원소들 각각 오른쪽에 자신보다 크기가 작은 원소의 개수를 찾는다.
 * 	4-1. 세그먼트 트리를 이용해 오른쪽에 자신보다 크기가 작은 원소의 개수를 O(logN)으로 찾는다.
 * 	4-2. n개의 원소만큼 반복하므로, 총 시간복잡도 O(NlogN)으로 swap횟수를 구할 수 있다.
 * 
 * 5. 수열을 이루는 숫자의 값 범위가 -10억~10억이므로, 세그먼트 트리를 사용하기엔 범위가 너무 넓다.
 * => 값에 인덱스를 부여한다. 값을 대신해 인덱스를 세그먼트 트리에 넣는다.
 * => 수정) 현재 문제에서 관심있는 게 값이 아니라 개수이므로, 인덱스 위치에 1을 추가한다.

 * 6. swap의 개수가 자신보다 작은 수의 개수에 의해 결정되므로, 수열의 작은 수부터 세그먼트 트리에 저장한다.
 * => 작은 값들부터 저장했으므로, 현재 저장되는 값은 이미 세그먼트 트리에 저장된 값들보다 큼을 보장할 수 있다.
 * 
 * 
 * 
 * 
 */

public class BOJ1517 {
	//수열의 숫자와 index를 저장하는 클래스
	static class Num implements Comparable<Num>{
		int index, value;
		public Num(int index, int value) {
			this.index = index;
			this.value = value;
		}
		//value를 기준으로 정렬
		@Override
		public int compareTo(Num o) {
			return Integer.compare(this.value, o.value);
		}
	}
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int n;
	static long result;
	
	//세그먼트 트리를 구현할 배열
	static long[] segmentTree;
	//수열을 저장할 배열
	static Num[] arr;
	//수열에서 동일한 값을 가진 원소들의 인덱스를 저장하는 리스트
	static List<Integer> sameValueIdxs;
	
	static void inputTestcase() throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		arr = new Num[n];
		st = new StringTokenizer(br.readLine().trim());
		for(int idx=0; idx<n; idx++) {
			arr[idx] = new Num(idx, Integer.parseInt(st.nextToken()));
		}
	}
	
	static int getNodeCnt() {
//		리프노드의 개수 n이 2의 거듭제곱이라면 perfect binary tree이므로, 전체 노드의 수는 2*n-1이다.
//		리프노드의 개수 n이 2의 거듭제곱이 아니라면 complete binary tree이므로, 높이가 h이면 총 노드의 개수는 2^(h-1)-1 ~ 2^(h)-1개이다.
//		complete binary tree인 경우를 대비해 충분한 크기의 배열이 필요하므로 2^h-1개의 노드가 있다고 가정하고 세그먼트 트리 배열을 생성한다.
		
		//complete binary tree의 높이 h
		int h = (int)Math.ceil(Math.log(n)/Math.log(2));
		//충분한 크기의 배열을 할당하기 위해서 높이를 1 추가해준다.
		h++;
		//완전이진트리의 노드개수를 계산해서 반환
		return (int)Math.pow(2, h)-1;
	}
	
	//세그먼트 트리의 값을 갱신하는 함수
	//node: 현재 세그먼트 트리에서 작업중인 노드의 인덱스
	//idx: 업데이트할 arr 배열의 인덱스
	//start: 현재 노드가 대표하는 범위의 시작
	//end: 현재 노드가 대표하는 범위의 끝
	static void update(int start, int end, int node, int idx) {
		//리프노드일 때
		if(idx==start && idx==end) {
			segmentTree[node]=1L;
			return;
		}
		//리프노드가 아니면, 현재 노드의 왼쪽 자식과 오른쪽 자식을 나누어서 재귀적으로 업데이트
		int mid = (start+end)/2;
		if(idx<=mid) {
			//왼쪽자식노드의 인덱스는 부모노드인덱스*2
			update(start, mid, node*2, idx);
		}else {
			//오른쪽자식노드의 인덱스는 부모노드인덱스*2+1
			update(mid+1, end, node*2+1, idx);
		}
		//세그먼트 트리 노드 값 최신화
		segmentTree[node]=segmentTree[node*2]+segmentTree[node*2+1];
	}
	
	//세그먼트 트리에서 주어진 범위 [left, right] 내의 값들의 합을 구하는 함수 == swap의 개수를 구하는 함수
	static long search(int start, int end, int node, int left, int right) {
		//현재노드가 대표하는 범위가 구하고자하는 범위를 벗어났을 때
		 if(left>end || right<start) {
			 return 0L;
		 }
		//현재노드가 대표하는 범위가 구하고자하는 범위에 속할 때
		if(left<=start && end<=right) {
			return segmentTree[node];
		}
		//재귀적으로 왼쪽자식과 오른쪽자식을 탐색해서 swap개수를 얻기
		int mid = (start+end)/2;
		return search(start, mid, node*2, left, right) + search(mid+1, end, node*2+1, left, right);
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력
		inputTestcase();
		//수열의 숫자를 기준으로 오름차순 정렬
		Arrays.sort(arr);
		//세그먼트 트리의 노드 개수를 구하기
		int nodeCnt = getNodeCnt();
		//세그먼트 트리를 나타내는 배열 생성하기
		segmentTree = new long[nodeCnt];
		
		//이전 값
		int pre = Integer.MIN_VALUE;
		//수열에서 동일한 값을 가진 요소들의 인덱스를 저장하는 리스트
		sameValueIdxs = new ArrayList<>();
		
		//swap개수 구하기
		for(Num element: arr) {
			//같은 값이 아닌 더 큰 값이 들어왔을 때
			if(pre < element.value) {
				//이전의 같은 값들을 세그먼트 트리에 저장
				for(int sameValueIdx : sameValueIdxs) {
					update(0, n-1, 1, sameValueIdx);
				}
				//같은 값들 초기화
				sameValueIdxs.clear();
				//현재 수열의 숫자를 이전값 변수로 옮기기
				pre=element.value;
			}
			
			//현재 세그먼트 트리에서 swap 개수 구하기
			result+=search(0,n-1,1,element.index+1, n-1);
			//중복 값이 들어올 수 있음므로 index 정보를 sameValueIdxs에 저장
			sameValueIdxs.add(element.index);
		}
		
		System.out.println(result);
	}
}
