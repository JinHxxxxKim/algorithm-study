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
 * 
 * 
 * +) 완전순열
 * : 사람들이 각각 자신의 모자를 벗었다가 아무 모자나 다시 쓰는데, 모든 사람이 자기 것이 아닌 모자를 쓰는 순열
 * => 선물 전달 문제는 전형적인 완전순열 문제이다.
 * 
 * +) 완전순열의 점화식 구하기
 * n=10라면,
 * A B C D E F G H I J : 사람
 * x                 : x에는 b~j까지 총 (n-1)개의 선물 중 하나가 들어가서 A와 대응.
 * 
 * A가 b에 대응된다고 가정한다면, 두가지 경우로 나뉠 수 있다.
 * - a가 B에 대응되는 경우: A과 B는 짝을 이루엇고, 나머지 (n-2)개로 다시 완전순열을 구한다.
 * - a가 B에 대응되지 않는 경우: B C D E F G H I J에게 a c d e f g h i j를 배분해야한다. 그런데, a가 B에 대응하면 안되므로, b c d e f g j i j를 배분하는 것과 같다. 즉, (n-1)로 완전순열을 구하는 것과 같다.
 *
 * 정리하자면,
 * dp[n] = (n-1) * (dp[n-1]+dp[n-2])
 * 
 * 
 * 참고: https://namu.wiki/w/%EC%99%84%EC%A0%84%EC%88%9C%EC%97%B4
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
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
