package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * inversion counting
 * 펜윅 트리로 해결
 * (세그먼트 트리와 펜윅 트리 두 가지 방법이 있으나, 펜윅 트리가 조금 더 간편한 듯)
 *
 * 버블 소트에서, (자신보다 큰 숫자와 swap되는 횟수) = (자신 왼쪽에 있는 자신보다 큰 숫자의 개수)이다.
 * 각 수열의 원소에 대해 자신보다 큰 숫자와 swap되는 횟수를 더해 주면 전체 swap 횟수를 얻을 수 있으므로,
 * 각 수열의 원소에 대해 자신 왼쪽에 있는 자신보다 큰 숫자의 개수를 구해 주면 문제를 해결 가능하다.
 *
 * 1. 변수 초기화
 *    - arr의 경우, value와 node를 가진 객체의 배열로 선언(정렬 이후에도 해당 원소의 원래 위치를 알 수 있게 하기 위함)
 * 2. 배열 정렬
 * 3. 큰 수부터, inversion counting 시작
 *    3-1. 자신 왼쪽에 있는 자신보다 큰 수의 개수를 구함
 *    3-2. fenwickTree 업데이트
 * 4. 정답 출력
 */
public class s_1517_버블소트_YongSoo {
    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static Node[] arr;
    static int[] fenwickTree;
    static long answer;
    static class Node {
        int value;
        int idx;

        public Node(int value, int idx) {
            this.value = value;
            this.idx = idx;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", idx=" + idx +
                    '}';
        }
    }
    public static void main(String[] args) throws IOException {
        // 1. 변수 초기화
        setInitVariable();

        // 2. 배열 정렬
        Arrays.sort(arr, (n1, n2) -> {
            // 동일한 값이 있을 경우, 원래 배열의 순서를 유지시킴(안정 정렬)
            if (n1.value == n2.value) {
                return Integer.compare(n1.idx, n2.idx);
            }

            return Integer.compare(n1.value, n2.value);
        });

        // 펜윅 트리 초기화
        fenwickTree = new int[N+1];


        // 3. 큰 수부터, inversion counting 시작
        for (int sortedIdx=N-1; sortedIdx>=0; sortedIdx--) {
            Node node = arr[sortedIdx];
            int idx = node.idx;

            // 3-1. 자신 왼쪽에 있는 자신보다 큰 수의 개수를 구함
            int biggerNum = getBiggerNum(idx);
            answer += biggerNum;
            // 3-2. fenwickTree 업데이트
            updateBiggerNum(idx);
        }

        System.out.println(answer);
    }

    private static void updateBiggerNum(int idx) {
        int cnt = 0;
        while (idx < fenwickTree.length) {
            fenwickTree[idx]++;
            idx += ((idx) & (-idx));

            if (++cnt == 20) break;
        }
    }

    private static int getBiggerNum(int idx) {
        int biggerNum = 0;
        while (idx > 0) {
            biggerNum += fenwickTree[idx];
            idx -= ((idx) & (-idx));
        }
        return biggerNum;
    }

    private static void setInitVariable() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());

        arr = new Node[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx=0; idx<N; idx++) {
            int value = Integer.parseInt(st.nextToken());
            arr[idx] = new Node(value, idx+1);
        }

        answer = 0;
    }
}
