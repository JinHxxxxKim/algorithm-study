import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder out = new StringBuilder();
    static StringTokenizer st;

    static int numOfNodes;

    static int[][] getTree(List<Integer>[] edges) {
        // 트리 만들기 (1이 항상 루트)
        int[] parentOf = new int[numOfNodes + 1];
        int[] depthOf = new int[numOfNodes + 1];
        parentOf[1] = 0;
        depthOf[1] = 1;

        // BFS로 연결 관계를 확인해가며 자식 노드를 추가해줌
        boolean[] isVisited = new boolean[numOfNodes + 1];
        Queue<Integer> visitQueue = new LinkedList<>();
        visitQueue.add(1);
        while (!visitQueue.isEmpty()) {
            int nextId = visitQueue.remove();
            if (isVisited[nextId]) {
                continue;
            }

            // 처음 방문하는 노드인 경우,
            isVisited[nextId] = true;
            // 이웃 노드가 곧 자식 노드이므로
            for (int neighborId : edges[nextId]) {
                // 아직 트리 노드로 만들어지지 않은 번호라면
                if (!isVisited[neighborId] && parentOf[neighborId] == 0) {
                    // 트리에 포함해주고 다음 순회 대상으로 넣어준다.
                    parentOf[neighborId] = nextId;
                    depthOf[neighborId] = depthOf[nextId] + 1;
                    visitQueue.add(neighborId);
                }
            }
        }

        int[][] result = new int[2][];
        result[0] = parentOf;
        result[1] = depthOf;
        return result;
    }

    static int getLcaNode(int id1, int id2, int[][] tree) {
        int[] parentOf = tree[0];
        int[] depthOf = tree[1];

        // 두 노드의 depth가 같아질 때까지 부모 방향으로 반복
        while (depthOf[id1] != depthOf[id2]) {
            if (depthOf[id1] < depthOf[id2]) {
                id2 = parentOf[id2];
            } else {
                id1 = parentOf[id1];
            }
        }

        // 두 노드의 공통 조상이 나올 때까지 부모 쪽으로 반복 순회
        while (parentOf[id1] != parentOf[id2]) {
            id1 = parentOf[id1];
            id2 = parentOf[id2];
        }

        // 두 노드 중 한 노드가 다른 노드의 자손인 경우 깊이가 더 얕은 노드가 LCA
        // 이외의 경우 공통 조상을 반환
        return id1 == id2 ? id1 : parentOf[id1];
    }

    public static void main(String[] args) throws Exception {
        // 정점 개수
        numOfNodes = Integer.parseInt(in.readLine());
        // 간선 연결 정보 저장
        List<Integer>[] edges = new List[numOfNodes + 1];
        for (int node = 1; node <= numOfNodes; node++) {
            edges[node] = new ArrayList<>();
        }
        for (int cnt = 0; cnt < numOfNodes - 1; cnt++) {
            st = new StringTokenizer(in.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            edges[from].add(to);
            edges[to].add(from);
        }

        // 트리 만들기
        int[][] tree = getTree(edges);

        // LCA를 구할 횟수
        int numOfOperations = Integer.parseInt(in.readLine());
        // LCA를 알아낼 노드 번호 쌍
        for (int cnt = 0; cnt < numOfOperations; cnt++) {
            st = new StringTokenizer(in.readLine());
            int id1 = Integer.parseInt(st.nextToken());
            int id2 = Integer.parseInt(st.nextToken());
            int lcaId = getLcaNode(id1, id2, tree);
            out.append(lcaId).append('\n');
        }

        System.out.println(out);
    }
}