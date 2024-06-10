package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1546 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int n, m;
	static double sum=0;
	static int[] arr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine().trim());
		m = 0;
		arr = new int[n];
		
		st = new StringTokenizer(br.readLine().trim());
		for(int idx=0; idx<n; idx++) {
			arr[idx] = Integer.parseInt(st.nextToken());
			if(m<arr[idx]) {
				m=arr[idx];
			}
		}
		
		for(int idx=0; idx<n; idx++) {
			sum+=(arr[idx]/(double)m)*100;
		}
		
		System.out.println(sum/n);
	}
}
