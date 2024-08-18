package Btest;
import java.util.*;
import java.io.*;

/**
 * 함수호출 횟수 확인하기
 * 	1-1. insert()<=30000
 * 	1-2. moveCursor()<=30000
 * 	1-3. countCharacter()<=40000
 * : countCharacter()을 빠르게 처리해야겠다.
 * 
 * 제약사항 확인하기
 *  1-1. 최대 글자수 <=90000
 * : 삭제가 없다.
 */

/**
 * 1. 문자열을 배열에 저장하면 빈번한 삽입으로 인해 시간이 많이 든다.
 * : LinkedList를 사용
 * 2. LinkedList를 사용하면 countCharacter를 계산하기 위해 처음부터 cursor까지 찾아간 후, 이후의 문자를 세야한다. 즉, 모든 문자열을 탐색해야 하므로 시간이 많이 걸린다.
 * : 전처리 기법 사용
 * 3. 전처리 기법을 사용하여 미리 각 문자 이후로 나타내는 문자들의 개수들을 세어 놓으면, insert할 때마다 insert문자 기준으로 앞에 있는 모든 문자들의 alpha배열 값을 수정해주어야 한다.
 * : 줄마다 linkedlist를 만들어서 alpha개수를 세어놓는다.
 * : coutCharacter를 계산하기 위해서 해당 줄에만 커서 이후로 타겟문자가 몇개 있는지 세고, 뒷줄에는 각각 미리 세어놓은 값들을 더한다. 
 */

/**
 * init()
 * 1. 한줄마다 LinkedList와 int[26] alphaCnt배열 생성
 * 2. 커서의 위치를 나타내기 위해서 int curH, curW 생성
 * 3. 전체 문자열의 마지막 문자 위치를 나타내기 위해서 int lastH, lastW 생성
 */

/**
 * insert()
 * 1. 커서가 위치한 곳에 문자 넣기
 * 2. 해당 라인의 마지막 문자가 최대너비를 넘어서면 마지막 문자를 다음 ll의 첫번째 문자로 옮기기
 *  2-1. 옮긴 후, 해당 줄의 alphaCnt에서 해당 문자의 숫자 1 감소
 *  2-2. 옮긴 후, 다음 줄의 alphaCnt에서 해당 문자의 숫자 1 증가
 * 3. 삽입한 문자 다음으로 커서 위치 옮기기
 */

/**
 * moveCursor()
 * 1. 커서의 위치를 mRow행, mCol열 문자의 왼쪽으로 이동
 * 2. mRow행, mCol열이 전체 문자열의 마지막 문자 위치를 넘어서는 경우, 마지막 문자 오른쪽으로 이동
 * 3. 
 * 
 * 
 */

/**
 * countCharacter()
 * 1. 커서가 위치한 라인에서 커서 이후의 문자들 중에 타겟문자 세기
 * 2. 다음 라인들의 alphaCnt에서 타겟문자의 개수들 더하기
 */

/**
 * curH, curW
 * : curH행 curW열의 문자 왼쪽에 커서가 있음을 의미
 * 
 * lastH, lastW
 * : lastH행 lastW열의 위치에 문자열의 마지막 문자가 위치함을 의미
 * 
 * 
 * 1. 커서의 위치가 해당 줄의 마지막 문자 오른쪽에 위치한다면
 * if(curW>MAX_W){curH++; curW=1;}
 * 
 * 2. 마지막 문자의 오른쪽에 커서가 위치한다면
 * if(curW>lastW)
 * 
 * 
 */


class UserSolution{
	static int MAX_H=301; 
	static int MAX_W=300;
	static int alpha=26;
	
	static LinkedList<Character>[] ll = new LinkedList[MAX_H];
	static int[][] alphaCnt;
	
	//커서의 위치
	static int curH, curW;
	//마지막 문자의 위치
	static int lastH, lastW;
	//현재 행, 열
	static int nowH, nowW;
	
	void init(int H, int W, char mStr[]){
		for(int idx=0; idx<301; idx++) {
			ll[idx]=new LinkedList<>();
		}
		alphaCnt = new int[301][alpha];
		
		this.MAX_H=H;
		this.MAX_W=W;
		
		//각 행의 0열에 해당하는 값 넣기
		for(int h=1; h<=H; h++) {
			ll[h].add('\0');
		}
		
		//각 줄에 해당하는 ll에 해당 줄에 속하는 문자들을 넣기
		for(int idx=0; mStr[idx]!='\0'; idx++) {
			//몇번째 행인지 파악
			lastH = idx/MAX_W+1;
			lastW = idx%MAX_W+1;
			//해당 행을 의미하는 ll에 문자 넣기
			ll[lastH].add(mStr[idx]);
			
			//해당 문자 개수 증가
			alphaCnt[lastH][mStr[idx]-'a']++;
		}
		
		//커서 위치 초기화 -> 1행 1열 문자의 왼쪽을 의미
		curH=1;
		curW=1;
		
//		//테스트코드
//		//각 라인의 문자들을 출력
//		for(int row=1; row<=lastH; row++) {
//			for(int col=1; col<ll[row].size(); col++) {
//				System.out.print(ll[row].get(col)+" ");
//			}
//			System.out.println();
//		}
//		//현재 커서 위치 출력
//		System.out.println("현재 커서 위치");
//		System.out.println(curH+" "+curW);
//		//현재 마지막 문자 위치 출력
//		System.out.println("현재 마지막 문자 위치");
//		System.out.println(lastH+" "+lastW);
//		//두번째 줄의 alphaCnt 개수 출력
//		System.out.println("두번째 줄의 alphaCnt 개수 출력");
//		for(int idx=0; idx<26; idx++) {
//			System.out.print(alphaCnt[2][idx]+" ");
//		}
//		System.out.println();
	}
	
