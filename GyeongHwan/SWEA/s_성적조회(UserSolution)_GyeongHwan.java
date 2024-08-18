package Btest;
import java.util.*;

/**
 * 제약조건 확인
 * 1. add() 호출횟수 <= 20000
 * 2. remove() 호출횟수 <= 80000
 * 3. query() 호출횟수 <= 80000
 * : 왜 add만 작지?
 * : remove()와 query()를 빠르게 처리해야 함
 * 
 */

/**
 * 문제풀이 아이디어
 * 1. 각 학년, 각 성별마다 ArrayList<Student> al 생성
 * 2. 각 학년, 각 성별마다 ArrayList<Integer> il 생성 => 해당 al에 있는 학생들의 id값을 저장하는 용도 
 * 3. 이진탐색으로 대상 학생을 찾기
 */

/**
 * init()
 * 1. 학생객체 정의
 * 	1-1. 속성: id, score
 * 	1-2. 정렬기준: score 오름차순, score가 같다면 id 오름차순
 * 2. ArrayList[grade][gender] al 생성
 * 	2-1. grade: 1~3
 * 	2-2. gender: 0은 female, 1은 male
 * 3. ArrayList[grade][gender] il 생성
 * 
 * 
 */

/**
 * add()
 * 1. mGender값들을 합쳐 String변수 Gender 생성
 * 2. 학년과 성별이 일치하는 ArrayList에 학생 객체 넣기
 * 	2-1. 이진탐색으로 들어가야하는 index값 찾기
 * 	2-2. 해당 index에 학생 객체 삽입
 *  2-3. mid를 il[mGrade][gender]에 이진탐색으로 넣기
 * 3. 학년과 성별이 일치하는 ArrayList에서 맨 오른쪽 요소 반환
 * 
 */

/**
 * remove()
 * 1. 각 학년, 각 성별 il을 하나씩 이분탐색하며 삭제할 targetId를 찾는다.
 * 	1-1. il[1~3][0~1]반복
 * 2. 특정 il[][]에 targetId가 있다면, al[][]에서 해당 학생을 찾아 삭제한다.
 * 	2-1. 삭제 후, 해당 arrayList의 첫번째 요소를 반환한다.
 * 	2-2. 삭제 후, 해당 arrayList의 size가 0이라면 0을 반환한다.
 * 3. 모든 arrayList에 targetId가 없다면, 0 반환 
 * 
 */

/**
 * query()
 * 1. 각 arrayList에서 조건에 맞는 학생들을 뽑아서 저장할 arrayList temp생성
 * 2. al[1~3][0~1] 반복
 * 	2-1. 이진탐색으로 점수가 mScore이고 id가 0인 학생이 들어갈 index찾기
 * 	2-2. 현재 해당 index에 있는 학생을 temp에 저장
 * 3. temp의 사이즈가 0이면 0 반환
 * 4. temp 정렬
 * 5. temp의 첫번째 요소의 id값 반환
 * 
 */


class UserSolution {
	static class Student implements Comparable<Student>{
		int id;
		int score;
		public Student(int id, int score) {
			this.id=id;
			this.score=score;
		}
		
		@Override
		public int compareTo(Student o) {
			if(this.score==o.score) {
				return this.id-o.id;
			}
			return this.score-o.score;
		}
	}
	
	static ArrayList<Student>[][] al;
	static ArrayList<Integer>[][] il;
	
	static int binarySearch1(int grade, int gender, Student now) {
		int leftIdx=0; 
		int rightIdx=al[grade][gender].size()-1;
		while(leftIdx<=rightIdx) {
			int midIdx = (leftIdx+rightIdx)/2;
			//midIdx학생이 now학생보다 왼쪽에 있다면
			if(al[grade][gender].get(midIdx).compareTo(now)<0) {
				leftIdx=midIdx+1;
			}
			//midIdx학생이 now학생보다 오른쪽에 있다면
			else if(al[grade][gender].get(midIdx).compareTo(now)>0) {
				rightIdx=midIdx-1;
			}
		}
		return leftIdx;
	}
	static int binarySearch2(int grade, int gender, int now) {
		int leftIdx=0; 
		int rightIdx=il[grade][gender].size()-1;
		while(leftIdx<=rightIdx) {
			int midIdx = (leftIdx+rightIdx)/2;
			//midIdx의 id값이 now보다 작다면
			if(il[grade][gender].get(midIdx)<now) {
				leftIdx=midIdx+1;
			}
			//midIdx의 id값이 now보다 크다면
			else if(il[grade][gender].get(midIdx)>now) {
				rightIdx=midIdx-1;
			}
			//midIdx의 id값이 now와 같다면
			else if(il[grade][gender].get(midIdx)==now) {
				return midIdx;
			}
		}
		return leftIdx;
	}
	
	
	public void init() {
		al = new ArrayList[4][2];
		il = new ArrayList[4][2];
		for(int grade=1; grade<=3; grade++) {
			for(int gender=0; gender<=1; gender++) {
				al[grade][gender]= new ArrayList<>();
			}
		}
		for(int grade=1; grade<=3; grade++) {
			for(int gender=0; gender<=1; gender++) {
				il[grade][gender]= new ArrayList<>();
			}
		}
	}

