package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author hkh12
 * 
 * 
 * 1. 특정 도시에서 다른 모든 도시로 가는 최단거리를 구하기 + 음의 가중치 존재 => 벨만포드 
 * 2. 시간을 무한히 오래전으로 되돌릴 수 있다. => 음의 사이클 존재 여부 확인
 * : 벨만포드이므로 간선리스트 방식으로 그래프 표현
 * 
 * 벨만포드 구현
 * 1. 최단거리 테이블 생성 및 초기화
 * 2. 모든 간선 확인
 * 3. 시작노드와 연결된 간선들을 거쳐 다른 노드로 가는 최단거리 비용 찾아서 테이블 갱신
 * 4. 2~3번을 V-1번 반복
 * 5. V번째 반복했을 때 테이블이 갱신된다면 음의 사이클 존재
 * 
 * +) 38%에서 출력초과 발생
 * : 출력이 더 되거나 하진 않는다. => 출력되는 값이 문제?
 * : 간선의 개수가 6000 * 모든 간선의 가중치가 -10000 * n번 반복 => int범위를 벗어나서 underflow발생
 * : int[] -> long[]으로 변경
 * 
 */

public class BOJ11657 {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int cityCnt, busLineCnt;
	static long[] distance;
	static long[] distanceBackup;
	
	//그래프를 간선리스트 방식으로 표현
	static class Edge{
		int from; 
		int to;
		int time;
		public Edge(int from, int to, int time) {
			this.from=from;
			this.to=to;
			this.time=time;
		}
	}
	
	static Edge[] edgeList;
	
	static int a, b, c;
	static int INF=Integer.MAX_VALUE;
	
	//음의 사이클 존재를 체크할 변수
	static boolean flag=false;
	
	
	static void inputTestCase() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		cityCnt = Integer.parseInt(st.nextToken());
		busLineCnt = Integer.parseInt(st.nextToken());
		
		//최단거리 테이블 생성 및 초기화
		distance = new long[cityCnt+1];
		distanceBackup = new long[cityCnt+1];
		Arrays.fill(distance, INF);
		distance[1]=0;
		
		//간선 입력
		edgeList = new Edge[busLineCnt];
		for(int idx=0; idx<busLineCnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			edgeList[idx] = new Edge(a, b, c);
		}
	}
	
	static void BellmanFord() {
		//cityCnt번 반복하며 최단거리테이블 갱신
		for(int idx=0; idx<=cityCnt; idx++) {
			//모든 간선 확인
			for(int edge=0; edge<busLineCnt; edge++) {
				//최단거리테이블에서 출발노드의 값이 INF가 아니라면 최단거리 테이블 갱신
				if(distance[edgeList[edge].from]!=INF && distance[edgeList[edge].to]>distance[edgeList[edge].from]+edgeList[edge].time) {
					distance[edgeList[edge].to]=distance[edgeList[edge].from]+edgeList[edge].time;
				}
			}
			
			//cityCnt-1번째 반복이면 최단거리를 따로 저장해두기
			if(idx==cityCnt-1) {
				for(int index=0; index<cityCnt+1; index++) {
					distanceBackup[index]=distance[index];
				}
			}
		}
		
		//distance와 distanceBackup이 다르다면 음의 사이클 존재
		for(int idx=1; idx<=cityCnt; idx++) {
			if(distance[idx]!=distanceBackup[idx]) {
				flag=true;
				break;
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		//입력
		inputTestCase();
		//벨만포드
		BellmanFord();
		//출력
		if(flag) {
			System.out.println(-1);
		}
		else {
			for(int idx=2; idx<=cityCnt; idx++) {
				if(distance[idx]==INF) {
					sb.append(-1).append("\n");
				}
				else{
					sb.append(distance[idx]).append("\n");
				}
			}
			System.out.println(sb);
		}
	}
}