	void insert(char mChar){
		//커서가 위치한 곳에 문자를 추가하기 : 커서는 각 라인의 마지막 문자 오른쪽에 위치하지 않는다. 다음 줄의 첫번째 문자 왼쪽에 항상 위치한다.
		//문자 추가
		ll[curH].add(curW, mChar);
		//커서가 위치한 행의 alphaCnt에서 target문자의 값을 +1
		alphaCnt[curH][mChar-'a']++;
		//커서가 위치한 행의 문자개수가 최대너비를 넘어섰다면, -> 행을 하나씩 증가하며 반복
		nowH=curH;
		while(ll[nowH].size()>MAX_W+1) {
			//마지막 문자를 다음 행의 첫번째 열로 옮기기
			char temp = ll[nowH].removeLast();
			ll[nowH+1].add(1, temp);
			//커서가 위치한 행의 alphaCnt에서 마지막 문자의 값을 -1
			alphaCnt[nowH][temp-'a']--;
			//다음 행의 alphaCnt에서 마지막 문자의 값을 +1
			alphaCnt[nowH+1][temp-'a']++;
			//행 하나 증가
			nowH++;
		}
		
		//lastH, lastW의 위치 갱신
		//기존의 lastW값에서 +1
		lastW++;
		//최대너비보다 크다면
		if(lastW>MAX_W) {
			lastH++;
			lastW=1;
		}
		
		//커서위치는 추가한 문자의 오른쪽에 위치시키기
		//기존의 curW값에서 +1
		curW++;
		//최대 너비보다 크다면 다음 행의 첫번째 열에
		if(curW>MAX_W) {
			curH++;
			curW=1;
		}
		
		
//		//테스트코드
//		//현재 커서 위치 출력
//		System.out.println("insert 후 커서 위치");
//		System.out.println(curH+" "+curW);
//		//현재 마지막 문자 위치 출력
//		System.out.println("insert 후 마지막 문자 위치");
//		System.out.println(lastH+" "+lastW);
//		//두번째 줄의 alphaCnt 개수 출력
//		System.out.println("두번째 줄의 alphaCnt 개수 출력");
//		for(int idx=0; idx<26; idx++) {
//			System.out.print(alphaCnt[2][idx]+" ");
//		}
//		System.out.println();
	}

	char moveCursor(int mRow, int mCol){
		//커서 이동
		//새로운 커서의 위치가 lastH, lastW를 넘어선다면
		if(mRow>lastH || (mRow==lastH && mCol>lastW)) {
			curH=lastH;
			curW=lastW+1;
			//커서가 최대 너비보다 크다면 다음 줄로 넘기기
			if(curW>MAX_W) {
				curH++;
				curW=1;
			}
			//커서 다음 문자 리턴
			return '$';
		}
		
		//새로운 커서의 위치가 lastH, lastW를 넘어서지 않는다면
		else {
			curH=mRow;
			curW=mCol;
			//테스트코드
//			System.out.println("새로운 커서 위치: "+curH+" "+curW);
//			System.out.println("커서 오른쪽 문자: "+ll[curH].get(curW));
			
			//커서 다음 문자 리턴
			return ll[curH].get(curW);
		}
	}

	int countCharacter(char mChar){
		
		//각 라인의 문자들을 출력
//		System.out.println("각 라인의 문자들을 출력");
//		for(int row=1; row<=lastH; row++) {
//			for(int col=1; col<ll[row].size(); col++) {
//				System.out.print(ll[row].get(col)+" ");
//			}
//			System.out.println();
//		}
		
		//타겟 문자 카운팅 변수
		int count=0;
		//커서가 위치한 행에서 타겟 문자의 개수 세기
		for(int idx=curW; idx<=ll[curH].size()-1; idx++) {
			if(ll[curH].get(idx)==mChar) {
				count++;
			}
		}
		
		//테스트코드
//		System.out.println("현재 라인에서의 타겟문자 개수: "+count);
		
		
		//커서가 위치한 행의 다음 행들에서 타겟 문자의 개수 더하기
		for(int idx=curH+1; idx<=lastH; idx++) {
//			System.out.println("행: "+idx);
//			System.out.println(alphaCnt[idx][mChar-'a']);
			count+=alphaCnt[idx][mChar-'a'];
		}
		
		//테스트코드
//		System.out.println("다음 라인들까지 포함한 타겟문자 개수: "+count);
		
		//테스트코드
//		//현재 커서 위치 출력
//		System.out.println("현재 커서 위치");
//		System.out.println(curH+" "+curW);
//		//현재 마지막 문자 위치 출력
//		System.out.println("현재 마지막 문자 위치");
//		System.out.println(lastH+" "+lastW);
		
		return count;
	}
}