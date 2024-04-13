package algorithm_study.BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [ inputTestCase() ]
 * 1. 정점의 개수와 간선의 개수를 입력받는다.
 * 2. 시작 정점의 번호를 입력받는다.
 * 3. 방문한 정점의 가중치 최솟값을 저장할 배열을 생성한다.
 *  3-1. MAX_VALUE 로 초기화
 *  3-2. 정점 리스트 초기화
 * 4. 간선의 개수 만큼 간선의 정보를 입력받는다.
 *
 * [ connectDijkstra() ]
 * 5. 다익스트라 알고리즘을 통한 정점 중심 그래프 탐색
 *  5-1. 시작 정점의 최소 가중치 0 으로 업데이트
 *  5-2. 현재 정점에서 연결된 간선을 따라 탐색
 *      5-2-1. 방문하지 않은 정점이면서 현재 최소 가중치보다 (현재 가중치 + 해당 정점으로의 가중치 값)이 더 작다면
 *          5-2-1-1. 해당 정점으로의 최소 가중치 업데이트
 *          5-2-1-2. 다음 방문을 위한 큐에 삽입
 *
 */
public class s1753_최단경로_박상후 {
    static class Node implements Comparable<Node> {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int nodeCount, edgeCount;
    static int startNode;
    static List<List<Node>> nodeList;
    static int[] minWeightList;

    static PriorityQueue<Node> dijkstraQ;

    public static void connectDijstra(int startNode) {
        dijkstraQ = new PriorityQueue<>();
        boolean[] visited = new boolean[nodeCount + 1];

        // 5-1. 시작 정점의 최소 가중치 0 으로 업데이트
        minWeightList[startNode] = 0;
        dijkstraQ.add(new Node(startNode, 0));
        visited[startNode] = true;

        while (!dijkstraQ.isEmpty()) {
            Node curNode = dijkstraQ.poll();

            // 현재 정점의 다음 정점에 연결된 정점들
            for (Node next : nodeList.get(curNode.to)) {

                // 5-2-1. 방문하지 않은 정점이면서 현재 최소 가중치보다 (현재 가중치 + 해당 정점으로의 가중치 값)이 더 작다면
                if (!visited[next.to] && curNode.weight + next.weight < minWeightList[next.to]) {
                    // 5-2-1-1. 해당 정점으로의 최소 가중치 업데이트
                    minWeightList[next.to] = curNode.weight + next.weight;

                    // 5-2-1-2. 다음 방문을 위한 큐에 삽입
                    dijkstraQ.add(new Node(next.to, minWeightList[next.to]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputTestCase();

        connectDijstra(startNode);

        for (int weightIdx = 1; weightIdx < nodeCount+1; weightIdx++) {
            if (minWeightList[weightIdx] == Integer.MAX_VALUE) {
                sb.append("INF").append("\n");
            } else {
                sb.append(minWeightList[weightIdx]).append("\n");
            }
        }

        System.out.println(sb);
    }

    public static void inputTestCase() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        nodeCount = Integer.parseInt(st.nextToken());
        minWeightList = new int[nodeCount+1];
        edgeCount = Integer.parseInt(st.nextToken());

        startNode = Integer.parseInt(br.readLine().trim());
        nodeList = new ArrayList<>();
        for (int nodeIdx = 0; nodeIdx < nodeCount+1; nodeIdx++) {
            minWeightList[nodeIdx] = Integer.MAX_VALUE;
            nodeList.add(new ArrayList<>());
        }

        for (int idx = 0; idx < edgeCount; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            nodeList.get(from).add(new Node(to, weight));
        }
    }
}