package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author hkh12
 * 
 * 0. dp테이블 생성 및 초기값 입력
 *
 * 1. 선물들을 가져온 사람들에게 배분하는 경우 ex) 사람 A B C에게 선물 a b c를 배분하는 경우
 * arr[n]=(n-1)*arr2[n-1]
 * 
 * 2. 선물들을 가져온 사람이 아닌 사람을 포함해 배분하는 경우 ex) 사람 A B C에게 선물 b c d를 배분하는 경우
 * arr2[n]=(n-1)*arr2[n-1]+arr[n-1]
 */

public class BOJ1947 {
	static BufferedReader br;
	static int student;
	static long[] arr, arr2;
	
	
	static void inputTestcase() throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		student = Integer.parseInt(br.readLine().trim());
		//dp테이블 생성 및 초기값 입력
		arr = new long[student+1];
		arr2 = new long[student+1];
		arr[1]=0;
		arr2[1]=1;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//입력
		inputTestcase();
		//dp테이블 채우기
		for(int idx=2; idx<=student; idx++) {
			arr[idx]=((idx-1)*arr2[idx-1])%1000000000;
			arr2[idx]=((idx-1)*arr2[idx-1]+arr[idx-1])%1000000000;
		}
		//출력
		System.out.println(arr[student]);
//		for(int idx=1; idx<=student; idx++) {
//			System.out.print(arr[idx]+" ");
//		}
//		System.out.println();
	}
}
