import java.util.*;
import java.io.*;

/**
 * 1. 에라토스테네스의 체로 소수들 구하기
 * : <=floor(sqrt(b))
 *
 * 2. 2에서부터 시작해 값을 1씩 늘려가면서 구한 소수들을 몇제곱할지 반복문
 * : 소수^idx가 <=b이하일 때까지
 * : a보다 크거나 같으면 카운팅
 *
 * 주의! 오버플로우로 인한 무한루프로 시간초과 발생
 * num1*num2가 오버플로우인지 확인할 때, 아래 코드 작성
 * if(num1>Long.MAX_VALUE/num2){~}
 *
 * */

public class BOJ1456 {
	static BufferedReader br;
	static StringTokenizer st;
	static long a, b;
	static boolean arr[];
	static int ans=0;
	static int maximum;
	static ArrayList<Integer> prime;


	static void inputTestcase() throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		a = Long.parseLong(st.nextToken());
		b = Long.parseLong(st.nextToken());
		prime = new ArrayList<>();
	}

	static void eratostenes(){
		maximum= (int)Math.sqrt(b);
		arr = new boolean[maximum+1];
		Arrays.fill(arr, true);
		arr[0]=false;
		arr[1]=false;
		for(int idx=2; idx*idx<=maximum; idx++) {
			if (arr[idx]) {
				for (int cnt = idx * idx; cnt <= maximum; cnt+=idx) {
					arr[cnt] = false;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException{
		//입력
		inputTestcase();
		//에라토스테네스의 체
		eratostenes();
		//에라토스테네스의 체 테스트
		// for(int idx=0; idx<arr.length; idx++){
		// 	if(arr[idx]){
		// 		System.out.print(idx+" ");
		// 	}
		// }
		// System.out.println();

		//소수들을 따로 저장하기
		for(int idx=0; idx<arr.length; idx++){
			if(arr[idx]){
				prime.add(idx);
			}
		}

		//구한 소수들을 하나씩 확인
		for(int idx=0; idx<prime.size(); idx++){
			int primeNum = prime.get(idx);
			long power = (long)primeNum*primeNum;
			while(power<=b){
				if(power>=a){
					ans++;
				}
				if(power>Long.MAX_VALUE / primeNum){break;}
				power*=primeNum;
			}
		}

		//출력
		System.out.println(ans);
		System.out.println(Math.pow(2, 31)-1);
	}
}