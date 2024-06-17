package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author hkh12
 * 
 * <첫번째 풀이 - 조합>
 * 1. 입력
 * 2. 처음부터 현재 강의까지의 길이를 저장하는 arr 생성
 * 3. 1~n-1 중에서 m-1개를 뽑는 조합 구하기 => 뽑은 수를 index로 가지는 숫자까지 블루레이로 나눌 것이다.
 * 4. 각 조각에 포함된 숫자들의 합을 구하고 그 중에서 최댓값 구하기
 * 5. ans와 비교해서 ans보다 작으면 ans값 갱신
 * 
 * 문제 복기
 * 문제) 재귀를 이용하여 구현한 조합의 시간복잡도를 고려하지 않고 풀이방법을 설계.
 * 원인) 재귀로 구현한 코드의 시간복잡도를 정확히 계산하지 못함
 * 해결) 재귀 코드에 대한 시간복잡도 계산하는 법 노션에 정리
 * +) 조합 구현에 시간이 많이 걸림
 * 
 * <두번째 풀이 - 이분탐색>
 * 1. 입력
 * 	1-1. 강의의 수 n, 블루레이의 개수 m
 * 	1-2. 강의의 길이 arr
 * 2. 블루레이의 크기가 가능한 범위는 max(arr) ~ sum(arr)
 *	2-1. sum(arr)는 최대 10^9까지 가능하므로 완전탐색은 시간초과 발생
 *	2-2. 이진탐색으로 최적의 블루레이 크기를 찾는다.
 *
 * 문제 복기
 * 문제) 해당 문제가 이진탐색이라는 것을 빠르게 파악하지 못함
 * 원인) 이진탐색
 * 해결) 이진탐색 문제 더 풀어보면서 연습하기 + 예시 몇개를 만들어서 직관적으로 어떻게 풀지 생각하고 직관 속에서 규칙성이나 문풀 아이디어 얻기
 * 
 * +) 50%에서 틀리는 경우 => 블루레이 개수가 m보다 작은 경우도 블루레이 하나의 크기를 갱신해주면 된다. 왜?
 * : 블루레이의 개수를 구하는 방식은 영상의 길이를 더하다가 설정된 블루레이 하나의 크기를 넘어서면 그 이전 영상까지 잘라서 하나의 블루레이로 만들고 카운팅하는 방식이다.
 * : 블루레이의 개수가 m보다 작은 경우, 영상이 2개 이상인 블루레이에서 하나를 꺼내 빈 블루레이에 주면 블루레이의 개수가 m보다 작은 경우도 블루레이 개수가 m이 되도록 만들 수 있다.
 * 
 * ex1)
 * 5 3
 * 1 2 3 4 100
 * 
 * getBlueray(100)은 2가 된다. 이 때, '1 2 3 4 / 100'와 같이 나뉜다.
 * 이것을 빈 블루레이에 길이가 1인 영상을 넘겨주면 '1 / 2 3 4 / 100'와 같이 나뉘고, 3개의 블루레이가 생성된다.
 * 
 * ex2)
 * 3 3
 * 70 100 150
 * 
 * getBlueray(170)은 2가 된다. 이 때, '70 100 / 150'와 같이 나뉜다.
 * 이것을 빈 블루레이에 길이가 70인 영상을 넘겨주면 '70 / 100 / 150'와 같이 나뉜고, 3개의 블루레이가 생성된다.
 *
 *
 */


public class BOJ2343 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int n, target, ans;
	static int[] arr;
	
	static boolean[] isSelected; //true면 해당 인덱스의 기준으로 블루레이를 나눈다.
	
	static void inputTestcase() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine().trim());
		arr = new int[n];
		for(int idx=0; idx<n; idx++) {
			arr[idx]=Integer.parseInt(st.nextToken());
		}
	}
	
	static int getBlueray(int size) {
		int cnt=0;
		int sumVal=0;
		for(int idx=0; idx<n; idx++) {
			int temp=sumVal;
			temp+=arr[idx];
			if(temp>size) {
				cnt++;
				sumVal=arr[idx];
			}else {
				sumVal=temp;
			}
		}
		cnt++;
		return cnt;
	}
	
	static void binarySearch() {
		int left=0;
		int right=0;
		for(int idx=0; idx<n; idx++) {
			left=Math.max(left, arr[idx]);
			right+=arr[idx];
		}
		int mid=0;
		while(left<=right) {
			mid=(left+right)/2;
			//mid는 블루레이 하나의 크기
			//target은 블루레이의 개수
			//mid를 블루레이 하나의 크기로 설정했을 때 몇개의 블루레이가 나오는지 구하기
			int bluerayCnt = getBlueray(mid);
			
			if(bluerayCnt<target) { //target이 bluerayCntd보다 작다면
				ans=mid; //50%에서 틀렸습니다 나올 때, 이걸 추가하면 되는데.... 
				right=mid-1;
			}
			else if(bluerayCnt>target) { //target이 bluerayCnt보다 크다면
				left=mid+1;
			}
			else if(bluerayCnt==target) { //target이 bluerayCnt와 같다면
				ans=mid;
				right=mid-1;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		//입력
		inputTestcase();
		//이진탐색
		binarySearch();
		//출력
		System.out.println(ans);
	}
}
