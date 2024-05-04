package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 1. 입력
 * 	1-1. 카드의 개수 n
 * 
 * 2. n번 카드만 남은 상황에서 역순으로 동작하기
 * 	2-1. idx=n-1 ~ 1 반복
 * 	2-2. 맨 앞에 idx번 카드 넣기
 * 	2-3. 뒤에서 하나 빼기. 뺀 값을 앞에 넣기 => idx번 반복
 * 
 * 
 */

public class BOJ1835 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static Deque<Integer> deque;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine().trim());
		
		deque = new ArrayDeque<>();
		deque.addFirst(n);
		for(int idx=n-1; idx>0; idx--) {
			deque.addFirst(idx);
			int count=0;
			while(count<idx) {
				count++;
				int element = deque.removeLast();
				deque.addFirst(element);
			}
		}
		
		//출력
		for(int idx=0; idx<n; idx++) {
			sb.append(deque.removeFirst()).append(" ");
		}
		System.out.println(sb);
		
	}
}
