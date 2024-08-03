package Btest;
import java.util.*;
/**
 * 1. 함수호출 횟수 확인
 * 	1-1. move()함수는 500회 호출
 * 	1-2. trade()함수는 1000회 호출
 * : trade를 좀 더 빠르게 구현하면 되겠다.
 *
 * 2. 제약사항 확인
 * : 선수들의 수가 왜 39990이라는 숫자에 제한이 걸려있지?
 * : 리그의 수가 왜 10개로 제한되어 있지?
 *
 * 리그의 수==10개인 경우
 * move(), trade()할때마다 이동하는 선수들은 총 18명
 * 이동 후, 각 리그 정렬
 */

/**
 * 첫번째 문제풀이 아이디어 - 단순 구현
 *
 * 1. 선수 클래스 생성
 * 	1-1. id, ability
 * 	1-2. 정렬조건: ability 내림차순, id 오름차순
 *
 * 2. 리그 배열
 * 	2-1. league = arr[l][n/l]
 * 	2-2. 중간선수: arr[][(n/l+1)/2-1]
 * 	2-3. 좋은선수: arr[][0]
 * 	2-4. 나쁜선수: arr[][n/l-1]
 *
 * 3. move 또는 trade 후 각 리그 정렬
 *
 * => 제한시간 초과 발생 
 * : 배열접근해서 값바꾸는 건 시간소모가 크지않다. 
 * : 함수호출마다 매번 리그별로 정렬해주는 것이 가장 시간소모가 크다.
 * -> 리그를 배열이 아닌 자동으로 정렬해주는 자료구조를 사용하자.
 * : 자동으로 정렬해주는 자료구조 
 * -> 우선순위 큐: 가장 큰 값이나 작은 값은 뺄 수 있지만 중간값은 못뺌
 */

/**
 * 두번째 문제풀이 아이디어 - PQ
 * 
 * 1. 선수 클래스 생성
 * 2. 각 리그를 pq로 생성
 * 3. move()
 * 	3-1. tempBottomAbility[idx]에 idx번째 리그에서 가장 부족한 선수를 빼서 넣기
 *  3-2. tempTopAbility[idx]에 idx번째 리그에서 가장 뛰어난 선수를 빼서 넣기
 *  	3-2-1. reversedPQ생성 후 idx번째 리그의 선수 다 넣기
 *  	3-2-2. reversedPQ에서 가장 뛰어난 선수 빼기
 *  	3-2-3. 새로 PQ[idx] 생성 후, 가장 뛰어난 선수가 빠진 후 남아있는 선수들 다 넣기
 * 4. trade()
 * 	4-1. Arrays.sort()보다 Collections.sort()가 더 빠르므로 ArraysList에 넣고 처리
 * 
 * => 제한시간 초과 발생
 * : 계속 자료구조를 새로 생성하고 데이터를 넣는 과정이 많아서 그런 듯....
 * 
 */

/**
 * 세번째 문제풀이 아이디어 - TreeSet
 */



class UserSolution {
	static class Player implements Comparable<Player>{
		int id, ability;
		public Player(int id, int ability){
			this.id=id;
			this.ability=ability;
		}
		@Override
		public int compareTo(Player o){
			if(this.ability==o.ability){
				return this.id-o.id;
			}
			return o.ability-this.ability;
		}
	}
	
	static int N, L;
	static int[] ability;
	static TreeSet<Player>[] leagues;
	static int playerPerLeague;

	void init(int N, int L, int mAbility[]) {
		this.N=N;
		this.L=L;
		this.ability=mAbility;
		playerPerLeague = N/L;
		leagues = new TreeSet[L];
		for(int idx=0; idx<L; idx++) {
			leagues[idx] = new TreeSet<>();
		}
		for(int id=0; id<N; id++) {
			int leagueNum=id/playerPerLeague;
			leagues[leagueNum].add(new Player(id,mAbility[id]));
		}
	}

	int move() {
		int ans=0;
		Player[] topPlayers = new Player[L];
		Player[] bottomPlayers = new Player[L];
		
		for(int idx=0; idx<L; idx++) {
			//각 리그에서 능력이 뛰어난 선수 따로 저장
			topPlayers[idx]=leagues[idx].first();
			//각 리그에서 능력이 부족한 선수 따로 저장
			bottomPlayers[idx]=leagues[idx].last();
		}
		
		//선수 교환하기
		for(int idx=1; idx<L; idx++) {
			leagues[idx-1].remove(bottomPlayers[idx-1]);
			leagues[idx].remove(topPlayers[idx]);
			
			leagues[idx-1].add(topPlayers[idx]);
			leagues[idx].add(bottomPlayers[idx-1]);
			
			ans+=topPlayers[idx].id+bottomPlayers[idx-1].id;
		}

		return ans;
	}

	int trade() {
		int ans=0;
		int mid=playerPerLeague/2;
		Player[] topPlayers = new Player[L];
		Player[] midPlayers = new Player[L];
		
		for(int idx=0; idx<L; idx++) {
			//각 리그에서 뛰어난 선수 따로 저장
			topPlayers[idx]=leagues[idx].first();
			//각 리그에서 중간 선수 따로 저장
			Iterator<Player> iterator = leagues[idx].iterator();
			for(int idx2=0; idx2<mid; idx2++) {
				iterator.next();
			}
			midPlayers[idx]=iterator.next(); //현재 가리키고 있는 요소를 반환
		}
		
		for(int idx=1; idx<L; idx++) {
			leagues[idx-1].remove(midPlayers[idx-1]);
			leagues[idx].remove(topPlayers[idx]);
			
			leagues[idx-1].add(topPlayers[idx]);
			leagues[idx].add(midPlayers[idx-1]);
			
			ans+=midPlayers[idx-1].id+topPlayers[idx].id;
		}

		return ans;
	}

}