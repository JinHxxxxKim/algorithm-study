import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 루트 없는 트리가 주어질 때, 트리의 루트를 1이라고 정한 경우 각 노드의 부모를 구한다.
 */
public class Main {
    static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder out = new StringBuilder();
    static StringTokenizer st;

    // 노드 개수
    static int numOfNodes;

    // 그래프
    static List<Integer>[] linkedNodes;

    static int[] solution() {
        int[] parentOf = new int[numOfNodes + 1];
        boolean[] isVisited = new boolean[numOfNodes + 1];
        Queue<Integer> visitQueue = new LinkedList<>();

        visitQueue.add(1);
        while (!visitQueue.isEmpty()) {
            int node = visitQueue.remove();
            if (isVisited[node]) {
                continue;
            }

            isVisited[node] = true;
            for (int nextNode : linkedNodes[node]) {
                if (!isVisited[nextNode]) {
                    parentOf[nextNode] = node;
                    visitQueue.add(nextNode);
                }
            }
        }

        return parentOf;
    }

    public static void main(String[] args) throws Exception {
        numOfNodes = Integer.parseInt(in.readLine());
        linkedNodes = new List[numOfNodes + 1];
        for (int node = 1; node <= numOfNodes; node++) {
            linkedNodes[node] = new ArrayList<>();
        }

        for (int v = 0; v < numOfNodes - 1; v++) {
            st = new StringTokenizer(in.readLine());
            int fromNode = Integer.parseInt(st.nextToken());
            int toNode = Integer.parseInt(st.nextToken());
            linkedNodes[fromNode].add(toNode);
            linkedNodes[toNode].add(fromNode);
        }

        int[] result = solution();
        for (int node = 2; node <= numOfNodes; node++) {
            out.append(result[node]).append('\n');
        }
        System.out.println(out);
    }
}
