package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 이분 탐색 이용
 * N <= 10^5이므로, N^2 <= 10^10이라 int로 연산할 시 오버플로가 난다!
 * long으로 변환해 주자
 *
 * 1. 변수 초기화
 * 2. 이분 탐색을 이용해 B[k]를 찾는다
 *    2-1. 탐색 범위를 start = 1, end = N^2으로 설정
 *    2-2. 중간값 mid = (start+end)/2에 대해, A에서 mid 이하인 수의 개수(belowNum)와 mid가 A에 속하는지 여부(isInArray)를 각각 계산한다.
 *         2-2-1. belowNum < k인 경우 : B[k]는 mid보다 크다. 따라서 mid+1 ~ end까지의 범위를 재탐색한다.
 *         2-2-2. belowNum >= k, isInArray = true인 경우 : mid도 답이 될 수 있다. start ~ mid까지의 범위를 탐색한다.
 *         2-2-3. belowNum >= k, isInArray = false인 경우 : mid는 답이 될 수 없다. start ~ (mid-1)까지의 범위를 탐색한다.
 *    2-3. 2-2 과정을 start == end일 때까지 반복한다.
 * 3. 정답을 출력
 */

public class s_1300_k번째수_YongSoo {
    static BufferedReader br;
    static long N;
    static long k;

    static class Result {
        long belowNum;
        boolean isInArray;

        public Result(long belowNum, boolean isInArray) {
            this.belowNum = belowNum;
            this.isInArray = isInArray;
        }
    }

    public static void main(String[] args) throws IOException {
        // 1. 변수 초기화
        setInitVariable();

        // 2. 이분 탐색
        // 2-1. 초기 start =1, end = N^2로 두고 탐색
        long answer = binarySearch(0, N*N);

        // 3. 정답을 출력
        System.out.println(answer);
    }

    private static long binarySearch(long start, long end) {
        // 종료 조건 : start == end인 경우
        if (start == end) return start;

        // 2-2. 중간값 mid = (start+end)/2에 대해, A에서 mid 이하인 수의 개수(belowNum)와 mid가 A에 속하는지 여부(isInArray)를 각각 계산한다.
        long mid = (start + end) / 2;
        Result result = getResultFrom(mid);

        // 2-2-1. mid 이하의 수가 k개 미만인 경우
        // k번째 수는 mid보다 더 크다
        if (result.belowNum < k) {
            return binarySearch(mid+1, end);
        }
        // 2-2-2. mid 이하의 수가 N개 이상이고, mid가 A 안에 있을 경우
        // start ~ mid 내에 정답이 있다
        else if (result.isInArray) {
            return binarySearch(start, mid);
        }
        // 2-2-3. mid가 A 안에 없을 경우
        // start ~ (mid-1) 내에 정답이 있다
        else {
            return binarySearch(start, mid-1);
        }
    }

    // 배열 A에서, num 이하의 수가 몇개 있는지(belowNum), 또한 num이 배열 안에 들어가 있는지 여부(isInArray)를 계산해 반환
    // 반환해야 하는 값이 2개이므로 객체로 만들어 반환
    private static Result getResultFrom(long num) {
        long belowNum = 0;
        boolean isInArray = false;

        for (int row=1; row<=N; row++) {
            // A[row]에 num 이하의 수가 몇 개 있는지를 계산
            belowNum += Math.min(N, num/row);

            // A[row]에 num이 있는 경우
            if (num%row == 0 && num/row <= N) {
                isInArray = true;
            }
        }

        return new Result(belowNum, isInArray);
    }


    private static void setInitVariable() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        k = Integer.parseInt(br.readLine().trim());
    }
}