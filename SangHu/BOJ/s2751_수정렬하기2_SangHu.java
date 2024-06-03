package algorithm_study.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [ init() ]
 * 1. 전체 개수 입력
 * 2. 정렬할 수 리스트에 저장
 * 3. 오름차순 정렬
 *  3-1. Arrays.sort 는 최악의 경우 시간복잡도가 n^2 이므로 Collections.sort 이용
 *
 * [ main ]
 * 4. 정렬한 수 출력
 */
public class s2751_수정렬하기2_SangHu {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int numCount;
    static List<Integer> numArr;

    public static void main(String[] args) throws IOException {
        init();

        // 4. 정렬한 수 출력
        for (int num : numArr) {
            sb.append(num).append('\n');
        }

        System.out.println(sb);
    }

    public static void init() throws IOException {
        // 1. 전체 개수 입력
        numCount = Integer.parseInt(br.readLine().trim());
        numArr = new LinkedList<>();

        // 2. 정렬할 수 리스트에 저장
        for (int idx = 0; idx < numCount; idx++) {
            numArr.add(Integer.parseInt(br.readLine().trim()));
        }

        // 3-1. Arrays.sort 는 최악의 경우 시간복잡도가 n^2 이므로 Collections.sort 이용
        Collections.sort(numArr);
    }
}