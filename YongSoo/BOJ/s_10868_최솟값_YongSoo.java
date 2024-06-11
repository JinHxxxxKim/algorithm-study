package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

/**
 * 세그먼트 트리 연습 문제
 * Tree의 자료형을 long으로 선언해야 함에 유의
 */

public class s_10868_최솟값_YongSoo {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static long[] numbers;
    static SegmentTree segmentTree;
    static class SegmentTree {
        long[] tree;

        public SegmentTree(int arraySize) {
            int pow = (int) Math.ceil(Math.log(arraySize) / Math.log(2));
            int treeSize = 1 << (pow+1);
            tree = new long[treeSize];
            Arrays.fill(tree, Long.MAX_VALUE);
        }


        public void update(int nodeIdx, int start, int end, int idx, long number) {
            if (start > idx || end < idx) return;
            tree[nodeIdx] = Math.min(tree[nodeIdx], number);

            if (start < end) {
                int mid = (start + end) >> 1;
                update(nodeIdx << 1, start, mid, idx, number);
                update((nodeIdx << 1) + 1, mid+1, end, idx, number);
            }
        }

        public long query(int nodeIdx, int start, int end, int from, int to) {
            if (start > to || end < from) return Long.MAX_VALUE;
            if (from <= start && end <= to) return tree[nodeIdx];

            int mid = (start + end) >> 1;
            return Math.min(query(nodeIdx << 1, start, mid, from, to),
                    query((nodeIdx << 1) + 1, mid+1, end, from, to));
        }
    }

    public static void main(String[] args) throws IOException {
        setInitVariable();

        segmentTree = new SegmentTree(numbers.length);

        for (int idx=0; idx< numbers.length; idx++) {
            segmentTree.update(1, 0, numbers.length-1, idx, numbers[idx]);
        }

        for (int cnt=0; cnt<M; cnt++) {
            st = new StringTokenizer(br.readLine().trim());

            // 여기서는 0~(N-1)의 인덱스를 가지는 것으로 생각
            // 문제에서 주어진 input에서 각각 1을 빼 준다
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;

            long minValue = segmentTree.query(1, 0, numbers.length-1, a, b);
            sb.append(minValue).append("\n");
        }

        System.out.println(sb);
    }

    private static void setInitVariable() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new long[N];
        for (int idx=0; idx<N; idx++) {
            numbers[idx] = Long.parseLong(br.readLine().trim());
        }
    }


}