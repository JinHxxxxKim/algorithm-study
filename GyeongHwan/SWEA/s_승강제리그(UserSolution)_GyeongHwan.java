import java.util.*;
/**
 * 1. 제약조건 및 함수호출 확인
 *	1-1. trade() 호출 <= 1000
 *	1-2. move()	호출 <= 500
 *	1-3. 선수들의 수 <= 39990
 *	1-4. 리그의 개수 <= 10
 *
 */

/**
 * 1. 리그별로 선수들을 어떤 자료구조로 저장해야 하는가?
 * 	1-1. 배열 => 접근은 빠르지만 선수이동 후 매번 정렬해야함
 * 	1-2. 자동 정렬이 되는 자료구조는 뭐가 있을까?
 * 		1-2-1. 우선순위 큐 => 중간값을 처리할 수 없음
 * 		1-2-2. 트리셋 => 처음, 끝의 검색이 자유로움. 중간은 iterator로 접근가능
 *  1-3. 각 리그마다 트리셋으로 생성
 *
 * 2. 각 리그에서 모두 한꺼번에 선수들을 제거한 다음, 한꺼번에 넣기
 * 	2-1. 0번째 리그의 좋은 선수와, 마지막 리그의 나쁜 선수는 이동 없음
 *
 */

/**
 * move()
 *
 * 1. 0번째 리그의 좋은 선수와 마지막 리그의 나쁜 선수를 빼서 따로 저장
 * 2. 1번째 리그(now)의 좋은 선수와 0번째 리그(prev)의 나쁜 선수 교환
 * 3. 반복
 * 4. 0번째 리그의 좋은 선수와 마지막 리그의 나쁜 선수를 추가
 *
 */

/**
 * trade()
 *
 * 1. 0번째 리그의 좋은 선수와 마지막 리그의 중간 선수를 빼서 따로 저장
 * 2. 1번째 리그(now)의 좋은 선수와 0번째 리그(prev)의 중간 선수 교환
 * 3. 반복
 * 4. 0번째 리그의 좋은 선수와 마지막 리그의 중간 선수를 추가
 *
 */



class UserSolution {
	public class Player implements Comparable<Player>{
		int id;
		int ability;
		public Player(int id, int ability){
			this.id=id;
			this.ability=ability;
		}

		public int compareTo(Player o){
			if(this.ability==o.ability){
				return this.id-o.id;
			}
			return o.ability-this.ability;
		}
	}
	int N;
	int L;
	int[] ability;
	int playerPerLeague;
	TreeSet<Player>[] leagues;


	void init(int N, int L, int mAbility[]) {
		this.N=N;
		this.L=L;
		this.ability=mAbility;
		playerPerLeague = N/L;
		leagues = new TreeSet[L];
		for(int idx=0; idx<L; idx++) {
			leagues[idx] = new TreeSet<>();
		}
		for(int idx=0; idx<N; idx++){
			int leagueNum = idx/playerPerLeague;
			leagues[leagueNum].add(new Player(idx, ability[idx]));
		}
	}

	int move() {
		int ans=0;
		//각 리그에서 좋은 선수와 나쁜 선수들 따로 저장해둘 리스트
		Player[] tempGoodPlayer = new Player[L];
		Player[] tempBadPlayer = new Player[L];

		//각 리그에서 좋은 선수와 나쁜 선수들 빼기
		for(int now=0; now<L; now++){
			tempGoodPlayer[now] = leagues[now].first();
			tempBadPlayer[now] = leagues[now].last();

			leagues[now].remove(tempGoodPlayer[now]);
			leagues[now].remove(tempBadPlayer[now]);
		}

		//이동한 결과를 각 리그에 넣기
		for(int now=1; now<L; now++){
			int prev=now-1;
			leagues[now].add(tempBadPlayer[prev]);
			leagues[prev].add(tempGoodPlayer[now]);

			ans+=tempBadPlayer[prev].id+tempGoodPlayer[now].id;
		}

		//0번째 리그의 좋은 선수와 마지막 리그의 나쁜 선수 넣기
		leagues[0].add(tempGoodPlayer[0]);
		leagues[L-1].add(tempBadPlayer[L-1]);

		return ans;
	}

	int trade() {
		int ans=0;
		int midIndex=playerPerLeague/2;
		//각 리그에서 좋은 선수와 중간 선수들 따로 저장해둘 리스트
		Player[] tempGoodPlayer = new Player[L];
		Player[] tempMidPlayer = new Player[L];

		//각 리그에서 좋은 선수와 중간 선수들 빼기
		for(int now=0; now<L; now++){
			tempGoodPlayer[now] = leagues[now].first();

			Iterator<Player> iterator = leagues[now].iterator();
			for(int idx=0; idx<midIndex; idx++){
				iterator.next();
			}
			tempMidPlayer[now] = iterator.next();

			leagues[now].remove(tempGoodPlayer[now]);
			leagues[now].remove(tempMidPlayer[now]);
		}

		//이동한 결과를 각 리그에 넣기
		for(int now=1; now<L; now++){
			int prev=now-1;
			leagues[now].add(tempMidPlayer[prev]);
			leagues[prev].add(tempGoodPlayer[now]);

			ans+=tempMidPlayer[prev].id+tempGoodPlayer[now].id;
		}

		//0번째 리그의 좋은 선수와 마지막 리그의 중간 선수 넣기
		leagues[0].add(tempGoodPlayer[0]);
		leagues[L-1].add(tempMidPlayer[L-1]);

		return ans;
	}

}