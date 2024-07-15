import java.util.*;
import java.io.*;

/**
 * @hkh1284
 *
 * 1. 다이나믹 프로그래밍
 * 	1-1. 2차원 dp테이블 생성: dp[str1.length+1][str2.length+1]
 * 	1-2. dp[i][j]는 0~i까지의 str1, 0~j까지의 str2에서의 lcs개수
 * 	1-3. str1.charAt(i)==str2.charAt(j)라면, dp[i][j]=dp[i-1][j-1]+1
 * 	1-4. str1.charAt(i)!=str2.charAt(j)라면, dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1])
 * 	1-5. dp[str1.length][str2.length]값이 lcs길이
 * 2. lcs 구하기
 * 	2-0. dp테이블을 역순으로 이동하며 str1과 str2의 문자가 같은 경우 stack에 넣기
 * 	2-1. str1과 str2의 문자가 같다면, str1.charAt(i)를 stack에 넣기
 * 	2-2. str1과 str2의 문자가 다르다면, dp[i-1][j]>dp[i][j-1]에서 dp[i][j]=dp[i-1][j]
 *
 *
 */

public class BOJ9252 {
	static BufferedReader br;
	static StringBuilder sb;
	static int lcsLength;
	static String lcs="";
	static String str1, str2;
	static int l1, l2;
	static int[][] dp;
	static Deque<String> stack;

	static void inputTestcase() throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		str1 = br.readLine().trim();
		str2 = br.readLine().trim();
		l1 = str1.length();
		l2 = str2.length();
		dp = new int[l1+1][l2+1];
	}

	static void getLCS(){
		//lcs에 포함되는 문자를 저장할 스택
		stack = new ArrayDeque<>();

		//탐색할 index
		int idx1=l1;
		int idx2=l2;

		//각 인덱스가 0이 될때까지 탐색
		while(idx1>0 && idx2>0){
			//현재 탐색하는 str1과 str2의 문자가 같다면
			if(str1.charAt(idx1-1)==str2.charAt(idx2-1)){
				stack.addLast(String.valueOf(str1.charAt(idx1-1)));
				idx1--;
				idx2--;
			}
			//현재 탐색하는 str1과 str2의 문자가 다르다면
			else{
				if(dp[idx1][idx2]>dp[idx1][idx2-1]){
					idx1--;
				}
				else{
					idx2--;
				}
			}
		}

		//stack에 담긴 문자들을 연결하여 lcs변수에 저장
		while(!stack.isEmpty()) {
			lcs += stack.removeLast();
		}
	}

	public static void main(String[] args) throws IOException{
		//입력
		inputTestcase();
		//dp테이블 채우기
		for(int row=1; row<=l1; row++) {
			for (int col = 1; col <= l2; col++) {
				if (str1.charAt(row - 1) == str2.charAt(col - 1)) {
					dp[row][col] = dp[row - 1][col - 1] + 1;
				} else {
					dp[row][col] = Math.max(dp[row - 1][col], dp[row][col - 1]);
				}
			}
		}
		//lcs의 길이 저장
		lcsLength = dp[l1][l2];
		//lcs 구하기
		getLCS();
		//출력
		sb.append(lcsLength).append("\n");
		sb.append(lcs);
		System.out.println(sb);
	}
}
