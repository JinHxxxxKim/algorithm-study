package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * @author hkh12
 * 
 * 1. 각 mNum마다 N개의 정수들과 비교하는 완전탐색의 경우, 시간초과
 * 2. n을 정렬 -> collections.sort() 사용 시, O(nlogn)
 * 3. 이진탐색으로 존재하는지 찾기
 * 
 */

public class BOJ1920 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int n, m, ans;
	static ArrayList<Integer> nNum, mNum;
	
	static void inputTestcase() throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine().trim());
		nNum = new ArrayList<>();
		st = new StringTokenizer(br.readLine().trim());
		for(int idx=0; idx<n; idx++) {
			nNum.add(Integer.parseInt(st.nextToken()));
		}
		
		m = Integer.parseInt(br.readLine().trim());
		mNum = new ArrayList<>();
		st = new StringTokenizer(br.readLine().trim());
		for(int idx=0; idx<m; idx++) {
			mNum.add(Integer.parseInt(st.nextToken()));
		}
	}
	
	
	static int binarySearch(int target) {
		//이진탐색 초기설정
		int left=0;
		int right=nNum.size()-1;
		int mid;
		
		//탐색반복
		while(left<=right) {
			mid=(left+right)/2;
			if(nNum.get(mid)<target) { //nNum.get(mid)가 target값보다 작다면
				left=mid+1;
			}
			else if(nNum.get(mid)>target) { //nNum.get(mid)가 target값보다 크다면
				right=mid-1;
			}
			else if(nNum.get(mid)==target) { //nNum.get(mid)가 target값이라면
				return 1;
			}
		}
		return 0;
	}
	
	static void check() {
		//m값들을 하나씩 이진탐색 수행
		for(int idx=0; idx<m; idx++) {
			//존재한다면 1, 존재하지 않으면 0을 반환
			ans=binarySearch(mNum.get(idx));
			sb.append(ans).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력
		inputTestcase();
		//정렬
		Collections.sort(nNum);
		//이진탐색 후 결과에 따라 출력
		check();
	}
}
