package algorithm_study.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ init ]
 * 1. 강의 수, 블루레이로 자를 수 입력
 * 2. 각 강의 시간 입력
 *  2-1. 최소 크기는 강의 시간의 최댓값보다 작을 수 없으므로 최댓값 저장
 *  2-2. 블루레이 수가 1이면 총합이므로 최대 크기인 총합을 저장하는 timeSum
 *
 * [ binarySearch ]
 * - 가능한 크기는 강의 시간의 최대값 ~ 총합 사이
 * - 해당 범위의 임의 중간값을 설정하고 요소의 합이 중간값보다 작을 때를 계산
 * - start: 강의 최댓값, end: 총합 에서 시작
 * 3. start > end 까지 반복
 *  3-1. 임의 중간값 설정
 *  3-2. 배열을 돌며 누적
 *      3-2-1. 다음 누적이 중간값을 넘으면 블루레이 수 카운팅 후 0 으로 초기화
 *  3-3. 반복이 끝나고 누적합이 0이 아니라면 블루레이가 하나 더 있는 것이므로 카운팅
 *  3-4. 현재 갯수가 자를 수 있는 블루레이 수를 넘었다면 범위 start를 mid+1 로 변경
 *      3-4-1. 작거나 같다면 end를 mid-1하고 다시 반복
 *
 * [ main ]
 * 4. start 출력
 */
public class s2343_기타레슨_SangHu {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int lessonCount, blurayCount;
    static int[] timeList;

    static int start, end;

    public static void binarySearch() {
        // 3. start > end 까지 반복
        while (start <= end) {
            // 3-1. 임의 중간값 설정
            int mid = (start + end) / 2;
            int curCount = 0;
            int curTimeSum = 0;

            // 3-2. 배열을 돌며 누적
            for (int idx = 0; idx < lessonCount; idx++) {
                // 3-2-1. 다음 누적이 중간값을 넘으면 블루레이 수 카운팅 후 0 으로 초기화
                if (curTimeSum + timeList[idx] > mid) {
                    curCount++;
                    curTimeSum = 0;
                }

                curTimeSum += timeList[idx];
            }

            // 3-3. 반복이 끝나고 누적합이 0이 아니라면 블루레이가 하나 더 있는 것이므로 카운팅
            if (curTimeSum != 0) {
                curCount++;
            }

            // 3-4. 현재 갯수가 자를 수 있는 블루레이 수를 넘었다면 범위 start를 mid+1 로 변경
            if (curCount > blurayCount)
                start = mid + 1;
            // 3-4-1. 작거나 같다면 end를 mid-1하고 다시 반복
            else
                end = mid - 1;
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        binarySearch();

        // 4. start 출력
        System.out.println(start);
    }

    public static void init() throws IOException {
        // 1. 강의 수, 블루레이로 자를 수 입력
        st = new StringTokenizer(br.readLine().trim());
        lessonCount = Integer.parseInt(st.nextToken());
        blurayCount = Integer.parseInt(st.nextToken());

        timeList = new int[lessonCount];
        st = new StringTokenizer(br.readLine().trim());
        start = 0; end = 0;
        // 2. 각 강의 시간 입력
        for (int idx = 0; idx < lessonCount; idx++) {
            timeList[idx] = Integer.parseInt(st.nextToken());
            // 2-1. 최소 크기는 강의 시간의 최댓값보다 작을 수 없으므로 최댓값 저장
            start = Math.max(start, timeList[idx]);
            // 2-2. 블루레이 수가 1이면 총합이므로 최대 크기인 총합을 저장하는 timeSum
            end += timeList[idx];
        }
    }
}