import java.util.*;

/**
 * 1. 제약 사항 및 함수호출 횟수
 * 	1-1. move()<=500
 * 	1-2. trade()<=1000 : 중간 선수를 찾는 것을 빠르게 수행
 */

/**
 * 1. 이동이 발생하는 모든 선수들을 다 빼놓고 맞는 자리 찾아넣기
 * 2. 이진탐색
 */

/**
 * 1. 설계를 제대로 하고 들어가자.
 *
 *
 *
 */

class UserSolution {
	public class Player implements Comparable<Player>{
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

	public int N, L;
	public int playerPerLeague;
	public int[] ability;
	public ArrayList<Player>[] leagues;

	//이진탐색
	int binarySearch(ArrayList<Player> league, Player one){
		int leftIdx=0;
		int rightIdx=league.size()-1;

		while(leftIdx<=rightIdx){
			int midIdx=(leftIdx+rightIdx)/2;
			Player midPlayer = league.get(midIdx);
			int temp = midPlayer.compareTo(one);
			if(temp<0){
				leftIdx=midIdx+1;
			}
			else {
				rightIdx=midIdx-1;

			}
		}
		return leftIdx;
	}


	void init(int N, int L, int mAbility[]) {
		this.N=N;
		this.L=L;
		ability=mAbility;
		playerPerLeague=N/L;
		leagues = new ArrayList[L];
		for(int idx=0; idx<L; idx++){
			leagues[idx] = new ArrayList<>();
		}
		for(int idx=0; idx<N; idx++){
			int lNum = idx/playerPerLeague;
			leagues[lNum].add(new Player(idx, ability[idx]));
		}
		//정렬
		for(int idx=0; idx<L; idx++){
			Collections.sort(leagues[idx]);
		}
		// System.out.println("초기상태");
		// for(int idx=0; idx<L; idx++){
		// 	for(int p=0; p<leagues[idx].size(); p++){
		// 		System.out.print(leagues[idx].get(p).id+" ");
		// 	}
		// 	System.out.println();
		// }
	}

	int move() {
		int ans=0;
		//각 리그의 뛰어난 선수와 부족한 선수를 따로 저장 후 삭제
		Player[] topPlayer = new Player[L];
		Player[] bottomPlayer = new Player[L];

		for(int idx=0; idx<L; idx++){
			topPlayer[idx] = leagues[idx].get(0);
			bottomPlayer[idx] = leagues[idx].get(playerPerLeague-1);
			leagues[idx].remove(0);
			leagues[idx].remove(leagues[idx].size()-1);
		}
		// System.out.println("move() 수행 전 삭제");
		// for(int idx=0; idx<L; idx++){
		// 	for(int p=0; p<leagues[idx].size(); p++){
		// 		System.out.print(leagues[idx].get(p).id+" ");
		// 	}
		// 	System.out.println();
		// }
		//이동하는 선수들을 이진탐색으로 넣을 위치를 찾은 후, 정렬되도록 넣기
		for(int now=1; now<L; now++){
			int prev=now-1;
			leagues[prev].add(binarySearch(leagues[prev], topPlayer[now]), topPlayer[now]);
			leagues[now].add(binarySearch(leagues[now], bottomPlayer[prev]), bottomPlayer[prev]);
			ans+=topPlayer[now].id+bottomPlayer[prev].id;
		}

		//0번째 리그의 뛰어난 선수와 마지막 리그의 부족한 선수를 추가
		leagues[0].add(binarySearch(leagues[0], topPlayer[0]), topPlayer[0]);
		leagues[L-1].add(binarySearch(leagues[L-1], bottomPlayer[L-1]), bottomPlayer[L-1]);
		// System.out.println("move() 수행 후");
		// for(int idx=0; idx<L; idx++){
		// 	for(int p=0; p<leagues[idx].size(); p++){
		// 		System.out.print(leagues[idx].get(p).id+" ");
		// 	}
		// 	System.out.println();
		// }

		return ans;
	}

	int trade() {
		int ans=0;
		int midIndex=playerPerLeague/2;
		//각 리그의 뛰어난 선수와 부족한 선수를 따로 저장 후 삭제
		Player[] topPlayer = new Player[L];
		Player[] midPlayer = new Player[L];

		for(int idx=0; idx<L; idx++){
			topPlayer[idx] = leagues[idx].get(0);
			midPlayer[idx] = leagues[idx].get(leagues[idx].size()/2);

			leagues[idx].remove(leagues[idx].size()/2);
			leagues[idx].remove(0);
		}

		// System.out.println("trade() 수행 전 삭제");
		// for(int idx=0; idx<L; idx++){
		// 	for(int p=0; p<leagues[idx].size(); p++){
		// 		System.out.print(leagues[idx].get(p).id+" ");
		// 	}
		// 	System.out.println();
		// }

		//이동하는 선수들을 이진탐색으로 넣을 위치를 찾은 후, 정렬되도록 넣기
		for(int now=1; now<L; now++){
			int prev=now-1;
			leagues[prev].add(binarySearch(leagues[prev], topPlayer[now]), topPlayer[now]);
			leagues[now].add(binarySearch(leagues[now], midPlayer[prev]), midPlayer[prev]);
			ans+=topPlayer[now].id+midPlayer[prev].id;
		}

		//0번째 리그의 뛰어난 선수와 마지막 리그의 중간 선수를 추가
		leagues[0].add(binarySearch(leagues[0], topPlayer[0]), topPlayer[0]);
		leagues[L-1].add(binarySearch(leagues[L-1], midPlayer[L-1]), midPlayer[L-1]);

		// System.out.println("trade() 수행 후");
		// for(int idx=0; idx<L; idx++){
		// 	for(int p=0; p<leagues[idx].size(); p++){
		// 		System.out.print(leagues[idx].get(p).id+" ");
		// 	}
		// 	System.out.println();
		// }

		return ans;
	}

}