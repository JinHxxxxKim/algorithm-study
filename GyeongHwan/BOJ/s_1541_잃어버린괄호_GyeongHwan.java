package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author hkh12
 * 
 * 1. 입력
 * 	1-1. 길이 50의 배열을 생성
 * 	1-2. 입력되는 식을 배열에 저장
 * 2. -가 처음 등장하기 전의 숫자들은 모두 더한다.
 * 3. -가 처음 등장한 후의 숫자들은 모두 뺀다.
 * 
 */

public class BOJ1541 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static boolean minus=false;
	static int ans=0;
	
	static char[] arr;
	
	static void stringToInt(int index, int offset) {
		//변환한 값을 저장할 변수 result
		int result=0;
		
		for(int idx=offset; idx>0; idx--) {
			int temp = (int)arr[index-idx]-'0';
			result+=temp*(Math.pow(10, idx-1));
		}
		
		//System.out.println("result: "+result);
		
		//-기호가 등장했다면
		if(minus==true) {
			ans-=result;
		}
		//-기호가 등장하지 않았다면
		else {
			ans+=result;
		}
		//System.out.println("ans: "+ans);
	}
	
	
	public static void main(String[] args) throws IOException {
		//입력
		br = new BufferedReader(new InputStreamReader(System.in));
		arr = new char[50];
		String input = br.readLine().trim();
		for(int idx=0; idx<input.length();idx++) {
			arr[idx]=input.charAt(idx);
		}
		
		//+ 또는 -를 만나면 문자열을 숫자로 변환하는 함수 실행
		int offset=0;
		for(int idx=0; idx<input.length();idx++) {
			if(arr[idx]=='+' || arr[idx]=='-') {
				stringToInt(idx, offset);
				offset=0;
				if(arr[idx]=='-') {
					minus=true;
				}
			}
			else {
				offset+=1;
			}
		}
		
		stringToInt(input.length(), offset);
		
		//출력
		System.out.println(ans);
	}
}
