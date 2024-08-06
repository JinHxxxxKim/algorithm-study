import java.util.*;
import java.io.*;

/**
 * 1. 상태공간트리
 * 	1-1. 각 노드 (a,b,c)
 * 	1-2. 자식노드: a->b, a->c, b->a, b->c, c->a, c->b
 * 	1-3. from->to일 때,
 * 		1-3-1. from이 0이면 넘어가기
 * 		1-3-2. 부모가 to->from이면, 넘어가기
 * 		1-3-3. to.value가 to.limit이면 넘어가기
 * 	1-4. 이미 방문한 노드면 백트래킹 -> 방문노드를 어떻게 체크하지?
 * 		1-4-1. boolean visited[a][b][c]가 true이면 방문
 *
 * 2. dfs탐색
 * 3. 출력
 * 	3-1. 중복제거 및  오름차순 출력
 *
 */

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	static int aLimit, bLimit, cLimit;
	static int nextA, nextB, nextC;
	static int[] arr = new int[3];
	static boolean[][][] visited;
	static boolean[] ans; //true인 값들을 정답으로 출력

	static void inputTestcase() throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(br.readLine().trim());
		aLimit = Integer.parseInt(st.nextToken());
		bLimit = Integer.parseInt(st.nextToken());
		cLimit = Integer.parseInt(st.nextToken());

		visited = new boolean[aLimit+1][bLimit+1][cLimit+1];
		ans = new boolean[cLimit+1];
	}

	static int[] getNext(int from, int to, int nowA, int nowB, int nowC){
		int newA=0;
		int newB=0;
		int newC=0;
		int[] arr = new int[3];

		//a->b
		if(from==1 && to==2){
			//to의 남은공간
			int toSpace = bLimit-nowB;
			//from의 물양과 to의 남은 공간 비교
			if(nowA>toSpace){
				newA=nowA-toSpace;
				newB=bLimit;
			}
			else{
				newA=0;
				newB=nowB+nowA;
			}
			newC=nowC;
		}
		//a->c
		else if(from==1 && to==3){
			//to의 남은공간
			int toSpace = cLimit-nowC;
			//from의 물양과 to의 남은 공간 비교
			if(nowA>toSpace){
				newA=nowA-toSpace;
				newC=cLimit;
			}
			else{
				newA=0;
				newC=nowC+nowA;
			}
			newB=nowB;
		}
		//b->a
		else if(from==2 && to==1){
			//to의 남은공간
			int toSpace = aLimit-nowA;
			//from의 물양과 to의 남은 공간 비교
			if(nowB>toSpace){
				newB=nowB-toSpace;
				newA=aLimit;
			}
			else{
				newB=0;
				newA=nowA+nowB;
			}
			newC=nowC;
		}
		//b->c
		else if(from==2 && to==3){
			//to의 남은공간
			int toSpace = cLimit-nowC;
			//from의 물양과 to의 남은 공간 비교
			if(nowB>toSpace){
				newB=nowB-toSpace;
				newC=cLimit;
			}
			else{
				newB=0;
				newC=nowC+nowB;
			}
			newA=nowA;
		}
		//c->a
		else if(from==3 && to==1){
			//to의 남은공간
			int toSpace = aLimit-nowA;
			//from의 물양과 to의 남은 공간 비교
			if(nowC>toSpace){
				newC=nowC-toSpace;
				newA=aLimit;
			}
			else{
				newC=0;
				newA=nowA+nowC;
			}
			newB=nowB;
		}
		//c->b
		else if(from==3 && to==2){
			//to의 남은공간
			int toSpace = bLimit-nowB;
			//from의 물양과 to의 남은 공간 비교
			if(nowC>toSpace){
				newC=nowC-toSpace;
				newB=bLimit;
			}
			else{
				newC=0;
				newB=nowB+nowC;
			}
			newA=nowA;
		}


		arr[0]=newA;
		arr[1]=newB;
		arr[2]=newC;
		return arr;
	}

	static void dfs(int nowA, int nowB, int nowC){
		//현재 노드의 nowA가 비어있다면
		if(nowA==0){
			ans[nowC]=true;
		}
		//방문체크
		visited[nowA][nowB][nowC]=true;

		//탐색(a=1, b=2, c=3)
		//a->b, a->c, b->a, b->c, c->a, c->b
		for(int from=1; from<=3; from++){
			//from이 0이면 넘어가기
			if((from==1 && nowA==0)||(from==2 && nowB==0)||(from==3 && nowC==0)){continue;}
			for(int to=1; to<=3; to++){
				if(from==to){continue;}
				//to.value가 to.limit이면 넘어가기
				if((to==1&&nowA==aLimit)||(to==2&&nowA==bLimit)||(to==3&&nowA==cLimit)){continue;}
				//탐색 수행
				//다음 물통의 상태 구하기
				arr = getNext(from, to, nowA, nowB, nowC);
				nextA=arr[0];
				nextB=arr[1];
				nextC=arr[2];
				//System.out.println(nextA+" "+nextB+" "+nextC);
				//다음 물통의 상태가 이미 방문한 노드라면 넘어가기
				if(visited[nextA][nextB][nextC]){
					continue;
				}
				//다음 상태 방문
				dfs(nextA, nextB, nextC);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		//입력
		inputTestcase();
		//dfs탐색
		dfs(0, 0, cLimit);
		//출력
		for(int idx=0; idx<=cLimit; idx++){
			if(ans[idx]){
				sb.append(idx+" ");
			}
			//System.out.print(ans[idx]+" ");
		}
		System.out.println(sb);
	}
}