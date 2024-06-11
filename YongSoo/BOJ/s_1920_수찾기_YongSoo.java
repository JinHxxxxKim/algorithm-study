package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 정렬 + 이분탐색
 */
public class s_1920_수찾기_YongSoo {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        // 1. 변수 초기화
        setInitVariable();

        // 2. 배열을 정렬
        Arrays.sort(arr);

        // 3. M개의 수 각각에 대해, 이 수들이 arr 안에 존재하는지 여부를 저장
        M = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());

        for (int cnt=0; cnt<M; cnt++) {
            int num = Integer.parseInt(st.nextToken());
            int result = isInArr(num);
            sb.append(result).append("\n");
        }

        // 4. 정답 출력
        System.out.println(sb);
    }

    // num이 arr에 들어 있는지 여부를 반환하는 함수
    // 들어 있으면 1, 아니면 0을 반환
    // 이분 탐색을 활용
    private static int isInArr(int num) {
        int start = 0;
        int end = N-1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (arr[mid] == num) return 1;
            else if (arr[mid] < num) start = mid + 1;
            else end = mid - 1;
        }

        return 0;
    }


    private static void setInitVariable() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());

        arr = new int[N];
        for (int idx=0; idx<N; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }
    }
}
