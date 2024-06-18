package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * @author hkh12
 * 
 * 1. 오큰수는 현재 요소보다 인덱스가 큰 숫자들 중에서 현재의 요소보다 작고 가장 인덱스가 작은 숫자를 의미한다.
 * 2. 완전탐색으로 오큰수를 찾는다면 O(n^2)의 시간복잡도를 가지므로 시간초과가 발생한다.
 * 3. 탐색에 들어가는 시간이 logN이하여야 한다.
 * : 어떤 자료구조를 사용해야할까? 스택
 * 
 * 스택 활용 풀이
 * 1. seq의 요소들을 index순서대로 가져온다.
 * 2. stack의 top이 가져온 요소보다 작다면 arr[top요소의 인덱스]에 가져온 요소를 넣고 다음 top과 비교한다.
 * 3. stack의 top이 가져온 요소보다 크다면 stack에 가져온 요소를 넣는다.
 * 
 */

public class BOJ17298 {
	static class IdxVal{
		int idx;
		int val;
		public IdxVal(int idx, int val) {
			this.idx=idx;
			this.val=val;
		}
	}
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int n, element;
	static int[] seq;
	static int[] ans;
	
	static Deque<IdxVal> stack;
	
	static void inputTestcase() throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		n = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine().trim());
		seq = new int[n];
		ans = new int[n];
		Arrays.fill(ans,-1);
		for(int idx=0; idx<n; idx++) {
			element = Integer.parseInt(st.nextToken());
			seq[idx]=element;
		}
		
		stack = new ArrayDeque<>();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력
		inputTestcase();
		
		//seq의 요소를 하나씩 가져온다.
		for(int idx=0; idx<n; idx++) {
			//스택이 비었다면
			if(stack.isEmpty()) {
				stack.addLast(new IdxVal(idx, seq[idx]));
			}
			//스택이 비지 않았다면
			else {
				//스택의 top이 가져온 seq의 요소보다 클 때까지 반복
				while(stack.getLast().val<seq[idx]) {
					ans[stack.getLast().idx]=seq[idx];
					stack.removeLast();
					if(stack.isEmpty()) {
						break;
					}
				}
				//스택의 top이 가져온 seq의 요소보다 작다면 stack에 적재
				stack.addLast(new IdxVal(idx, seq[idx]));
			}
		}
		//출력
		for(int idx=0; idx<n; idx++) {
			sb.append(ans[idx]).append(" ");
		}
		System.out.println(sb);
	}
}
