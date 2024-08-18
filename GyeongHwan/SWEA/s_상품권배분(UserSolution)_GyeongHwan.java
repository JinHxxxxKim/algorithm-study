package Btest;
import java.util.*;

class UserSolution {
	static int Group, department;
	static HashMap<Integer, Integer> dict;
	int[] parent; //부모정보
	int[] sub; //인원수 총합 정보
	int[] child; //자식 수 정보
	boolean[] isDead; //이미 삭제된 부서인지에 대한 정보
	
	public void init(int N, int mId[], int mNum[]) {
		//그룹의 수
		Group = N;
		//등록된 부서의 수 == 부서인덱스
		department=0;
		
		//특정 부서의 하위 부서 개수를 저장하는 해시맵
		dict = new HashMap<>();
		
		//초기화
		parent = new int[18000]; //특정 노드의 부모노드를 저장
		sub = new int[18000]; //특정 노드의 인원수를 저장
		child = new int[18000]; //특정 노드의 자식노드 개수를 저장
		isDead = new boolean[18000]; //특정 노드가 삭제되었는지 살았는지 저장
		
		//각 그룹의 최상위 부서들 삽입
		for(int idx=0; idx<N; idx++) {
			//해시맵에 최상위부서들 요소 삽입
			//key: 부서id, value: 몇번째 부서인지 명시
			dict.put(mId[idx], department);
			//최상위 부서들의 부모노드는 -1로 설정
			parent[department]=-1;
			//해당 노드를 루트노드로 하는 서브트리의 총 인원수
			sub[department]=mNum[idx];
			//해당 부서의 하위부서 개수
			child[department]=0;
			//부서 인덱스 증가
			department++;
		}
		
		return;
	}

	public int add(int mId, int mNum, int mParent) {
		
		//mParent부서의 하위부서 개수 구하기
		int parentIdx = dict.get(mParent);
		//하위 부서의 개수가 이미 3개라면, -1을 반환
		if(child[parentIdx]>=3) {
			return -1;
		}
		
		//현재 새로 추가할 부서의 부서idx
		int nowIdx=department;
		department++;
		//해시맵에 현재 부서의 mId를 key값으로 요소 삽입
		dict.put(mId, nowIdx);
		//부모 노드의 하위부서 개수+1
		child[parentIdx]+=1;
		//현재 노드의 부모 설정
		parent[nowIdx]=parentIdx;
		//현재 노드의 하위부서 개수 설정
		child[nowIdx]=0;
		//현재 노드부터 루트노드까지 인원수를 +mNum
		sub[nowIdx]=0;
		while(nowIdx!=-1) {
			sub[nowIdx]+=mNum;
			nowIdx=parent[nowIdx];
		}
		
		return sub[parentIdx];
	}

	public int remove(int mId) {
		//targetId가 존재하지 않는다면 -1을 반환한다.
		if(!dict.containsKey(mId)) {return -1;}
		
		//삭제할 id의 부서index
		int nowIdx = dict.get(mId);
		
		//이미 삭제된 부서의 id가 주어진다면 -1을 반환한다.
		//루트노드까지의 경로에서 삭제된 노드가 있다면 삭제 취급
		for(int idx=nowIdx; idx!=-1; idx=parent[idx]) {
			if(isDead[idx]) {return -1;}
		}
		
		//현재 부서노드 삭제하기
		isDead[nowIdx]=true;
		//부모노드의 자식 수 -1
		child[parent[nowIdx]]--;
		
		//재귀적으로 sub[]의 인원수 감소
		int num = sub[nowIdx];
		while(nowIdx!=-1) {
			sub[nowIdx]-=num;
			nowIdx=parent[nowIdx];
		}
		
		return num;
	}

	public int distribute(int K) {
		//최상위 부서를 루트노드로 하는 트리에 속하는 인원의 총합. 즉, 그룹의 총 인원수 구하기
		int[] arr = Arrays.copyOf(sub, Group);
		//정렬
		Arrays.sort(arr);
		//총 인원수를 계산
		int totalNum = 0;
		for(int idx=0; idx<Group; idx++) {
			totalNum+=arr[idx];
		}
		//총 인원수가 K이하인 경우, 그룹 중 가장 많은 인원수 반환
		if(totalNum<=K) {
			return arr[arr.length-1];
		}
		//총 인원수가 K초과인 경우, 상한선 L 정하기
		else {
			//매개변수 탐색으로 상한선 L구하기
			int L=0, count=0;
			int leftIdx=0;
			int rightIdx=K;
			while(leftIdx<=rightIdx) {
				int midIdx=(leftIdx+rightIdx)/2;
				//상한선을 midIdx값으로 잡을 경우, 나누어주는 상품권의 개수 확인하기
				count=0;
				for(int idx=0; idx<Group; idx++) {
					if(arr[idx]<=midIdx){
						count+=arr[idx];
					}
					else {
						count+=midIdx;
					}
				}
				
				//상품권을 딱 맞게 나누어 주었다면
				if(count==K) {
					L=midIdx;
					break;
				}
				//상품권을 더 나눠줄 수 있다면
				else if(count<K) {
					L=midIdx;
					leftIdx=midIdx+1;
				}
				//상품권이 부족하다면
				else if(count>K) {
					rightIdx=midIdx-1;
				}
			}
			return L;
		}
	}
}