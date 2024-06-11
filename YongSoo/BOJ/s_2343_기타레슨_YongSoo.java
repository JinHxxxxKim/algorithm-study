package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 이분 탐색 연습 문제
 * 이분탐색 종료 조건과, 탐색 결과에 따른 start / end 조정에 다소 유의를 요함
 */

public class s_2343_기타레슨_YongSoo {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[] lessons;
    static int answer;

    public static void main(String[] args) throws IOException {

        // 1. 변수 초기화
        setInitVariable();

        // 2. 이분 탐색을 이용한 answer 업데이트
        solve();

        // 3. 정답 출력
        System.out.println(answer);
    }

    // 이분 탐색 알고리즘 사용
    // start : 불가능한 블루레이 크기, end : 가능한 블루레이 크기로 고정
    // start와 end 사이에 공간이 없다면(start +1 = end), end가 답이 된다
    private static void solve() {
        // 2-1. lower bound, upper bound 설정
        int start = 0;
        int end = N  * 10000;   // 각 강의의 최대 길이는 10000분

        // 2-2. 이분 탐색 수행
        while (start + 1 < end) {
            int mid = (start + end) >> 1;

            // start : 불가능, end : 가능으로 설정했음에 유의
            // 2-2-1. mid가 가능한 경우 : end = mid
            if (possible(mid)) {
                end = mid;
            }
            // 2-2-2. mid가 불가능한 경우 : start = mid
            else {
                start = mid;
            }
        }

        // 2-3. 반복문 수행 후 answer 업데이트
        answer = end;
    }

    // {size} 크기의 블루레이 M개로, 모든 기타 강의 영상을 녹화할 수 있는지 여부를 반환
    // 앞에서부터 하나하나 영상을 담아 본다
    private static boolean possible(int size) {

        // 현재까지 사용한 블루레이의 개수
        int cnt = 0;
        // 현재까지 녹화한 영상의 index
        int idx = -1;


        while (cnt <= M && ++idx < N) {
            cnt++;

            // {cnt}번째 블루레이에, 담을 수 있는 만큼 영상을 담는다
            int lessonSize = lessons[idx];

            if (lessonSize > size) {
                cnt = M+1;
                break;
            }

            while (idx+1 < N && lessonSize + lessons[idx+1] <= size) {
                lessonSize += lessons[++idx];
            }
        }

        // 반복문을 종료했을 때, cnt <= M이면 영상을 모두 담을 수 있는 것
        return cnt <= M;
    }

    private static void setInitVariable() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lessons = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx=0; idx<N; idx++) {
            lessons[idx] = Integer.parseInt(st.nextToken());
        }

        answer = 0;
    }


}