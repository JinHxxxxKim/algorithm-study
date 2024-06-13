package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [버블 소트] - BOJ
 * <p>
 * merge sort
 * <p>
 * 분할된 오른쪽 배열원소 > 분할된 왼쪽 배열원소 => 남아있는 왼쪽 배열 원소 개수만큼 SWAP 해야한다.
 */
public class s1517_버블소트_JinHyung {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;
    static int[] srcArr;
    static int[] destArr;
    static long swapCnt;

    public static void main(String[] args) throws Exception {
        N = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());
        srcArr = new int[N];
        destArr = new int[N];

        // 배열 입력
        for (int i = 0; i < N; i++) {
            srcArr[i] = Integer.parseInt(st.nextToken());
        }

        swapCnt = 0;
        // 병합정렬 시작
        mergeSort(0, N - 1);
//        System.out.println("Arrays.toString(destArr) = " + Arrays.toString(destArr));
        System.out.println(swapCnt);
    }

    private static void mergeSort(int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;

        // divide
        mergeSort(left, mid);
        mergeSort(mid + 1, right);

        // conquer
        merge(left, mid, right);
    }

    private static void merge(int left, int mid, int right) {
        int destIdx = left;
        int tempLeft = left;
        int tempRight = mid + 1;

        while (tempLeft <= mid && tempRight <= right) {
            // 왼쪽 오른쪽 원소의 크기가 동일할 때는 SWAP 안하도록 동등연산자 추가
            if (srcArr[tempLeft] <= srcArr[tempRight]) {
                destArr[destIdx] = srcArr[tempLeft];
                ++tempLeft;
            } else {
                destArr[destIdx] = srcArr[tempRight];
                ++tempRight;
                
                // SWAP Count
                swapCnt += (mid - tempLeft + 1);
            }
            ++destIdx;
        }

        while (tempLeft <= mid) {
            destArr[destIdx] = srcArr[tempLeft];
            ++tempLeft;
            ++destIdx;
        }
        while (tempRight <= right) {
            destArr[destIdx] = srcArr[tempRight];
            ++tempRight;
            ++destIdx;
        }
        for (int idx = left; idx <= right; ++idx) {
            srcArr[idx] = destArr[idx];
        }
    }
}
