import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 1. 두 정점을 연결하는 간선에 대한 정보가 여러 개 주어질 때, Min spanning tree의 가중치를 구한다.
 * 2. 단, 그래프는 무향이며 간선의 가중치는 절댓값이 1,000,000을 넘지 않는 정수이다.
 * 3. MST의 가중치 합은 int 범위로 표현될 수 있다.
 */
public class Main {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int NUM_OF_VERTICES;
    static int NUM_OF_EDGES;
    static List<Edge>[] graph;

    // 크루스칼 알고리즘 적용
    static int solution() {
        // MST의 간선 가중치 합을 갱신
        int weightSum = 0;
        // MST에 포함된 간선의 수를 기록 (노드가 N개면, MST의 간선 개수는 항상 N-1이므로)
        int numOfIncludedEdges = 0;

        // 방문 여부를 기록 (1번 노드부터 순회)
        boolean[] isVisited = new boolean[NUM_OF_VERTICES + 1];
        isVisited[1] = true;
        // 1번 노드와 연결된 간선을 우선 순위 큐에 넣어 순회 시작
        PriorityQueue<Edge> edges = new PriorityQueue<>();
        for (Edge edge : graph[1]) {
            edges.add(edge);
        }

        // 처리된 간선의 숫자를 모두 채울 때까지, 확장되어 있는 경계 노드 바깥으로 미방문 노드를 연결하는 간선을 하나씩 MST에 추가
        while (!edges.isEmpty() && numOfIncludedEdges < NUM_OF_VERTICES - 1) {
            // 방문 여부와 무관하게, 이때 우선 순위 큐에서 가져온 간선은 이전에 확인한 적 없는 간선 중
            // 가중치가 가장 작은 간선임은 보장된다.
            Edge edge = edges.remove();
            // 아직 MST에 포함되지 않은 정점이 발견된 경우
            if (isVisited[edge.to]) {
                continue;
            }

            // 방문 처리 해주고
            isVisited[edge.to] = true;
            numOfIncludedEdges++;
            weightSum += edge.weight;

            // 아직 방문하지 않은 노드와 연결되어 있다면, 다음 확장 후보로 넣어줌
            for (Edge candidateEdge : graph[edge.to]) {
                if (!isVisited[candidateEdge.to]) {
                    edges.add(candidateEdge);
                }
            }
        }

        return weightSum;
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(in.readLine());
        // 정점의 개수
        NUM_OF_VERTICES = Integer.parseInt(st.nextToken());
        // 간선의 개수
        NUM_OF_EDGES = Integer.parseInt(st.nextToken());

        // 간선 정보 저장
        graph = new List[NUM_OF_VERTICES + 1];
        for (int node = 1; node <= NUM_OF_VERTICES; node++) {
            graph[node] = new ArrayList<Edge>();
        }

        for (int edgeNum = 0; edgeNum < NUM_OF_EDGES; edgeNum++) {
            st = new StringTokenizer(in.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[from].add(new Edge(to, weight));
            graph[to].add(new Edge(from, weight));
        }

        System.out.println(solution());
    }

    static class Edge implements Comparable<Edge> {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        // 가중치가 작은 간선부터 greedy하게 MST에 포함할 것이므로, Min Heap을 사용한다.
        @Override
        public int compareTo(Edge foe) {
            return this.weight - foe.weight;
        }
    }
}
