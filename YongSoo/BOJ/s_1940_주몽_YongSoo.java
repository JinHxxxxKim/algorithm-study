package BOJ;

/**
 * 투 포인터 알고리즘 사용

 * 1. 변수 초기화
 * 2. 배열 정렬
 * 3. 투 포인터 알고리즘 수행
 *    3-1. numbers[i] + numbers[j] > M일 경우 : 합을 낮춰 주어야 한다(j--)
 *    3-2. numbers[i] + numbers[j] < M일 경우 : 합을 올려 주어야 한다(i++)
 *    3-3. numbers[i] + numbers[j] == M일 경우 : 해당 두 재료로 갑옷을 만든다.
 *         재료는 재사용을 하지 못하므로, i++, j--를 해 준다
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class s_1940_주몽_YongSoo {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int M;
    static int[] numbers;

    public static void main(String[] args) throws IOException {

        // 1. 주어진 변수 초기화
        setInitVariable();

        int i = 0;
        int j = N-1;
        int cnt = 0;

        // 2. 배열 정렬
        Arrays.sort(numbers);

        // 3. 투 포인터 알고리즘 수행
        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum > M) {
                j--;
            }
            else if (sum < M) {
                i++;
            }
            else {
                i++;
                j--;
                cnt++;
            }
        }

        System.out.println(cnt);
    }



    private static void setInitVariable() throws IOException {
        N = Integer.parseInt(br.readLine().trim());
        M = Integer.parseInt(br.readLine().trim());

        numbers = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
    }
}