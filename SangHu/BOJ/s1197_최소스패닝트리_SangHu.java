package algorithm_study.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [ inputTestCase() ]
 * 1. 정점의 개수, 간선의 개수를 입력받는다.
 * 2. 간선의 정보를 입력받는다.
 *  2-1. from, to, weight 를 저장하는 Edge 클래스
 *
 * [ make() ]
 * 3. 각 정점의 부모로 자신을 가르키는 parents 배열 생성
 *
 * [ main() ]
 * 4. 간선의 가중치를 기준으로 오름차순 정렬
 * 5. 간선 리스트에서 간선을 하나씩 빼며 크루스칼 수행
 *  5-1. from, to 정점 간 union 수행
 *      5-1-1. true 라면 싸이클을 생성하지 않고 선택할 수 있으므로 간선 선택
 *      5-1-2. 선택된 간선의 weight 누적
 *      5-1-3. 선택한 간선 카운팅
 *  5-2. 선택한 간선이 정점-1 개이면 종료
 * 6. 누적 weight 출력
 */
public class s1197_최소스패닝트리_SangHu {
    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        // 가중치를 기준 오름차순 정렬을 위한 compareTo 오버라이드
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int nodeCount, edgeCount;
    static Edge[] edgeList;

    static int mstNodeCount, weightSum;
    static int[] parents;

    public static boolean union(int from, int to) {
        int fromParent = find(from);
        int toParent = find(to);

        // 부모가 같다면 싸이클을 선택 시 싸이클이 생성되므로
        if (fromParent == toParent)
            return false;

        // 더 큰 쪽에 병합
        if (fromParent > toParent)
            parents[toParent] = fromParent;
        else
            parents[fromParent] = toParent;

        return true;
    }

    public static int find(int nodeIdx) {
        // 자기 자신이 부모이면 루트 노드이므로
        if (parents[nodeIdx] == nodeIdx) {
            return nodeIdx;
        }

        // 경로 압축
        return parents[nodeIdx] = find(parents[nodeIdx]);
    }

    public static void make() {
        parents = new int[nodeCount+1];

        for (int idx = 0; idx < nodeCount+1; idx++) {
            parents[idx] = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        inputTestCase();

        // 4. 간선의 가중치를 기준으로 오름차순 정렬
        Arrays.sort(edgeList);

        // 3. 각 정점의 부모로 자신을 가르키는 parents 배열 생성
        make();

        // 5. 간선 리스트에서 간선을 하나씩 빼며 크루스칼 수행
        mstNodeCount = 0;
        weightSum = 0;
        for (Edge edge : edgeList) {
            int from = edge.from;
            int to = edge.to;
            int weight = edge.weight;

            // 5-1-1. true 라면 싸이클을 생성하지 않고 선택할 수 있으므로 간선 선택
            if (union(from, to)) {
                // 5-1-2. 선택된 간선의 weight 누적
                weightSum += weight;
                // 5-1-3. 선택한 간선 카운팅
                mstNodeCount++;
            }

            // 5-2. 선택한 간선이 정점-1 개이면 종료
            if (mstNodeCount == (nodeCount-1))
                break;
        }

        // 6. 누적 weight 출력
        System.out.println(weightSum);
    }

    public static void inputTestCase() throws IOException {
        // 1. 정점의 개수, 간선의 개수를 입력받는다.
        st = new StringTokenizer(br.readLine().trim());
        nodeCount = Integer.parseInt(st.nextToken());
        edgeCount = Integer.parseInt(st.nextToken());

        // 2. 간선의 정보를 입력받는다.
        edgeList = new Edge[edgeCount];
        for (int idx = 0; idx < edgeCount; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edgeList[idx] = new Edge(from, to, weight);
        }
    }
}