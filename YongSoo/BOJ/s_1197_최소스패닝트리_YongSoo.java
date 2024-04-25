import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * 프림 알고리즘 연습!
 */

public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static int V, E;
    static ArrayList<Edge>[] edges;
    static boolean[] isConnected;
    static PriorityQueue<Edge> heap;
    static int answer;

    static class Edge {
        int dest;
        int weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }



    public static void main(String[] args) throws IOException {
        // 1. 변수 초기화
        setInitVariable();

        // 2. 초기 힙 세팅
        isConnected[1] = true;
        for (Edge edge: edges[1]) {
            heap.offer(edge);
        }

        // (V-1)개의 간선을 추가해야 함
        for (int cnt=0; cnt<V-1; cnt++) {

            while (true) {
                Edge edge = heap.poll();
                int dest = edge.dest;

                // 현재 간선을 추가해야 하는 경우
                if (!isConnected[dest]) {
                    isConnected[dest] = true;
                    answer += edge.weight;

                    // 해당 정점(dest)로부터 아직 연결되지 않은 정점으로 갈 수 있는 경우,
                    // 그 간선 정보를 heap에 추가한다.
                    for (Edge newEdge: edges[dest]) {
                        if (!isConnected[newEdge.dest]) {
                            heap.offer(newEdge);
                        }
                    }
                    break;
                }
            }
        }

        // 정답 출력
        System.out.println(answer);

    }



    private static void setInitVariable() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edges = new ArrayList[V+1];
        for (int id=1; id<=V; id++) {
            edges[id] = new ArrayList<>();
        }

        for (int cnt=0; cnt<E; cnt++) {
            st = new StringTokenizer(br.readLine().trim());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            edges[A].add(new Edge(B, C));
            edges[B].add(new Edge(A, C));
        }

        heap = new PriorityQueue<>((e1, e2) -> {
            return Integer.compare(e1.weight, e2.weight);
        });

        // 프림 알고리즘이 특정 정점에서 시작하여 점차 넓혀가는 형식이므로,
        // 특정 정점(여기서는 1번)과 연결되어 있는지 여부만 저장하면 된다.
        isConnected = new boolean[V+1];
        answer = 0;
    }
}
