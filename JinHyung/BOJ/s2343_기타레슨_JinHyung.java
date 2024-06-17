package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [기타레슨] - BOJ
 * 이진탐색
 *  N (1 ≤ N ≤ 100,000)과 M (1 ≤ M ≤ N)
 *  각 강의 시간은 10000분 이하
 *
 * 블루레이 크기의 "최솟값"을 찾는 것이 목표
 * => 블루레이 크기: 이진탐색의 대상
 * => 초기 left 값: record의 길이 중 최대값, right 값: 강의 길이의 합
 *
 */
public class s2343_기타레슨_JinHyung {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static int[] records;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        records = new int[N];

        int left = 0; // 초기 블루레이 하한값
        int right = 0; // 초기 블루레이 상한값
        
        // 레코드 길이 입력
        st = new StringTokenizer(br.readLine().trim());
        for(int idx = 0; idx < N; idx++) {
            records[idx] = Integer.parseInt(st.nextToken());
            right += records[idx]; // record 중 최대 값 (블루레이 크기가 작을 경우, 해당 record 는 저장할 수 없음)
            left = Math.max(left, records[idx]); // 모든 record 합 
        }

        int ans = Integer.MAX_VALUE;
        // 이진탐색 시작
        while (left <= right) {
            int mid = (left + right) / 2; // 확인하고자 하는 블루레이 크기
            // 해당 블루레이 크기에 모든 강의가 담길 수 있는지 확인
            int reqDisk = getDisckCount(mid);

            if (reqDisk > M) {
                // 필요 블루레이 개수보다 많이 사용한다 -> 블루레이 크기를 늘릴필요가 있다.
                left = mid + 1;
            } else {
                // 필요 블루레이 개수보다 적거나 같게 사용한다 -> 블루레이 크기를 줄일 필요가 있다.
                right = mid - 1;
                ans = Math.min(ans, mid);
            }

        }
        System.out.println(ans);
    }

    // 해당 disk 사이즈에 대해 요구되는 블루레이 개수 반환
    private static int getDisckCount(long diskSize) {
        int diskCnt = 0;
        int currDiskStatus = 0; // 현재 블루레이에 대해 사용한 용량(size) 카운팅

        for (int record : records) {
            if(record + currDiskStatus > diskSize) {
                currDiskStatus = 0;
                ++diskCnt;
            }
            currDiskStatus += record;
        }
        if (currDiskStatus != 0) {
            ++diskCnt;
        }
        return diskCnt;
    }
    
}
