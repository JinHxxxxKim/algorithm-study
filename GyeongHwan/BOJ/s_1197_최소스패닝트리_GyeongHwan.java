import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 1. 입력
 * 	1-1. 정점의 개수 v
 * 	1-2. 간선의 개수 e
 * 	1-3. fromNode, toNode, weight
 * 	
 * 2. 가중치를 기준으로 간선을 오름차순 정렬
 * 
 * 3. fromNode와 toNode가 같은 집합에 속하지 않으면 union
 * 
 * 4. 그래프에 포함한 간선의 개수가 v-1개라면 종료
 * 
 * 
 */

public class Main {
	static class Edge implements Comparable<Edge>{
		int from;
		int to; 
		int weight;
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int v, e;
	static int from, to, weight;
	static Edge[] edgeList;
	
	static int[] parents;
	
	static void make() {
		parents = new int[v+1];
		for(int idx=1; idx<=v; idx++) {
			parents[idx]=idx;
		}
	}
	
	static int find(int element) {
		//부모가 자기자신이라면
		if(parents[element]==element) {return element;}
		//부모가 자기자신이 아니라면
		return parents[element]=find(parents[element]);
	}
	
	static boolean union(int e1, int e2) {
		//두 원소가 속한 집합의 대표원소 찾기
		int rootE1 = find(e1);
		int rootE2 = find(e2);
		
		//대표원소들이 같은 집합에 속한다면
		if(rootE1==rootE2) {return false;}
		
		//대표원소들이 같은 집합에 속하지 않는다면
		if(rootE1>rootE2) {
			parents[rootE1]=rootE2;
		}else{
			parents[rootE2]=rootE1;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//입력
		st = new StringTokenizer(br.readLine().trim());
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		edgeList = new Edge[e];
		
		for(int idx=0; idx<e; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			edgeList[idx]= new Edge(from, to, weight);
		}
		
		//정렬
		Arrays.sort(edgeList);
		
		make();
		
		//그래프에 포함한 간선의 개수 cnt, 가중치의 합 sumWeight
		int cnt=0;
		int sumWeight=0;
		
		for(int idx=0; idx<e; idx++) {
			if(union(edgeList[idx].from, edgeList[idx].to)) {
				cnt++;
				sumWeight+=edgeList[idx].weight;
			}
			if(cnt==v-1) {break;}
		}
		
		System.out.println(sumWeight);
	}
}
