package BOJ;

/**
 * 정수 N개가 있을 때, 구간은 N+1개로 나누어진다.
    - 마지막 구간에 포함되는 숫자는, 구간의 개수가 항상 무한대
    - [a, b]구간에 속하는 수 x가 있을 때, x를 포함하는 좋은 구간의 개수는 (x-a) * (b-x) - 1이다.
    - 물론, x == a 혹은 x == b이면 구간의 개수는 0이다.
 * PriorityQueue에 (정수의 값, 해당 정수를 포함하는 좋은 구간의 개수)를 집어넣으며, n개의 수를 꺼내서 출력하면 된다
 * 다만 edge Case가 발생하기 쉬워 주의가 필요

 * EdgeCase
 * 1. 둘째 줄에 포함되는 정수가, 오름차순이라는 보장이 없다...
 * 2. PriorityQueue에 구간의 끝점을 넣고 로직을 수행했는데, 구간의 끝점 외에도 구간의 개수가 0이 되는 수가 존재할 수 있다.
 *    예를 들어, 구간이 [2, 4]일 때, 3을 포함하는 좋은 구간의 개수는 0이다.
 * 3. 로직상 구간 바깥쪽에 있는 수를 탐색하고 안쪽의 수를 추가하는 식으로 구현할텐데,
      이 때 어디까지 추가할지 로직 구현이 필요
      개인적으로 이 풀이보다 HashSet을 이용해 visited를 관리하는 게 훨씬 간편한 듯
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class s_1060_좋은수_YongSoo {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int L;
    static int n;
    static Interval[] intervals;
    static PriorityQueue<Node> pq;
    static class Node {
        int val;
        int intervalNum;
        long cnt;

        public Node(int val, int intervalNum, long cnt) {
            this.val = val;
            this.intervalNum = intervalNum;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", intervalNum=" + intervalNum +
                    ", cnt=" + cnt +
                    '}';
        }
    }
    static class Interval {
        int from;
        int to;
        int mid;
        boolean clear;

        public Interval(int from, int to) {
            this.from = from;
            this.to = to;
            this.mid = (from + to) / 2;
            this.clear = false;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "from=" + from +
                    ", to=" + to +
                    ", mid=" + mid +
                    ", clear=" + clear +
                    '}';
        }
    }


    public static void main(String[] args) throws IOException {

        // 1. 주어진 변수 초기화
        setInitVariable();


        // 2. pq에 넣을 초기 값 설정
        for (int idx=0; idx< intervals.length; idx++) {
            Interval interval = intervals[idx];

            // 마지막 구간은, {구간의 시작점} + 1만 넣어 주면 된다
            if (idx == L) {
                pq.add(new Node(interval.from+1, L, Long.MAX_VALUE));
            }
            else {

                // 구간의 끝점을 삽입
                pq.add(new Node(interval.to, idx, 0));

                // EdgeCase 2: 구간의 끝점 말고도 좋은 구간의 개수가 0인 점 존재할 수 있음
                // 이를 커버하기 위해, 처음에 구간의 가장 바깥쪽 점 2개를 넣고 시작한다
                int startVal = interval.from + 1;

                // 구간이 [6, 7]과 같을 때는 들어가면 안됨
                if (startVal != interval.to) {
                    pq.add(new Node(startVal, idx, getCnt(startVal, interval)));

                    // 반대쪽 점도 추가할 필요가 있는지 확인
                    if (startVal < interval.from + interval.to - startVal) {
                        pq.add(new Node(interval.from + interval.to - startVal, idx, getCnt(startVal, interval)));
                    }
                    else {
                        interval.clear = true;
                    }
                }
            }
        }

        // 3. 상위 N개의 수 꺼내기
        for (int i=0; i<n; i++) {
            Node node = pq.poll();
//            System.out.println("node = " + node);
            sb.append(node.val).append(" ");

            // 마지막 구간에 속해 있을 경우
            if (node.intervalNum == L) {
                pq.add(new Node(node.val+1, L, Long.MAX_VALUE));
            }
            else {
                Interval interval = intervals[node.intervalNum];

                // 현재 구간에 있는 모든 수를 pq에 추가했을 경우 : continue
                if (interval.clear) continue;

                // 현재 값이 interval.mid일 경우 : 더 추가할 필요가 없다
                if (node.val == interval.mid) {
                    interval.clear = true;
                    continue;
                }


                if (node.val > interval.mid && node.val < interval.to) {
                    int nextVal = node.val - 1;

                    // nextVal을 포함하는 좋은 구간의 개수
                    long cnt = getCnt(nextVal, interval);
                    pq.add(new Node(interval.from + interval.to - nextVal, node.intervalNum, cnt));

                    if (nextVal > interval.mid) {
                        pq.add(new Node(nextVal, node.intervalNum, cnt));
                    }
                }
            }



        }

        System.out.println(sb);
    }

    // {val}이 {interVal} 구간에 속해 있을 때, {val}을 포함하는 좋은 구간의 개수를 구한다
    private static long getCnt(int val, Interval interval) {
        return ((long) (val - interval.from)) * (interval.to - val) - 1;
    }

    private static void setInitVariable() throws IOException{
        L = Integer.parseInt(br.readLine().trim());

        int[] numbers = new int[L];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx=0; idx<L; idx++) {
            numbers[idx] = Integer.parseInt(st.nextToken());
        }

        // EdgeCase 1 : numbers를 정렬해 주어야 한다.
        Arrays.sort(numbers);

        // numbers에 의해 분할되는 L+1개의 구간들
        intervals = new Interval[L+1];
        int from = 0;
        for (int idx=0; idx<L; idx++) {
            intervals[idx] = new Interval(from, numbers[idx]);
            from = numbers[idx];
        }

        // 마지막 구간은 따로 처리
        intervals[L] = new Interval(from, Integer.MAX_VALUE);

        n = Integer.parseInt(br.readLine().trim());
        pq = new PriorityQueue<>((n1, n2) -> {
            if (n1.cnt == n2.cnt) return Integer.compare(n1.val, n2.val);
            return Long.compare(n1.cnt, n2.cnt);
        });
    }

}