package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author JinHxxxxKim
 * 
 * 1. 진실을 아는 사람들을 하나의 집합(union)으로 만든다.
 * 		- 진실을 아는사람들의 수가 0이라면 파티의 수가 정답이 된다.
 * 2. 모든 파티를 조회하며, 같은 파티에 참여한 사람들을 하나의 집합(union)으로 만든다.
 * 3. 한번의 파티 순회가 끝나면, 진실을 알고있는 사람들의 Root를 구한다(find).
 * 4. 다시한번 모든 파티를 순회하며, 각 파티 참석자들의 Root를 조회한다(find).
 * 		- 모든 파티 참석자들의 Root가 진실을 알고있는 사람들의 Root와 다르다면 카운팅한다.
 */
public class Sol_1043 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;
	
	private static int memberNumber; // 총 사람 수
	private static int partyNumber; // 파티 개수
	private static int memberNumberKnowTruth; // 진실을 알고있는 사람들의 수
	private static int[] membersKnowTruth; // 진실을 알고있는 사람들
	private static Party[] parties; // 파티 정보를 저장하는 배열
	
	// Union-Find 관련 변수
	private static int[] rootInfo; // 각 노드의 루트 정보

	public static void main(String[] args) throws Exception {
		// 입력 Start
		st = new StringTokenizer(br.readLine().trim());
		memberNumber = Integer.parseInt(st.nextToken());
		partyNumber = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine().trim());
		memberNumberKnowTruth = Integer.parseInt(st.nextToken());
		if(memberNumberKnowTruth == 0) {
			// 진실을 아는 사람이 없는 경우
			System.out.println(partyNumber);
			return;
		}
		
		membersKnowTruth = new int[memberNumberKnowTruth];
		for(int cnt = 0;cnt<memberNumberKnowTruth;++cnt) {
			membersKnowTruth[cnt] = Integer.parseInt(st.nextToken());
		}

		parties = new Party[partyNumber];
		for (int idx = 0; idx < partyNumber; ++idx) {
			st = new StringTokenizer(br.readLine().trim());
			int currPartyMemberNum = Integer.parseInt(st.nextToken());
			int[] currMembers = new int[currPartyMemberNum];
			for (int cnt = 0; cnt < currPartyMemberNum; ++cnt) {
				currMembers[cnt] = Integer.parseInt(st.nextToken());
			}
			parties[idx] = new Party(currPartyMemberNum, currMembers);
		}
		// 입력 End
		
		// UnionFind를 위한 초기화
		makeSet();
		for (int idx = 1; idx < memberNumberKnowTruth; ++idx) {
			// 진실을 알고있는 사람들을 하나의 집합으로 형성
			// 0번 사람을 고정하고 이후 사람들을 Union
			union(membersKnowTruth[0], membersKnowTruth[idx]);
		}
		
		// 파티 1차 순회: 진실을 아는 사람들을 동일 집합으로 만들기
		for (Party currParty : parties) {
			for (int idx = 1; idx < currParty.memberNum; ++idx) {
				// 동일하게 파티 참석자 중 0번을 고정하고 나머지 사람들을 Union
				union(currParty.members[0], currParty.members[idx]);
			}
		}
		
		int truthRoot = find(membersKnowTruth[0]); // 진실을 아는 집합의 루트
		int ans = 0;
		// 파티 2차 순회: 거짓말 칠 수 있는 파티의 수 카운팅
		for (Party currParty : parties) {
			boolean canLie = true;
			for (int idx = 0; idx < currParty.memberNum; ++idx) {
				int currMember = currParty.members[idx];
				if(find(currMember) == truthRoot) {
					canLie = false;
					break;
				}
			}
			if(canLie) {
				++ans;
			}
		}
		System.out.println(ans);
	}

	private static void makeSet() {
		rootInfo = new int[memberNumber + 1];
		for(int idx = 1;idx<memberNumber+1;++idx)
			rootInfo[idx] = idx;
	}

	private static void union(int node1, int node2) {
		int rootA = find(node1);
		int rootB = find(node2);
		
		if(rootA == rootB) return; // 이미 같은 집합일 경우
		rootInfo[rootB] = rootA;
	}

	private static int find(int node) {
		if(node == rootInfo[node]) return node;
		else return rootInfo[node] = find(rootInfo[node]);
	}

	// 파티 클래스
	static class Party {
		int memberNum;
		int[] members;

		public Party(int memberNum, int[] members) {
			super();
			this.memberNum = memberNum;
			this.members = members;
		}
	}
}