package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * inversion counting
 * 세그먼트 트리로 해결
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
 *    3-2. segmentTree 업데이트
 * 4. 정답 출력
 */
public class s_1517_버블소트_YongSoo {
    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static Node[] arr;
    static int[] segmentTree;
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

        // 세그먼트 트리 초기화
        int n = (int) Math.ceil(Math.log(N) / Math.log(2));

        int treeSize = (1 << (n+1));
        segmentTree = new int[treeSize];


        // 3. 큰 수부터, inversion counting 시작
        for (int sortedIdx=N-1; sortedIdx>=0; sortedIdx--) {

            Node node = arr[sortedIdx];
            int idx = node.idx;

            // 3-1. 자신 왼쪽에 있는 자신보다 큰 수의 개수를 구함
            int biggerNum = getBiggerNum(1, 0, idx-1, 0, N-1);
            answer += biggerNum;
            // 3-2. segmentTree 업데이트
            updateBiggerNum(1, idx, 0, N-1);
        }

        System.out.println(answer);
    }

    private static void updateBiggerNum(int nodeIdx, int idx, int start, int end) {
        if (start > idx || end < idx) return;

        segmentTree[nodeIdx]++;

        if (start < end) {
            updateBiggerNum(2*nodeIdx, idx, start, (start+end)/2);
            updateBiggerNum(2*nodeIdx+1, idx, (start+end)/2+1, end);
        }
    }

    // from ~ to 사이의 구간합을 구하는 메서드
    // inversion counting에 사용
    private static int getBiggerNum(int nodeIdx, int from, int to, int start, int end) {
        if (to < start || from > end) return 0;
        if (from <= start && end <= to) return segmentTree[nodeIdx];

        return getBiggerNum(2*nodeIdx, from, to, start, (start+end)/2) +
                getBiggerNum(2*nodeIdx+1, from, to, (start+end)/2+1, end);
    }


    private static void setInitVariable() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());

        arr = new Node[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx=0; idx<N; idx++) {
            int value = Integer.parseInt(st.nextToken());
            arr[idx] = new Node(value, idx);
        }

        answer = 0;
    }
}
