package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author hkh12
 * 
 * 1. 입력 => min, max의 범위가 크므로 long 타입 변수에 입력값 저장
 * 2. 에라토스테네스의 체 응용
 * 	2-1. 에라토스테네스의 체를 적용할 max-min+1 길이의 boolean 배열 생성
 * 	2-2. 2~sqrt(max)까지 제곱ㄴㄴ수가 아닌 숫자 제거 반복
 * 		2-2-1. 제곱수 pow 구하기
 * 		2-2-2. pow로 나누어 떨어지는 구간 내 가장 작은 수 찾기
 * 			2-2-2-1. min%pow==0이면, (min/pow)*pow가 구간 내 가장 작은 수 => (min/pow)*pow - min, ((min/pow)+1)*pow - min, ... max-min 값보다 크거나 같아질 때까지 해당 값을 인덱스로 갖는 check를 true로 변환 
 * 			2-2-2-2. min%pow!=0이면, ((min/pow)+1)*pow가 구간 내 가장 작은 수 => ((min/pow)+1)*pow - min, ((min/pow)+2)*pow - min, ... max-min 값보다 크거나 같아질 때까지 해당 값을 인덱스로 갖는 check를 true로 변환
 * 
 * 
 */ 

public class BOJ1016 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static long min, max,ans;
	//제곱ㄴㄴ수인지 체크하는 배열
	static boolean[] check;
	
	static void inputTestcase() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		min = Long.parseLong(st.nextToken());
		max = Long.parseLong(st.nextToken());
		check = new boolean[(int)(max-min+1)];
	}
	
	
	
	public static void main(String[] args) throws IOException {
		//입력
		inputTestcase();
		//제곱수로 나누어떨어지는 첫번째 숫자 찾기
		for(long idx=2; idx<=Math.sqrt(max); idx++) {
			//제곱수
			long pow = idx*idx;
			//pow로 나누어떨어지는 범위 내 가장 작은 수를 찾기
			long start;
			if(min%pow==0) {
				start=min/pow;
			}else {
				start=(min/pow)+1;
			}
			//그걸 이용해 범위 내 해당 제곱수로 나누어떨어지는 모든 수 찾기
			for(long now = start; now*pow<=max; now++) {
				check[(int)((now*pow)-min)]=true;
			}
		}
		//출력
		for(int idx=0; idx<max-min+1; idx++) {
			if(!check[idx]) { //제곱ㄴㄴ수일 경우
				ans++;
			}
		}
		System.out.println(ans);
	}
}
