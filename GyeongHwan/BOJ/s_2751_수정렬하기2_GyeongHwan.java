import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int n, element;
	static ArrayList<Integer> arr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine().trim());
		arr = new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			element = Integer.parseInt(br.readLine().trim());
			arr.add(element);
		}
		
		Collections.sort(arr);
		
		for(int i=0; i<n; i++) {
			sb.append(arr.get(i)).append("\n");
		}
		System.out.println(sb);
	}
}