package algorithm_study.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 추가
 * - Arrays.sort() 에서 merge sort 시도
 *  -- 병합 과정에서 정렬된 원소를 담을 임시 배열 mergedList 필요
 * - 결과: 퀵 정렬과 시간초가 4ms 밖에 차이가 안난다..
 *  -- 퀵 정렬: 평균 = nlogn, 최악 = n^2
 *  -- 병합 정렬: 평균 = nlogn, 최악 = nlogn -> 평균, 최악 둘 다 nlogn 을 보장하기 때문에 사용, but 정렬 과정을 저장할 추가적인 메모리가 필요
 *
 * [ init ]
 * 1. N 입력, N 개의 정수 배열 originList에 저장
 * 2. N 개의 정수 배열 오름차순 정렬
 * 3. M 입력, M 개의 정수를 입력받으며 이분탐색
 *
 * [ binarySearch(left, right, target) ]
 * 4. 임의의 mid, left + right / 2 설정
 * 5. originList[mid] 가 현재 탐색 중인 수와 같다면 1 출력 후 종료
 *  5-1. originList[mid] 가 더 작다면 left 를 mid + 1 로 재귀 호출
 *  5-2. originList[mid] 가 더 크다면 right 를 mid - 1 로 재귀 호출
 * 6. left > right 일 때 종료
 */
public class s1920_수찾기_SangHu {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static int[] originList;

    static int[] mergedList;

    // Bottom-Up 방식 구현
    private static void divideOriginList(int right) {

        /*
         * 1 - 2 - 4 - 8 - ... 식으로 1부터 서브리스트를 나누는 기준을 두 배씩 늘리며 진행
         */
        for(int size = 1; size <= right; size += size) {
            /*
             * [ 두 부분리스트를 순서대로 병합 작업 ]
             * 현재 부분리스트의 크기가 1(size=1)일 때
             * 왼쪽 부분리스트(low ~ mid)와 오른쪽 부분리스트(mid + 1 ~ high)를 설정하면
             * 왼쪽 부분리스트는 low = mid = 0 이고,
             * 오른쪽 부분리스트는 mid + 1부터 low + (2 * size) - 1 = 1 이 된다.
             *
             * - high가 배열의 인덱스를 넘어갈 수 있으므로 right와 둘 중 작은 값이 병합되도록 한다.
             */
            for(int low = 0; low <= right - size; low += (2 * size)) {
                int mid = low + size - 1;
                int high = Math.min(low + (2 * size) - 1, right);
                merge(low, high, mid);	// 병합작업
            }
        }

    }

    /**
     * 합칠 부분리스트는 originList 의 left ~ right
     *
     * @param startLeft	배열의 시작점
     * @param endRight	배열의 끝 점
     * @param mid	배열의 중간점
     */
    private static void merge(int startLeft, int endRight, int mid) {
        int left = startLeft; // 왼쪽 부분리스트 시작점
        int right = mid + 1; // 오른쪽 부분리스트의 시작점
        int traget = startLeft; // 채워넣을 배열의 인덱스


        while(left <= mid && right <= endRight) {
            // 왼쪽 부분리스트 left 번째 원소가 오른쪽 부분리스트 right 번째 원소보다 작거나 같을 경우
            if(originList[left] <= originList[right]) {
                // 왼쪽의 left 번째 원소를 새 배열에 넣고
                mergedList[traget] = originList[left];
                // left 와 traget 1 증가
                left++;
                traget++;
            } // 오른쪽 부분리스트 right 번째 원소가 왼쪽 부분리스트 left 번째 원소보다 작거나 같을 경우
            else {
                // 오른쪽의 right 번째 원소를 새 배열에 넣고
                mergedList[traget] = originList[right];
                //  right 와 target 1 증가
                right++;
                traget++;
            }
        }

        /*
         * 왼쪽 부분리스트가 먼저 모두 새 배열에 채워졌을 경우 (left > mid)
         * = 오른쪽 부분리스트 원소가 아직 남아있을 경우
         */
        if(left > mid) {
            // 오른쪽 부분리스트의 나머지 원소들을 새 배열에 채워준다.
            while(right <= endRight) {
                mergedList[traget] = originList[right];
                traget++;
                right++;
            }
        }
        /*
         * 오른쪽 부분리스트가 먼저 모두 새 배열에 채워졌을 경우 (right > endRight)
         * = 왼쪽 부분리스트 원소가 아직 남아있을 경우
         */
        else {
            // 왼쪽 부분리스트의 나머지 원소들을 새 배열에 채워준다.
            while(left <= mid) {
                mergedList[traget] = originList[left];
                traget++;
                left++;
            }
        }

        // 정렬된 새 배열을 기존의 배열에 복사
        for(int idx = startLeft; idx <= endRight; idx++) {
            originList[idx] = mergedList[idx];
        }
    }

    public static void binarySearch(int left, int right, int target) {
        if (left > right) {
            sb.append(0).append("\n");
            return;
        }

        int mid = (left + right) >> 1;

        if (originList[mid] < target)
            binarySearch(mid + 1, right, target);
        else if (originList[mid] > target)
            binarySearch(left, mid - 1, target);
        else
            sb.append(1).append("\n");
    }

    public static void main(String[] args) throws IOException {
        init();

        System.out.println(sb);
    }

    public static void init() throws IOException {
        N = Integer.parseInt(br.readLine().trim());

        originList = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < N; idx++) {
            originList[idx] = Integer.parseInt(st.nextToken());
        }

        // Arrays.sort(originList)

        // 정렬된 원소를 저장할 임시 배열
        mergedList = new int[N];
        // Bottom-up 방식의 분할 시작
        divideOriginList(N-1);

        M = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < M; idx++) {
            binarySearch(0, originList.length-1, Integer.parseInt(st.nextToken()));
        }
    }
}