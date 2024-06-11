package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 단순 정렬 문제
 * mergeSort를 이용
 */

public class s_2751_수정렬하기2_YongSoo {
    static BufferedReader br;
    static StringBuilder sb;
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        // 1. 변수 초기화
        setInitVariable();

        // 2. 배열 정렬 (merge sort 이용)
        mergeSort(0, arr.length-1);

        // 3. 정렬한 결과를 한 줄에 하나씩 출력
        for (int num: arr) {
            sb.append(num).append("\n");
        }

        System.out.println(sb);
    }

    // arr[start] ~ arr[end]까지의 원소들을 정렬
    private static void mergeSort(int start, int end) {

        // 종료 조건 : 현재 정렬해야 할 배열의 길이가 1인 경우
        if (start == end) return;

        int mid = (start + end) / 2;

        // 배열을 반으로 나누어 각각 정렬한다
        mergeSort(start, mid);
        mergeSort(mid+1, end);

        // 정렬된 두 배열들을 합친다
        merge(start, mid, end);

    }

    // arr[start] ~ arr[mid]로 이루어진 배열과, arr[mid+1] ~ arr[end]로 이루어진 배열을 병합
    private static void merge(int start, int mid, int end) {
        int[] temp = new int[end-start+1];

        int tempIdx = 0;
        int leftIdx = start;
        int rightIdx = mid+1;

        // 왼쪽 배열과 오른쪽 배열의 값을 각각 비교하며, 더 작은 값을 temp에 저장
        // 두 배열 중 적어도 하나가 범위를 벗어날 때까지 반복
        while (leftIdx <= mid && rightIdx <= end) {
            if (arr[leftIdx] <= arr[rightIdx]) {
                temp[tempIdx] = arr[leftIdx];
                leftIdx++;
            }
            else {
                temp[tempIdx] = arr[rightIdx];
                rightIdx++;
            }
            tempIdx++;
        }

        // 왼쪽 배열의 원소가 아직 남아 있는 경우
        if (leftIdx <= mid) {
            while (leftIdx <= mid) {
                temp[tempIdx] = arr[leftIdx];
                leftIdx++;
                tempIdx++;
            }
        }

        // 최적화 1 : 여기서 아래 주석 처리한 코드는 실행할 필요가 없다
        // rightIdx <= end인 경우, arr[rightIdx] ~ arr[end]까지는 이미 올바른 위치에 정렬되어 있음
        // 때문에 굳이 이 부분을 temp에 다시 옮겼다, arr로 옮길 필요가 없다

//        if (rightIdx <= end) {
//            while (rightIdx <= end) {
//                temp[tempIdx] = arr[rightIdx];
//                rightIdx++;
//                tempIdx++;
//            }
//        }

        // temp에 임시로 저장된 값을 arr에 다시 옮겨 준다
        for (int idx=0; idx<tempIdx; idx++) {
            arr[idx+start] = temp[idx];
        }
    }

    private static void setInitVariable() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        sb = new StringBuilder();

        arr = new int[N];
        for (int idx=0; idx<N; idx++) {
            arr[idx] = Integer.parseInt(br.readLine().trim());
        }
    }


}