	public int add(int mId, int mGrade, char mGender[], int mScore) {
		//1. mGender값들을 합쳐 String변수 temp 생성
		String temp="";
		int gender=-1;
		for(int idx=0; mGender[idx]!='\0'; idx++) {
			temp+=mGender[idx];
		}
		
		if(temp.equals("female")) {
			gender=0;
		}
		else if(temp.equals("male")) {
			gender=1;
		}
		
		//2. 학년과 성별이 일치하는 ArrayList에 학생 객체 넣기
		 //2-1. 이진탐색으로 들어가야하는 index값 찾기
		 //2-2. 해당 index에 학생 객체 삽입
		 //2-3. mid를 il[mGrade][gender]에 이진탐색으로 넣기
		Student now = new Student(mId, mScore);
		al[mGrade][gender].add(binarySearch1(mGrade, gender, now), now);
		il[mGrade][gender].add(binarySearch2(mGrade, gender, mId), mId);
		
		
		//테스트코드
		//System.out.println(al[mGrade][gender].get(al[mGrade][gender].size()-1).id);
		
		
		//3. 학년과 성별이 일치하는 ArrayList에서 맨 오른쪽 요소의 id 반환
		return al[mGrade][gender].get(al[mGrade][gender].size()-1).id;
	}

	public int remove(int mId) {
		//1. 각 학년, 각 성별 arrayList를 하나씩 이분탐색하며 삭제할 targetId를 찾는다.
		// 1-1. al[1~3][0~1]반복
		// 1-2. 이진탐색함수를 돌린 후 해당 결과로 얻은 인덱스 값이 targetId인지 확인.
		for(int grade=1; grade<=3; grade++) {
			for(int gender=0; gender<=1; gender++) {
				int tempIdx=binarySearch2(grade, gender, mId);
				//테스트코드
//				System.out.println("il에 존재하는 id들 확인");
//				for(int idx=0; idx<il[grade][gender].size(); idx++) {
//					System.out.print(il[grade][gender].get(idx)+" ");
//				}
//				System.out.println();
//				System.out.println("tempIdx: "+tempIdx);
				//2-0. tempIdx가 특정 arrayList에는 없는 인덱스가 주어진다면
				if(tempIdx>=il[grade][gender].size()) {continue;}
				//2. 특정 arrayList에 targetId가 있다면, al과 il에서 해당 학생을 삭제한다.
				if(il[grade][gender].get(tempIdx)==mId) {
					//al에서 해당 학생 삭제
					for(int idx=0; idx<al[grade][gender].size(); idx++) {
						if(al[grade][gender].get(idx).id==mId) {
							al[grade][gender].remove(idx);
						}
					}
					//il에서 해당 mid값 삭제
					il[grade][gender].remove(tempIdx);
					
				 	//2-1. 삭제 후, 해당 arrayList의 첫번째 요소의 id값을 반환한다.
					if(al[grade][gender].size()>0) {
						//테스트코드
						//System.out.println(al[grade][gender].get(0).id);
						return al[grade][gender].get(0).id;
					}
					//2-2. 삭제 후, 해당 arrayList의 size가 0이라면 0을 반환한다.
					else {
						//테스트코드
						//System.out.println(0);
						return 0;
					}
					
				}
			}
		}
		//테스트코드
		//System.out.println(0);
		//3. 모든 arrayList에 targetId가 없다면, 0 반환 
		return 0;
	}
	
	public int GendertoInt(char[] mGender) {
		//테스트코드
//		for(int idx=0; idx<mGender.length; idx++) {
//			System.out.println(idx+": "+mGender[idx]);
//		}
//		System.out.println("");
		
		String result = "";
		for(int idx=0; mGender[idx]!='\0'; idx++) {
			result+=mGender[idx];
		}
		//테스트코드
//		System.out.println("result: "+result);
		if(result.equals("female")) {
			return 0;
		}
		else if(result.equals("male")) {
			return 1;
		}
		return -1;
	}
	
	
	public int query(int mGradeCnt, int mGrade[], int mGenderCnt, char mGender[][], int mScore) {
		//1. 각 arrayList에서 조건에 맞는 학생들을 뽑아서 저장할 arrayList temp생성
		ArrayList<Student> temp = new ArrayList<>();
		Student now = new Student(0, mScore);
		//2. 주어진 학년 집합과 성별 집합에 속하는 경우만 확인하기
		for(int idx=0; idx<mGradeCnt; idx++) {
			int grade=mGrade[idx];
			for(int idx2=0; idx2<mGenderCnt; idx2++) {
				int gender=GendertoInt(mGender[idx2]);
				//2-1. 이진탐색으로 점수가 mScore이고 id가 0인 학생이 들어갈 index찾기
				//테스트코드
//				System.out.println("grade: "+grade);
//				System.out.println("gender: "+gender);
//				System.out.println("id: "+now.id);
//				System.out.println("score: "+now.score);
				int tempIdx=binarySearch1(grade, gender, now);
				//2-2. 찾은 index에 현재 mScore이상의 점수를 가진 학생이 없다면, 넘어가기
				if(al[grade][gender].size()==tempIdx) {
					continue;
				}
				//2-2. 찾은 index에 현재 mScore이상의 점수를 가진 학생이 있다면
				temp.add(al[grade][gender].get(tempIdx));
			}
		}
		
		//3. temp의 사이즈가 0이면 0반환
		if(temp.size()==0) {
			return 0;
		}
		//4. temp 정렬
		Collections.sort(temp);
		
		//테스트코드
		//System.out.println(temp.get(0).id);
		
		//5. temp의 첫번째 요소의 id값 반환
		return temp.get(0).id;
	}
}