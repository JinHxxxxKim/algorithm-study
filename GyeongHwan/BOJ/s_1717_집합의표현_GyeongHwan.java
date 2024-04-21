import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * goal: 1로 시작하는 입력에 대해 두 원소가 같은 집합에 속해있다면 yes, 같은 집합이 아니라면 no를 출력
 * 
 * 1. 입력
 * 	1-1. 초기 집합의 개수 n+1
 * 	1-2. 입력으로 주어지는 연산의 개수 m
 * 	1-3. m개의 입력들
 * 
 * 2. 유니온파인드
 * 	2-0. make()
 * 	2-1. union()
 * 	2-2. find()
 * 
 * 
 */

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int n, m;
	
	static int[] parentList;
	
	static void make() {
		parentList = new int[n+1];
		for(int idx=0; idx<=n; idx++) {
			parentList[idx]=idx;
		}
	}
	
	static int find(int element) {
		//부모노드가 자기자신이라면
		if(element==parentList[element]) {
			return element;
		}
		//부모노드가 자기자신이 아니라면
		return parentList[element]=find(parentList[element]);
	}
	
	static void union(int num1, int num2) {
		//두 원소가 속한 집합의 루트노드 찾기
		int rootNum1 = find(num1);
		int rootNum2 = find(num2);
		
		//두 원소가 이미 같은 집합에 속해있다면
		if(rootNum1==rootNum2) {return;}
		
		//두 원소가 다른 집합에 속해있다면
		//루트노드가 더 큰 원소를 더 작은 원소의 집합에 포함시키기
		if(rootNum1>rootNum2) {
			parentList[rootNum1]=rootNum2;
		}else {
			parentList[rootNum2]=rootNum1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		
		make();
		for(int idx=0; idx<m; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int oper = Integer.parseInt(st.nextToken());
			int element1 = Integer.parseInt(st.nextToken());
			int element2 = Integer.parseInt(st.nextToken());
			
			if(oper==0) {
				union(element1, element2);
			}
			else if(oper==1) {
				if(find(element1)==find(element2)) {
                    sb.append("YES").append("\n");
					
				}else {
					sb.append("NO").append("\n");
				}
			}
		}
        System.out.println(sb);
	}
}