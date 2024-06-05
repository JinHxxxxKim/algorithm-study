package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 1. 입력
 * 	1-1. 배열의 크기 n
 * 	1-2. 타겟 인덱스 k
 * 
 * 2. n*n 배열 arr 만들기
 * 
 * 3. arr 배열의 값들을 arr2에 넣기 => 하나하나 넣으면 O(n^2)가 걸린다.
 * 
 * 4. arr2를 정렬하기 => O(n^2 log n^2)가 걸린다.
 * 
 * 5. arr2[k] 구하기
 * 
 * => 위의 방식대로 하면 시간초과가 발생한다.
 * 
 * 6. 찾고자하는 것은 arr2[k]=target_value 이다.
 * 
 * 7. target_value가 arr2에서 몇번째 오는 값인지 찾는 방법
 * 	7-1. arr[][] 배열에서 첫번째 행은 1의 배수, 2번째 행은 2의 배수, ...
 * 	7-2. 첫번째 행에서 target_value보다 작거나 같은 수의 개수는 최대 target_value/1=target_value개수이다. 하지만, 열의 크기가 n이므로 Min(target_value/1,n)이다.
 * 	7-3. 두번째 행에서 target_value보다 작거나 같은 수의 개수는 최대 target_value/1=target_value개수이다. 하지만, 열의 크기가 n이므로 Min(target_value/2,n)이다.
 * 	7-4. ....
 * 	7-5. i번째 행에서 target_value보다 작거나 같은 수의 개수는 Min(target_value/i, n)
 * 
 * 8. 이진탐색
 * 	8-1. left=1, right=k
 * 	8-2. Min(target_value/i, n)과 k를 비교하기
 * 
 * 
 */

public class BOJ1300 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int n, k, ans;
	
	static void inputTestcase() throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		n = Integer.parseInt(br.readLine().trim());
		k = Integer.parseInt(br.readLine().trim());
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력
		inputTestcase();
		
		//이진탐색
		int left=1;
		int right=k;
		
		while(left<=right) {
			int mid = (left+right)/2; //임의의 수 mid
			int cnt = 0;
			
			//mid보다 작거나 같은 수는 몇개인지 계산
			for(int idx=1; idx<=n; idx++) {
				cnt+=Math.min(mid/idx, n);
			}
			
			//target_value보다 작거나 같은 숫자의 개수 cnt
			if(cnt<k) {
				left=mid+1;
			}else {
				ans=mid;
				right=mid-1;
			}
		}
		
		System.out.println(ans);
		
	}
}
