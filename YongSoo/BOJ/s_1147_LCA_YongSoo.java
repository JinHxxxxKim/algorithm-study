package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * LCA를 찾는 과정에서, 트리의 높이를 하나씩 증가시키면서 확인하는 것은 시간이 다소 오래 걸림
 * parents라는 2차원 배열을 두어서, parents[node][0], parents[node][1], ...이 각각
 * node의 2^0번째 조상, node의 2^1번째 조상, ...을 가리키게 한다
 * 1. 변수 초기화
 * 2. root node부터 dfs 탐색을 하며, parents와 depths를 업데이트
 * 3. LCA 구하기
 *    3-1. node1과 node2가 있을 때, 더 아래에 있는 노드(depths 값이 큰 노드)를 높이가 같게 맞추어 줌
 *    3-2. 높이가 같아졌을 때, node1 == node2라면 node1이 공통조상(종료)
 *    3-3. node1과 node2의 parents 값을 비교해가며 LCA를 찾는다
 * 4. 정답 출력
 */

public class s_1147_LCA_YongSoo {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int N, M;
    static ArrayList<Integer>[] adjLists;

    static int[][] parents;
    static int[] depths;

    public static void main(String[] args) throws IOException {
        // 1. 변수 초기화
        setInitVariable();

        // 2. ROOT_NODE(1)부터 시작해, depths 배열과 parents 배열의 값을 채운다
        final int ROOT_NODE = 1;
        depths[ROOT_NODE] = 0;

        for (int child: adjLists[ROOT_NODE]) {
            updateParents(child, ROOT_NODE, 1);
        }

        // 3. 가장 가까운 공통 조상을 StringBuilder에 저장(M번 반복)
        for (int cnt=0; cnt<M; cnt++) {
            st = new StringTokenizer(br.readLine().trim());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            int lca = getLCA(node1, node2);

            sb.append(lca).append("\n");
        }

        // 4. 정답 출력
        System.out.println(sb);
    }

    // parents 배열과 depths 배열의 값을 채우는 재귀함수
    private static void updateParents(int child, int parent, int depth) {
        // child의 부모 노드는 parent이다.
        parents[child][0] = parent;

        // child의 depth는 depth이다.
        depths[child] = depth;

        int idx = 1;

        while (true) {
            // child의 2^(idx)번째 조상을 구하려면, 먼저 child의 2^(idx-1)번째 조상을 구한 뒤,
            // 거기서 다시 2^(idx-1)번째 조상을 구하면 된다

            // 즉, parents[child][idx] = parents[parents[child][idx-1]][idx-1]이다.
            int prevParent = parents[child][idx-1];

            if (parents[prevParent][idx-1] == 0) break;
            parents[child][idx] = parents[prevParent][idx-1];
            idx++;
        }

        // child의 자식 노드에 대하여 다시 재귀탐색
        ArrayList<Integer> adjList = adjLists[child];

        for (int nextChild: adjList) {
            if (nextChild != parent) {
                updateParents(nextChild, child, depth+1);
            }
        }
    }

    private static int getLCA(int node1, int node2) {

        // node1의 depth가 더 작게 맞춰 줌
        if (depths[node1] > depths[node2]) {
            int temp = node1;
            node1 = node2;
            node2 = temp;
        }

        // node2의 depth와 node1의 depth가 같아질 때까지, node2를 올린다
        int depthDiff = depths[node2] - depths[node1];

        for (int idx=0; idx<parents[0].length; idx++) {
            if (((1 << idx) & (depthDiff)) > 0) {
                node2 = parents[node2][idx];
            }
        }

        // 높이가 같아졌을 때, node1 == node2라면 node1이 LCA이다.
        if (node1 == node2) return node1;

        // idx를 큰 범위에서 시작해서 감소시키며 찾아주어야 함에 유의!
        for (int idx=parents[0].length-1; idx>=0; idx--) {
            if (parents[node1][idx] != parents[node2][idx]) {
                node1 = parents[node1][idx];
                node2 = parents[node2][idx];
            }
        }

        return parents[node1][0];
    }


    private static void setInitVariable() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine().trim());

        adjLists = new ArrayList[N+1];
        for (int id=1; id<=N; id++) {
            adjLists[id] = new ArrayList<>();
        }

        for (int cnt=0; cnt<N-1; cnt++) {
            st = new StringTokenizer(br.readLine().trim());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            adjLists[node1].add(node2);
            adjLists[node2].add(node1);
        }

        M = Integer.parseInt(br.readLine().trim());

        depths = new int[N+1];

        // 트리의 최대 길이는 N-1이므로, parents 배열이 이를 모두 커버할 수 있도록 작성해야 함
        int parentSize = (int) (Math.log(N) / Math.log(2));
        parents = new int[N+1][parentSize+1];
    }
}
